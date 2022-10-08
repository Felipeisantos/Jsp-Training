package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionPostgresql {

	private static String database = "jdbc:postgresql://localhost:5432/jsp-training?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;

	static {
		Connect();
	}

	public SingleConnectionPostgresql() {
		Connect();
	}

	private static void Connect() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(database, user, password);
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
