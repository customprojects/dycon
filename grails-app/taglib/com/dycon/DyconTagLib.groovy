package com.dycon

class DyconTagLib {

    def dynamicContentService

    static namespace = "dycon"


    def page = { attrs ->

        if(attrs.name){
            def pageContent = dynamicContentService.getPageContent("Home",true)
            request.setAttribute("dycon-pageContent",pageContent)
        }else{
            request.setAttribute("dycon-pageContent",[:])
        }

    }

    def content = {  attrs ->

        if(!request.getAttribute("dycon-pageContent")){
            out << "content tag - no page defined"
            return
        }

        def pageContent = request.getAttribute("dycon-pageContent")
        def value = "content tag - no name defined"
        if(attrs.name){
            value = pageContent[attrs.name]?:attrs.default?:"value not found"
        }

        out << value

    }





}
