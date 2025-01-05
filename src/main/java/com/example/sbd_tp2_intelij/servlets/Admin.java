package com.example.sbd_tp2_intelij.servlets;



import com.example.sbd_tp2_intelij.Connection;
import com.example.sbd_tp2_intelij.Parque;
import com.example.sbd_tp2_intelij.tipoVeiculo;
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

@WebServlet("/Admin")
public class Admin extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comando = req.getParameter("comando");
        String nomeCliente = req.getParameter("nomeCliente");
        String nomeCondutor = req.getParameter("nomeCondutor");
        String morada = req.getParameter("morada");
        String nif = req.getParameter("nif");
        String prefLinguistica = req.getParameter("prefLinguistica");
        String capSocial = req.getParameter("capSocial");
        String telemovel = req.getParameter("telemovel");
        String nCartaConducao = req.getParameter("nCartaConducao");
        String dataEmissao = req.getParameter("dataEmissao");
        String dataValidade = req.getParameter("dataValidade");
        String dataNascimento = req.getParameter("dataNascimento");
        String reputacao = req.getParameter("reputacao");


        req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String query = req.getParameter("query");
        Connection dbConnection = new Connection();
        boolean result = dbConnection.executaQueryBool(query);


        if (result) {

            resp.getWriter().write("Executou com sucesso");
            req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
        } else {

            resp.getWriter().write("Erro");
            req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
        }
    }
}
