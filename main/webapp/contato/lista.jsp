<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dados dos Veículos</title>
        <link href="EstiloCSS/CSS3.css" rel="stylesheet" />
    </head>
    <body>
        <a href="ContatoServlet?acao=abreCadastro" class="myButton1" >Cadastro</a>
        <div class="caixa2">
            <h3>Veículos</h3>
        </div>
        <div class="caixa">
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Veículo</th>
                        <th>Nº Placa</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="p">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.nome}</td>
                            <td>${p.placa}</td>
                            <td>
                                <a href="ContatoServlet?acao=visualiza&id=${p.id}" class="myButton">Atualizar</a>
                                <a href="ContatoServlet?acao=exclui&id=${p.id}" class="myButton" onclick="window.alert('Excluido com sucesso!')">Excluir</a>
                            </td>       
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>