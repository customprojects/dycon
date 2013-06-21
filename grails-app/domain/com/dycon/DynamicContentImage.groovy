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
        if(!order)
            order = findAll().size() + 1
    }
}
