grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
        //mavenRepo "http://nexus.skynewsonline.bskyb.com/content/groups/public"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.21'
    }

    plugins {
        compile ":hibernate:$grailsVersion"


        runtime ":jquery:1.7.2"
        runtime ":resources:1.1.6"

        runtime ":ajax-uploader:1.1"

        build(":tomcat:$grailsVersion") {
            export = false
        }


        build ':release:2.2.1', ':rest-client-builder:1.0.3', {
            export = false
        }
    }
}

grails.plugin.repos.distribution.myRepository = "http://nexus.skynewsonline.bskyb.com/content/groups/public/"

/*grails.project.dependency.distribution = {
    String serverRoot = 'http://nexus.skynewsonline.bskyb.com'
    remoteRepository(id: 'internalPluginSnapshots',
            url: serverRoot + '/content/groups/public/') {
        authentication username: 'admin', password: 'admin123'
    }
}
remoteRepository(id: 'internalPluginReleases',
        url: serverRoot + '/content/groups/public/') {
    authentication username: 'admin', password: 'admin123'
}*/

