import com.dycon.DynamicContent
import com.dycon.DynamicContentImage
import com.dycon.DynamicContentPage

class DyconBootStrap {


    def init = { servletContext ->



        def pages = DynamicContentPage.findAll()


        pages.each {
            def content = DynamicContent.findAllByPageAndLiveAndOrderIsNull(it,false)

            Integer order = 0;
            def orderedContent  = DynamicContent.findAllByLiveAndOrderNotIsNull(false,[sort: "order",order:"desc"])
            if(orderedContent.size() < 0){
                order = orderedContent[0].order
            }

            content.each {
                order++;
                it.order = order;
                it.save()
            }

            def images = DynamicContentImage.findAllByPageAndLiveAndOrderIsNull(it,false)
            order = 0;
            def orderedImages  = DynamicContentImage.findAllByLiveAndOrderNotIsNull(false,[sort: "order",order:"desc"])
            if(orderedImages.size() < 0){
                order = orderedImages[0].order
            }

            images.each {
                order++;
                it.order = order;
                it.save()
            }
        }
    }

}