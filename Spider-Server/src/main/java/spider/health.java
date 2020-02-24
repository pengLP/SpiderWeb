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
 * 健康
 * @author 落雪封尘
 *
 */
public class health extends Spider{
	private Spider health;
	private String health_text;
	private ArrayList<String> title;
	private ArrayList<String> address;
	private ArrayList<String> time;
	private ArrayList<String> imgurl = new ArrayList<String>();
	private SelectTitleFromDateBase db;
	public health() {
		db = new SelectTitleFromDateBase("health");
		health = new Spider("http://health.sina.com.cn/","UTF8");
		health_text = health.getHtmlDoc();					//获取健康网站源码
		new ArrayList<String>();
		title = new ArrayList<String>();
		address = new ArrayList<String>();
		time = new ArrayList<String>();
		Pattern p = Pattern.compile("\\<div class\\=\"news\\-item\"><h2><a href\\=\"(.*?)\" target=\"_blank\"\\>(.*?)</a></h2><div class=\"c clearfix\">");					//获取新闻信息
		Matcher m = p.matcher(health_text);
		while (m.find()) {
			address.add(m.group(1));
			imgurl.add(new getImg().getImgurl(m.group(1), "utf-8"));
			title.add(m.group(2));
			if (address.size() > 50) break;
		}
		Pattern newsTime = Pattern.compile("\\<div class\\=\"time\"\\>(.*?)</div>");					//匹配新闻发布时间
		Matcher match_NewsTime = newsTime.matcher(health_text);
		while (match_NewsTime.find()) {
			time.add(match_NewsTime.group(1));
			if (time.size() > 50) break;
		}
//		for (int i = 0; i < address.size(); i++) {
//			System.out.println("标题	:\t" + title.get(i));
//			System.out.println("新闻网址:\t" + address.get(i));
//			System.out.println("发布时间:\t" + time.get(i) + "\n");
//			System.out.println("图片地址:\t" + imgurl.get(i) + "\n");
//		}
		for (int i = 0; i < address.size() + 0; i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "health")) {
				toDataBase(j + 1,title.get(i - 0), address.get(i - 0), time.get(i - 0),imgurl.get(i));
			}
			else {
				continue;
			}
		}
 	}
	
	public void toDataBase(int id,String title,String address,String time,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO health(id,title,address,time,newsfrom,imgurl)" + "VALUES (?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setString(4, time);
			ptst.setString(5, "新浪新闻");
			ptst.setString(6, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	public String getHealth_text() {
		return health_text;
	}
	public void setHealth_text(String health_text) {
		this.health_text = health_text;
	}

	public Spider getHealth() {
		return health;
	}

	public void setHealth(Spider health) {
		this.health = health;
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

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
}
