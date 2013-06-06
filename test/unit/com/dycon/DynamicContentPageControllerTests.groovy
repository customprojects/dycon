package com.dycon



import org.junit.*
import grails.test.mixin.*

@TestFor(DynamicContentPageController)
@Mock(DynamicContentPage)
class DynamicContentPageControllerTests {

    void testSomething(){}


    /*   def populateValidParams(params) {
           assert params != null
           // TODO: Populate valid properties like...
           //params["name"] = 'someValidName'
       }

       void testIndex() {
           controller.index()
           assert "/dynamicContentPage/list" == response.redirectedUrl
       }

       void testList() {

           def model = controller.list()

           assert model.dynamicContentPageInstanceList.size() == 0
           assert model.dynamicContentPageInstanceTotal == 0
       }

       void testCreate() {
           def model = controller.create()

           assert model.dynamicContentPageInstance != null
       }

       void testSave() {
           controller.save()

           assert model.dynamicContentPageInstance != null
           assert view == '/dynamicContentPage/create'

           response.reset()

           populateValidParams(params)
           controller.save()

           assert response.redirectedUrl == '/dynamicContentPage/show/1'
           assert controller.flash.message != null
           assert DynamicContentPage.count() == 1
       }

       void testShow() {
           controller.show()

           assert flash.message != null
           assert response.redirectedUrl == '/dynamicContentPage/list'

           populateValidParams(params)
           def dynamicContentPage = new DynamicContentPage(params)

           assert dynamicContentPage.save() != null

           params.id = dynamicContentPage.id

           def model = controller.show()

           assert model.dynamicContentPageInstance == dynamicContentPage
       }

       void testEdit() {
           controller.edit()

           assert flash.message != null
           assert response.redirectedUrl == '/dynamicContentPage/list'

           populateValidParams(params)
           def dynamicContentPage = new DynamicContentPage(params)

           assert dynamicContentPage.save() != null

           params.id = dynamicContentPage.id

           def model = controller.edit()

           assert model.dynamicContentPageInstance == dynamicContentPage
       }

       void testUpdate() {
           controller.update()

           assert flash.message != null
           assert response.redirectedUrl == '/dynamicContentPage/list'

           response.reset()

           populateValidParams(params)
           def dynamicContentPage = new DynamicContentPage(params)

           assert dynamicContentPage.save() != null

           // test invalid parameters in update
           params.id = dynamicContentPage.id
           //TODO: add invalid values to params object

           controller.update()

           assert view == "/dynamicContentPage/edit"
           assert model.dynamicContentPageInstance != null

           dynamicContentPage.clearErrors()

           populateValidParams(params)
           controller.update()

           assert response.redirectedUrl == "/dynamicContentPage/show/$dynamicContentPage.id"
           assert flash.message != null

           //test outdated version number
           response.reset()
           dynamicContentPage.clearErrors()

           populateValidParams(params)
           params.id = dynamicContentPage.id
           params.version = -1
           controller.update()

           assert view == "/dynamicContentPage/edit"
           assert model.dynamicContentPageInstance != null
           assert model.dynamicContentPageInstance.errors.getFieldError('version')
           assert flash.message != null
       }

       void testDelete() {
           controller.delete()
           assert flash.message != null
           assert response.redirectedUrl == '/dynamicContentPage/list'

           response.reset()

           populateValidParams(params)
           def dynamicContentPage = new DynamicContentPage(params)

           assert dynamicContentPage.save() != null
           assert DynamicContentPage.count() == 1

           params.id = dynamicContentPage.id

           controller.delete()

           assert DynamicContentPage.count() == 0
           assert DynamicContentPage.get(dynamicContentPage.id) == null
           assert response.redirectedUrl == '/dynamicContentPage/list'
       }
   */}
