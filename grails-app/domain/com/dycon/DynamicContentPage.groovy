package com.dycon

class DynamicContentPage {

    String name
    String path

    static constraints = {
        name(blank: false)
    }
}
