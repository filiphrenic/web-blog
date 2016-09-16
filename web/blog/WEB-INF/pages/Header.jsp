<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    if (session.getAttribute("current.user") != null) {
%>
<p>
	Korisnik: <b><%=session.getAttribute("current.user").toString()%></b> <a
		href="<%=request.getContextPath()%>/servleti/logout">Logout</a>
</p>
<%
    } else {
%>
<p>
	<b>Anonimni korisnik</b> možete se <a
		href="<%=request.getContextPath()%>/servleti/register">Registrirati</a>
</p>
<form action="<%=request.getContextPath()%>/servleti/login"
	method="post">
	<table>
		<tr>
			<td><div>Nick</div></td>
			<td><div>Password</div></td>
		</tr>
		<tr>
			<td><input type="text" name="nick" value="" size="20" /></td>
			<td><input type="password" name="password" value="" size="20" /></td>
			<td><input type="submit" name="metoda" value="Login" /></td>
		</tr>
	</table>
	<c:if test="${zapis.imaGreske()}">
		<c:if test="${zapis.imaGresku('nick')}">
			<c:out value="${zapis.dohvatiGresku('nick')}" />
			<br>
		</c:if>
		<c:if test="${zapis.imaGresku('password')}">
			<c:out value="${zapis.dohvatiGresku('password')}" />
		</c:if>
	</c:if>
</form>
<%
    }
%>