<div class="fieldcontain ${hasErrors(bean: dynamicContentPageInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="dynamicContentPage.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${dynamicContentPageInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentPageInstance, field: 'path', 'error')} ">
	<label for="path">
		<g:message code="dynamicContentPage.path.label" default="Path" />
	</label>
	<g:textField name="path" value="${dynamicContentPageInstance?.path}"/>
</div>
