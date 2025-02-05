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
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;
import org.json.JSONArray;
import org.json.JSONObject;


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
        String matricula = req.getParameter("matricula");
        String matriculaV = req.getParameter("matriculaV");
        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        String tipo = req.getParameter("tipo");
        String cor = req.getParameter("cor");

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

            String [][] preTable = null;
            try {preTable = AdminManager.getInfoCarros(dados);} catch (SQLException e) {e.printStackTrace();}

            txt="<h2>Todos os carros</h2><br><table><tr><th>Matricula</th><th>Marca</th><th>Modelo</th><th>Tipo</th></tr>";

            for (int coluna = 0; coluna < preTable[0].length; coluna++) {
                txt += "<tr>";
                for (int linha = 0; linha < preTable.length; linha++) {
                    String dado = preTable[linha][coluna];
                    System.out.println(dado);
                    if (dado == null || dado.equals("")) { // verifica se é null ou não
                        txt+="<td>-</td>";
                    } else {
                        txt+="<td>"+dado+"</td>";
                    }
                }
                txt+="</tr>";
            }
            txt+="</table>";

        } else if(comando.equals("Exportar")){

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();

            if (matricula == null || matricula.isEmpty()) {
                out.print("{\"erro\": \"Matrícula não fornecida\"}");
                return;
            }




            try {

                String queryVeiculo = "SELECT Matricula, Marca, Modelo, Cor, Tipo FROM Veiculo WHERE Matricula = '" + matricula + "';";
                ResultSet resultVeiculo = AdminManager.getDataExport(dados, queryVeiculo);

                if (resultVeiculo.next()) {

                    JSONObject veiculoJSON = new JSONObject();
                    veiculoJSON.put("Matricula", resultVeiculo.getString("Matricula"));
                    veiculoJSON.put("Marca", resultVeiculo.getString("Marca"));
                    veiculoJSON.put("Modelo", resultVeiculo.getString("Modelo"));
                    veiculoJSON.put("Cor", resultVeiculo.getString("Cor"));
                    veiculoJSON.put("Tipo", resultVeiculo.getString("Tipo"));


                    String queryManutencao = "SELECT Data_Intervencao, Km_Atual, Descricao FROM Manutencao WHERE Matricula = '" + matricula + "';";
                    ResultSet resultManutencao = AdminManager.getDataExport(dados, queryManutencao);


                    JSONArray manutencaoArray = new JSONArray();

                    while (resultManutencao.next()) {
                        JSONObject manutencaoJSON = new JSONObject();
                        manutencaoJSON.put("Data", resultManutencao.getString("Data_Intervencao"));
                        manutencaoJSON.put("Km_Atual", resultManutencao.getString("Km_Atual"));
                        manutencaoJSON.put("Descricao", resultManutencao.getString("Descricao"));
                        manutencaoArray.put(manutencaoJSON);
                    }


                    veiculoJSON.put("Historico_Manutencao", manutencaoArray);
                    out.print(veiculoJSON.toString());
                } else {
                    out.print("{\"erro\": \"Veículo não encontrado\"}");
                }

                dados.desligar();
            } catch (SQLException e) {
                e.printStackTrace();
                out.print("{\"erro\": \"Erro a encontrar os dados\"}");
            }
        } else if(comando.equals("D")){

            boolean action = AdminManager.gerirVeiculo(dados,matriculaV,marca,modelo,tipo,cor);

            if(action){
                txt+="<h1>Operação realizada com sucesso!</h1>";
            }else{
                txt+="<h1>Ocorreu um erro a realizar a operação</h1>";
            }
        }
            System.out.println(txt);
            dados.desligar();

            resp.getWriter().write(txt);

        }else{

            req.getRequestDispatcher("/administrador.jsp").forward(req, resp);
        }


    }


}
