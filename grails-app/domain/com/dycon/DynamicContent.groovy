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
        if(!order)
            order = findAll().size() + 1
    }
}
