package com.kory.database;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlAccess {
	private static Connection connect;
	public static Connection getConnexion()  {
		MysqlDataSource sourceDonnee = new MysqlDataSource(); // instanciation d'un objet de type MysqlDataSource en tant que Source de donn�es
		sourceDonnee.setUser("root"); //nom d'utlisateur de la base 
		sourceDonnee.setPassword(""); //mot de passe utilisateur
		sourceDonnee.setServerName("localhost"); //nom du serveur
		sourceDonnee.setPort(3306); // port connect�
		sourceDonnee.setDatabaseName("hopital"); //nom de la base de donn�es
		
		try {
			connect = sourceDonnee.getConnection();
		}catch(SQLException se) {
			se.printStackTrace();
		}
		return connect;
	}
}