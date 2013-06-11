package com.dycon

import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(DyconTagLib)
class DyconTagLibTests {


    void testEmptyPageNameReturnsEmptyMap() {

        applyTemplate('<dycon:page/>')
        Map pageContent = request.getAttribute("dycon-pageContent")
        assertEquals(0, pageContent.size())

    }

    void testValidPageReturnsPopulatedMap(){

        setUpContent()

        applyTemplate('<dycon:page name="Home"/>')

        Map pageContent = request.getAttribute("dycon-pageContent")

        assertEquals("content-1 value",pageContent['content-1'])
        assertEquals("content-2 value",pageContent['content-2'])

        Map pageImages = request.getAttribute("dycon-pageImages")

        assertEquals("image-1 value",pageImages['image-1'])
        assertEquals("image-2 value",pageImages['image-2'])

    }

    private void setUpContent() {

        def dyconServiceMock = mockFor(DynamicContentService)

        dyconServiceMock.demand.getPageContent() { pageName, live ->

            def pageContent = [:]
            pageContent["content-1"] = "content-1 value"
            pageContent["content-2"] = "content-2 value"

            return pageContent
        }

        dyconServiceMock.demand.getPageImages() { pageName, live ->

            def imageContent = [:]
            imageContent["image-1"] = "image-1 value"
            imageContent["image-2"] = "image-2 value"

            return imageContent
        }

        tagLib.dynamicContentService = dyconServiceMock.createMock()
    }

    void testContentMethodWithoutCallingPageTag(){

         assertOutputEquals("content tag - no page defined or no content defined for page",'<dycon:content name="content-1" />')
    }

    void testContentMethodRendersCorrectValue(){

        setUpContent()
        applyTemplate('<dycon:page name="Home"/>')
        assertOutputEquals("content-1 value",'<dycon:content name="content-1" />')

    }

    void testImageMethodRendersCorrectValue(){

        setUpContent()
        applyTemplate('<dycon:page name="Home"/>')
        assertOutputEquals("${grailsApplication.config.dycon.imageWebPath}/image-2 value".toString(),'<dycon:image name="image-2"/>')

    }

}
