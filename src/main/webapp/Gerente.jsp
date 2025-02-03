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
        <input type="submit" id="Comando" value="Atualizar Cliente">
    </form>
</div>


<div id="resultados"></div>
<div id="listaCarros"></div>
</body>
</html>
<script>


    function getResultados() {

        var comando = document.getElementById("Comando").value;

        var xhr = new XMLHttpRequest();


        xhr.open("GET", "Gerente?comando=" + encodeURIComponent(comando) , true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                document.getElementById('resultados').innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }


</script>