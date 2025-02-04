package com.example.sbd_tp2_intelij.servlets;

import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class AdminManager {

    public static boolean gerirClientePessoal(Manipula dados, String[] array) { // Dados vai ser do tipo Manipula dados
        // e gerirCliente vai retornar um boolean

        if (array == null || array.length < 10) {
            System.out.println("Dados inválidos");
            return false;
        }

        // a verificacao se o NIF esta completo tem de ser feita na parte do jsp

        String pQuery = "INSERT IGNORE Pessoa (NIF) VALUES ('" + array[0] + "')";

        boolean k = dados.xDirectiva(pQuery);


        String query = "INSERT INTO Cliente (NIF, Nome, Contacto, Morada, Preferencias_Linguisticas)"
                + "VALUES('" + array[0] + "','" + array[1]+ "','" + array[2]+ "' ,'" + array[3]+ "','" + array[4]+ "')"
                + "AS new ON DUPLICATE KEY UPDATE Nome = new.Nome, Contacto = new.Contacto,"
                + "    Morada = new.Morada,"
                + "    Preferencias_Linguisticas = new.Preferencias_Linguisticas;";
        boolean altera_dados = dados.xDirectiva(query);



        String nQuery = "INSERT INTO Condutor(Numero_Carta,Nome,Data_Emissao_Carta,Data_Nascimento,Validade_Carta,Reputacao,NIF)"
                + "VALUES('"+ array[5] + "','"+ array[1] + "','"+ array[6] + "','"+ array[7] + "','"
                + array[8] + "','"+ array[9] + "','"+ array[0] + "') "
                + "AS new ON DUPLICATE KEY UPDATE"
                + "    Nome = new.Nome,"
                + "    Data_Emissao_Carta = new.Data_Emissao_Carta,"
                + "    Data_Nascimento = new.Data_Nascimento,"
                + "    Validade_Carta = new.Validade_Carta,"
                + "    Reputacao = new.Reputacao,"
                + "    NIF = new.NIF;";

        boolean altera_dados_2 = dados.xDirectiva(nQuery);



        return (altera_dados && altera_dados_2);

    }




    public static boolean gerirClienteEmpresarial(Manipula dados, String[] array) {

        if (array == null || array.length < 11) {
            System.out.println("Dados inválidos");
            return false;
        }

        String pQuery = "INSERT IGNORE Pessoa (NIF) VALUES ('" + array[0] + "')";

        boolean k = dados.xDirectiva(pQuery);

        String query = "INSERT INTO Cliente (NIF, Nome, Contacto, Morada, Preferencias_Linguisticas)"
                + "VALUES('" + array[0] + "','" + array[1]+ "','" + array[2]+ "','" + array[3]+ "','" + array[4]+ "')"
                + "AS new ON DUPLICATE KEY UPDATE Nome = new.Nome, Contacto = new.Contacto,"
                + "    Morada = new.Morada,"
                + "    Preferencias_Linguisticas = new.Preferencias_Linguisticas;";
        boolean altera_dados = dados.xDirectiva(query);


        String notherQuery = "INSERT INTO Empresa (NIF,Capital_Social) VALUES('" + array[0] + "','"+ array[11]+ "') AS new ON DUPLICATE KEY UPDATE" +
                " Capital_Social = new.Capital_Social;";

        boolean altera_dados_2 = dados.xDirectiva(notherQuery);


        String nQuery = "INSERT INTO Condutor(Numero_Carta,Nome,Data_Emissao_Carta,Data_Nascimento,Validade_Carta,Reputacao,NIF)"
                + "VALUES('"+ array[5] + "','"+ array[10] + "','"+ array[6] + "','"+ array[7] + "','"
                + array[8] + "','"+ array[9] + "','"+ array[0] + "') "
                + "AS new ON DUPLICATE KEY UPDATE"
                + "    Nome = new.Nome,"
                + "    Data_Emissao_Carta = new.Data_Emissao_Carta,"
                + "    Data_Nascimento = new.Data_Nascimento,"
                + "    Validade_Carta = new.Validade_Carta,"
                + "    Reputacao = new.Reputacao,"
                + "    NIF = new.NIF;";

        boolean altera_dados_3 = dados.xDirectiva(nQuery);

        return (altera_dados && altera_dados_2 && altera_dados_3);
    }

    public static String[][] getInfoCarros(Manipula dados) throws SQLException {

        String query = "SELECT Matricula,Marca,Modelo,Tipo FROM Veiculo;";

        ResultSet carros = dados.getResultado(query);

        ArrayList<String> allMatriculas = new ArrayList<>();
        ArrayList<String> allMarcas = new ArrayList<>();
        ArrayList<String> allModelos = new ArrayList<>();
        ArrayList<String> allTipos = new ArrayList<>();

        while(carros.next()) {
            allMatriculas.add(carros.getString("Matricula"));
            allMarcas.add(carros.getString("Marca"));
            allModelos.add(carros.getString("Modelo"));
            allTipos.add(carros.getString("Tipo"));
        }
        String[][] array = {allMatriculas.toArray((allMarcas.toArray(new String[0]))),allMarcas.toArray(new String[0]),allModelos.toArray(new String[0]),allTipos.toArray(new String[0])};

        return array;
    }


    public static ResultSet getDataExport(Manipula dados,String query) throws SQLException {

        return dados.getResultado(query);
    }
}
