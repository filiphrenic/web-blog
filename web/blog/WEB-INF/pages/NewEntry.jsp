<%@page import="hr.fer.zemris.app.model.BlogUser"%>
<%@page import="hr.fer.zemris.app.web.forms.EntryForm"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Blog</title>
<style type="text/css">
.greska {
	font-family: monospace;
	font-weight: bold;
	font-size: 0.9em;
	color: #FF0000;
}

.atrib {
	font-style: italic;
	font-family: Georgia, serif;
	font-size: 1.2em;
	color: #0000FF;
}

.left {
	text-align: right;
	vertical-align: baseline;
}
</style>
</head>

<body>

	<h1>Uređivanje bloga</h1>

	<form
		action="<%=request.getContextPath()%>/servleti/author/${user.nick}/${type}"
		method="post">

		<table>

			<tr>
				<td class="left"><div class="atrib">Naslov</div></td>
				<td><input type="text" name="title"
					value='<c:out value="${zapis.title}"/>' size="50" /></td>

				<c:if test="${zapis.imaGresku('title')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('title')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><div class="atrib">Sadržaj</div></td>
				<td><textarea name="text" rows="32" cols="50"><c:out
							value="${zapis.text}" /></textarea></td>

				<c:if test="${zapis.imaGresku('text')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('text')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><input type="submit" name="metoda"
					value="Pohrani"></td>
				<td><input type="submit" name="metoda" value="Odustani"></td>
			</tr>
		</table>

		<input type="hidden" name="id" value="${zapis.id}" /> <input
			type="hidden" name="tip" value="${type}" />

	</form>

</body>
</html>