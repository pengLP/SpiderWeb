package spider;

import java.io.File;

public class fileIsRight {
	public fileIsRight() {
		File f = new File("/error.log");
		if (f.exists()) {
			System.out.println("�ļ��Ѵ���");
		}
		else {
			f.mkdir();
			System.out.println("�ļ������ڣ����Ѵ���");
		}
	}
}
