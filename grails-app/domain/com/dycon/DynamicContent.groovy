package com.dycon

class DynamicContent {

    String name
    String value
    Integer order
    Boolean live

    static constraints = {
        name(blank: false)
        value(blank:false, size: 1..4000)
    }

    static mapping = {
        sort order: 'asc'
    }

    static belongsTo = [ page : DynamicContentPage ]

    def beforeValidate() {
        if(!order){
            def content = DynamicContent.findAllByPageAndLive(page,live, [sort: "order",order:"desc"])
            if(content?.size() > 0){
                order = content[0].order + 1
            }else{
                order = findAll().size() + 1
            }
        }
    }
}
