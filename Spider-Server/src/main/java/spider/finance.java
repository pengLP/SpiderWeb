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
 * 财经
 * @author 落雪封尘
 *
 */
public class finance extends Spider{
	private Spider finance;
	private String finance_text;
	private ArrayList<String> content;
	private ArrayList<String> address;
	private ArrayList<String> title;
	private ArrayList<String> time;
	private ArrayList<String> imgurl = new ArrayList<String>();
	private SelectTitleFromDateBase db;
	public finance() {
		db = new SelectTitleFromDateBase("finance");
		content = new ArrayList<String>();
		address = new ArrayList<String>();
		title = new ArrayList<String>();
		time = new ArrayList<String>();
		finance = new Spider("http://finance.qq.com/","UTF8");
		finance_text = finance.getHtmlDoc();					//获取财经源码
		Pattern p = Pattern.compile("<a target=\"_blank\" class=\"linkto\" href=\"(.*?)\">(.*?)</a><div class=\"st\">");  //匹配新闻地址和标题
		Matcher m = p.matcher(finance_text);
		while (m.find()) {
			address.add(m.group(1));
			imgurl.add(new getImg().getImgurl(m.group(1), "utf-8"));
			title.add(m.group(2));
			Pattern news_Time = Pattern.compile("(\\d{8}).*?");			//匹配发布时间
			Matcher match_News_Time = news_Time.matcher(m.group(1));
			while (match_News_Time.find()) {
				time.add(match_News_Time.group(1));
			}
			if (address.size() > 50) {
				break;
			}
		}
//		for (int i =0; i < address.size(); i++) {
//			System.out.println("标题:	 \t" + title.get(i));
//			System.out.println("新闻网址:\t" + address.get(i));
//			System.out.println("发布时间:\t" + time.get(i) + "\n");
//			System.out.println("图片地址:\t" + imgurl.get(i) + "\n");
//		}
		for (int i = 0; i < address.size() + 0; i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "finance")) {
				toDataBase(j + 1, title.get(i - 0).toString(), address.get(i - 0).toString(), StringToDate(time.get(i - 0)),imgurl.get(i));
			}
			else {
				continue;
			}
		}
	}
	
	public Date StringToDate(String str) {
		java.util.Date date = new java.util.Date(Integer.parseInt(str.substring(0, 4)) - 1900, Integer.parseInt(str.substring(4, 6)) - 1 , Integer.parseInt(str.substring(6, 8)));
		return new Date(date.getTime());
	}
	
	public void toDataBase(int id,String title,String address,Date time,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO finance(id,title,address,time,newsfrom,imgurl)" + "VALUES (?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setDate(4, time);
			ptst.setString(5, "腾讯新闻");
			ptst.setString(6, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	public String getFinance_text() {
		return finance_text;
	}
	public void setFinance_text(String finance_text) {
		this.finance_text = finance_text;
	}
	public Spider getFinance() {
		return finance;
	}
	public void setFinance(Spider finance) {
		this.finance = finance;
	}
	public ArrayList<String> getContent() {
		return content;
	}
	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
	public ArrayList<String> getAddress() {
		return address;
	}
	public void setAddress(ArrayList<String> address) {
		this.address = address;
	}
	public ArrayList<String> getTitle() {
		return title;
	}
	public void setTitle(ArrayList<String> title) {
		this.title = title;
	}
	public ArrayList<String> getTime() {
		return time;
	}
	public void setTime(ArrayList<String> time) {
		this.time = time;
	}
}
