package com.dycon

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(DynamicContentService)
@Mock([DynamicContent, DynamicContentPage])
class DynamicContentServiceTests {

    void setUp(){
        def home = new DynamicContentPage(name:"home",path: "").save()
        new DynamicContent(name:"content-1",value:"content-1-non-live-value",live:false,page: home).save()
        new DynamicContent(name:"content-2",value:"content-2-non-live-value",live:false,page: home).save()
        new DynamicContent(name:"content-1",value:"content-1-live-value",live:true,page: home).save()
        new DynamicContent(name:"content-2",value:"content-2-live-value",live:true,page: home).save()
    }

   void testGetContentForNonExistentPageReturnsEmptyMap(){
      def content = service.getPageContent("nopage",false)
      assertEquals(0, content.size())
   }

   void testGetNonLiveContentGetsCorrectResults(){
       def content = service.getPageContent("home",false)
       assertEquals("content-1-non-live-value", content['content-1'])
       assertEquals("content-2-non-live-value", content['content-2'])
   }

    void testGetLiveContentGetsCorrectResults(){
        def content = service.getPageContent("home",true)
        assertEquals("content-1-live-value", content['content-1'])
        assertEquals("content-2-live-value", content['content-2'])
    }
}
