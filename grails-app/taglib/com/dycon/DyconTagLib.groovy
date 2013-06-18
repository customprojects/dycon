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

    private boolean showLiveContent(HttpServletRequest request){

        if(!grailsApplication.config.dycon?.containsKey("previewDomain")){
            return true
        }

        return !(request.getRequestURL().contains(grailsApplication.config.dycon.previewDomain))
    }

    def content = {  attrs,body ->

        if(!request.getAttribute("dycon-pageContent")){

            if(attrs.useBodyAsDefault){
                out << body()
            } else {
                def value =  attrs.default?:"no value or default provided"
                out << value
            }
            return
        }

        def pageContent = request.getAttribute("dycon-pageContent")
        def value = "content tag - no name defined"

        if(attrs.name){
            if(pageContent[attrs.name]){
                value=pageContent[attrs.name].value
            }else{
                if(attrs.useBodyAsDefault){
                    value = body()
                }else{
                    value = attrs.default?:"value not found"
                }
            }

        }

        out << value
    }

    def image = {  attrs ->

        if(!request.getAttribute("dycon-pageImages")){
            def value =  attrs.default?:"no value or default provided"
            out << value
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
