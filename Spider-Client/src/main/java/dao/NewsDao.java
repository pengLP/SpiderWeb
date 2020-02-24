package dao;

import java.util.List;

import Main.News;

public interface NewsDao {
	List<News> findAll(String name);
}
