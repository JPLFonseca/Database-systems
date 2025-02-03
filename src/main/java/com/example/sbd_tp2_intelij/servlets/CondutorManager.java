package com.example.sbd_tp2_intelij.servlets;

import com.example.sbd_tp2_intelij.database.Manipula;

import java.sql.SQLException;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class CondutorManager {

    public static String[][] levantarVeiculo(Manipula dados,String NIF) throws SQLException {
        String query = "SELECT Matricula, Coordenadas_Recolha, Coordenadas_Entrega, Inicio, Fim, Custo_Final, Desconto " +
                "FROM Aluguer WHERE NIF = '" + NIF + "';";

        ResultSet alugueres = dados.getResultado(query);

        ArrayList<String> allMatriculas = new ArrayList<>();
        ArrayList<String> allRecolhas = new ArrayList<>();
        ArrayList<String> allEntregas = new ArrayList<>();
        ArrayList<String> allInicios = new ArrayList<>();
        ArrayList<String> allFins = new ArrayList<>();
        ArrayList<String> allCustos = new ArrayList<>();
        ArrayList<String> allDescontos = new ArrayList<>();

        while (alugueres.next()) {
            allMatriculas.add(alugueres.getString("Matricula"));
            allRecolhas.add(alugueres.getString("Coordenadas_Recolha"));
            allEntregas.add(alugueres.getString("Coordenadas_Entrega"));
            allInicios.add(alugueres.getString("Inicio"));
            allFins.add(alugueres.getString("Fim"));
            allCustos.add(alugueres.getString("Custo_Final"));
            allDescontos.add(alugueres.getString("Desconto"));
        }

        String[][] array = {
                allMatriculas.toArray(new String[0]),
                allRecolhas.toArray(new String[0]),
                allEntregas.toArray(new String[0]),
                allInicios.toArray(new String[0]),
                allFins.toArray(new String[0]),
                allCustos.toArray(new String[0]),
                allDescontos.toArray(new String[0])
        };

        return array;
}
    public static String[][] entregarVeiculo(Manipula dados,String NIF,String codaN, String codaW) throws SQLException {

        // Vai procurar a última reserva feita com aquele NIF. Limita a 1 valor apenas
        String queryEntrega =
                "SELECT P.Localidade, P.Morada " +
                        "FROM Aluguer A " +
                        "JOIN Parque_Estacionamento P ON A.Coordenadas_Entrega = P.Coordenadas " +
                        "WHERE A.NIF = '" + NIF + "' " +
                        "ORDER BY A.Fim DESC LIMIT 1;";

        ResultSet resultEntrega = dados.getResultado(queryEntrega);
        String localidadeEntrega = null;
        String moradaEntrega = null;

        if (resultEntrega.next()) {
            localidadeEntrega = resultEntrega.getString("Localidade");
            moradaEntrega = resultEntrega.getString("Morada");
        }


        String queryParqueMaisPerto =
                "SELECT P.Localidade, P.Morada, " +
                        "   (6371 * ACOS( " +
                        "       COS(RADIANS(" + codaN + ")) * COS(RADIANS(SUBSTRING_INDEX(P.Coordenadas, ',', 1))) * " +
                        "       COS(RADIANS(SUBSTRING_INDEX(P.Coordenadas, ',', -1)) - RADIANS(" + codaW + ")) + " +
                        "       SIN(RADIANS(" + codaN + ")) * SIN(RADIANS(SUBSTRING_INDEX(P.Coordenadas, ',', 1))) " +
                        "   )) AS Distancia_KM " +
                        "FROM Parque_Estacionamento P " +
                        "ORDER BY Distancia_KM ASC LIMIT 1;";

        ResultSet resultParque = dados.getResultado(queryParqueMaisPerto);
        String localidadeParque = null;
        String moradaParque = null;

        if (resultParque.next()) {
            localidadeParque = resultParque.getString("Localidade");
            moradaParque = resultParque.getString("Morada");
        }


        String[][] array = new String[2][2];  // Agora temos duas colunas: Localidade e Morada
        array[0][0] = (localidadeEntrega != null) ? localidadeEntrega : "-";
        array[0][1] = (moradaEntrega != null) ? moradaEntrega : "-";
        array[1][0] = (localidadeParque != null) ? localidadeParque : "-";
        array[1][1] = (moradaParque != null) ? moradaParque : "-";

        return array;
    }

    public static boolean fazerAvaliacao(Manipula dados, String NIF, String avaliacao, String comentario){
        if (NIF == null || avaliacao == null || comentario == null) {
            System.out.println("Dados inválidos");
            return false;
        }

        try {

            String queryUltimoAluguer = "SELECT ID_Aluguer FROM Aluguer WHERE NIF = '" + NIF + "' ORDER BY Fim DESC LIMIT 1;";
            ResultSet rsUltimoAluguer = dados.getResultado(queryUltimoAluguer);

            if (!rsUltimoAluguer.next()) {
                System.out.println("Nenhum aluguer encontrado para este cliente.");
                return false;
            }

            String idAluguer = rsUltimoAluguer.getString("ID_Aluguer");


            String queryGerarID = "SELECT ID_Avaliacao FROM Avaliacao_Servico ORDER BY ID_Avaliacao DESC LIMIT 1;";
            ResultSet rsNovoID = dados.getResultado(queryGerarID);
            String idAvaliacao = "AV001";

            if (rsNovoID.next()) {
                String lastID = rsNovoID.getString("ID_Avaliacao");
                int numericPart = Integer.parseInt(lastID.substring(2));
                idAvaliacao = String.format("AV%03d", numericPart + 1);
            }


            String queryInsert = "INSERT INTO Avaliacao_Servico (ID_Avaliacao, ID_Aluguer, Opiniao, Avaliacao) "
                    + "VALUES ('" + idAvaliacao + "', '" + idAluguer + "', '" + comentario + "', " + avaliacao + ") "
                    + "AS new ON DUPLICATE KEY UPDATE "
                    + "    Opiniao = new.Opiniao, "
                    + "    Avaliacao = new.Avaliacao;";

            boolean valor = dados.xDirectiva(queryInsert);
            return valor;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
