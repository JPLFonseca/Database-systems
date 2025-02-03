package com.example.sbd_tp2_intelij.servlets;

import com.example.sbd_tp2_intelij.Connection;
import com.example.sbd_tp2_intelij.Parque;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;
import com.example.sbd_tp2_intelij.tipoVeiculo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Cliente")
public class Cliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comandoPedir = req.getParameter("comandoPedir");
        String comando = req.getParameter("comando");
        String nif = req.getParameter("nif");

        Manipula dados = new Manipula(new Configura());

        if(comandoPedir.equals("getStuff")) {
            String txt_html = "";

            String[][] preTable = null;
            try {
                preTable = ClienteManager.getInfoAluguer(dados);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            txt_html += "<form action='ProcessarAluguer' method='post'>";


            txt_html += "<label for='tipoVeiculo'>Escolha o tipo de veículo:</label>";
            txt_html += "<select id='tipoVeiculo' name='tipoVeiculo'>";
            if (preTable[0].length > 0) {
                for (String tipo : preTable[0]) {
                    txt_html += "<option value='" + tipo + "'>" + tipo + "</option>";
                }
            } else {
                txt_html += "<option value='' disabled>Sem veículos disponíveis</option>";
            }
            txt_html += "</select><br><br>";


            txt_html += "<label for='parque'>Escolha um parque de estacionamento:</label>";
            txt_html += "<select id='parque' name='parque'>";
            if (preTable[1].length > 0) {
                for (int i = 0; i < preTable[1].length; i++) {
                    String localidade = preTable[1][i];
                    String morada = preTable[2][i];
                    txt_html += "<option value='" + localidade + " - " + morada + "'>" + localidade + " - " + morada + "</option>";
                }
            } else {
                txt_html += "<option value='' disabled>Sem parques disponíveis</option>";
            }
            txt_html += "</select><br><br>";


            txt_html += "<label for='dataInicio'>Data de Início do Aluguer:</label>";
            txt_html += "<input type='date' id='dataInicio' name='dataInicio' required><br><br>";


            txt_html += "<label for='dataFim'>Data de Devolução:</label>";
            txt_html += "<input type='date' id='dataFim' name='dataFim' required><br><br>";

            txt_html += "<button formnovalidate=\"formnovalidate\" id=\"Executar\" onclick=\"document.getElementById('Comando').value = \" fazerAluguer \"; getResultados()\">Executar</button>\n";
            txt_html += "</form>";


            resp.getWriter().write(txt_html);
        } else if(comando.equals("fazerAluguer")) {

        }



    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String nome = req.getParameter("nome");
//
//        System.out.println(nome);
//        resp.sendRedirect("/SBD_TP2_INTELIJ_war_exploded/Cliente");
//    }
}
