package spider;
/**
 * ¿Æ¼¼
 * @author ÂäÑ©·â³¾
 *
 */
public class tech extends Spider{
	private Spider tech;
	private String tech_text;
	private save_txt write;
	private ParseJson parse;
	public tech() throws Exception {
		tech = new Spider("http://temp.163.com/special/00804KVA/cm_tech.js?callback=data_callback","GBK");
		tech_text = tech.getHtmlDoc();
		write = new save_txt(tech_text, "D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\tech.txt");
		parse = new ParseJson("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\tech.txt","tech");
	}
	public String getTech_text() {
		return tech_text;
	}
	public void setTech_text(String tech_text) {
		this.tech_text = tech_text;
	}
	public Spider getTech() {
		return tech;
	}
	public void setTech(Spider tech) {
		this.tech = tech;
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
}
