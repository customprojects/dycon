package com.dycon

class DynamicContent {

    String name
    String value
    Boolean live

    static constraints = {
        name(blank: false)
    }

    static belongsTo = [ page : DynamicContentPage ]

}
