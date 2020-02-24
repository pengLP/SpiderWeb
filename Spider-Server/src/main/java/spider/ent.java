package spider;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DBUtils;

/**
 * 娱乐
 * @author 落雪封尘
 *
 */
public class ent extends Spider{
	private Spider ent;
	private String ent_text;
	private ArrayList<String> content;
	private ArrayList<String> title;
	private ArrayList<String> address;
	private ArrayList<String> imgurl = new ArrayList<String>();
	private SelectTitleFromDateBase db;
	public ent() {
		db = new SelectTitleFromDateBase("ent");
		ent = new Spider("http://ent.sina.com.cn/","UTF8");
		ent_text = ent.getHtmlDoc();													//获取娱乐新闻源码
		content = new ArrayList<String>();
		title = new ArrayList<String>();
		address = new ArrayList<String>();
		Pattern p = Pattern.compile("<ul class=\"seo_data_list\"><li>(.*?)</li></ul>");	//匹配新闻部分源码
		Matcher m = p.matcher(ent_text);
		while (m.find()) {
			content.add(m.group(1));
			Pattern news_title = Pattern.compile("title\\=(.*?)\\>");					//匹配标题
			Matcher match_NewsTitle = news_title.matcher(m.group(1));
			while (match_NewsTitle.find()) {
				title.add(match_NewsTitle.group(1));
			}
			
			Pattern news_Address = Pattern.compile("<a href\\=\"(.*?)\"");				//匹配新闻网址
			Matcher match_News_Address = news_Address.matcher(m.group(1));
			while (match_News_Address.find()) {
				address.add(match_News_Address.group(1));
				imgurl.add(new getImg().getImgurl(match_News_Address.group(1), "utf-8"));
			}
			if (address.size() > 50) {
				break;
			}
		}
//		for (int i = 0; i < title.size(); i++) {
//			System.out.println("标题:\t" + title.get(i));
//			System.out.println("图片:\t" + imgurl.get(i));
//			System.out.println("网址:\t" + address.get(i));
//			System.out.println("图片地址:\t" + imgurl.get(i) + "\n");
//		}
		for (int i = 0; i < 50 + 0; i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "ent")) {
				toDataBase(j + 1, title.get(i - 0).toString(), address.get(i - 0).toString(),imgurl.get(i));
			}
			else {
				continue;
			}
		}
	}
	
	public void toDataBase(int id,String title,String address,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO ent(id,title,address,newsfrom,imgurl)" + "VALUES (?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setString(4, "新浪新闻");
			ptst.setString(5, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	public String getEnt_text() {
		return ent_text;
	}
	public void setEnt_text(String ent_text) {
		this.ent_text = ent_text;
	}

	public Spider getEnt() {
		return ent;
	}

	public void setEnt(Spider ent) {
		this.ent = ent;
	}

	public ArrayList<String> getContent() {
		return content;
	}

	public void setContent(ArrayList<String> content) {
		this.content = content;
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
