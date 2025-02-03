<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modo Cliente</title>
</head>
<body>
<h2>Cliente</h2>
<div class="Operacoes">
    <label for="Operacao">Escolha a operação que pretende realizar: </label>
    <select id="Operacao" onchange="atualizarPagina()">
        <option value="ResVeiculo">Reservar veículo</option>
        <option value="ReservState">Estado da reserva</option>
        <option value="CRD">Consultar reputação e descontos</option>
    </select>
    <button formnovalidate="formnovalidate" id="Executar" onclick="document.getElementById('Comando').value = document.getElementById('Operacao').value; getResultados()">Executar</button>
</div>

<div class="atualizar">

    <form action="Admin" method="post">
        <br><br>
        <label for="nif">NIF:</label>
        <input type="text" id="nif" pattern="\d{9}" maxlength="9" ><br><br>

        <div id="veiculo_data"></div>

        <input type="submit" id="Comando" value="Atualizar Cliente">
    </form>
</div>


<div id="resultados"></div>

</body>
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


        if (operacao === "ResVeiculo") {
            document.getElementById("nif").disabled = false;



            var xhr22 = new XMLHttpRequest();

            var comando = "getStuff";

            xhr22.open("GET", "Cliente?comandoPedir=" + encodeURIComponent(comando) , true);

            xhr22.onreadystatechange = function () {
                if (xhr22.readyState == 4 && xhr22.status == 200) {
                    document.getElementById('veiculo_data').innerHTML = xhr.responseText;
                }
            };
            xhr22.send();



        } else if (operacao === "B") {

            document.getElementById("nif").disabled = false;


        } else if (operacao === "C") {


        }


    }

    function getResultados() {

        var comando = document.getElementById("Comando").value;
        var nif = document.getElementById("Comando").value;
        var parque = document.getElementById("parque").value;
        var tipoVeiculo = document.getElementById("tipoVeiculo").value;
        var dataInicio = document.getElementById("dataInicio").value;
        var dataFim = document.getElementById("dataFim").value;



        var xhr = new XMLHttpRequest();


        xhr.open("GET", "Cliente?comando=" + encodeURIComponent(comando) + "&nif=" + encodeURIComponent(nif) + "&tipoVeiculo=" + encodeURIComponent(tipoVeiculo) + "&parque=" + encodeURIComponent(parque)+ "&dataInicio=" + encodeURIComponent(dataFim), true);

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
</html>
