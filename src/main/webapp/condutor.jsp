<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modo Condutor</title>
</head>
<body>
<h2>Condutor</h2>
<div class="Operacoes">
    <label for="Operacao">Escolha a operação que pretende realizar: </label>
    <select id="Operacao" onchange="atualizarPagina()">
        <option value="L">Levantar veículo</option>
        <option value="E">Entregar veículo</option>
        <option value="Av">Fazer avaliação</option>
    </select>
    <button formnovalidate="formnovalidate" id="Executar" onclick="document.getElementById('Comando').value = document.getElementById('Operacao').value; getResultados()">Executar</button>
</div>

<div class="atualizar">

    <form action="Condutor" method="post">
<br><br>
        <label for="nif">Introduza o seu NIF:</label>
        <input type="text" id="nif" pattern="\d{9}" maxlength="9" ><br><br>


        <label for="codaN">Introduza a sua coordenada N:</label>
        <input type="text" id="codaN" ><br><br>

        <label for="codaW">Introduza a sua coordenada W:</label>
        <input type="text" id="codaW" ><br><br>

        <label for="avaliacao">Avalie o serviço:</label>
        <input type="text" id="avaliacao" ><br><br>

        <label for="comment">Comentário:</label>
        <select id="comment">
            <option value="adorei">Adorei</option>
            <option value="gostei">Gostei</option>
            <option value="não vou voltar">Não vou voltar</option>
        </select>
        <br><br>

        <input type="submit" id="Comando" value="...">
    </form>
</div>


<div id="resultados"></div>
</body>
</html>
<script>
    function atualizarPagina() {


        var operacao = document.getElementById("Operacao").value;


        var inputs = document.getElementsByTagName("input");
        var selects = document.getElementsByTagName("select");
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].disabled = true;
        }
        for (var i = 0; i < selects.length; i++) {
            selects[i].disabled = true;
        }
        document.getElementById("Operacao").disabled = false;


        if (operacao === "L") {

            document.getElementById("nif").disabled = false;

        } else if (operacao === "E") {
            document.getElementById("nif").disabled = false;
            document.getElementById("codaN").disabled = false;
            document.getElementById("codaW").disabled = false;

        } else if(operacao === "Av"){
            document.getElementById("nif").disabled = false;
            document.getElementById("avaliacao").disabled = false;
            document.getElementById("comment").disabled = false;
        }


    }

    function getResultados() {

        var comando = document.getElementById("Comando").value;
        var nif = document.getElementById("nif").value;
        var codaN = document.getElementById("codaN").value;
        var codaW = document.getElementById("codaW").value;
        var avaliacao = document.getElementById("avaliacao").value;
        var comment = document.getElementById("comment").value;

        var xhr = new XMLHttpRequest();


        xhr.open("GET", "Condutor?comando=" + encodeURIComponent(comando) + "&nif=" + encodeURIComponent(nif)+ "&codaN=" + encodeURIComponent(codaN)+ "&codaW=" + encodeURIComponent(codaW)+ "&avaliacao=" + encodeURIComponent(avaliacao)+ "&comentario=" + encodeURIComponent(comment) , true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById('resultados').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    window.onload = function() {
        atualizarPagina();
    };
</script>