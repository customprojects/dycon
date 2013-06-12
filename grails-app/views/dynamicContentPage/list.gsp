<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dynamicContentLayout">
		<g:set var="entityName" value="${message(code: 'dynamicContentPage.label', default: 'DynamicContentPage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dynamicContentPage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dycon')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dynamicContentPage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'dynamicContentPage.name.label', default: 'Name')}" />
						<g:sortableColumn property="path" title="${message(code: 'dynamicContentPage.path.label', default: 'Path')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${dynamicContentPageInstanceList}" status="i" var="dynamicContentPageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${dynamicContentPageInstance.id}">${fieldValue(bean: dynamicContentPageInstance, field: "name")}</g:link></td>
						<td>${fieldValue(bean: dynamicContentPageInstance, field: "path")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dynamicContentPageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
