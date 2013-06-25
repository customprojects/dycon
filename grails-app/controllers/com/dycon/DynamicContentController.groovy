package com.dycon

import grails.validation.ValidationException
import org.springframework.dao.DataIntegrityViolationException

class DynamicContentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def dynamicContentService

    static defaultAction = 'list'

    def list(Integer max, Boolean live, Integer pageId, String filter) {

        params.max = Math.min(max ?: 10, 100)
        params.live = live ? true : false
        params. offset = params.offset ?: 0;
        params.filter = filter ? "%$filter%" : "%";

        def pages = DynamicContentPage.findAll()
        def page
        if (!pageId) {
            if (pages) {
                page = pages[0]
            }
        } else {
            page = DynamicContentPage.findById(pageId)
        }

        def content = []
        def currentPageId
        if (page) {
            currentPageId = page.id
            content = DynamicContent.findAllByLiveAndPageAndNameIlike(params.live, page, params.filter, params)
        }

        def all = DynamicContent.findAllByLiveAndPageAndNameIlike(params.live, page, params.filter)

        [dynamicContentInstanceList: content, dynamicContentInstanceTotal: all.size(), currentPageId: currentPageId, live: params.live, pages: pages, filter:filter]
    }

    def create() {

        [dynamicContentInstance: new DynamicContent(params), mode: "create"]
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

        [dynamicContentInstance: dynamicContentInstance, mode: "edit"]
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
            def decrementSortOrder = DynamicContent.findAllByOrderGreaterThanAndPageAndLive(dynamicContentInstance.order, dynamicContentInstance.page,false);
            decrementSortOrder.each {
                it.order -= 1
            }
            dynamicContentInstance.delete(flush: true)
            decrementSortOrder*.save()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), id])
            redirect(action: "show", id: id)
        }
    }

    def publish(Integer id) {

        dynamicContentService.publish(id)

        redirect(action: "list", params: [pageId: id])
    }

    def move(Boolean live, Integer pageId, Integer contentId, Integer move) {

        def chosenItemToMove = DynamicContent.get(contentId)
        def page = DynamicContentPage.findById(pageId)

        def itemToMove = DynamicContent.findByPageAndLiveAndOrder(page,false,chosenItemToMove.order + move);

        try {
            if (itemToMove) {
                if (chosenItemToMove.order && itemToMove.order) {
                    Integer tempOrder = chosenItemToMove.order
                    chosenItemToMove.order = itemToMove.order
                    itemToMove.order = tempOrder
                }

                chosenItemToMove.save(failOnError: true)
                itemToMove.save(failOnError: true)
            }
        } catch (ValidationException e) {
            flash.message = message(code: 'default.not.moved.message', args: [message(code: 'dynamicContent.label', default: 'DynamicContent'), contentId])
        } finally {
            redirect(action: "list", params: [pageId: pageId, live: live, offset: params.offset, filter:params.filter])
        }

    }
}
