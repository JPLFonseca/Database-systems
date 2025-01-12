package com.example.sbd_tp2_intelij.servlets;



import com.example.sbd_tp2_intelij.Connection;
import com.example.sbd_tp2_intelij.Parque;
import com.example.sbd_tp2_intelij.tipoVeiculo;
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

        String txt = "";

        Manipula dados = new Manipula(new Configura());

        if(comando != null) {


        if(comando.equals("A")){ // atualiazar info pessoal

            System.out.println("Numero carta conducao: " + nCartaConducao);

            String[] array = new String[]{nif,nomeCliente,telemovel,morada,prefLinguistica,nCartaConducao,dataEmissao,dataNascimento,dataValidade,reputacao};

            boolean caCliente = AdminManager.gerirClientePessoal(dados,array);

            if(caCliente) {
                txt+="<h1>Operação realizada com sucesso!</h1>";
            }
            else {
                txt+="<h1>Erro a realizar a operação!</h1>";
            }

        } else if(comando.equals("B")){


            String[] array = new String[]{nif,nomeCliente,telemovel,morada,prefLinguistica,nCartaConducao,dataEmissao,dataNascimento,dataValidade,reputacao,nomeCondutor,capSocial};

            boolean caCliente = AdminManager.gerirClienteEmpresarial(dados,array);

            if(caCliente) {
                txt+="<h1>Operação realizada com sucesso!</h1>";
            }
            else {
                txt+="<h1>Erro a realizar a operação!</h1>";
            }
        } else if(comando.equals("C")){

//            String [][] preTable = null;
//            try {preTable = AdminManager.getInfoCarros(dados);} catch (SQLException e) {e.printStackTrace();}
//
//            txt="<h2>Todos os carros</h2><br><table><tr><th>Marca</th><th>Modelo</th><th>Tipo</th></tr>";
//
//            for (int coluna = 0; coluna < preTable[0].length; coluna++) { // Iterate over rows
//                txt += "<tr>";
//                for (int linha = 0; linha < preTable.length; linha++) { // Iterate over columns
//                    String dado = preTable[linha][coluna];
//                    System.out.println(dado);
//                    if (dado == null || dado.equals("")) {
//                        txt+="<td>-</td>"; // Handle null or empty values
//                    } else {
//                        txt+="<td>"+dado+"</td>";
//                    }
//                }
//                txt+="</tr>";
//            }
//            txt+="</table>";
//
        }
            System.out.println(txt);
            dados.desligar();

            resp.getWriter().write(txt);

        }else{

            req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
        }


    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String query = req.getParameter("query");
//        Connection dbConnection = new Connection();
//        boolean result = dbConnection.executaQueryBool(query);
//
//
//        if (result) {
//
//            resp.getWriter().write("Executou com sucesso");
//            req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
//        } else {
//
//            resp.getWriter().write("Erro");
//            req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
//        }
//    }
}
