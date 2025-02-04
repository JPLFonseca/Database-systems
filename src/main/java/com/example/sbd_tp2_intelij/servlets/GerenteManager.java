package com.example.sbd_tp2_intelij.servlets;


import com.example.sbd_tp2_intelij.database.Manipula;

import java.sql.SQLException;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

public class GerenteManager {

    public static String[][] historicoVeiculo(Manipula dados, String matricula) throws SQLException {
        String query = "SELECT DISTINCT A.Avaliacao, A.Opiniao, M.Data_Intervencao, M.Km_Atual, M.Descricao " +
                "FROM Avaliacao_Servico AS A " +
                "JOIN Aluguer AS AL ON A.ID_Aluguer = AL.ID_Aluguer " +
                "JOIN Manutencao AS M ON AL.Matricula = M.Matricula " +
                "WHERE AL.Matricula = '" + matricula + "' " +
                "ORDER BY M.Data_Intervencao DESC;";

        ResultSet historico = dados.getResultado(query);

        ArrayList<String> allAvaliacoes = new ArrayList<>();
        ArrayList<String> allComentarios = new ArrayList<>();
        ArrayList<String> allDatas = new ArrayList<>();
        ArrayList<String> allKm = new ArrayList<>();
        ArrayList<String> allDescricoes = new ArrayList<>();

        while (historico.next()) {
            String avaliacao = historico.getString("Avaliacao");
            String comentario = historico.getString("Opiniao");
            String data = historico.getString("Data_Intervencao");
            String km = historico.getString("Km_Atual");
            String descricao = historico.getString("Descricao");

            // Ensure uniqueness before adding to lists
            if (!allAvaliacoes.contains(avaliacao) || !allDatas.contains(data)) {
                allAvaliacoes.add(avaliacao);
                allComentarios.add(comentario);
                allDatas.add(data);
                allKm.add(km);
                allDescricoes.add(descricao);
            }
        }

        String[][] array = {
                allAvaliacoes.toArray(new String[0]),
                allComentarios.toArray(new String[0]),
                allDatas.toArray(new String[0]),
                allKm.toArray(new String[0]),
                allDescricoes.toArray(new String[0])
        };

        return array;
    }
}
