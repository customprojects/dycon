package com.dycon

import org.springframework.dao.DataIntegrityViolationException

class DynamicContentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def dynamicContentService

    def index() {
        redirect(action: "list", params: params)
    }


    def list(Integer max,Boolean live,Integer pageId) {

        println params

        params.max = Math.min(max ?: 10, 100)
        params.live = live ? true : false

        def page
        if(!pageId){
            def pages = DynamicContentPage.findAll()
            if(pages?.size() > 0){
                page = pages[0]
            }
        }else{
            page = DynamicContentPage.findById(pageId)
        }

        def content = []
        def currentPageId
        if(page){
            currentPageId = page.id
            content = DynamicContent.findAllByLiveAndPage(params.live,page,[max: params.max])
        }

        [dynamicContentInstanceList: content, dynamicContentInstanceTotal: content.size(), currentPageId: currentPageId, live: params.live]
    }

    def create() {
        [dynamicContentInstance: new DynamicContent(params),mode:"create"]
    }

    def save() {
        def dynamicContentInstance = new DynamicContent(params)
        if (!dynamicContentInstance.save(flush: true)) {
            render(view: "create", model: [dynamicContentInstance: dynamicContentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), dynamicContentInstance.id])
        redirect(action: "show", id: dynamicContentInstance.id)
    }

    def show(Long id) {
        def dynamicContentInstance = DynamicContent.get(id)
        if (!dynamicContentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "list")
            return
        }

        [dynamicContentInstance: dynamicContentInstance]
    }

    def edit(Long id) {
        def dynamicContentInstance = DynamicContent.get(id)
        if (!dynamicContentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "list")
            return
        }

        [dynamicContentInstance: dynamicContentInstance,mode:"edit"]
    }

    def update(Long id, Long version) {
        def dynamicContentInstance = DynamicContent.get(id)
        if (!dynamicContentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dynamicContentInstance.version > version) {
                dynamicContentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dynamicContent.label', default: 'DynamicContent')] as Object[],
                          "Another user has updated this DynamicContent while you were editing")
                render(view: "edit", model: [dynamicContentInstance: dynamicContentInstance])
                return
            }
        }

        dynamicContentInstance.properties = params

        if (!dynamicContentInstance.save(flush: true)) {
            render(view: "edit", model: [dynamicContentInstance: dynamicContentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), dynamicContentInstance.id])
        redirect(action: "show", id: dynamicContentInstance.id)
    }

    def delete(Long id) {
        def dynamicContentInstance = DynamicContent.get(id)
        if (!dynamicContentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "list")
            return
        }

        try {
            dynamicContentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "show", id: id)
        }
    }


    def publish(Integer id){


        dynamicContentService.publish(id)

        redirect(action: "list",params: [pageId: id])
    }
}
