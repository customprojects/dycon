<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dynamicContentLayout">
		<g:set var="entityName" value="${message(code: 'dynamicContent.label', default: 'DynamicContent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dynamicContent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/dycon')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dynamicContent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<div id="list-dynamicContent-selection">
				<label for="pageName">Choose Page: </label>
				<g:select name="pageName"
					  from="${pages}"
					  value="${currentPageId}"
					  optionValue="name"
					  optionKey="id" />
				<label for="live">Live content: </label>
				<g:checkBox name="live" checked="${live}" value="${live}" />

			</div>
			<g:link target="_blank" class="preview-page" name="preview-page" controller="dynamicContentPage" action="preview" id="${currentPageId}"><g:message code="preview.label" /></g:link>
			<g:link class="publish-page-content" name="publish-page-content" action="publish" id="${currentPageId}"><g:message code="publishContent.label" /></g:link>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'dynamicContent.name.label', default: 'Name')}" />
						<g:sortableColumn property="value" title="${message(code: 'dynamicContent.value.label', default: 'Value')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${dynamicContentInstanceList}" status="i" var="dynamicContentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <g:if test="${live == true}">
							<td>${fieldValue(bean: dynamicContentInstance, field: "name")}</td>
						</g:if>

						<g:if test="${live != true}">
							<td><g:link action="show" id="${dynamicContentInstance.id}">${fieldValue(bean: dynamicContentInstance, field: "name")}</g:link></td>
						</g:if>

						<td>${dynamicContentInstance.value.trimLength(100)}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dynamicContentInstanceTotal}" params="[pageId: dynamicContentInstanceList[0]?.page?.id]" />
			</div>
		</div>
	</body>
</html>
