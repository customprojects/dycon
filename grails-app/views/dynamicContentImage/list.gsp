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
				<li><a class="home" href="${createLink(uri: '/dycon')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-dynamicContentImage" class="content scaffold-list" role="main">
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

            <g:form ID="search" action="list" params="[pageId: currentPageId, live:live]">
                <input type="text" name="filter" value="${filter}" />
                <g:link action="list" params="[pageId: currentPageId, live:live]" class="search_reset">reset</g:link>
                <input type="submit" value="Search" />
            </g:form>

			<g:link target="_blank" class="preview-page" name="preview-page" controller="dynamicContentPage" action="preview" id="${currentPageId}"><g:message code="preview.label" /></g:link>
			<g:link class="publish-page-images" name="publish-page-images" action="publish" id="${currentPageId}"><g:message code="publishImages.label" /></g:link>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>

			<table>
				<thead>
					<tr>
                        <th>${message(code: 'dynamicContentImage.name.label', default: 'Name')}</th>
                        <th>${message(code: 'dynamicContentImage.imageFile.label', default: 'File')}</th>
                        <th>${message(code: 'dynamicContentImage.image.label', default: 'Image')}</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${dynamicContentImageInstanceList}" status="i" var="dynamicContentImageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <g:if test="${live != true}">
                            <td>
                                <div class="ordering_container">
                                    <g:link action="move" params="[move:'1', contentId:dynamicContentImageInstance.id, pageId:dynamicContentImageInstance.page.id, live:live, offset:params.offset, filter:filter]"><div class="ordering_up" >up</div></g:link>
                                    <g:link action="move" params="[move:'-1', contentId:dynamicContentImageInstance.id, pageId:dynamicContentImageInstance.page.id, live:live, offset:params.offset, filter:filter]"><div class= "ordering_down" >down</div></g:link>
                                </div>
                                <g:link action="show" id="${dynamicContentImageInstance.id}">${fieldValue(bean: dynamicContentImageInstance, field: "name")}</g:link>
                            </td>
                        </g:if>
                        <g:if test="${live == true}">
                            <td>
                                ${fieldValue(bean: dynamicContentImageInstance, field: "name")}
                            </td>
                        </g:if>
						<td>${fieldValue(bean: dynamicContentImageInstance, field: "imageFile")}</td>
						<td><img class="small-preview-image" src="http://${grailsApplication.config.dycon.previewDomain}/${grailsApplication.config.dycon.imageWebPath}/${fieldValue(bean: dynamicContentImageInstance, field: "imageFile")}"/></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dynamicContentImageInstanceTotal}" params="[pageId: currentPageId, live:live,filter:filter]" />
			</div>
		</div>
	</body>
</html>
