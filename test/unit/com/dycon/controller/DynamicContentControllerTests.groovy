package com.dycon.controller

import com.dycon.DynamicContent
import com.dycon.DynamicContentController
import com.dycon.DynamicContentPage

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*


@TestFor(DynamicContentController)
@Mock([DynamicContent, DynamicContentPage])
class DynamicContentControllerTests {

    DynamicContentPage contentPage

    void setUp() {

        contentPage = new DynamicContentPage(name: "testPage", path: "").save()


        def content1 = new DynamicContent(page: contentPage,live: false, name: "Content1",value: "Value1",order: 1).save()
        def content2 = new DynamicContent(page: contentPage,live: false, name: "Content2",value: "Value2",order: 2).save()
        def content3 = new DynamicContent(page: contentPage,live: false, name: "Content3",value: "Value3",order: 3).save()
        def content4 = new DynamicContent(page: contentPage,live: false, name: "Content4",value: "Value4",order: 4).save()

    }

    void tearDown() {

        def content = DynamicContent.findAllByPage(contentPage)
        content.each {
            it.delete()
        }

        contentPage.delete()
    }

    void testMoveFirstItemUpMakesNoDifference() {

        def contentToMove = DynamicContent.findByName("Content1")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,-1)

        assertEquals(1,DynamicContent.findByName("Content1").order)
        assertEquals(2,DynamicContent.findByName("Content2").order)
        assertEquals(3,DynamicContent.findByName("Content3").order)
        assertEquals(4,DynamicContent.findByName("Content4").order)

    }

    void testMoveFirstItemDownHasExpectedResult() {

        def contentToMove = DynamicContent.findByName("Content1")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,1)

        assertEquals(2,DynamicContent.findByName("Content1").order)
        assertEquals(1,DynamicContent.findByName("Content2").order)
        assertEquals(3,DynamicContent.findByName("Content3").order)
        assertEquals(4,DynamicContent.findByName("Content4").order)

    }


    void testMoveSecondItemUpHasExpectedResult() {

        def contentToMove = DynamicContent.findByName("Content2")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,-1)

        assertEquals(2,DynamicContent.findByName("Content1").order)
        assertEquals(1,DynamicContent.findByName("Content2").order)
        assertEquals(3,DynamicContent.findByName("Content3").order)
        assertEquals(4,DynamicContent.findByName("Content4").order)

    }

    void testMoveSecondItemDownHasExpectedResult() {

        def contentToMove = DynamicContent.findByName("Content2")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,1)

        assertEquals(1,DynamicContent.findByName("Content1").order)
        assertEquals(3,DynamicContent.findByName("Content2").order)
        assertEquals(2,DynamicContent.findByName("Content3").order)
        assertEquals(4,DynamicContent.findByName("Content4").order)

    }

    void testMoveLastItemUpHasExpectedResult() {

        def contentToMove = DynamicContent.findByName("Content4")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,-1)

        assertEquals(1,DynamicContent.findByName("Content1").order)
        assertEquals(2,DynamicContent.findByName("Content2").order)
        assertEquals(4,DynamicContent.findByName("Content3").order)
        assertEquals(3,DynamicContent.findByName("Content4").order)

    }

    void testMoveLastItemDownMakesNoDifference() {

        def contentToMove = DynamicContent.findByName("Content4")

        controller.move(false,(Integer)contentPage.id,(Integer)contentToMove.id,1)

        assertEquals(1,DynamicContent.findByName("Content1").order)
        assertEquals(2,DynamicContent.findByName("Content2").order)
        assertEquals(3,DynamicContent.findByName("Content3").order)
        assertEquals(4,DynamicContent.findByName("Content4").order)

    }

    void testDeleteFirstItemMovesOtherItemsUp() {

        def contentToDelete = DynamicContent.findByName("Content1")

        controller.delete((Integer)contentToDelete.id)

        assertEquals(1,DynamicContent.findByName("Content2").order)
        assertEquals(2,DynamicContent.findByName("Content3").order)
        assertEquals(3,DynamicContent.findByName("Content4").order)

    }

    void testDeleteThirdItemMovesLastItemUp() {

        def contentToDelete = DynamicContent.findByName("Content3")

        controller.delete((Integer)contentToDelete.id)

        assertEquals(1,DynamicContent.findByName("Content1").order)
        assertEquals(2,DynamicContent.findByName("Content2").order)
        assertEquals(3,DynamicContent.findByName("Content4").order)

    }

    void testDeleteLastItemLeavesOthersItems() {

        def contentToDelete = DynamicContent.findByName("Content4")

        controller.delete((Integer)contentToDelete.id)

        assertEquals(1,DynamicContent.findByName("Content1").order)
        assertEquals(2,DynamicContent.findByName("Content2").order)
        assertEquals(3,DynamicContent.findByName("Content3").order)

    }

}
