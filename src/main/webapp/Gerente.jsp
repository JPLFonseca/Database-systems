<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modo Gerente</title>
</head>
<body>
<h2>Gerente</h2>
<div class="Operacoes">
    <label for="Operacao">Escolha a operação que pretende realizar: </label>
    <select id="Operacao" onchange="atualizarPagina()">
        <option value="hist">Histórico de um veículo</option>
        <option value="mLucro">Ranking veículos que geraram menos lucro</option>
        <option value="mAvalicao">Ranking veículos com melhor avaliação na semana passada</option>
        <option value="mKm">Ranking veículos com menos km no último trimestre</option>
        <option value="clientes">Ranking clientes</option>
    </select>
    <button formnovalidate="formnovalidate" id="Executar" onclick="document.getElementById('Comando').value = document.getElementById('Operacao').value; getResultados()">Executar</button>
</div>

<div class="atualizar">

    <form action="Gerente" method="post">

        <label for="matricula">Matricula:</label>
        <input type="text" id="matricula" ><br><br>

        <input type="submit" id="Comando" value="...">
    </form>
</div>


<div id="resultados"></div>
</body>
</html>
<script>


    function getResultados() {

        var comando = document.getElementById("Comando").value;


        var xhr = new XMLHttpRequest();

        var matricula = document.getElementById("matricula").value;



        xhr.open("GET", "Gerente?comando=" + encodeURIComponent(comando) + "&matricula=" + encodeURIComponent(matricula), true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById('resultados').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

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


        if (operacao === "hist") {
            document.getElementById("matricula").disabled = false;
        }



    }
    window.onload = function() {
        atualizarPagina();
    };

</script>