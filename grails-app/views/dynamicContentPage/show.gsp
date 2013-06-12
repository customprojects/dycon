<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dynamicContentLayout">
		<g:set var="entityName" value="${message(code: 'dynamicContentPage.label', default: 'DynamicContentPage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dynamicContentPage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dycon')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dynamicContentPage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dynamicContentPage">

				<g:if test="${dynamicContentPageInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="dynamicContentPage.name.label" default="Name" /></span>
					<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${dynamicContentPageInstance}" field="name"/></span>
				</li>
				</g:if>

				<g:if test="${dynamicContentPageInstance?.path}">
				<li class="fieldcontain">
					<span id="path-label" class="property-label"><g:message code="dynamicContentPage.path.label" default="Path" /></span>
					<span class="property-value" aria-labelledby="path-label"><g:fieldValue bean="${dynamicContentPageInstance}" field="path"/></span>
				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dynamicContentPageInstance?.id}" />
					<g:link class="edit" action="edit" id="${dynamicContentPageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
