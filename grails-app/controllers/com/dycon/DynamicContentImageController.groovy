package com.dycon

import grails.converters.JSON
import grails.validation.ValidationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import uk.co.desirableobjects.ajaxuploader.AjaxUploaderService
import uk.co.desirableobjects.ajaxuploader.exception.FileUploadException

import javax.servlet.http.HttpServletRequest

class DynamicContentImageController {

    AjaxUploaderService ajaxUploaderService

    def dynamicContentService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static defaultAction = 'list'

    def list(Integer max, Boolean live, Integer pageId, String filter) {

        params.max = Math.min(max ?: 10, 100)
        params.live = live ? true : false
        params. offset = params.offset ?: 0;
        params.filter = filter ? "%$filter%" : "%"

        def pages = DynamicContentPage.findAll()
        def page
        if (!pageId) {
            if (pages) {
                page = pages[0]
            }
        } else {
            page = DynamicContentPage.get(pageId)
        }

        def images = []
        def currentPageId
        if (page) {
            currentPageId = page.id
            images = DynamicContentImage.findAllByLiveAndPageAndNameIlike(params.live, page, params.filter, params)
        }

        def all = DynamicContentImage.findAllByLiveAndPageAndNameIlike(params.live, page, params.filter)

        [dynamicContentImageInstanceList: images, dynamicContentImageInstanceTotal: all.size(), currentPageId: currentPageId, live: params.live, pages: pages, filter:filter]
    }

    def create() {
        [dynamicContentImageInstance: new DynamicContentImage(params), mode: "create"]
    }

    def save() {
        def dynamicContentImageInstance = new DynamicContentImage(params)
        if (!dynamicContentImageInstance.save(flush: true)) {
            render(view: "create", model: [dynamicContentImageInstance: dynamicContentImageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), dynamicContentImageInstance.id])
        redirect(action: "show", id: dynamicContentImageInstance.id)
    }

    def show(Long id) {
        def dynamicContentImageInstance = DynamicContentImage.get(id)
        if (!dynamicContentImageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), id])
            redirect(action: "list")
            return
        }

        [dynamicContentImageInstance: dynamicContentImageInstance]
    }

    def edit(Long id) {
        def dynamicContentImageInstance = DynamicContentImage.get(id)
        if (!dynamicContentImageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), id])
            redirect(action: "list")
            return
        }

        [dynamicContentImageInstance: dynamicContentImageInstance, mode: "edit"]
    }

    def update(Long id, Long version) {
        def dynamicContentImageInstance = DynamicContentImage.get(id)
        if (!dynamicContentImageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dynamicContentImageInstance.version > version) {
                dynamicContentImageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage')] as Object[],
                        "Another user has updated this DynamicContentImage while you were editing")
                render(view: "edit", model: [dynamicContentImageInstance: dynamicContentImageInstance])
                return
            }
        }

        dynamicContentImageInstance.properties = params

        if (!dynamicContentImageInstance.save(flush: true)) {
            render(view: "edit", model: [dynamicContentImageInstance: dynamicContentImageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), dynamicContentImageInstance.id])
        redirect(action: "show", id: dynamicContentImageInstance.id)
    }

    def delete(Long id) {
        def dynamicContentImageInstance = DynamicContentImage.get(id)
        if (!dynamicContentImageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), id])
            redirect(action: "list")
            return
        }

        try {
            def decrementSortOrder = DynamicContentImage.findAllByOrderGreaterThanAndPage(dynamicContentImageInstance.order, dynamicContentImageInstance.page);
            decrementSortOrder.each {
                it.order -= 1
            }
            dynamicContentImageInstance.delete(flush: true)
            decrementSortOrder*.save()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), id])
            redirect(action: "show", id: id)
        }
    }

    def upload = {
        try {

            File uploaded = createTemporaryFile()
            InputStream inputStream = selectInputStream(request)

            ajaxUploaderService.upload(inputStream, uploaded)

            def responseValue = [:]
            responseValue["success"] = "true"
            responseValue["filename"] = uploaded.name

            log.debug responseValue

            return render(text: responseValue as JSON, contentType: 'text/json')

        } catch (FileUploadException e) {

            log.error("Failed to upload file.", e)
            return render(text: [success: false] as JSON, contentType: 'text/json')
        }
    }

    private InputStream selectInputStream(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile uploadedFile = ((MultipartHttpServletRequest) request).getFile('qqfile')
            return uploadedFile.inputStream
        }
        return request.inputStream
    }

    private File createTemporaryFile() {

        String uuid = UUID.randomUUID()

        String uploadedFileName = params.qqfile
        def fileParts = uploadedFileName.split("\\.")

        String extension = fileParts.size() > 1 ? "." + fileParts[1] : ""

        String newFileName = uuid.replaceAll("-", "") + extension

        File uploaded
        if (grailsApplication.config.dycon?.containsKey('imageUploadDirectory')) {
            uploaded = new File(grailsApplication.config.dycon.imageUploadDirectory, newFileName)
        } else {
            uploaded = File.createTempFile('grails', 'ajaxupload')
        }
        return uploaded
    }

    def publish(Integer id) {

        dynamicContentService.publishImages(id)

        redirect(action: "list", params: [pageId: id])
    }

    def move(Boolean live, Integer pageId, Integer contentId, Integer move) {
        def chosenItemToMove = DynamicContentImage.get(contentId)
        def itemToMove = DynamicContentImage.findByOrder(chosenItemToMove.order - move);
        try {
            if (itemToMove) {
                if (chosenItemToMove?.order && itemToMove?.order) {
                    Integer tempOrder = chosenItemToMove.order
                    chosenItemToMove.order = itemToMove.order
                    itemToMove.order = tempOrder
                }

                chosenItemToMove.save(failOnError: true)
                itemToMove.save(failOnError: true)


            }
        } catch (ValidationException e) {
            flash.message = message(code: 'default.not.moved.message', args: [message(code: 'dynamicContentImage.label', default: 'DynamicContentImage'), contentId])
        } finally {
            redirect(action: "list", params: [pageId: pageId, live: live, offset: params.offset, filter:params.filter])
        }
    }
}
