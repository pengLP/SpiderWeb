package spider;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class getImg {
	private String url;
	private String filename;
	private String htmlDoc = "";
	private String imgurl;
	public getImg() {}
	public String getImgurl(String url,String code) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();	//创建httpclient
		HttpGet httpget = new HttpGet(url);										//创建方法实例
		try {
			HttpResponse response = httpclient.execute(httpget);				//执行get请求，execute()方法请求访问具体的资源
			HttpEntity entity = response.getEntity();							//获取相应消息实体
			if(entity != null){
				htmlDoc = EntityUtils.toString(entity, code);					//转换编码
				htmlDoc = htmlDoc.replaceAll("\\s{2,99}|\t|\r|\n", "");			//去掉空格回车换行，方便正则表达式匹配
				EntityUtils.consume(entity);									//关闭流
				
				Pattern p = Pattern.compile("<img src=\"(.*?)\"");
				Matcher m = p.matcher(htmlDoc);
				while (m.find()) {
						imgurl = m.group(1);
						if (!imgurl.equals("")) {
							return imgurl;
						}
				}
			}
		}
		catch (Exception e) {
			new error_log(e);
		}
		finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				new error_log(e);
			}
		}
		return "";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getHtmlDoc() {
		return htmlDoc;
	}
	public void setHtmlDoc(String htmlDoc) {
		this.htmlDoc = htmlDoc;
	}
	
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
}
