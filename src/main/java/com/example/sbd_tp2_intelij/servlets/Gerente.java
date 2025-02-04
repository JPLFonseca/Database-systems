package com.example.sbd_tp2_intelij.servlets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;
@WebServlet("/Gerente")
public class Gerente extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comando = req.getParameter("comando");
        String matricula = req.getParameter("matricula");
        String txt = "";

        Manipula dados = new Manipula(new Configura());

        if (comando != null) {


            if (comando.equals("hist")) { // historico de um veículo, avaliações e intervenções

                String[][] preTable = null;
                try {preTable = GerenteManager.historicoVeiculo(dados,matricula);} catch (SQLException e) {e.printStackTrace();}


                txt = "<h2>Histórico do Veículo</h2><br>";


                txt += "<h3>Avaliações do Veículo</h3>" +
                        "<table style='border-collapse: collapse; width: 30%; text-align: center;'>" +
                        "<tr>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Avaliação</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Comentário</th>" +
                        "</tr>";

                boolean hasReviews = false;
                if (preTable != null && preTable.length > 0 && preTable[0].length > 0) {
                    HashSet<String> seenReviews = new HashSet<>();

                    for (int coluna = 0; coluna < preTable[0].length; coluna++) {
                        String reviewData = preTable[0][coluna] + preTable[1][coluna];

                        if (!seenReviews.contains(reviewData)) {
                            seenReviews.add(reviewData);
                            hasReviews = true;

                            txt += "<tr>";
                            txt += "<td style='border: 1px solid black; padding: 8px;'>" + preTable[0][coluna] + "</td>"; // Avaliação
                            txt += "<td style='border: 1px solid black; padding: 8px;'>" + preTable[1][coluna] + "</td>"; // Comentário
                            txt += "</tr>";
                        }
                    }
                }
                if (!hasReviews) {
                    txt += "<tr><td colspan='2' style='border: 1px solid black; padding: 8px;'>Nenhuma avaliação disponível</td></tr>";
                }
                txt += "</table><br>";


                txt += "<h3>Histórico de Manutenção</h3>" +
                        "<table style='border-collapse: collapse; width: 100%; text-align: center;'>" +
                        "<tr>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Data Manutenção</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Km Manutenção</th>" +
                        "<th style='border: 1px solid black; padding: 8px; background-color: #f2f2f2;'>Descrição Manutenção</th>" +
                        "</tr>";

                boolean hasMaintenance = false;
                if (preTable != null && preTable.length > 0 && preTable[2].length > 0) {
                    for (int coluna = 0; coluna < preTable[2].length; coluna++) {
                        hasMaintenance = true;
                        txt += "<tr>";
                        txt += "<td style='border: 1px solid black; padding: 8px;'>" + preTable[2][coluna] + "</td>"; // Data Manutenção
                        txt += "<td style='border: 1px solid black; padding: 8px;'>" + preTable[3][coluna] + " km</td>"; // Km Manutenção
                        txt += "<td style='border: 1px solid black; padding: 8px;'>" + preTable[4][coluna] + "</td>"; // Descrição Manutenção
                        txt += "</tr>";
                    }
                }
                if (!hasMaintenance) {
                    txt += "<tr><td colspan='3' style='border: 1px solid black; padding: 8px;'>Nenhum histórico de manutenção disponível</td></tr>";
                }
                txt += "</table>";
            } else if (comando.equals("mLucro")) { // 3 marcas de veículos que geraram menos lucro


            } else if (comando.equals("mAvaliacao")) { // 5 modelos de veículos com melhor avaliação na semana passada


            } else if (comando.equals("mKm")) { // 10 veículos que percorreram menos km no trimestre passado
                
            } else if (comando.equals("clientes")) { // 100 clientes ordenados por reputação

            }

            System.out.println(txt);
            dados.desligar();

            resp.getWriter().write(txt);
        } else {

                req.getRequestDispatcher("/gerente.jsp").forward(req, resp);
            }


        }
    }
