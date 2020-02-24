package foreign;

import java.sql.Connection;
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
 * 日本
 * http://www.tv-tokyo.co.jp/
 * @author 落雪封尘
 *
 */
public class japan {
	private Spider japan;
	private String context;
	private ArrayList<String> title;
	private ArrayList<String> cntitle;
	private ArrayList<String> url;
	private SelectTitleFromDateBase db;
	
	public japan() {
		japan = new Spider("http://www.tv-tokyo.co.jp/", "UTF-8");
		db = new SelectTitleFromDateBase("japan");
		context = japan.getHtmlDoc();
		title = new ArrayList<String>();
		url = new ArrayList<String>();
		cntitle = new ArrayList<String>();
		Pattern p = Pattern.compile("<li class=\"txcms_cms_programUnit \"(.*?)</a></li>");	//匹配源码有用信息
		Matcher m = p.matcher(context);
		while (m.find()) {
			Pattern p2 = Pattern.compile("<p class=\"txcms_unitName\">(.*?)</p><p class=\"txcms_unitTime");	//匹配标题
			Matcher m2= p2.matcher(m.group(1));
			while (m2.find()) {
				title.add(m2.group(1));
				cntitle.add(new translate().translate_title(m2.group(1).toString()));
			}
			
			Pattern p3 = Pattern.compile("<a href=\"(.*?)\" class=\"txcms_unit\">");	//匹配地址
			Matcher m3 = p3.matcher(m.group(1));
			while (m3.find()) {
				url.add("http://www.tv-tokyo.co.jp" + m3.group(1));
			}
		}
//		for (int i = 0; i < title.size(); i++) {
//			System.out.println("标题：" + title.get(i));
//			System.out.println("地址：" + url.get(i) + "\n");
//		}
		
		for (int i = 0; i < title.size(); i++) {
			int j = db.getid();
			if (!new isExist().isexist(title.get(i), "japan")) {
				toDataBase(j + 1,title.get(i - 0), url.get(i - 0),cntitle.get(i));
			}
			else {
				continue;
			}
		}
	}
	
	public void toDataBase(int id,String title,String address,String cn_title) {
		Connection conn = null;
		String sql = "INSERT INTO japan(id,title,address,newsfrom,cn_title)" + "VALUES (?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setString(4, "日本番剧");
			ptst.setString(5, cn_title);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	
	public Spider getJapan() {
		return japan;
	}

	public void setJapan(Spider japan) {
		this.japan = japan;
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

	public SelectTitleFromDateBase getDb() {
		return db;
	}

	public void setDb(SelectTitleFromDateBase db) {
		this.db = db;
	}
}
