package com.dycon

import org.springframework.dao.DataIntegrityViolationException

class DynamicContentPageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static defaultAction = 'list'

    def list() {

         [dynamicContentPageInstanceList: DynamicContentPage.list(params), dynamicContentPageInstanceTotal: DynamicContentPage.count()]
    }

    def create() {
        [dynamicContentPageInstance: new DynamicContentPage(params)]
    }

    def save() {
        def dynamicContentPageInstance = new DynamicContentPage(params)
        if (!dynamicContentPageInstance.save(flush: true)) {
            render(view: "create", model: [dynamicContentPageInstance: dynamicContentPageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), dynamicContentPageInstance.id])
        redirect(action: "show", id: dynamicContentPageInstance.id)
    }

    def show(Long id) {
        def dynamicContentPageInstance = DynamicContentPage.get(id)
        if (!dynamicContentPageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "list")
            return
        }

        [dynamicContentPageInstance: dynamicContentPageInstance]
    }

    def edit(Long id) {
        def dynamicContentPageInstance = DynamicContentPage.get(id)
        if (!dynamicContentPageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "list")
            return
        }

        [dynamicContentPageInstance: dynamicContentPageInstance]
    }

    def update(Long id, Long version) {
        def dynamicContentPageInstance = DynamicContentPage.get(id)
        if (!dynamicContentPageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dynamicContentPageInstance.version > version) {
                dynamicContentPageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage')] as Object[],
                          "Another user has updated this DynamicContentPage while you were editing")
                render(view: "edit", model: [dynamicContentPageInstance: dynamicContentPageInstance])
                return
            }
        }

        dynamicContentPageInstance.properties = params

        if (!dynamicContentPageInstance.save(flush: true)) {
            render(view: "edit", model: [dynamicContentPageInstance: dynamicContentPageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), dynamicContentPageInstance.id])
        redirect(action: "show", id: dynamicContentPageInstance.id)
    }

    def delete(Long id) {
        def dynamicContentPageInstance = DynamicContentPage.get(id)

        def content = DynamicContent.findAllByPage(dynamicContentPageInstance)
        def images = DynamicContentImage.findAllByPage(dynamicContentPageInstance)

        if(content || images){
            flash.message = message(code: 'page.not.deleted.content.exists', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "show", id: id)
            return
        }

        if (!dynamicContentPageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "list")
            return
        }

        try {
            dynamicContentPageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dynamicContentPage.label', default: 'DynamicContentPage'), id])
            redirect(action: "show", id: id)
        }
    }

    def preview(Long id){

        def page = DynamicContentPage.get(id)

        if(!page){
            render (status: 404, 'text': 'Page not found')
            return
        }

        log.debug "Preview domain in controller - ${grailsApplication.config.dycon.previewDomain}"

        if(!grailsApplication.config.dycon?.containsKey("previewDomain")){
            render (status: 404, 'text': 'Preview URL not configured')
            return
        }

        redirect(url: "http://${grailsApplication.config.dycon.previewDomain}/${page.path}")
    }
}
