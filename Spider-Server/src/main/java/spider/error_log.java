package spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class error_log {
	public error_log() {};
	public error_log(Exception e) {
		File file = new File("D:\\apache-tomcat-9.0.0.M26-windows-x64\\apache-tomcat-9.0.0.M26\\wtpwebapps\\Spider-Server\\WEB-INF\\classes\\error_log.txt");
		if (!file.exists()) {
			file.mkdir();
		}
		FileWriter out = null;
		BufferedWriter write = null;
		try {
			out = new FileWriter(file,true);
			write = new BufferedWriter(out);
			write.write(e.toString());
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(write != null) {
					write.flush();
					write.close();
					out.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
