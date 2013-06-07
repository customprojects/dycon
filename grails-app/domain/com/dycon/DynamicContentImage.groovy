package com.dycon

class DynamicContentImage {

    String name
    String imageFile
    Boolean live

    static constraints = {
        name(blank: false)
    }

    static belongsTo = [ page : DynamicContentPage ]
}
