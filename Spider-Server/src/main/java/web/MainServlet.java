package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import spider.Main;
import spider.error_log;
import spider.fileIsRight;

public class MainServlet extends HttpServlet{
	/**
	 * 用来接收请求,实现定时判断新闻是否更新
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		new Main();
		req.getRequestDispatcher("index.jsp").forward(req, res);
//		System.out.println(this.getServletContext().getRealPath("/"));
	}
}
