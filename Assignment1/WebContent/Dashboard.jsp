<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard</title>
<style>

ul li {
	display:inline;
	padding :5px;
}
ul {
	float:right;
}

</style>
</head>
<body>
	<ul>
	<li>Home</li>
	<li>Home</li>
	<li>Welcome <% session.getValue("username") ;%></li>
	</ul>
	<h1>Dashboard</h1>
	<div>
		
		<div>
			<div style="float:left; display:inline; height:20%; width:40%; border:solid black">Area A</div>
			<div style="float:right;  display:inline; height:20%;width:40%; border:solid black">
				<div>Area B</div>
				<div>Area C/div>
			</div>
		</div>
	</div>
</body>
</html>