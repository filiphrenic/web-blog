<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Moja stranica blogova</title>
</head>
<body>
	<jsp:include page="Header.jsp" />

	<c:choose>
		<c:when test="${authors.isEmpty()}">
			<p>Trenutno ne postoji ni jedan autor.</p>
		</c:when>
		<c:otherwise>
			<p>Odaberi nekog od ponuđenih autora blogova</p>
			<ol>
				<c:forEach var="a" items="${authors}">
					<li><a
						href="<%=request.getContextPath()%>/servleti/author/${a.nick}">${a.nick}</a>
					</li>
				</c:forEach>
			</ol>
		</c:otherwise>
	</c:choose>

</body>
</html>