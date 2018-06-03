package fieldgen2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import cn.hutool.db.ds.simple.SimpleDataSource;

public class Main3 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1/ssm3";
		String user = "root";
		String pass = "root";
		String driver = "com.mysql.jdbc.Driver";
		DataSource ds = new SimpleDataSource(url, user, pass, driver);
		Connection connection = null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement createStatement = null;
		try {
			createStatement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 100; i++) {
			String sql="insert into t_user(id,username,password) values("+i+",'zs"+i+"','zs')";
			try {
				createStatement.execute(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
