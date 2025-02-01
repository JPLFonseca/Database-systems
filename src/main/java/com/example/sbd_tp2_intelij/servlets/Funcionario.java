package com.example.sbd_tp2_intelij.servlets;
import com.example.sbd_tp2_intelij.Connection;
import com.example.sbd_tp2_intelij.servlets.AdminManager;
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

@WebServlet("/Funcionario")
public class Funcionario extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comando = req.getParameter("comando");
        String nomeCliente = req.getParameter("nomeCliente");
        String nif = req.getParameter("nif");
        String reputacao = req.getParameter("reputacao");
        String matricula = req.getParameter("matricula");
        String data = req.getParameter("data");
        String descricao = req.getParameter("descricao");
        String km = req.getParameter("km");
        String desconto = req.getParameter("desconto");

        String txt = "";

        Manipula dados = new Manipula(new Configura());

        if (comando != null) {


            if (comando.equals("lc")) { // localizar veículo
                String [][] preTable = null;
                try {preTable = FuncionarioManager.locCarro(dados,matricula);} catch (SQLException e) {e.printStackTrace();}

                txt = "<h2 style='text-align: center;'>Localização do Veículo</h2><br>" +
                        "<table style='width: 80%; margin: auto; border-collapse: collapse; text-align: center; font-family: Arial, sans-serif;'>" +
                        "<tr style='background-color: #f2f2f2;'>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Nome do Condutor</th>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Telemóvel</th>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Data de Devolução</th>" +
                        "</tr>";

                if (preTable != null && preTable.length > 0 && preTable[0].length > 0) {
                    for (int coluna = 0; coluna < preTable[0].length; coluna++) {
                        txt += "<tr>";
                        for (int linha = 0; linha < preTable.length; linha++) {
                            String dado = preTable[linha][coluna];
                            System.out.println(dado);
                            txt += "<td style='border: 1px solid black; padding: 10px;'>" +
                                    (dado == null || dado.equals("") ? "-" : dado) +
                                    "</td>";
                        }
                        txt += "</tr>";
                    }
                } else {
                    txt += "<tr><td colspan='3' style='border: 1px solid black; padding: 10px; text-align: center;'>Nenhuma informação disponível para este veículo.</td></tr>";
                }

                txt += "</table>";


            } else if (comando.equals("ri")) { // registar intervencao

                boolean action = FuncionarioManager.postIntervencao(dados,matricula,data,km,descricao);

                if(action) {
                    txt = "Intervenção registada com sucesso!";
                }else{
                    txt = "Ocorreu um erro a registar intervenção";
                }

            } else if (comando.equals("edc")) { // encontrar dados do cliente

                String [][] preTable = null;
                try {preTable = FuncionarioManager.getCliente(dados,nomeCliente);} catch (SQLException e) {e.printStackTrace();}
                txt = "<h2 style='text-align: center;'>Dados do Cliente</h2><br>" +
                        "<table style='width: 80%; margin: auto; border-collapse: collapse; text-align: center; font-family: Arial, sans-serif;'>" +
                        "<tr style='background-color: #f2f2f2;'>" +
                        "<th style='border: 1px solid black; padding: 10px;'>NIF</th>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Nome</th>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Telemóvel</th>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Morada</th>" +
                        "<th style='border: 1px solid black; padding: 10px;'>Preferências Linguísticas</th>" +
                        "</tr>";

                if (preTable != null && preTable.length > 0 && preTable[0].length > 0) {
                    for (int coluna = 0; coluna < preTable[0].length; coluna++) { // Iterate over rows
                        txt += "<tr>";
                        for (int linha = 0; linha < preTable.length; linha++) { // Iterate over columns
                            String dado = preTable[linha][coluna];
                            System.out.println(dado);
                            txt += "<td style='border: 1px solid black; padding: 10px;'>" +
                                    (dado == null || dado.equals("") ? "-" : dado) +
                                    "</td>";
                        }
                        txt += "</tr>";
                    }
                } else {
                    txt += "<tr><td colspan='5' style='border: 1px solid black; padding: 10px; text-align: center;'>Nenhum dado disponível para este cliente.</td></tr>";
                }

                txt += "</table>";



            }else if (comando.equals("ac")) { // avaliar condutor

            boolean action = FuncionarioManager.avaliaCondutor(dados,nif,reputacao,desconto);

            if(action) {
                txt = "Registo feito com sucesso!";
            }else{
                txt = "Ups! Ocorreu um erro a fazer o registo";
            }


            }else if (comando.equals("icd")) { // identificar condutor pela data

                try {txt = FuncionarioManager.getCondutorCarro(dados,matricula,data);} catch (SQLException e) {e.printStackTrace();}


            }
            System.out.println(txt);
            dados.desligar();

            resp.getWriter().write(txt);

        } else {

            req.getRequestDispatcher("/funcionario.jsp").forward(req, resp);
        }


    }
}