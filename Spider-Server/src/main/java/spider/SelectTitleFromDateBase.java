package spider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DBUtils;

public class SelectTitleFromDateBase {
	private String sql;		//sql语句
	private String name;	//新闻类别名
	private String sql_id;	//获取最后一条新闻的id
	public SelectTitleFromDateBase() {}
	public SelectTitleFromDateBase(String name) {
		this.name = name;
		sql = "SELECT title FROM " + name;
		sql_id = "SELECT id FROM " + name;
	}
	public List<String> Query() {
		List<String> list = new ArrayList<String>();
		Connection conn = DBUtils.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("title"));
			}
			return list;
		} catch (SQLException e) {
			new error_log(e);
			e.printStackTrace();
		} finally {
			DBUtils.close(conn);
		}
		return null;
	}
	
	public int getid() {
		Connection conn = DBUtils.getConnection();
		Statement st;
		int id = 0;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql_id);
			while (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			new error_log(e);
			e.printStackTrace();
		} finally {
			DBUtils.close(conn);
		}
		return 0;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSql_id() {
		return sql_id;
	}
	public void setSql_id(String sql_id) {
		this.sql_id = sql_id;
	}
}
