package foreign;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.DBUtils;
import spider.SelectTitleFromDateBase;
import spider.Spider;
import spider.error_log;
import spider.isExist;
/**
 * 爬取CNN新闻源码并处理
 * @author 落雪封尘
 *
 */
public class America {
	private Spider cnn;
	private String context;
	private ArrayList<String> title;
	private ArrayList<String> cntitle;
	private ArrayList<String> url;
	private ArrayList<String> time;
	private SelectTitleFromDateBase db;
	public America() {
		cnn = new Spider("https://edition.cnn.com/", "GBK");
		context = cnn.getHtmlDoc();
		context = context.replaceAll("u003cstrong>", "").replaceAll("u003c/strong>", "");
		context = context.replaceAll("\\\\", "");
		title = new ArrayList<String>();
		url = new ArrayList<String>();
		time = new ArrayList<String>();
		cntitle = new ArrayList<String>();
		db = new SelectTitleFromDateBase("america");
		Pattern p = Pattern.compile("articleList(.*?)registryURL");
		Matcher m = p.matcher(context);
		while (m.find()) {
			Pattern p2 = Pattern.compile("\"uri\":\"(.*?)\",");	//匹配URl
			Matcher m2 = p2.matcher(m.group(1).toString());
			while (m2.find()) {
				url.add("https://edition.cnn.com" + m2.group(1));
			}
			
			Pattern p3 = Pattern.compile("headline\":\"(.*?)\",\"thumbnail");			//匹配标题
			Matcher m3 = p3.matcher(m.group(1).toString());
			while (m3.find()) {
				title.add(m3.group(1).toString());
				cntitle.add(new translate().translate_title(m3.group(1).toString()));
			}
		}
		
//		for (int i = 0; i < title.size(); i++) {
//			System.out.println("标题：" + title.get(i));
//			System.out.println("地址：" + url.get(i) + "\n");
//		}
		
		for (int i = 0; i < title.size(); i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "america")) {
				toDataBase(j + 1,title.get(i - 0), url.get(i - 0),cntitle.get(i));
			}
			else {
				continue;
			}
		}
	}
	
	public void toDataBase(int id,String title,String address,String cn_title) {
		Connection conn = null;
		String sql = "INSERT INTO america(id,title,address,newsfrom,cn_title)" + "VALUES (?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setString(4, "CNN");
			ptst.setString(5, cn_title);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	
	public Spider getCnn() {
		return cnn;
	}

	public void setCnn(Spider cnn) {
		this.cnn = cnn;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public ArrayList<String> getTitle() {
		return title;
	}

	public void setTitle(ArrayList<String> title) {
		this.title = title;
	}

	public ArrayList<String> getUrl() {
		return url;
	}

	public void setUrl(ArrayList<String> url) {
		this.url = url;
	}

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}

	public SelectTitleFromDateBase getDb() {
		return db;
	}

	public void setDb(SelectTitleFromDateBase db) {
		this.db = db;
	}
}
