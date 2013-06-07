
<%@ page import="com.dycon.DynamicContentImage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dynamicContentLayout">
		<g:set var="entityName" value="${message(code: 'dynamicContentImage.label', default: 'DynamicContentImage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dynamicContentImage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dynamicContentImage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'dynamicContentImage.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="file" title="${message(code: 'dynamicContentImage.imageFile.label', default: 'File')}" />
					
						<g:sortableColumn property="live" title="${message(code: 'dynamicContentImage.live.label', default: 'Live')}" />
					
						<th><g:message code="dynamicContentImage.page.label" default="Page" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dynamicContentImageInstanceList}" status="i" var="dynamicContentImageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dynamicContentImageInstance.id}">${fieldValue(bean: dynamicContentImageInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: dynamicContentImageInstance, field: "imageFile")}</td>
					
						<td><g:formatBoolean boolean="${dynamicContentImageInstance.live}" /></td>
					
						<td>${fieldValue(bean: dynamicContentImageInstance, field: "page")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dynamicContentImageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
