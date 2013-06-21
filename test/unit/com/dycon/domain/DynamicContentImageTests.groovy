package com.dycon.domain

import com.dycon.DynamicContent
import com.dycon.DynamicContentImage
import com.dycon.DynamicContentPage
import grails.test.mixin.*
import grails.test.mixin.support.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(DynamicContentImage)
@Mock([DynamicContentPage])
class DynamicContentImageTests {
    DynamicContentImage domain

    void setUp() {
        domain = new DynamicContentImage(name:'test',imageFile: 'test.png', live:false, page: new DynamicContentPage())
    }

    void testValidDynamicContentImage() {
        assert domain.validate()
        assert domain.order != null
    }

    void testBlankName() {
        domain.name = ''
        assert !domain.validate()
        assert domain.errors['name'].code == 'blank'
    }

    void testReturnedOrder() {
        def page = new DynamicContentPage(name: 'test', path: '')
        page.save(flush:true)

        new DynamicContentImage(name: 'test2',imageFile: 'test2.png', live: false, page: page).save(flush:true)
        new DynamicContentImage(name: 'test1',imageFile: 'test1.png', live: false, page: page).save(flush:true)

        def allForPage = DynamicContentImage.findAll()
        assert allForPage.size() == 2
        assert allForPage[0].name == 'test2'
        assert allForPage[1].name == 'test1'
    }
}
