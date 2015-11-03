package com.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.List;
//数据库的操作工具类
public class DBUtil {
	/**
	 * read config file
	 */
	public static String getPara(String ParaName) {
		Properties prop = new Properties();
		try {
			InputStream is = DBUtil.class.getResourceAsStream("../../DBConfig.property");
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println("there is error to read config file...");
			e.printStackTrace();
		}
		System.out.println("数据库路劲显示："+prop.getProperty(ParaName));
		return prop.getProperty(ParaName);
	}

	/**
	 * get connection
	 */
	public static Connection getConn() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(getPara("MySQLURL"));
		} catch (Exception e) {
			System.out.println("there is error in connecting...");
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * close the connection
	 */
	public static void close(Connection cn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (cn != null) {
				cn.close();
				cn = null;
			}

		} catch (Exception e) {
			System.out.println("there is error in close connection");
			e.printStackTrace();
		}
	}

	// convert the database result to the List 把获取的数据转化为List
	public static List<Map<String, String>> getHashMap(ResultSet rs) throws SQLException {

		List<Map<String, String>> dataTable = new ArrayList<Map<String, String>>();
		ResultSetMetaData rsmd = rs.getMetaData();

		while (rs.next()) {
			Map<String, String> item = new HashMap<String, String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				//getColumnName不能获得列别名
				//putToHash(item, rsmd.getColumnName(i).toLowerCase(), rs.getString(i));
				putToHash(item, rsmd.getColumnLabel(i).toLowerCase(), rs.getString(i));
			}
			dataTable.add(item);
		}
		return dataTable;
	}

	private static void putToHash(Map<String, String> item, String key, String value) {
		//if (value != null) { 
			item.put(key, value);
		//}
	}

}
