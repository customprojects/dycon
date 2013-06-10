package com.dycon

import javax.servlet.http.HttpServletRequest

class DyconTagLib {

    def dynamicContentService

    static namespace = "dycon"


    def page = { attrs ->

        if(attrs.name){
            def pageContent = dynamicContentService.getPageContent("Home",showLiveContent(request))
            request.setAttribute("dycon-pageContent",pageContent)
            def pageImages = dynamicContentService.getPageImages("Home",showLiveContent(request))
            request.setAttribute("dycon-pageImages",pageImages)

        }else{
            request.setAttribute("dycon-pageContent",[:])
            request.setAttribute("dycon-pageImages",[:])

        }

    }

    def showLiveContent(HttpServletRequest request){


        if(!grailsApplication.config.dycon?.containsKey("previewDomain")){
            return true
        }

        return !(request.getRequestURI().contains(grailsApplication.config.dycon.previewDomain))
    }


    def content = {  attrs ->

        if(!request.getAttribute("dycon-pageContent")){
            out << "content tag - no page defined or no content defined for page"
            return
        }

        def pageContent = request.getAttribute("dycon-pageContent")
        def value = "content tag - no name defined"
        if(attrs.name){
            value = pageContent[attrs.name]?pageContent[attrs.name].value:attrs.default?:"value not found"
        }

        out << value

    }

    def image = {  attrs ->


        println "in image tag"

        if(!request.getAttribute("dycon-pageImages")){
            out << "image tag - no page defined or no images defined for page"
            return
        }

        def pageImages = request.getAttribute("dycon-pageImages")
        def value = "image tag - no name defined"
        if(attrs.name){
            value = pageImages[attrs.name]? grailsApplication.config.dycon.imageWebPath + "/" + pageImages[attrs.name]:attrs.default?:"value not found"
        }

        out << value

    }



}
