package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.News;
import dao.Data;
import dao.NewsDao;

public class MainServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String path = req.getServletPath();
//		System.out.println(path);
		NewsDao dao = new Data();
		List<News> list = new ArrayList<News>();
//		List<News> imglist = new ArrayList<News>();
		if ("/index.do".equals(path)){
			list = dao.findAll("culture");
		} else if ("/culture.do".equals(path)) {
			list = dao.findAll("culture");
		} else if ("/domestic.do".equals(path)) {
			list = dao.findAll("domestic");
		} else if ("/ent.do".equals(path)) {
			list = dao.findAll("ent");
		} else if ("/finance.do".equals(path)) {
			list = dao.findAll("finance");
		} else if ("/health.do".equals(path)) {
			list = dao.findAll("health");
		} else if ("/learning.do".equals(path)) {
			list = dao.findAll("learning");
		} else if ("/sports.do".equals(path)) {
			list = dao.findAll("sports");
		} else if ("/tech.do".equals(path)) {
			list = dao.findAll("tech");
		} else if ("/war.do".equals(path)) {
			list = dao.findAll("war");
		} else if ("/america.do".equals(path)) {
			list = dao.findAll("america");
		} else if ("/japan.do".equals(path)) {
			list = dao.findAll("japan");
		} else if ("/america-zh.do".equals(path)) {
			list = dao.findAll("america");
		} else if ("/japan-zh.do".equals(path)) {
			list = dao.findAll("japan");
		}
		
		
//		imglist = dao.findAll("war");
//		for (int i = 0; i < imglist.size(); i++) {
//		System.out.println(imglist.get(i).getTitle());
//		System.out.println(imglist.get(i).getAddress());
//		System.out.println(imglist.get(i).getTime());
//		System.out.println(imglist.get(i).getFrom());
//		System.out.println(imglist.get(i).getImgurl() + "\n");
//	}
		if (!list.isEmpty()) {
			if ("/japan.do".equals(path) || "/america.do".equals(path)) {
				req.setAttribute("data", list);
				req.setAttribute("length", list.size());
				req.getRequestDispatcher("/WEB-INF/news2.jsp").forward(req, res);
			} else if ("/japan-zh.do".equals(path) || "/america-zh.do".equals(path)) {
				req.setAttribute("data", list);
				req.setAttribute("length", list.size());
				req.getRequestDispatcher("/WEB-INF/news-zh.jsp").forward(req, res);
			} else {
				req.setAttribute("data", list);
//				req.setAttribute("imglist", imglist);
				req.setAttribute("length", list.size());
				req.getRequestDispatcher("/WEB-INF/news.jsp").forward(req, res);
			}
			System.out.println("正在跳转");
		} else {
			res.setContentType("text/html;charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<p>访问路径不正确</p>");
			out.close();
		}
	}
}
