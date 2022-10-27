<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<body>
<jsp:include page="../header.jsp"></jsp:include>
		<table class="table">
			<thead>
				<tr>
					<th>보낸사람 : ${dm.senderId }</th>
				</tr>
				<tr>
					<th >받은시간 : ${dm.sendDate }</th>
				</tr>
				
			</thead>
			</table>


<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>