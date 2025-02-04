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




                txt = "<h2>Informações do Aluguer</h2><br>" +
                        "<table style='border-collapse: collapse; width: 100%; text-align: center;'>" +
                        "<tr>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Matrícula</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Local de Recolha</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Local de Entrega</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Início</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Fim</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Custo Final</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Desconto</th>" +
                        "</tr>";

                if (preTable != null && preTable.length > 0 && preTable[0].length > 0) {
                    for (int coluna = 0; coluna < preTable[0].length; coluna++) {
                        txt += "<tr>";

                        // Matrícula
                        txt += "<td style='border: 1px solid black; padding: 8px;'>" +
                                (preTable[0][coluna] == null || preTable[0][coluna].equals("") ? "-" : preTable[0][coluna]) +
                                "</td>";

                        // Local de Recolha Localidade + Morada
                        String localidadeRecolha = preTable[1][coluna] != null ? preTable[1][coluna] : "-";
                        String moradaRecolha = preTable[2][coluna] != null ? preTable[2][coluna] : "-";
                        txt += "<td style='border: 1px solid black; padding: 8px;'>" + localidadeRecolha + "<br><small>" + moradaRecolha + "</small></td>";

                        // Local de Entrega Localidade + Morada
                        String localidadeEntrega = preTable[3][coluna] != null ? preTable[3][coluna] : "-";
                        String moradaEntrega = preTable[4][coluna] != null ? preTable[4][coluna] : "-";
                        txt += "<td style='border: 1px solid black; padding: 8px;'>" + localidadeEntrega + "<br><small>" + moradaEntrega + "</small></td>";

                        for (int linha = 5; linha < preTable.length; linha++) {
                            String dado = preTable[linha][coluna];


                            if (linha == preTable.length - 1) {

                                try {
                                    double desconto = Double.parseDouble(dado);
                                    dado = String.valueOf(desconto * 10) + "%";
                                } catch (NumberFormatException e) {

                                }
                            }

                            txt += "<td style='border: 1px solid black; padding: 8px;'>" +
                                    (dado == null || dado.equals("") ? "-" : dado) +
                                    "</td>";
                        }
                        txt += "</tr>";
                    }
                } else {
                    txt += "<tr><td colspan='7' style='border: 1px solid black; padding: 8px;'>Nenhum dado disponível</td></tr>";
                }

                txt += "</table>";

            } else if(comando.equals("E")){String[][] preTable = null;
                try {
                    preTable = CondutorManager.entregarVeiculo(dados, nif, codaN, codaW);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                txt = "<h2>Entrega do Veículo</h2><br>" +
                        "<table style='border-collapse: collapse; width: 60%; text-align: center;'>" +
                        "<tr>" +
                        "   <th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Local de Entrega Previsto</th>" +
                        "   <th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Local de Entrega mais Próximo</th>" +
                        "</tr>";


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
                    txt += "<tr><td colspan='4' style='border: 1px solid black; padding: 8px;'>Nenhum dado disponível</td></tr>";
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