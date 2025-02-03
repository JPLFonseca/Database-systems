package com.example.sbd_tp2_intelij.servlets;


import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class ClienteManager {

    public static String[][] getInfoAluguer(Manipula dados)throws SQLException{

        String queryTipos = "SELECT Tipo FROM Tipo_Veiculo;";
        String queryParques = "SELECT Localidade, Morada FROM Parque_Estacionamento;";


        ResultSet tiposVeiculos = dados.getResultado(queryTipos);
        ResultSet parques = dados.getResultado(queryParques);


        ArrayList<String> allTipos = new ArrayList<>();
        ArrayList<String> allLocalidades = new ArrayList<>();
        ArrayList<String> allMoradas = new ArrayList<>();


        while (tiposVeiculos.next()) {
            allTipos.add(tiposVeiculos.getString("Tipo"));
        }


        while (parques.next()) {
            allLocalidades.add(parques.getString("Localidade"));
            allMoradas.add(parques.getString("Morada"));
        }


        String[][] array = {
                allTipos.toArray(new String[0]),
                allLocalidades.toArray(new String[0]),
                allMoradas.toArray(new String[0])
        };

        return array;

    }
}
