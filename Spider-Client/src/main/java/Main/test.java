//package Main;
//
//import java.util.List;
//
//import dao.Data;
//import dao.NewsDao;
//
//public class test {
//	public static void main(String[] args) {
//		String path = "/sports";
//		NewsDao dao = new Data();
//		List<News> list = null;
//		if ("/index".equals(path)){
//			list = dao.findAll("culture");
//		} else if ("/culture".equals(path)) {
//			list = dao.findAll("culture");
//		} else if ("/domestic".equals(path)) {
//			list = dao.findAll("domestic");
//		} else if ("/ent".equals(path)) {
//			list = dao.findAll("ent");
//		} else if ("/finance".equals(path)) {
//			list = dao.findAll("finance");
//		} else if ("/health".equals(path)) {
//			list = dao.findAll("health");
//		} else if ("/learning".equals(path)) {
//			list = dao.findAll("learning");
//		} else if ("/sports".equals(path)) {
//			list = dao.findAll("sports");
//		} else if ("/tech".equals(path)) {
//			list = dao.findAll("tech");
//		} else if ("/war".equals(path)) {
//			list = dao.findAll("war");
//		} else if ("/america".equals(path)) {
//			list = dao.findAll("america");
//		} else if ("/japan".equals(path)) {
//			list = dao.findAll("japan");
//		}
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getTitle());
//			System.out.println(list.get(i).getAddress());
//			System.out.println(list.get(i).getTime());
//			System.out.println(list.get(i).getFrom());
//			System.out.println(list.get(i).getImgurl() + "\n");
//		}
//	}
//}
