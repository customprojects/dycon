class DyConUrlMappings {

    static mappings = {

        "/dycon/dynamicContentPage/$action?/$id?" (controller: 'dynamicContentPage')
        "/dycon/dynamicContent/$action?/$id?" (controller: 'dynamicContent')
        "/dycon/dynamicContentImage/$action?/$id?" (controller: 'dynamicContentImage')
        "/dycon" (controller: 'dycon')

        //Don't allow direct access to controllers...all requests must come through /dycon
        "/dynamicContent" (controller: 'dyconRedirectToHome')
        "/dynamicContentImage" (controller: 'dyconRedirectToHome')
        "/dynamicContent/*" (controller: 'dyconRedirectToHome')
        "/dynamicContentImage/*" (controller: 'dyconRedirectToHome')
    }
}
