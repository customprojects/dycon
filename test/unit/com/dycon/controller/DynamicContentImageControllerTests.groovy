package com.dycon.controller

import com.dycon.DynamicContentImage
import com.dycon.DynamicContentController
import com.dycon.DynamicContentImage
import com.dycon.DynamicContentImageController
import com.dycon.DynamicContentPage

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

@TestFor(DynamicContentImageController)
@Mock([DynamicContentImage, DynamicContentPage])
class DynamicContentImageControllerTests {
    DynamicContentPage contentPage

    void setUp() {

        contentPage = new DynamicContentPage(name: "testPage", path: "").save()


        def content1 = new DynamicContentImage(page: contentPage,live: false, name: "Image1",imageFile: "Value1",order: 1).save()
        def content2 = new DynamicContentImage(page: contentPage,live: false, name: "Image2",imageFile: "Value2",order: 2).save()
        def content3 = new DynamicContentImage(page: contentPage,live: false, name: "Image3",imageFile: "Value3",order: 3).save()
        def content4 = new DynamicContentImage(page: contentPage,live: false, name: "Image4",imageFile: "Value4",order: 4).save()

    }

    void tearDown() {

        def content = DynamicContentImage.findAllByPage(contentPage)
        content.each {
            it.delete()
        }

        contentPage.delete()
    }

    void testMoveFirstItemUpMakesNoDifference() {

        def contentToMove = DynamicContentImage.findByName("Image1")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,-1)

        assertEquals(1,DynamicContentImage.findByName("Image1").order)
        assertEquals(2,DynamicContentImage.findByName("Image2").order)
        assertEquals(3,DynamicContentImage.findByName("Image3").order)
        assertEquals(4,DynamicContentImage.findByName("Image4").order)

    }

    void testMoveFirstItemDownHasExpectedResult() {

        def contentToMove = DynamicContentImage.findByName("Image1")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,1)

        assertEquals(2,DynamicContentImage.findByName("Image1").order)
        assertEquals(1,DynamicContentImage.findByName("Image2").order)
        assertEquals(3,DynamicContentImage.findByName("Image3").order)
        assertEquals(4,DynamicContentImage.findByName("Image4").order)

    }


    void testMoveSecondItemUpHasExpectedResult() {

        def contentToMove = DynamicContentImage.findByName("Image2")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,-1)

        assertEquals(2,DynamicContentImage.findByName("Image1").order)
        assertEquals(1,DynamicContentImage.findByName("Image2").order)
        assertEquals(3,DynamicContentImage.findByName("Image3").order)
        assertEquals(4,DynamicContentImage.findByName("Image4").order)

    }

    void testMoveSecondItemDownHasExpectedResult() {

        def contentToMove = DynamicContentImage.findByName("Image2")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,1)

        assertEquals(1,DynamicContentImage.findByName("Image1").order)
        assertEquals(3,DynamicContentImage.findByName("Image2").order)
        assertEquals(2,DynamicContentImage.findByName("Image3").order)
        assertEquals(4,DynamicContentImage.findByName("Image4").order)

    }

    void testMoveLastItemUpHasExpectedResult() {

        def contentToMove = DynamicContentImage.findByName("Image4")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,-1)

        assertEquals(1,DynamicContentImage.findByName("Image1").order)
        assertEquals(2,DynamicContentImage.findByName("Image2").order)
        assertEquals(4,DynamicContentImage.findByName("Image3").order)
        assertEquals(3,DynamicContentImage.findByName("Image4").order)

    }

    void testMoveLastItemDownMakesNoDifference() {

        def contentToMove = DynamicContentImage.findByName("Image4")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,1)

        assertEquals(1,DynamicContentImage.findByName("Image1").order)
        assertEquals(2,DynamicContentImage.findByName("Image2").order)
        assertEquals(3,DynamicContentImage.findByName("Image3").order)
        assertEquals(4,DynamicContentImage.findByName("Image4").order)

    }

    void testDeleteFirstItemMovesOtherItemsUp() {

        def contentToDelete = DynamicContentImage.findByName("Image1")

        controller.delete((Integer)contentToDelete.id)

        assertEquals(1,DynamicContentImage.findByName("Image2").order)
        assertEquals(2,DynamicContentImage.findByName("Image3").order)
        assertEquals(3,DynamicContentImage.findByName("Image4").order)

    }

    void testDeleteThirdItemMovesLastItemUp() {

        def contentToDelete = DynamicContentImage.findByName("Image3")

        controller.delete((Integer)contentToDelete.id)

        assertEquals(1,DynamicContentImage.findByName("Image1").order)
        assertEquals(2,DynamicContentImage.findByName("Image2").order)
        assertEquals(3,DynamicContentImage.findByName("Image4").order)

    }

    void testDeleteLastItemLeavesOthersItems() {

        def contentToDelete = DynamicContentImage.findByName("Image4")

        controller.delete((Integer)contentToDelete.id)

        assertEquals(1,DynamicContentImage.findByName("Image1").order)
        assertEquals(2,DynamicContentImage.findByName("Image2").order)
        assertEquals(3,DynamicContentImage.findByName("Image3").order)

    }


}
