modules = {
    application {
        resource url:'js/application.js'
    }

    dycon_core{
        dependsOn 'jquery'
        resource url:'/js/dycon.js', disposition: 'head'
    }
}