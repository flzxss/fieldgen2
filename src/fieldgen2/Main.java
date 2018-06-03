package fieldgen2;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.SqlRunner;
import cn.hutool.db.ds.simple.SimpleDataSource;

public class Main {

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
		SqlRunner runner = SqlRunner.create(ds);
		String sql = null;
		String fileName = "sql.sql";
		sql = FileUtil.readUtf8String(fileName);
		sql = StrUtil.trim(sql);
		sql = StrUtil.removeSuffix(sql, ";");
		// Object params;
		List<Entity> query = null;
		try {
			query = runner.query(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Entity entity = CollUtil.getFirst(query);
		System.out.println("entity=");
		System.out.println(entity);
	}

}
