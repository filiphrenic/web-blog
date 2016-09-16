<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<title>${entry.title}</title>
<body>

	<jsp:include page="Header.jsp" />

	<h1>${entry.title}
		<c:if test="${sameUser}">
		| <a
				href="<%=request.getContextPath()%>/servleti/author/${user.nick}/edit?id=${entry.id}">Uredi
				blog</a>
		</c:if>
	</h1>

	<div style="font-size: medium; width: 300px; font-style: italic;">
		<p style="position: relative; word-wrap: break-word">
			<c:out value="${entry.text}"></c:out>
		</p>
	</div>

	<c:choose>
		<c:when test="${comments.isEmpty()}">
			<h4>Ne postoji ni jedan komentar.</h4>
		</c:when>
		<c:otherwise>
			<ol>
				<c:forEach var="c" items="${comments}">
					<li><div style="font-weight: bold">
							[Korisnik=${c.usersEMail}] ${c.postedOn}</div>
						<div style="padding-left: 10px;">${c.message}</div></li>
				</c:forEach>
			</ol>
		</c:otherwise>
	</c:choose>
	<h5>
		Dodaj komentar<br> Komentari bez sadržaja neće biti objavljeni
	</h5>
	<form
		action="<%=request.getContextPath()%>/servleti/author/${user.nick}/${entry.id}"
		method="post">

		<table>
			<tr>
				<td><div>E-mail</div></td>
				<td><input type="text" name="usersEMail"
					value='<c:out value="${zapis.usersEMail}"/>' size="20" /></td>
			</tr>

			<tr>
				<td><div>Komentar</div></td>
				<td><textarea name="message" rows="6" cols="16"><c:out
							value="${zapis.message}" /></textarea></td>
			</tr>

			<tr>
				<td><input type="submit" name="metoda" value="Objavi"></td>
				<td></td>
			</tr>
		</table>
	</form>

	<jsp:include page="Footer.jsp" />

</body>
</html>