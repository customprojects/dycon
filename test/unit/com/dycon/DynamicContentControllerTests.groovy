package com.dycon



import org.junit.*
import grails.test.mixin.*

@TestFor(DynamicContentController)
@Mock(DynamicContent)
class DynamicContentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dynamicContent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dynamicContentInstanceList.size() == 0
        assert model.dynamicContentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dynamicContentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dynamicContentInstance != null
        assert view == '/dynamicContent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dynamicContent/show/1'
        assert controller.flash.message != null
        assert DynamicContent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContent/list'

        populateValidParams(params)
        def dynamicContent = new DynamicContent(params)

        assert dynamicContent.save() != null

        params.id = dynamicContent.id

        def model = controller.show()

        assert model.dynamicContentInstance == dynamicContent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContent/list'

        populateValidParams(params)
        def dynamicContent = new DynamicContent(params)

        assert dynamicContent.save() != null

        params.id = dynamicContent.id

        def model = controller.edit()

        assert model.dynamicContentInstance == dynamicContent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContent/list'

        response.reset()

        populateValidParams(params)
        def dynamicContent = new DynamicContent(params)

        assert dynamicContent.save() != null

        // test invalid parameters in update
        params.id = dynamicContent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dynamicContent/edit"
        assert model.dynamicContentInstance != null

        dynamicContent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dynamicContent/show/$dynamicContent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dynamicContent.clearErrors()

        populateValidParams(params)
        params.id = dynamicContent.id
        params.version = -1
        controller.update()

        assert view == "/dynamicContent/edit"
        assert model.dynamicContentInstance != null
        assert model.dynamicContentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContent/list'

        response.reset()

        populateValidParams(params)
        def dynamicContent = new DynamicContent(params)

        assert dynamicContent.save() != null
        assert DynamicContent.count() == 1

        params.id = dynamicContent.id

        controller.delete()

        assert DynamicContent.count() == 0
        assert DynamicContent.get(dynamicContent.id) == null
        assert response.redirectedUrl == '/dynamicContent/list'
    }
}
