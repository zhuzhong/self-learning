<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>hello page</title>
<style type="text/css">
.timeClass {
	
}

.submitClass {
	
}

.submitClass input {
	
}
</style>
</head>
<body>
	<div>hello page</div>
	<form name="orderInfoForm" action="<c:url value="/sayHello.html"/>"
		method="post">

		<div class="orderNoClass">
			姓名 <input type="text" name="name">
		</div>
		<div class="submitClass">
			<input type="submit" value="sayhello">
		</div>

	</form>


</body>
</html>