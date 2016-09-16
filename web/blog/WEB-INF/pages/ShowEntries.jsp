<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Blogovi korisnika ${user.nick}</title>
</head>
<body>

	<jsp:include page="Header.jsp" />

	<c:choose>
		<c:when test="${entries.isEmpty()}">
			<h2>Trenutno ne postoji ni jedan blog korisnika ${user.nick}.</h2>
		</c:when>
		<c:otherwise>
			<h2>Odaberi neki od blogova:</h2>
			<ol>
				<c:forEach var="e" items="${entries}">
					<li><a
						href="<%=request.getContextPath()%>/servleti/author/${user.nick}/${e.id}">${e.title}</a>
					</li>
				</c:forEach>
			</ol>
		</c:otherwise>
	</c:choose>

	<c:if test="${sameUser}">
		<a
			href="<%=request.getContextPath()%>/servleti/author/${user.nick}/new">Izradi
			novi blog</a>
	</c:if>

	<jsp:include page="Footer.jsp" />

</body>
</html>