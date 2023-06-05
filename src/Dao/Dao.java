package Dao;

import java.sql.*;

 public class Dao {
 		Connection connect = null;
 		Statement statement = null;		
 		ResultSet resultSet = null;
	public void connect() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/counsel", "root", "1357");
		} catch (ClassNotFoundException e) {
			throw new Exception("JDBC 드라이버를 찾을 수 없습니다.", e);
		} catch (SQLException e) {
			throw new Exception("데이터베이스 연결에 실패했습니다.", e);
		}
	}

	public void create(String query) {
		try {
			statement = connect.createStatement();
			if (!statement.execute(query))
				System.out.println("insert OK!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet retrieve(String query) {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;

	}

	public void update(String query) {
		try {
			statement = connect.createStatement();
			if (!statement.execute(query))
				System.out.println("update OK!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String query) {
		try {
			statement = connect.createStatement();
			if (!statement.execute(query))
				System.out.println("delete OK!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
