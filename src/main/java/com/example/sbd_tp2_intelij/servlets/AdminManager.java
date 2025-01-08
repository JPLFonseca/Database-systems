package com.example.sbd_tp2_intelij.servlets;

import com.example.sbd_tp2_intelij.database.Configura;
import com.example.sbd_tp2_intelij.database.Manipula;

public class AdminManager {

    public static boolean gerirClientePessoal(Manipula dados, String[] array) { // Dados vai ser do tipo Manipula dados
        // e gerirCliente vai retornar um boolean

        if (array == null || array.length < 10) {
            System.out.println("Dados inválidos");
            return false;
        }

        // a verificacao se o NIF esta completo tem de ser feita na parte do jsp


        String query = "INSERT INTO Cliente (NIF, Nome, Contacto, Morada, Preferencias_Linguisticas)"
                + "VALUES('" + array[0] + "','" + array[1]+ "','" + array[2]+ "','" + array[3]+ "','" + array[4]+ "')"
                + "AS new ON DUPLICATE KEY UPDATE Nome = new.Nome, Contacto = new.Contacto,"
                + "    Morada = new.Morada,"
                + "    Preferencias_Linguisticas = new.Preferencias_Linguisticas;";
        boolean altera_dados = dados.xDirectiva(query);

        return altera_dados;
    }

    //TODO verificar o query novamente, se é pref linguistica ou capSOcial
    public static boolean gerirClienteEmpresarial(Manipula dados, String[] array) {

        if (array == null || array.length < 10) {
            System.out.println("Dados inválidos");
            return false;
        }




        String query = "INSERT INTO Cliente (NIF, Nome, Contacto, Morada, Preferencias_Linguisticas)"
                + "VALUES('" + array[0] + "','" + array[1]+ "','" + array[2]+ "','" + array[3]+ "','" + array[4]+ "')"
                + "AS new ON DUPLICATE KEY UPDATE Nome = new.Nome, Contacto = new.Contacto,"
                + "    Morada = new.Morada,"
                + "    Preferencias_Linguisticas = new.Preferencias_Linguisticas;";
        /*boolean altera_dados = dados.xDiretiva(query)
         *
         *
         * if (altera_dados){
         * */


        //TODO Ver aqui o array 6, porque depende se é empresa ou é pessoal
        String nQuery = "INSERT INTO Condutor(Numero_Carta,Nome,Data_Emissao_Carta,Data_Nascimento,Validade_Carta,Reputacao,NIF)"
                + "VALUES('"+ array[5] + "','"+ array[6] + "','"+ array[7] + "','"+ array[8] + "','"
                + array[9] + "','"+ array[10] + "','"+ array[0] + "') AS new"
                + "ON DUPLICATE KEY UPDATE"
                + "    Nome = new.Nome,\r\n"
                + "    Data_Emissao_Carta = new.Data_Emissao_Carta,"
                + "    Data_Nascimento = new.Data_Nascimento,"
                + "    Validade_Carta = new.Validade_Carta,"
                + "    Reputacao = new.Reputacao,"
                + "    NIF = new.NIF;";

        /* boolean altera_dados_n = dados.xDiretiva(nQuery)
         *
         * return altera_dados_n;
         * }
         *
         * return false
         *
         */



        return true; // alterar para "altera_dados"
    }
}
