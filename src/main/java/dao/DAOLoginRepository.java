package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionPostgresql;
import model.ModelLogin;

public class DAOLoginRepository {

	private static Connection connection = null;

	public DAOLoginRepository() {
		connection = SingleConnectionPostgresql.getConnection();
	}

	public Boolean authUser(ModelLogin modelLogin) throws Exception {

		String query = "select * from users where upper(login) = upper(?) and upper(password) = upper(?) ";

		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next())
			return true;

		return false;

	}

}
