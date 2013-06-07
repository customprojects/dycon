<%@ page import="com.dycon.DynamicContentImage" %>



<div class="fieldcontain ${hasErrors(bean: dynamicContentImageInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="dynamicContentImage.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${dynamicContentImageInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentImageInstance, field: 'imageFile', 'error')} ">
	<label for="file">
		<g:message code="dynamicContentImage.file.label" default="File" />
	</label>
	<g:hiddenField name="imageFile" value="${dynamicContentImageInstance?.imageFile}"/>
    <span id="imageFileDisplay"></span>
    <uploader:uploader id="imageuploader" url="${[controller:'dynamicContentImage', action:'upload']}">
        <uploader:onComplete>
            $('#imageFile').val(responseJSON.filename);
            $('#imageFileDisplay').html(responseJSON.filename);
        </uploader:onComplete>
     </uploader:uploader>
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentImageInstance, field: 'live', 'error')} ">
	<label for="live">
		<g:message code="dynamicContentImage.live.label" default="Live" />
		
	</label>
	<g:checkBox name="live" value="${dynamicContentImageInstance?.live}" />
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentImageInstance, field: 'page', 'error')} required">
	<label for="page">
		<g:message code="dynamicContentImage.page.label" default="Page" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="page" name="page.id" from="${com.dycon.DynamicContentPage.list()}" optionKey="id" required="" value="${dynamicContentImageInstance?.page?.id}" class="many-to-one"/>
</div>

