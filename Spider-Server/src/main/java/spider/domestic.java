package spider;
/**
 * 国内
 * @author 落雪封尘
 *
 */
public class domestic extends Spider{
	private Spider domestic;
	private String domestic_text;
	private save_txt write;
	private ParseJson parse;
	private SelectTitleFromDateBase db;
	public domestic() throws Exception {
		domestic = new Spider("http://temp.163.com/special/00804KVA/cm_guonei.js?callback=data_callback","GBK");
		domestic_text = domestic.getHtmlDoc();					//获取国内新闻源码
		write = new save_txt(domestic_text, "D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\domestic.txt");
		parse = new ParseJson("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\domestic.txt","domestic");
	}
	public String getDomestic_text() {
		return domestic_text;
	}
	public void setDomestic_text(String domestic_text) {
		this.domestic_text = domestic_text;
	}
	public Spider getDomestic() {
		return domestic;
	}
	public void setDomestic(Spider domestic) {
		this.domestic = domestic;
	}
	public save_txt getWrite() {
		return write;
	}
	public void setWrite(save_txt write) {
		this.write = write;
	}
	public ParseJson getParse() {
		return parse;
	}
	public void setParse(ParseJson parse) {
		this.parse = parse;
	}
	public SelectTitleFromDateBase getDb() {
		return db;
	}
	public void setDb(SelectTitleFromDateBase db) {
		this.db = db;
	}
}
