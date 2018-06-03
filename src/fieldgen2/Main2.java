package fieldgen2;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.SqlRunner;
import cn.hutool.db.ds.simple.SimpleDataSource;

public class Main2 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1/ssm3";
		String user = "root";
		String pass = "root";
		String driver = "com.mysql.jdbc.Driver";
		String template ="<div>${▲}</div>";
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
		String sql = null;
		String fileName = "sql.sql";
		sql = FileUtil.readUtf8String(fileName);
		sql = StrUtil.trim(sql);
		sql = StrUtil.removeSuffix(sql, ";");
		ResultSet executeQuery = null;
		try {
			executeQuery = createStatement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		StringBuilder sb=new StringBuilder();
		try {
			if(executeQuery.next()){
				ResultSetMetaData metaData = executeQuery.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String columnTypeName = metaData.getColumnTypeName(i);
					String columnLabel = metaData.getColumnLabel(i);
					int columnType = metaData.getColumnType(i);
					String columnName = metaData.getColumnName(i);
//					System.out.println("columnTypeName="+columnTypeName);
//					System.out.println("columnLabel="+columnLabel);
//					System.out.println("columnType="+columnType);
//					System.out.println("columnName="+columnName);
					String row=template.replace("▲", columnLabel);
					sb.append(row);
					sb.append("\n");
					System.out.println(i);
				}
			}
			System.out.println(sb.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
