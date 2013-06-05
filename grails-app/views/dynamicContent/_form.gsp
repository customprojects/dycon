<%@ page import="com.dycon.DynamicContent" %>


   <g:hiddenField name="live" value="false"></g:hiddenField>


<div class="fieldcontain ${hasErrors(bean: dynamicContentInstance, field: 'page', 'error')} ">
    <g:if test="${mode == "create"}">
        <label for="page">
            <g:message code="dynamicContent.page.label" default="Page" />

        </label>
        <g:select id="page" name="page.id" from="${com.dycon.DynamicContentPage.list()}" optionKey="id" optionValue="name" required="" value="${dynamicContentInstance?.page?.id}" class="many-to-one"/>
    </g:if>
    <g:if test="${mode == "edit"}">
        <span id="page-label" class="property-label"><g:message code="dynamicContent.page.label" default="Page" /></span>
        <span class="property-value" aria-labelledby="page-label">${dynamicContentInstance?.page?.name?.encodeAsHTML()}</span>
    </g:if>
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentInstance, field: 'name', 'error')} ">
    <g:if test="${mode == "create"}">
        <label for="name">
            <g:message code="dynamicContent.name.label" default="Name" />
        </label>
        <g:textField name="name" value="${dynamicContentInstance?.name}" />
    </g:if>
    <g:if test="${mode == "edit"}">
        <span id="name-label" class="property-label"><g:message code="dynamicContent.name.label" default="Name" /></span>
        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${dynamicContentInstance}" field="name"/></span>
    </g:if>
</div>

<div class="fieldcontain ${hasErrors(bean: dynamicContentInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="dynamicContent.value.label" default="Value" />
		
	</label>
	<g:textArea name="value" value="${dynamicContentInstance?.value}" />
</div>

