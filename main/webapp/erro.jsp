<%--
Document : erro
Created on : 31 de mai de 2020, 18:23:23
Author : Cliente
--%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERRO</title>
    </head>
    <body>
        <h1>Ocorreu um erro</h1>
        <c:if test="${erro != null}">
            ${erro}
        </c:if>
        <c:if test="${erro == null}">
            Só digo isso
        </c:if>
    </body>
</html>