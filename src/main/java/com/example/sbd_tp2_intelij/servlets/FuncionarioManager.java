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
}
