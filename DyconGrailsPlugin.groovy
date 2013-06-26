class DyconGrailsPlugin {
    def version = "0.0.9"
    def grailsVersion = "2.0 > *"

    def groupId = "csiPlugins"

    def title = "Dycon Plugin"
    def author = "Tim Coombe"
    def authorEmail = "tim.coombe@bskyb.com"
    def description = 'A plugin that allows very simple Content management for pages without requiring a full CMS.'

    def documentation = "http://grails.org/plugin/dycon"

    def license = "APACHE"
    def issueManagement = [system: 'GITHUB', url: 'https://github.com/customprojects/dycon/issues']
    def scm = [url: 'https://github.com/customprojects/dycon']

    def doWithDynamicMethods = { ctx ->

        Object.metaClass.trimLength = { Integer stringLength ->

            String trimString = delegate?.toString()
            String concatenateString = "..."
            List separators = [".", " "]

            if (stringLength && (trimString?.length() > stringLength)) {
                trimString = trimString.substring(0, stringLength - concatenateString.length())
                String separator = separators.findAll{trimString.contains(it)}?.min{trimString.lastIndexOf(it)}
                if(separator){
                    trimString = trimString.substring(0, trimString.lastIndexOf(separator))
                }
                trimString += concatenateString
            }
            return trimString
        }
    }
}
