package es.ubu.lsi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class CommentsDAO {

	private static Database db;
	
	static {
		db = Database.getInstance();
	}
	
	// methods
	public void add(Comment c) throws Exception {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = db.getConnection();
			String query = "INSERT INTO COMMENTS"
					+ "(name, surname, comments, date) VALUES"
					+ "(?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getSurname());
			preparedStatement.setString(3, c.getComments());
			java.sql.Timestamp date = new java.sql.Timestamp(c
					.getDate().getTime());
			preparedStatement.setTimestamp(4, date);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException ex2) {
				throw new Exception("Rooling back query error", ex2);
			}
			throw new Exception("General SQL error", ex);
		} finally {
			close(preparedStatement);
			close(con);
		}
	}
	
	public List<Comment> getComments() throws Exception {
		String query = "SELECT * FROM COMMENTS";
		List<Comment> list = new ArrayList<Comment>();
		
		try (Connection conn = db.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String comments = resultSet.getString("comments");
                Timestamp date = resultSet.getTimestamp("date");
                list.add(new Comment(id, name, surname, comments, date));
            }
            
            return list;

        } catch (SQLException e) {
        	throw new Exception("General SQL error", e);
        } catch (Exception e) {
        	throw new Exception(e);
        }
	}

	// handle connections
	private static void close(Statement st) throws Exception {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException ex) {
				throw new Exception("", ex);
			}
		}
	}

	private static void close(Connection con) throws Exception {
		if (con != null) {
			try {
				db.close(con);
			} catch (SQLException ex) {
				throw new Exception("", ex);
			}
		}
	}
}
