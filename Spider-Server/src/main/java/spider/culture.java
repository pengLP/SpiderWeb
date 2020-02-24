package spider;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DBUtils;

/**
 * �Ļ�
 * @author ��ѩ�⳾
 *
 */
public class culture extends Spider{
	private Spider culture;
	private String culture_text;
	private ArrayList<String> content;					//������ȡ��������
	private ArrayList<String> address;					//������ַ
	private ArrayList<String> title;					//���ű���
	private ArrayList<String> time;						//���ŷ���ʱ��
	private ArrayList<String> news_Detailed_Content; 	//������ϸ��Ϣ	
	private ArrayList<String> imgurl = new ArrayList<String>();
	private SelectTitleFromDateBase db;
	public culture() {
		db = new SelectTitleFromDateBase("culture");
		culture = new Spider("http://culture.ifeng.com/","UTF8");
		culture_text = culture.getHtmlDoc();				//��ȡ�Ļ���ҳԴ����
		content = new ArrayList<String>();
		address = new ArrayList<String>();
		title = new ArrayList<String>();
		time = new ArrayList<String>();
		news_Detailed_Content = new ArrayList<String>();
		Pattern p = Pattern.compile("<h2>(<a[^>]*>[^?]*)</a></p>");
		Matcher m = p.matcher(culture_text);
		while (m.find()) {
			content.add(m.group(1).toString());
			Pattern newsAddress = Pattern.compile(".*href=\"(.*?)\"");			//ƥ����������
			Matcher matcher_NewsAddress = newsAddress.matcher(m.group(1));
			while (matcher_NewsAddress.find()) {
				address.add(matcher_NewsAddress.group(1));
				imgurl.add(new getImg().getImgurl(matcher_NewsAddress.group(1), "utf-8"));
				Pattern newsTime = Pattern.compile("http://culture.ifeng.com/a/(\\d+)*");		//ƥ�����ŷ���ʱ��
				Matcher matcher_NewsTime = newsTime.matcher(matcher_NewsAddress.group(1));
				while (matcher_NewsTime.find()) {
					time.add(matcher_NewsTime.group(1));
				}
			}
			
			Pattern newsTitle = Pattern.compile("<a[^>]*>([^<]*)</a></h2>");	//ƥ�����ű���
			Matcher matcher_NewsTitle = newsTitle.matcher(m.group(1));
			while (matcher_NewsTitle.find()) {
				title.add(matcher_NewsTitle.group(1));
			}
		}
		
		for (int i = 0; i < time.size() + 0; i++) {
			int j = (Integer)db.getid();
			if (!new isExist().isexist(title.get(i), "culture")) {
				toDataBase(++j,title.get(i - 0), address.get(i - 0), StringToDate(time.get(i - 0)),imgurl.get(i).toString());
			} 
			else {
				continue;
			}
		}
//		for (int i = 0; i < time.size(); i++) {
//			System.out.println("����:	\t" + title.get(i));
//			System.out.println("���µ�ַ:\t" + address.get(i));
//			System.out.println("����ʱ��:\t" + time.get(i));
//			System.out.println("ͼƬ��ַ:\t" + imgurl.get(i) + "\n");
//		}
	}
	
	public Date StringToDate(String str) {
		java.util.Date date = new java.util.Date(Integer.parseInt(str.substring(0, 4)) - 1900, Integer.parseInt(str.substring(4, 6)) - 1 , Integer.parseInt(str.substring(6, 8)));
		return new Date(date.getTime());
	}
	
	public void toDataBase(int id,String title,String address,Date time,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO culture(id,title,address,time,newsfrom,imgurl) " + "VALUES (?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setDate(4, time);
			ptst.setString(5, "�����");
			ptst.setString(6, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	
	public String getCulture_text() {
		return culture_text;
	}
	public void setCulture_text(String culture_text) {
		this.culture_text = culture_text;
	}
	public Spider getCulture() {
		return culture;
	}
	public void setCulture(Spider culture) {
		this.culture = culture;
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
	public ArrayList<String> getNews_Detailed_Content() {
		return news_Detailed_Content;
	}
	public void setNews_Detailed_Content(ArrayList<String> news_Detailed_Content) {
		this.news_Detailed_Content = news_Detailed_Content;
	}

	public SelectTitleFromDateBase getDb() {
		return db;
	}

	public void setDb(SelectTitleFromDateBase db) {
		this.db = db;
	}
}
