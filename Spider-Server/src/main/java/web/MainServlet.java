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
	 * ������������,ʵ�ֶ�ʱ�ж������Ƿ����
	 */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		new Main();
		req.getRequestDispatcher("index.jsp").forward(req, res);
//		System.out.println(this.getServletContext().getRealPath("/"));
	}
}
