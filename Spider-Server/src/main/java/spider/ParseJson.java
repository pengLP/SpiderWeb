package spider;
import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import dao.DBUtils;
public class ParseJson {
	private File file;
	private BufferedReader in;
	private FileReader read;
	private ArrayList<Object> Title;
	private ArrayList<Object> TitleUrl;
	private ArrayList<Object> Time;
	private ArrayList<Object> imgurl = new ArrayList<Object>();
	private SelectTitleFromDateBase db;
	public ParseJson() {}
	public ParseJson(String filename,String name) {
		file = new File(filename);
		try {
			read = new FileReader(file);
		} catch (FileNotFoundException e1) {
			new error_log(e1);
		}
		BufferedReader in = new BufferedReader(read);
		String tmp;
		StringBuffer s = new StringBuffer();
		Title = new ArrayList<Object>();
		TitleUrl = new ArrayList<Object>();
		Time = new ArrayList<Object>();
		try {
			while((tmp = in.readLine()) != null)
			{
				s.append(tmp);
			}
		} catch (IOException e) {
			new error_log(e);
		}
		String re = "\\w+\\((.*)\\)";
		String result = s.toString().replaceAll(re, "$1");
		JSONArray array = JSON.parseArray(result);
		if(array.size() > 0)
		{
//			for (int i = 0;i < array.size(); i ++)
//			{
//				JSONObject jo = array.getJSONObject(i);
//				Title.add(jo.get("title"));TitleUrl.add(jo.get("docurl"));Time.add(jo.get("time"));
//				System.out.println("标        题:\t" + jo.get("title"));
//				System.out.println("新闻链接:\t" + jo.get("docurl"));
//				System.out.println("发布时间:\t" + jo.get("time") + "\n");
//			}
			if ("domestic".equals(name)) {
				db = new SelectTitleFromDateBase("domestic");
				for (int i = 0;i < array.size() + 0; i ++) {
					int j = db.getid();
					JSONObject jo = array.getJSONObject(i - 0);
					Title.add(jo.get("title"));
					if (!new isExist().isexist(Title.get(i).toString(), "domestic")) {
						toDataBase("domestic", j + 1, jo.get("title").toString(), jo.get("docurl").toString(), StringToDate(jo.get("time").toString()),jo.get("imgurl").toString());
					}
					else {
						continue;
					}
				}
			} else if ("tech".equals(name)) {
				db = new SelectTitleFromDateBase("tech");
				for (int i = 0;i < array.size() + 0; i ++) {
					int j = db.getid();
					JSONObject jo = array.getJSONObject(i - 0);
					Title.add(jo.get("title"));
					if (!new isExist().isexist(Title.get(i).toString(), "tech")) {
						toDataBase("tech", j + 1, jo.get("title").toString(), jo.get("docurl").toString(), StringToDate(jo.get("time").toString()),jo.get("imgurl").toString());
					}
					else {
						continue;
					}
				}
			} else if ("war".equals(name)) {
				db = new SelectTitleFromDateBase("war");
				for (int i = 0;i < array.size() + 0; i ++) {
					int j = db.getid();
					JSONObject jo = array.getJSONObject(i - 0);
					Title.add(jo.get("title"));
					if (!new isExist().isexist(Title.get(i).toString(), "war")) {
						toDataBase("war", j + 1, jo.get("title").toString(), jo.get("docurl").toString(), StringToDate(jo.get("time").toString()),jo.get("imgurl").toString());
					}
					else {
						continue;
					}
				}
			}
		}
	}
	
	public static Date StringToDate(String str) {
		java.util.Date date = new java.util.Date(Integer.parseInt(str.substring(6, 10)) - 1900, Integer.parseInt(str.substring(0, 2)) - 1 , Integer.parseInt(str.substring(3, 5)));
		return new Date(date.getTime());
	}
	
	public void toDataBase(String tablename,int id,String title,String address,Date time,String imgurl) {
		Connection conn = null;
		String sql = "INSERT INTO " + tablename + "(id,title,address,time,newsfrom,imgurl)" + "VALUES (?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ptst = conn.prepareStatement(sql);
			ptst.setInt(1, id);
			ptst.setString(2, title);
			ptst.setString(3, address);
			ptst.setDate(4, time);
			ptst.setString(5, "网易新闻");
			ptst.setString(6, imgurl);
			ptst.executeUpdate();
		} catch (SQLException e) {
			new error_log(e);
		} finally {
			DBUtils.close(conn);
		}
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public FileReader getRead() {
		return read;
	}
	public void setRead(FileReader read) {
		this.read = read;
	}
	public ArrayList<Object> getTitle() {
		return Title;
	}
	public void setTitle(ArrayList<Object> title) {
		Title = title;
	}
	public ArrayList<Object> getTitleUrl() {
		return TitleUrl;
	}
	public void setTitleUrl(ArrayList<Object> titleUrl) {
		TitleUrl = titleUrl;
	}
	public ArrayList<Object> getTime() {
		return Time;
	}
	public void setTime(ArrayList<Object> time) {
		Time = time;
	}
}
