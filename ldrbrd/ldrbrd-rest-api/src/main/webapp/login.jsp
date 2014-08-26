<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
		<h3>Login with Username and Password</h3>
		<form action="/j_spring_security_check" method="POST">
			<table>
				<tr>
					<td>User ID:</td>
					<td><input type='text' name='username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Login" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>