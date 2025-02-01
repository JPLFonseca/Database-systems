package com.example.sbd_tp2_intelij.servlets;


import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class FuncionarioManager {

    public static String[][] locCarro(Manipula dados, String matricula) throws SQLException{

        String query = "SELECT C.Nome, C.Contacto, A.Fim " +
                "FROM Aluguer A " +
                "INNER JOIN Cliente C ON A.NIF = C.NIF " +
                "WHERE A.Matricula = '" + matricula + "' " +
                "ORDER BY A.Fim DESC LIMIT 1;";

        ResultSet aluguerInfo = dados.getResultado(query);


        ArrayList<String> allNomes = new ArrayList<>();
        ArrayList<String> allTelefones = new ArrayList<>();
        ArrayList<String> allFim = new ArrayList<>();

        while (aluguerInfo.next()) {
            allNomes.add(aluguerInfo.getString("Nome"));
            allTelefones.add(aluguerInfo.getString("Contacto"));
            allFim.add(aluguerInfo.getString("Fim"));
        }

        String[][] array = {
                allNomes.toArray(new String[0]),
                allTelefones.toArray(new String[0]),
                allFim.toArray(new String[0])
        };

        return array;
    }

    public static boolean postIntervencao(Manipula dados, String matricula,String data, String km, String descricao){
        String query = "INSERT INTO Manutencao (Matricula, Data_Intervencao, Km_Atual, Descricao) " +
                "VALUES ('" + matricula + "', '" + data + "', " + km + ", '" + descricao + "')";

        boolean value = dados.xDirectiva(query);

        return value;
    }

    public static String[][] getCliente(Manipula dados, String nome) throws SQLException{
        String query = "SELECT NIF, Nome, Contacto, Morada, Preferencias_Linguisticas " +
                "FROM Cliente WHERE Nome = '" + nome + "';";

        ResultSet clienteInfo = dados.getResultado(query);


        ArrayList<String> allNIFs = new ArrayList<>();
        ArrayList<String> allNomes = new ArrayList<>();
        ArrayList<String> allTelefones = new ArrayList<>();
        ArrayList<String> allMoradas = new ArrayList<>();
        ArrayList<String> allLinguas = new ArrayList<>();

        while (clienteInfo.next()) {
            allNIFs.add(clienteInfo.getString("NIF"));
            allNomes.add(clienteInfo.getString("Nome"));
            allTelefones.add(clienteInfo.getString("Contacto"));
            allMoradas.add(clienteInfo.getString("Morada"));
            allLinguas.add(clienteInfo.getString("Preferencias_Linguisticas"));
        }


        String[][] array = {
                allNIFs.toArray(new String[0]),
                allNomes.toArray(new String[0]),
                allTelefones.toArray(new String[0]),
                allMoradas.toArray(new String[0]),
                allLinguas.toArray(new String[0])
        };

        return array;
    }

    public static boolean avaliaCondutor(Manipula dados, String nif,String reputacao, String desconto){

        String queryReputacao = "UPDATE Condutor SET Reputacao = " + reputacao + " WHERE NIF = '" + nif + "'";
        boolean v1 = dados.xDirectiva(queryReputacao);

        boolean v2 = true;
        if( desconto != null){
        String queryDesconto = "UPDATE Aluguer SET Desconto = '" + desconto + "' WHERE NIF = '" + nif + "'";
        v2 = dados.xDirectiva(queryDesconto);}


        return (v1 && v2);
    }

    public static String getCondutorCarro(Manipula dados, String matricula,String data) throws SQLException{

        String query = "SELECT C.Nome, C.Numero_Carta, C.Reputacao " +
                "FROM Aluguer A " +
                "INNER JOIN Condutor C ON A.NIF = C.NIF " +
                "WHERE A.Matricula = '" + matricula + "' " +
                "AND '" + data + "' BETWEEN A.Inicio AND A.Fim " +
                "ORDER BY A.Fim DESC LIMIT 1;"; // encontrar a data mais recente

        ResultSet result = dados.getResultado(query);

        if (!result.next()) {
            return "Nenhum condutor encontrado para esta data.";
        }

        String nome = result.getString("Nome");
        String carta = result.getString("Numero_Carta");
        String reputacao = result.getString("Reputacao");


        return "Condutor: " + nome + " | Nº Carta: " + carta + " | Reputação: " + reputacao;
    }
}
