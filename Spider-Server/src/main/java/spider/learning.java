package spider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DBUtils;

/**
 * 教育
 * @author 落雪封尘
 *
 */
public class learning extends Spider{
	private Spider learning;
	private String learning_text;
	private ArrayList<String> title;
	private ArrayList<String> address;
	private SelectTitleFromDateBase db;
	private ArrayList<String> imgurl = new ArrayList<String>();
	public learning() {
		db = new SelectTitleFromDateBase("learning");
		learning = new Spider("http://learning.sohu.com/","UTF8");
		learning_text = learning.getHtmlDoc();
		title = new ArrayList<String>();
		address = new ArrayList<String>();
		Pattern p = Pattern.compile("</div><h4><a href\\=\"(.*?)\" target=\"_blank\">(.*?)</a></h4><div class=\"other\">");
		Matcher m = p.matcher(learning_text);
		while (m.find()) {
			address.add(m.group(1));
			imgurl.add("");
			title.add(m.group(2));
		}
		
//		for (int i = 0; i < address.size(); i++) {
//			System.out.println("标题:	\t" + title.get(i));
//			System.out.println("新闻网址:\t" + address.get(i) + "\n");
//			System.out.println("图片地址:\t" + imgurl.get(i) + "\n");
//		}
		for (int i = 0; i < title.size() + 0; i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "learning")) {
				toDataBase(j + 1, title.get(i - 0).toString(), address.get(i - 0).toString(),imgurl.get(i));
			}
			else {
				continue;
			}
		}
	}
	public void toDataBase(int id,String title,String address,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO learning(id,title,address,newsfrom,imgurl)" + "VALUES (?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setString(4, "搜狐新闻");
			ptst.setString(5, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	public String getLearning_text() {
		return learning_text;
	}
	public void setLearning_text(String learning_text) {
		this.learning_text = learning_text;
	}
	public Spider getLearning() {
		return learning;
	}
	public void setLearning(Spider learning) {
		this.learning = learning;
	}
	public ArrayList<String> getTitle() {
		return title;
	}
	public void setTitle(ArrayList<String> title) {
		this.title = title;
	}
	public ArrayList<String> getAddress() {
		return address;
	}
	public void setAddress(ArrayList<String> address) {
		this.address = address;
	}
}
