<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Registracija</title>
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

	<h1>Registracija novog korisnika</h1>

	<form action="<%=request.getContextPath()%>/servleti/register"
		method="post">

		<table>

			<tr>
				<td class="left"><div class="atrib">Nadimak</div></td>
				<td><input type="text" name="nick"
					value='<c:out value="${zapis.nick}"/>' size="20" /></td>

				<c:if test="${zapis.imaGresku('nick')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('nick')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><div class="atrib">Lozinka</div></td>
				<td><input type="password" name="password" value="" size="20" /></td>

				<c:if test="${zapis.imaGresku('password')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('password')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><div class="atrib">Ime</div></td>
				<td><input type="text" name="firstName"
					value='<c:out value="${zapis.firstName}"/>' size="40" /></td>

				<c:if test="${zapis.imaGresku('firstName')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('firstName')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><div class="atrib">Prezime</div></td>
				<td><input type="text" name="lastName"
					value='<c:out value="${zapis.lastName}"/>' size="40" /></td>

				<c:if test="${zapis.imaGresku('lastName')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('lastName')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><div class="atrib">E-mail</div></td>
				<td><input type="text" name="email"
					value='<c:out value="${zapis.email}"/>' size="40" /></td>

				<c:if test="${zapis.imaGresku('email')}">
					<td><div class="greska">
							<c:out value="${zapis.dohvatiGresku('email')}" />
						</div></td>
				</c:if>
			</tr>

			<tr>
				<td class="left"><input type="submit" name="metoda"
					value="Registriraj se"></td>
				<td><input type="submit" name="metoda" value="Odustani"></td>
			</tr>
		</table>

	</form>

</body>
</html>