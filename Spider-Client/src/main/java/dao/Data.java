package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Main.News;
import Main.getDateBase;

public class Data implements Serializable,NewsDao{
	/**
	 * 获取数据库信息
	 */
	List<News> list;
	News news;
	
	public Data() {
		list = new ArrayList<News>();
		
	}
	
	public List<News> findAll(String name) {
		getDateBase news_info = new getDateBase(name);
		for(int i = 0; i < news_info.getNewsaddress().size(); i++) {
			String address = news_info.getNewsaddress().get(i);
			String title = news_info.getNewstitle().get(i);
			String time = news_info.getNewstime().get(i);
			String from = news_info.getNewsfrom().get(i);
			String imgurl = news_info.getNewsimgurl().get(i);
			String cn_title = news_info.getNewsZhtitle().get(i);
			news = new News();
			news.setAddress(address);
			news.setTitle(title);
			news.setTime(time);
			news.setImgurl(imgurl);
			news.setFrom(from);
			news.setCn_title(cn_title);
			list.add(news);
		}
		return list;
	}
}
