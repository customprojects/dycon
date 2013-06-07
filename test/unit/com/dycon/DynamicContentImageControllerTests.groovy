package com.dycon



import org.junit.*
import grails.test.mixin.*

@TestFor(DynamicContentImageController)
@Mock(DynamicContentImage)
class DynamicContentImageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dynamicContentImage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dynamicContentImageInstanceList.size() == 0
        assert model.dynamicContentImageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dynamicContentImageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dynamicContentImageInstance != null
        assert view == '/dynamicContentImage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dynamicContentImage/show/1'
        assert controller.flash.message != null
        assert DynamicContentImage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContentImage/list'

        populateValidParams(params)
        def dynamicContentImage = new DynamicContentImage(params)

        assert dynamicContentImage.save() != null

        params.id = dynamicContentImage.id

        def model = controller.show()

        assert model.dynamicContentImageInstance == dynamicContentImage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContentImage/list'

        populateValidParams(params)
        def dynamicContentImage = new DynamicContentImage(params)

        assert dynamicContentImage.save() != null

        params.id = dynamicContentImage.id

        def model = controller.edit()

        assert model.dynamicContentImageInstance == dynamicContentImage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContentImage/list'

        response.reset()

        populateValidParams(params)
        def dynamicContentImage = new DynamicContentImage(params)

        assert dynamicContentImage.save() != null

        // test invalid parameters in update
        params.id = dynamicContentImage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dynamicContentImage/edit"
        assert model.dynamicContentImageInstance != null

        dynamicContentImage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dynamicContentImage/show/$dynamicContentImage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dynamicContentImage.clearErrors()

        populateValidParams(params)
        params.id = dynamicContentImage.id
        params.version = -1
        controller.update()

        assert view == "/dynamicContentImage/edit"
        assert model.dynamicContentImageInstance != null
        assert model.dynamicContentImageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dynamicContentImage/list'

        response.reset()

        populateValidParams(params)
        def dynamicContentImage = new DynamicContentImage(params)

        assert dynamicContentImage.save() != null
        assert DynamicContentImage.count() == 1

        params.id = dynamicContentImage.id

        controller.delete()

        assert DynamicContentImage.count() == 0
        assert DynamicContentImage.get(dynamicContentImage.id) == null
        assert response.redirectedUrl == '/dynamicContentImage/list'
    }
}
