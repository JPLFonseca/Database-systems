<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cliente - Reserva de Veículo</title>
</head>
<body>
<h1>Cliente</h1>
<h2>Alugar Veiculo</h2>

<form method="post" action="/Cliente?action=reservarVeiculo">
    <!-- Campo para selecionar o tipo de veículo -->
    <label for="tipoVeiculo">Tipo de Veículo:</label>
    <select name="tipoVeiculo" id="tipoVeiculo" required>
        <c:forEach var="tipos" items="${tiposVeiculos}">
            <option value="${tipos.Tipo}">${tipos.Tipo}</option>
        </c:forEach>
    </select>
    <br><br>

    <!-- Campo para selecionar o parque de estacionamento -->
    <label for="parqueLevantamento">Parque de Estacionamento:</label>
    <select name="parqueLevantamento" id="parqueLevantamento" required>
        <c:forEach var="parque" items="${parques}">
            <option value="${parque.coordenadas}">${parque.morada} - ${parque.localidade}</option>
        </c:forEach>
    </select>
    <br><br>

    <!-- Campo para selecionar o período de aluguer -->
    <label for="periodoAluguer">Período de Aluguer:</label>
    <select name="periodoAluguer" id="periodoAluguer" required>
        <option value="1">1 hora</option>
        <option value="2">2 horas</option>
        <option value="3">3 horas</option>

        <option value="24">1 dia</option>
        <option value="48">2 dias</option>
        <option value="72">3 dias</option>

        <option value="336">1 semana</option>
        <option value="336">2 semanas</option>
    </select>
    <br><br>

    <button type="submit">Reservar</button>
</form>
</body>
</html>
