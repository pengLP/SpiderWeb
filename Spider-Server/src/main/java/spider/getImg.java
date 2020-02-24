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
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();	//����httpclient
		HttpGet httpget = new HttpGet(url);										//��������ʵ��
		try {
			HttpResponse response = httpclient.execute(httpget);				//ִ��get����execute()����������ʾ������Դ
			HttpEntity entity = response.getEntity();							//��ȡ��Ӧ��Ϣʵ��
			if(entity != null){
				htmlDoc = EntityUtils.toString(entity, code);					//ת������
				htmlDoc = htmlDoc.replaceAll("\\s{2,99}|\t|\r|\n", "");			//ȥ���ո�س����У�����������ʽƥ��
				EntityUtils.consume(entity);									//�ر���
				
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
