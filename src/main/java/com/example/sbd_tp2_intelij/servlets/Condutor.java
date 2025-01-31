package com.example.sbd_tp2_intelij.servlets;



import com.example.sbd_tp2_intelij.Connection;
import com.example.sbd_tp2_intelij.Parque;
import com.example.sbd_tp2_intelij.tipoVeiculo;
import com.example.sbd_tp2_intelij.servlets.CondutorManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;


@WebServlet("/Condutor")
public class Condutor extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comando = req.getParameter("comando");
        String nif = req.getParameter("nif");
        String codaN = req.getParameter("codaN");
        String codaW = req.getParameter("codaW");
        String avaliacao = req.getParameter("avaliacao");
        String comentario = req.getParameter("comentario");

        String txt = "";

        Manipula dados = new Manipula(new Configura());

        if(comando != null) {


            if(comando.equals("L")){ // Levantar veículo

                String[][] preTable = null;
                try {preTable = CondutorManager.levantarVeiculo(dados,nif);} catch (SQLException e) {e.printStackTrace();}


                txt = "<h2>Informações do Aluguer</h2><br><table>" +
                        "<tr><th>Matricula</th><th>Coordenadas de Recolha</th><th>Coordenadas de Entrega</th>" +
                        "<th>Início</th><th>Fim</th><th>Custo Final</th><th>Desconto</th></tr>";

                if (preTable != null && preTable.length > 0 && preTable[0].length > 0) {
                    txt += "<style>"
                            + "table{border-collapse: collapse; width: 100%;}"
                            + "th,td{border: 1px solid black; padding: 8px; text-align: center;}"
                            + "th{ background-color: #f2f2f2;}"
                            + "</style>";

                    for (int coluna = 0; coluna < preTable[0].length; coluna++) { // Iterate over rows
                        txt += "<tr>";
                        for (int linha = 0; linha < preTable.length; linha++) { // Iterate over columns
                            String dado = preTable[linha][coluna];
                            System.out.println(dado);
                            if (dado == null || dado.equals("")) {
                                txt += "<td>-</td>"; // Handle null or empty values
                            } else {
                                txt += "<td>" + dado + "</td>";
                            }
                        }
                        txt += "</tr>";
                    }

                    txt += "</table>";
                } else {
                    txt = "<h2>Informações do Aluguer</h2><br><div>Nenhum aluguer encontrado para este cliente.</div>";
                }

            } else if(comando.equals("E")){
                String[][] preTable = null;
                try {
                    preTable = CondutorManager.entregarVeiculo(dados, nif, codaN, codaW);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                txt = "<h2>Entrega do Veículo</h2><br>" +
                        "<table style='border-collapse: collapse; width: 50%; text-align: center;'>" +
                        "<tr><th style='border: 1px solid black; padding: 8px;'>Coordenadas de Entrega</th>" +
                        "<th style='border: 1px solid black; padding: 8px;'>Parque Mais Próximo</th></tr>";

                if (preTable != null && preTable.length > 0 && preTable[0].length > 0) {
                    for (int i = 0; i < preTable[0].length; i++) {
                        txt += "<tr>";
                        for (int j = 0; j < preTable.length; j++) {
                            String dado = preTable[j][i];
                            System.out.println(dado);
                            txt += "<td style='border: 1px solid black; padding: 8px;'>" +
                                    (dado == null || dado.equals("") ? "-" : dado) +
                                    "</td>";
                        }
                        txt += "</tr>";
                    }
                } else {
                    txt += "<tr><td colspan='2' style='border: 1px solid black; padding: 8px;'>Nenhum dado disponível</td></tr>";
                }

                txt += "</table>";

            } else if(comando.equals("Av")){
                boolean valor = CondutorManager.fazerAvaliacao(dados,nif,avaliacao,comentario);

                if(valor){
                    txt = "Avaliação registada com sucesso!";
                } else{
                    txt = "Ups! Ocorreu um erro a registar a sua avaliação";
                }
            }
            System.out.println(txt);
            dados.desligar();

            resp.getWriter().write(txt);
        } else{

            req.getRequestDispatcher("/condutor.jsp").forward(req, resp);
        }


    }}