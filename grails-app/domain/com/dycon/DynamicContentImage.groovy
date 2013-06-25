package com.dycon

class DynamicContentImage {

    String name
    String imageFile
    Integer order
    Boolean live

    static constraints = {
        name(blank: false)
    }

    static mapping = {
        sort order: 'asc'
    }

    static belongsTo = [ page : DynamicContentPage ]


    def beforeValidate() {

        if(!order){
            def content = DynamicContentImage.findAllByPageAndLive(page,live, [sort: "order",order:"desc"])
            if(content?.size() > 0){
                order = content[0].order + 1
            }else{
                order = findAll().size() + 1
            }
        }
    }
}
