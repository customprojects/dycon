<%@ page import="com.dycon.DynamicContentPage" %>



<div class="fieldcontain ${hasErrors(bean: dynamicContentPageInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="dynamicContentPage.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${dynamicContentPageInstance?.name}"/>
</div>

