package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

public class ConnectionFactory {
	private static final String URL = "jdbc:mysql://localhost:3306/cadagenda";
	private static final String USER = "root";
	private static final String PASS = "1234";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException ex) {
			throw new RuntimeException("Erro na conexão: ", ex);
		}
	}

		public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
			closeConnection((Connection) con, stmt);
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException ex) {
				ex.getStackTrace();
			}
		}
		public static void closeConnection(Connection con, PreparedStatement stmt) {
			
		}
}