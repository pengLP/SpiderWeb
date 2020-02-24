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
 * 体育
 * @author 落雪封尘
 *
 */
public class sports extends Spider{
	private Spider sports;
	private String sports_text;
	private ArrayList<String> title;
	private ArrayList<String> address;
	private ArrayList<String> time;
	private ArrayList<String> imgurl = new ArrayList<String>();
	private SelectTitleFromDateBase db;
	public sports() {
		db = new SelectTitleFromDateBase("sports");
		sports = new Spider("http://sports.gmw.cn/","UTF8");
		sports_text = sports.getHtmlDoc();
		title = new ArrayList<String>();
		address = new ArrayList<String>();
		time = new ArrayList<String>();
		Pattern p = Pattern.compile("\\<div class\\=\"arc fr\"><h3><a href\\=\"(.*?)\" target=\"_blank\">(.*?)</a></h3>");					//获取新闻信息
		Matcher m = p.matcher(sports_text);
		while (m.find()) {
			address.add("http://sports.gmw.cn/" + m.group(1));
			imgurl.add("");
			title.add(m.group(2));
			time.add(m.group(1).substring(0, 10));
		}
		
//		for (int i = 0; i < address.size(); i++) {
//			System.out.println("标题	:\t" + title.get(i));
//			System.out.println("新闻网址:\t" + address.get(i));
//			System.out.println("发布时间:\t" + time.get(i) + "\n");
//			System.out.println("图片地址:\t" + imgurl.get(i) + "\n");
//		}
		for (int i = 0; i < address.size() + 0; i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "sports")) {
				toDataBase(j + 1,title.get(i - 0), address.get(i - 0), StringToDate(time.get(i - 0)),imgurl.get(i));
			}
			else {
				continue;
			}
		}
	}

	public Date StringToDate(String str) {
		java.util.Date date = new java.util.Date(Integer.parseInt(str.substring(0, 4)) - 1900, Integer.parseInt(str.substring(5, 7)) - 1 , Integer.parseInt(str.substring(8, 10)));
		return new Date(date.getTime());
	}
	
	public void toDataBase(int id,String title,String address,Date time,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO sports(id,title,address,time,newsfrom,imgurl)" + "VALUES (?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setDate(4, time);
			ptst.setString(5, "光明网");
			ptst.setString(6, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	public String getSports_text() {
		return sports_text;
	}
	public void setSports_text(String sports_text) {
		this.sports_text = sports_text;
	}

	public Spider getSports() {
		return sports;
	}

	public void setSports(Spider sports) {
		this.sports = sports;
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
