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
import java.util.List;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;
@WebServlet("/Gerente")
public class Gerente extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String comando = req.getParameter("comando");
        String txt = "";

        Manipula dados = new Manipula(new Configura());

        if (comando != null) {


            if (comando.equals("hist")) { // historico de um veículo, avaliações e intervenções


            } else if (comando.equals("mLucro")) { // 3 marcas de veículos que geraram menos lucro


            } else if (comando.equals("mAvaliacao")) { // 5 modelos de veículos com melhor avaliação na semana passada


            } else if (comando.equals("mKm")) { // 10 veículos que percorreram menos km no trimestre passado
                
            } else if (comando.equals("clientes")) { // 100 clientes ordenados por reputação

            }
        } else {

                req.getRequestDispatcher("/gerente.jsp").forward(req, resp);
            }


        }
    }
