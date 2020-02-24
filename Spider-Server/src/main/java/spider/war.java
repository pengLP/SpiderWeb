package spider;
/**
 * ¾üÊÂ
 * @author ÂäÑ©·â³¾
 *
 */
public class war extends Spider{
	private Spider war;
	private String war_text;
	private save_txt write;
	private ParseJson parse;
	public war() throws Exception {
		war = new Spider("http://temp.163.com/special/00804KVA/cm_war.js?callback=data_callback","GBK");
		war_text = war.getHtmlDoc();
		write = new save_txt(war_text, "D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\war.txt");
		parse = new ParseJson("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\war.txt","war");
	}
	public String getWar_text() {
		return war_text;
	}
	public void setWar_text(String war_text) {
		this.war_text = war_text;
	}
	public Spider getWar() {
		return war;
	}
	public void setWar(Spider war) {
		this.war = war;
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
