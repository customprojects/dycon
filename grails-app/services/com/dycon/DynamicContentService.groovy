package com.dycon

class DynamicContentService {

    def getPageContent(String pageName, Boolean live) {

        def contentMap = [:]

        def pages = DynamicContentPage.findAllByName(pageName)
        def page
        if(pages?.size() > 0){
            page = pages[0]
            def content = DynamicContent.findAllByPageAndLive(page,live)
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


    def publishImages(Integer id){

        def page = DynamicContentPage.findById(id)

        println "Publishing images for ${page.name}"

        if(page){

            def images = DynamicContentImage.findAllByLiveAndPage(false,page)

            println "Found ${images.size()} images to publish"

            images.each {it ->

                def liveImage = DynamicContentImage.findByLiveAndPageAndName(true,page,it.name)

                if(liveImage){
                    println "Updating existing live image"
                    liveImage.imageFile = it.imageFile
                }else{
                    println "Creating new live image"
                    liveImage = new DynamicContentImage(live:true,page: page, name: it.name,imageFile:it.imageFile)
                }

                liveImage.save(flush: true)

            }

            //clear live entries from page which are no longer required
            def allLiveImages = DynamicContentImage.findAllByLiveAndPage(true,page)
            allLiveImages.each {it ->

                def nonLiveImage = DynamicContentImage.findByLiveAndPageAndName(false,page,it.name)

                if(!nonLiveImage){
                    it.delete()
                }

            }

        }
    }

    def getPageImages(String pageName, boolean live) {

        def imageMap = [:]

        def pages = DynamicContentPage.findAllByName(pageName)
        def page
        if(pages?.size() > 0){
            page = pages[0]
            def images = DynamicContentImage.findAllByPageAndLive(page,live)
            images.each{ it -> imageMap.putAt(it.name, it.imageFile) }
        }

        return imageMap


    }
}
