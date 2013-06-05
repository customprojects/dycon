package com.dycon

class DynamicContent {

    String name
    String value
    Boolean live

    static constraints = {
        name(blank: false)
        value(blank:false, size: 1..4000)
    }

    static belongsTo = [ page : DynamicContentPage ]

}
