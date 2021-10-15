package es.ubu.lsi.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Database {
	
	private static Logger logger = LoggerFactory.getLogger(Database.class);
	private static Database baseDatos; // singleton pattern

	private static DataSource ds;
	
	private Database() {
		try{
			Context contextoInicial = new InitialContext();
			ds = (DataSource) contextoInicial.lookup("jdbc/comentarios");
			if (ds == null) {
				logger.error("No jdbc resource created...");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			logger.error("No resource created");
		}		
	}
	
	public static Database getInstance() {
		if (baseDatos==null){
			baseDatos = new Database();
			return baseDatos;
		}
		else{
			return baseDatos;
		}		
	}
	
	private DataSource getDataSource(){
		return ds;
	}
	
	public Connection getConnection() throws SQLException{
		return ds.getConnection();		
	}
	
	public ResultSet executeSQL(Connection conn, String sql) throws SQLException{
		Statement stmt = null;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 0);
		ResultSet resultSet = stmt.executeQuery(sql);
		return resultSet;		
	}
	
	public void close(Connection con) throws SQLException{
		if (con!=null){
				System.err.println("Closing connection!");
				con.close();
		}
	}
	
}