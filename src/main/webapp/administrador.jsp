<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modo Admin</title>
</head>
<body>
<h2>Administrador</h2>
<div class="Operacoes">
    <label for="Operacao">Escolha a operação que pretende realizar: </label>
    <select id="Operacao" onchange="atualizarPagina()">
        <option value="A">Atualizar dados do cliente (pessoal)</option>
        <option value="B">Atualizar dados do cliente (empresa)</option>
        <option value="C">Mostrar dados de veículo</option>

    </select>
    <button formnovalidate="formnovalidate" id="Executar" onclick="document.getElementById('Comando').value = document.getElementById('Operacao').value; getResultados()">Executar</button>
</div>

<div class="atualizar">

    <form action="Admin" method="post">
<br><br>
        <label for="nif">NIF:</label>
        <input type="text" id="nif" pattern="\d{9}" maxlength="9" ><br><br>


        <label for="nomeCliente">Nome do cliente:</label>
        <input type="text" id="nomeCliente" ><br><br>

        <label for="telemovel">Número de telemóvel:</label>
        <input type="tel" id="telemovel" pattern="\d{9,12}" ><br><br>

        <label for="morada">Morada:</label>
        <input type="text" id="morada" ><br><br>

        <label for="linguagem">Preferência linguística:</label>
        <input type="text" id="linguagem"  ><br><br>

        <label for="capSocial">Capital Social:</label>
        <input type="number" id="capSocial"><br><br>

        <label for="nomeCondutor">Nome do condutor:</label>
        <input type="text" id="nomeCondutor" ><br><br>

        <label for="cconducao">Número de Carta de Condução:</label>
        <input type="text" id="cconducao" maxlength="15" ><br><br>

        <label for="dataEmissao">Data de Emissão:</label>
        <input type="date" id="dataEmissao" ><br><br>

        <label for="dataNascimento">Data de Nascimento:</label>
        <input type="date" id="dataNascimento"><br><br>

        <label for="dataValidade">Data de validade:</label>
        <input type="date" id="dataValidade"><br><br>

        <label for="reputacao">Reputacao:</label>
        <input type="text" id="reputacao" maxlength="3" placeholder="Valor entre 0 e 100"><br><br>

        <input type="submit" id="Comando" value="Atualizar Cliente">
    </form>
</div>


<div id="resultados"></div>
<div id="listaCarros"></div>

<label for="matricula">Digite a Matrícula do Veículo:</label>
<input type="text" id="matricula" placeholder="Ex: AA1234">
<button onclick="exportarJSON()">Exportar JSON</button>
<div id="jsonOutput" style="white-space: pre-wrap; background: #f4f4f4; padding: 10px; border: 1px solid #ddd; margin-top: 10px;"></div>

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


        if (operacao === "A") {
            document.getElementById("nomeCliente").disabled = false;
            document.getElementById("nif").disabled = false;
            document.getElementById("linguagem").disabled = false;
            document.getElementById("telemovel").disabled = false;
            document.getElementById("morada").disabled = false;
            document.getElementById("cconducao").disabled = false;
            document.getElementById("dataEmissao").disabled = false;
            document.getElementById("dataValidade").disabled = false;
            document.getElementById("dataNascimento").disabled = false;
            document.getElementById("reputacao").disabled = false;
        } else if (operacao === "B") {
            document.getElementById("nomeCliente").disabled = false;
            document.getElementById("nif").disabled = false;
            document.getElementById("telemovel").disabled = false;
            document.getElementById("morada").disabled = false;
            document.getElementById("linguagem").disabled = false;
            document.getElementById("capSocial").disabled = false;
            document.getElementById("nomeCondutor").disabled = false;
            document.getElementById("cconducao").disabled = false;
            document.getElementById("dataEmissao").disabled = false;
            document.getElementById("dataValidade").disabled = false;
            document.getElementById("dataNascimento").disabled = false;
            document.getElementById("reputacao").disabled = false;

        } else if (operacao === "C") {

            document.getElementById("matricula").disabled = false;
        }


    }

    function getResultados() {

        var comando = document.getElementById("Comando").value;
        var nomeCliente = document.getElementById("nomeCliente").value;
        var nomeCondutor = document.getElementById("nomeCondutor").value;
        var morada = document.getElementById("morada").value;
        var nif = document.getElementById("nif").value;
        var telemovel = document.getElementById("telemovel").value;
        var prefLinguistica = document.getElementById("linguagem").value;
        var capSocial = document.getElementById("capSocial").value;
        var nCartaConducao = document.getElementById("cconducao").value;
        var dataEmissao = document.getElementById("dataEmissao").value;
        var dataNascimento = document.getElementById("dataNascimento").value;
        var dataValidade = document.getElementById("dataValidade").value;
        var reputacao = document.getElementById("reputacao").value;
        var matricula = document.getElementById("matricula").value;



        var xhr = new XMLHttpRequest();


        xhr.open("GET", "Admin?comando=" + encodeURIComponent(comando) + "&nomeCliente=" + encodeURIComponent(nomeCliente) + "&nomeCondutor=" + encodeURIComponent(nomeCondutor) + "&morada=" + encodeURIComponent(morada) + "&nif=" + encodeURIComponent(nif) + "&prefLinguistica=" + encodeURIComponent(prefLinguistica) + "&capSocial=" + encodeURIComponent(capSocial) + "&telemovel=" + encodeURIComponent(telemovel) + "&nCartaConducao=" + encodeURIComponent(nCartaConducao) + "&dataEmissao=" + encodeURIComponent(dataEmissao) + "&dataNascimento=" + encodeURIComponent(dataNascimento) + "&dataValidade=" + encodeURIComponent(dataValidade) + "&reputacao=" + encodeURIComponent(reputacao)+ "&matricula=" + encodeURIComponent(matricula), true);

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



    function exportarJSON() {
        var matricula = document.getElementById("matricula").value;
        var comandoExport = "Exportar";

        if (!matricula) {
            alert("Por favor, insira uma matrícula válida");
            return;
        }


        fetch("Admin?matricula=" + encodeURIComponent(matricula) + "&comando=" + encodeURIComponent(comandoExport))
            .then(response => response.json())
            .then(data => {

                document.getElementById("jsonOutput").textContent = JSON.stringify(data, null, 4);


                var blob = new Blob([JSON.stringify(data, null, 4)], { type: "application/json" });
                var link = document.createElement("a");
                link.href = URL.createObjectURL(blob);
                link.download = "veiculo_" + matricula + ".json";
                link.click();
            })
            .catch(error => console.error("Erro ao buscar dados:", error));
    }
</script>