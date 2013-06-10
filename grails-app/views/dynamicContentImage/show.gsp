
<%@ page import="com.dycon.DynamicContentImage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dynamicContentLayout">
		<g:set var="entityName" value="${message(code: 'dynamicContentImage.label', default: 'DynamicContentImage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dynamicContentImage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dycon')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dynamicContentImage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dynamicContentImage">

                <g:if test="${dynamicContentImageInstance?.page}">
                    <li class="fieldcontain">
                        <span id="page-label" class="property-label"><g:message code="dynamicContentImage.page.label" default="Page" /></span>

                        <span class="property-value" aria-labelledby="page-label"><g:link controller="dynamicContentPage" action="show" id="${dynamicContentImageInstance?.page?.id}">${dynamicContentImageInstance?.page?.name?.encodeAsHTML()}</g:link></span>

                    </li>
                </g:if>
			
				<g:if test="${dynamicContentImageInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="dynamicContentImage.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${dynamicContentImageInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dynamicContentImageInstance?.imageFile}">
				<li class="fieldcontain">
					<span id="file-label" class="property-label"><g:message code="dynamicContentImage.file.label" default="File" /></span>
					
						<span class="property-value" aria-labelledby="file-label"><g:fieldValue bean="${dynamicContentImageInstance}" field="imageFile"/></span>
					
				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dynamicContentImageInstance?.id}" />
					<g:link class="edit" action="edit" id="${dynamicContentImageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
