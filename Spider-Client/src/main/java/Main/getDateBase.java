package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DBUtils;

public class getDateBase {
	List<String> newstitle = new ArrayList<String>();
	List<String> newsaddress = new ArrayList<String>();
	List<String> newstime = new ArrayList<String>();
	List<String> newsfrom = new ArrayList<String>();
	List<String> newsimgurl = new ArrayList<String>();
	List<String> newsZhtitle = new ArrayList<String>();
	public getDateBase() {};
	public getDateBase(String name) {
		Connection conn = null;
		Statement st = null;
		String sql = "SELECT * FROM " + name + " ORDER BY time DESC";
		try {
			conn = DBUtils.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getString("time") != null && rs.getString("cn_title") != null) {
					newstitle.add(rs.getString("title"));
					newsaddress.add(rs.getString("address"));
					newstime.add(rs.getString("time"));
					newsimgurl.add(rs.getString("imgurl"));
					newsfrom.add(rs.getString("newsfrom"));
					newsZhtitle.add(rs.getString("cn_title"));
				} else if (rs.getString("time") != null && rs.getString("cn_title") == null) {
					newstitle.add(rs.getString("title"));
					newsaddress.add(rs.getString("address"));
					newstime.add("time");
					newsimgurl.add(rs.getString("imgurl"));
					newsfrom.add(rs.getString("newsfrom"));
					newsZhtitle.add("");
				} else if (rs.getString("time") == null && rs.getString("cn_title") != null) {
					newstitle.add(rs.getString("title"));
					newsaddress.add(rs.getString("address"));
					newstime.add("");
					newsimgurl.add(rs.getString("imgurl"));
					newsfrom.add(rs.getString("newsfrom"));
					newsZhtitle.add(rs.getString("cn_title"));
				} else if (rs.getString("time") == null && rs.getString("cn_title") == null) {
					newstitle.add(rs.getString("title"));
					newsaddress.add(rs.getString("address"));
					newstime.add("");
					newsimgurl.add(rs.getString("imgurl"));
					newsfrom.add(rs.getString("newsfrom"));
					newsZhtitle.add("");
				} 
			}
		} catch (SQLException e) {
			new error_log(e);
			e.printStackTrace();
		}
		
	}
	public List<String> getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(List<String> newstitle) {
		this.newstitle = newstitle;
	}
	public List<String> getNewsaddress() {
		return newsaddress;
	}
	public void setNewsaddress(List<String> newsaddress) {
		this.newsaddress = newsaddress;
	}
	public List<String> getNewstime() {
		return newstime;
	}
	public void setNewstime(List<String> newstime) {
		this.newstime = newstime;
	}
	public List<String> getNewsfrom() {
		return newsfrom;
	}
	public void setNewsfrom(List<String> newsfrom) {
		this.newsfrom = newsfrom;
	}
	public List<String> getNewsimgurl() {
		return newsimgurl;
	}
	public void setNewsimgurl(List<String> newsimgurl) {
		this.newsimgurl = newsimgurl;
	}
	public List<String> getNewsZhtitle() {
		return newsZhtitle;
	}
	public void setNewsZhtitle(List<String> newsZhtitle) {
		this.newsZhtitle = newsZhtitle;
	}
	

}
