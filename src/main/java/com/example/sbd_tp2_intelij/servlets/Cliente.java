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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Cliente")
public class Cliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection dbConnection = new Connection();
        List<Parque> parques = dbConnection.procuraParques();
        List<tipoVeiculo> tiposVeiculos = dbConnection.procuraVeiculos();

        req.setAttribute("parques",parques);
        req.getRequestDispatcher("/cliente.jsp").forward(req, resp);
        req.setAttribute("tiposVeiculos",tiposVeiculos);
        req.getRequestDispatcher("/cliente.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");

        System.out.println(nome);
        resp.sendRedirect("/SBD_TP2_INTELIJ_war_exploded/Cliente");
    }
}
