<%-- 
    Document   : visualiza
    Created on : 28/06/2020, 00:42:43
    Author     : Cliente
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Veículo</title>
        <link href="EstiloCSS/CSS2.css" rel="stylesheet" />
    </head>
    <body>
        <a href="ContatoServlet?acao=lista" class="myButton1">Lista</a>
        
        <div class="campo1">Atualizar Veículo</div>
            <form action="ContatoServlet" method="post">
                <input type="hidden" name="acao" value="atualiza" />
                <input type="hidden" name="id" value="${contato.id}" />
                <div class="campo">
                    <label>Nome do Carro:</label>
                    <input type="text" name="nome" style="width: 20em" value="${contato.nome}"/>
                </div>
                <div class="campo">
                    <label>Placa do Carro:</label>
                    <input type="text" name="telefone" style="width: 20em" value="${contato.placa}"/>
                </div>
                <input type="submit" value="Atualizar" class="myButton" onclick="window.alert('Atualizado com sucesso!')"/>
        </form>
    </body>
</html>
