package com.dycon.domain

import com.dycon.DynamicContent
import com.dycon.DynamicContentPage

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(DynamicContent)
@Mock([DynamicContentPage])
class DynamicContentTests {

    DynamicContent domain

    void setUp() {
        domain = new DynamicContent(name: 'test', value: 'test', live:false, page: new DynamicContentPage())
    }

    void testValidDynamicContent() {
        assert domain.name == "test"
        assert domain.validate()
        assert domain.order != null
    }

    void testBlankName() {
        domain.name = ''
        assert !domain.validate()
        assert domain.errors['name'].code == 'blank'
    }

    void testBlankValue () {
        domain.value = ''
        assert !domain.validate()
        assert domain.errors['value'].code == 'blank'
    }

    void testLargeValue () {
        domain.value = ''
        for(int i = 0; i < 4001; i++){
            domain.value += 'a'
        }
        assert !domain.validate()
        assert domain.errors['value'].code == 'size.toobig'
    }

    void testReturnOrder() {
        def page = new DynamicContentPage(name: 'test', path: '')
        page.save(flush:true)

        new DynamicContent(name: 'test2', value: 'test2', live: true, page: page).save(flush:true)
        new DynamicContent(name: 'test1', value: 'test1', live: true, page: page).save(flush:true)

        def allForPage = DynamicContent.findAll()
        assert allForPage.size() == 2
        assert allForPage[0].name == 'test2'
        assert allForPage[1].name == 'test1'
    }
}
