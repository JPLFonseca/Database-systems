package com.example.sbd_tp2_intelij;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Connection {
	static java.sql.Connection con;

	static Statement stmt;

	static ResultSet rs;

	//	for how to set up data source see below.

	private static String server="localhost:3306";
	private static String database="MyRentACar";
	private static String user="root";
	private static String password="root";

	public static String DRV = "com.mysql.cj.jdbc.Driver";
	public static String URL = "jdbc:mysql://"+server+"/"+database+
			"?user="+user+"&password="+password+
			"&useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon";


	public Connection() {
		loadDriver();
		makeConnection();
	}

	static void loadDriver() {
		try {
			Class.forName(DRV);
			System.out.println("Load driver: "+DRV);
		} catch (ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
	}

	static void makeConnection() {
		try {
			con = DriverManager.getConnection(URL);
			System.out.println(con);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Database connection: " + ex.getMessage());
			System.out.println();

		}
	}

	public List<tipoVeiculo> procuraVeiculos(){
		List<tipoVeiculo> tiposVeiculos = new ArrayList<>();
		String query = "SELECT Tipo, Descricao, Potencia, Numero_Portas, Numero_Lugares, Tipo_Motor, Capacidade_Carga FROM tipoVeiculo";
		try (Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				tiposVeiculos.add(new tipoVeiculo(
						rs.getString("Tipo"),
						rs.getString("Descricao"),
						rs.getInt("Potencia"),
						rs.getInt("Numero_Portas"),
						rs.getInt("Numero_Lugares"),
						rs.getString("Tipo_Motor"),
						rs.getInt("Capacidade_Carga")
				));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Erro ao procurar veiculos: " + ex.getMessage());
		}

		return tiposVeiculos;
	}
	public List<Parque> procuraParques() {
		List<Parque> parques = new ArrayList<>();
		String query = "SELECT Coordenadas, Morada, Localidade FROM parque_estacionamento";

		try (Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				parques.add(new Parque(
						rs.getString("Coordenadas"),
						rs.getString("Morada"),
						rs.getString("Localidade")
				));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Erro ao procurar parques: " + ex.getMessage());
		}

		return parques;
	}



	public boolean executaQueryBool(String query) {

		try (Statement stmt = con.createStatement()) {
			int dadosAlt = stmt.executeUpdate(query);
			if (dadosAlt > 0) {
				System.out.println("Query executada com sucesso. Nº de campos alterados " + dadosAlt);
				return true;
			} else {
				System.out.println("Query executada, mas nenhum campo foi alterado");
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Erro de execução: " + ex.getMessage());
			return false;
		}
	}


	}
