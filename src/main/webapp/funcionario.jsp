<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Modo Funcionário</title>
</head>
<body>
<h2>Funcionário</h2>
<div class="Operacoes">
  <label for="Operacao">Escolha a operação que pretende realizar: </label>
  <select id="Operacao" onchange="atualizarPagina()">
    <option value="lc">Localizar Veículo</option>
    <option value="ri">Registar intervenção</option>
    <option value="edc">Encontrar dados do cliente</option>
    <option value="ac">Avaliar cliente</option>
    <option value="icd">Identificar condutor pela data</option>
  </select>
  <button formnovalidate="formnovalidate" id="Executar" onclick="document.getElementById('Comando').value = document.getElementById('Operacao').value; getResultados()">Executar</button>
</div>

<div class="atualizar">
  <h2>Funcionário</h2>
  <form action="Funcionario" method="post">

    <label for="nif">NIF:</label>
    <input type="text" id="nif" pattern="\d{9}" maxlength="9" ><br><br>

    <label for="nomeCliente">Nome do cliente:</label>
    <input type="text" id="nomeCliente" ><br><br>

    <label for="reputacao">Reputacao:</label>
    <input type="text" id="reputacao" maxlength="3" placeholder="Valor entre 0 e 100"><br><br>

    <label for="desconto">Desconto:</label>
    <input type="text" id="desconto" maxlength="3" placeholder="Valor entre 0 e 100"><br><br>

    <label for="matricula">Matricula:</label>
    <input type="text" id="matricula" ><br><br>

    <label for="data">Data:</label>
    <input type="date" id="data" ><br><br>

    <label for="descricao">Descrição da intervenção:</label>
    <input type="text" id="descricao" ><br><br>

    <input type="submit" id="Comando" value="Atualizar Cliente">
  </form>
</div>


<div id="resultados"></div>
<div id="listaCarros"></div>
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


    if (operacao === "lc") {
      document.getElementById("matricula").disabled = false;

    } else if (operacao === "ri") {
      document.getElementById("matricula").disabled = false;
      document.getElementById("descricao").disabled = false;

    } else if (operacao === "edc") {
      document.getElementById("nomeCliente").disabled = false;

    }else if (operacao === "ac") {
      document.getElementById("NIF").disabled = false;
      document.getElementById("reputacao").disabled = false;
      document.getElementById("desconto").disabled = false;
    }else if (operacao === "icd") {
      document.getElementById("Matriula").disabled = false;
      document.getElementById("data").disabled = false;
    }


  }

  function getResultados() {

    var comando = document.getElementById("Comando").value;
    var nomeCliente = document.getElementById("nomeCliente").value;
    var nif = document.getElementById("nif").value;
    var reputacao = document.getElementById("reputacao").value;
    var desconto = document.getElementById("desconto").value;
    var matricula = document.getElementById("matricula").value;
    var data = document.getElementById("data").value;
    var descricao = document.getElementById("descricao").value;

    var xhr = new XMLHttpRequest();


    xhr.open("GET", "Funcionario?comando=" + encodeURIComponent(comando) + "&nomeCliente=" + encodeURIComponent(nomeCliente) + "&nif=" + encodeURIComponent(nif) + "&reputacao=" + encodeURIComponent(reputacao)+ "&desconto=" + encodeURIComponent(desconto)+ "&matricula=" + encodeURIComponent(matricula)+ "&data=" + encodeURIComponent(data)+ "&descricao=" + encodeURIComponent(descricao), true);

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200) {
        document.getElementById('resultados').innerHTML = xhr.responseText;
      }
    };
    xhr.send();
  }


</script>