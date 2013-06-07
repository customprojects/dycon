<%@ page import="com.dycon.DynamicContentImage" %>

<g:hiddenField name="live" value="false"></g:hiddenField>

<div class="fieldcontain ${hasErrors(bean: dynamicContentImageInstance, field: 'page', 'error')} ">
    <g:if test="${mode == "create"}">
        <label for="page">
            <g:message code="dynamicContent.page.label" default="Page" />

        </label>
        <g:select id="page" name="page.id" from="${com.dycon.DynamicContentPage.list()}" optionKey="id" optionValue="name" required="" value="${dynamicContentInstance?.page?.id}" class="many-to-one"/>
    </g:if>
    <g:if test="${mode == "edit"}">
        <span id="page-label" class="property-label"><g:message code="dynamicContent.page.label" default="Page" /></span>
        <span class="property-value" aria-labelledby="page-label">${dynamicContentImageInstance?.page?.name?.encodeAsHTML()}</span>
    </g:if>
</div>

%{--<div class="fieldcontain ${hasErrors(bean: dynamicContentImageInstance, field: 'page', 'error')} required">
	<label for="page">
		<g:message code="dynamicContentImage.page.label" default="Page" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="page" name="page.id" from="${com.dycon.DynamicContentPage.list()}" optionKey="id" required="" value="${dynamicContentImageInstance?.page?.id}" class="many-to-one"/>
</div>--}%

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
    <span id="imageFileDisplay">${dynamicContentImageInstance?.imageFile}</span>
    <uploader:uploader id="imageuploader" url="${[controller:'dynamicContentImage', action:'upload']}">
        <uploader:onComplete>
            $('#imageFile').val(responseJSON.filename);
            $('#imageFileDisplay').html(responseJSON.filename);
        </uploader:onComplete>
     </uploader:uploader>
</div>


