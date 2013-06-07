<%--
  Created by IntelliJ IDEA.
  User: tim
  Date: 05/06/13
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="dynamicContentLayout">
    <title></title>
</head>
<body>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link controller="dynamicContentPage" action="list">Pages</g:link></li>
            <li><g:link controller="dynamicContent" action="list">Content</g:link></li>
            <li><g:link controller="dynamicContentImage" action="list">Images</g:link></li>
        </ul>
    </div>
</body>
</html>