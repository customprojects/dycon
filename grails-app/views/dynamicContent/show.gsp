
<%@ page import="com.dycon.DynamicContent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dynamicContent.label', default: 'DynamicContent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-dynamicContent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dynamicContent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dynamicContent">
			
%{--				<g:if test="${dynamicContentInstance?.live}">
				<li class="fieldcontain">
					<span id="live-label" class="property-label"><g:message code="dynamicContent.live.label" default="Live" /></span>
					
						<span class="property-value" aria-labelledby="live-label"><g:formatBoolean boolean="${dynamicContentInstance?.live}" /></span>
					
				</li>
				</g:if>--}%
			

			
				<g:if test="${dynamicContentInstance?.page}">
				<li class="fieldcontain">
					<span id="page-label" class="property-label"><g:message code="dynamicContent.page.label" default="Page" /></span>
					
						<span class="property-value" aria-labelledby="page-label"><g:link controller="dynamicContentPage" action="show" id="${dynamicContentInstance?.page?.id}">${dynamicContentInstance?.page?.name?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

                <g:if test="${dynamicContentInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="dynamicContent.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${dynamicContentInstance}" field="name"/></span>

                    </li>
                </g:if>

                <g:if test="${dynamicContentInstance?.value}">
				<li class="fieldcontain">
					<span id="value-label" class="property-label"><g:message code="dynamicContent.value.label" default="Value" /></span>
					
						<span class="property-value" aria-labelledby="value-label"><g:fieldValue bean="${dynamicContentInstance}" field="value"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dynamicContentInstance?.id}" />
					<g:link class="edit" action="edit" id="${dynamicContentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
