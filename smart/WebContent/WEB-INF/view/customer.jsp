<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="base" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="5">
		<tr>
			<th>客户号</th>
			<th>客户名字</th>
			<th>客户联系人</th>
			<th>客户电话</th>
			<th>客户Email</th>
			<th>Comment</th>
			<th>操作</th>
		</tr>
		<c:forEach var="customer" items="${customerList}">
		    <tr>
		    	<td>${customer.id}</td>
		    	<td>${customer.name}</td>
		    	<td>${customer.contact}</td>
		    	<td>${customer.telephone}</td>
		    	<td>${customer.email}</td>
		    	<td>${customer.remark}</td>
		    	<td>
		    		<a href="${base}/customer_edit?id=${customer.id}">编辑</a>
		    		<a href="${base}/customer_edit?id=${customer.id}">删除</a>
		    	</td>
		    </tr>
		</c:forEach>
	</table>

</body>
</html>