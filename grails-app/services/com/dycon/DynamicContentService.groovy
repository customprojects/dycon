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
}
