package spider;

import java.util.List;

import foreign.America;
import foreign.japan;

public class isUpdate {
	private String title = "";						//爬取的标题
	private List title_db;	//数据库中的标题
	//判断名为那么的新闻类型是否更新
	public boolean isupdate(String spidername) {
		title_db = new SelectTitleFromDateBase(spidername).Query();
		if (spidername.equals("culture")) {
			title = new culture().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("domestic")) {
			try {
				new domestic();
				title = (String) new ParseJson("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\domestic.txt","domestic").getTitle().get(0);
				for (Object l : title_db) {
					if (l.toString().equals(title)) {
						return false;
					}
					else {
						continue;
					}
				}
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		} else if (spidername.equals("ent")) {
			title = new ent().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("finance")) {
			title = new finance().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("health")) {
			title = new health().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("learning")) {
			title = new learning().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("sports")) {
			title = new sports().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("tech")) {
			try {
				new tech();
				title = (String) new ParseJson("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\tech.txt","tech").getTitle().get(0);
				for (Object l : title_db) {
					if (l.toString().equals(title)) {
						return false;
					}
					else {
						continue;
					}
				}
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		} else if (spidername.equals("war")) {
			try {
				new war();
				title = (String) new ParseJson("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\war.txt","war").getTitle().get(0);
				for (Object l : title_db) {
					if (l.toString().equals(title)) {
						return false;
					}
					else {
						continue;
					}
				}
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		} else if (spidername.equals("america")) {
			title = new America().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		} else if (spidername.equals("japan")) {
			title = new japan().getTitle().get(0);
			for (Object l : title_db) {
				if (l.toString().equals(title)) {
					return false;
				}
				else {
					continue;
				}
			}
		}
		return true;
	}
}
