<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	session="true"%>

<html>
<body>
	<h2>Uspjeh</h2>
	<p>
		Dragi <b>${user.firstName} ${user.lastName}</b>, tvoja registracija
		bila je uspješna!
	</p>
	<p>
		Da bi se ulogirao, idi na <a
			href="<%=request.getContextPath()%>/index.jsp">početnu stranicu</a> i
		tamo obavi svoj prvi login!
	</p>
</body>
</html>
