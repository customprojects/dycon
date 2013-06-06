package com.dycon

class DynamicContentService {

    def getPageContent(String pageName, Boolean live) {

        def contentMap = [:]

        def pages = DynamicContentPage.findAllByName(pageName)
        def page
        if(pages?.size() > 0){
            page = pages[0]
            def content = DynamicContent.findAllByPageAndLive(page,true)
            content.each{ it -> contentMap.putAt(it.name, it.value) }
        }

        return contentMap

    }

    def publish(Integer id){

        def page = DynamicContentPage.findById(id)

        if(page){

            def content = DynamicContent.findAllByLiveAndPage(false,page)


            content.each {it ->

                def liveContent = DynamicContent.findByLiveAndPageAndName(true,page,it.name)

                if(liveContent){
                    liveContent.value = it.value
                }else{
                    liveContent = new DynamicContent(live:true,page: page, name: it.name,value:it.value)
                }

                liveContent.save(flush: true)

            }

            //clear live entries from page which are no longer required
            def allLiveContent = DynamicContent.findAllByLiveAndPage(true,page)
            allLiveContent.each {it ->

                def nonLiveContent = DynamicContent.findByLiveAndPageAndName(false,page,it.name)

                if(!nonLiveContent){
                    it.delete()
                }

            }

        }
    }
}
