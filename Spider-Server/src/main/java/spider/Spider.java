package spider;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Spider {
	private String url;
	private String filename;
	private String htmlDoc = "";
	public Spider() {}
	public Spider(String url,String code) {
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();	//����httpclient
		HttpGet httpget = new HttpGet(url);										//��������ʵ��
		try {
			HttpResponse response = httpclient.execute(httpget);				//ִ��get����execute()����������ʾ������Դ
			HttpEntity entity = response.getEntity();							//��ȡ��Ӧ��Ϣʵ��
			if(entity != null){
				htmlDoc = EntityUtils.toString(entity, code);					//ת������
				htmlDoc = htmlDoc.replaceAll("\\s{2,99}|\t|\r|\n", "");			//ȥ���ո�س����У�����������ʽƥ��
				EntityUtils.consume(entity);									//�ر���
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
}
