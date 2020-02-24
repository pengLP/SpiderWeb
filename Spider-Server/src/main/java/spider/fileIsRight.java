package spider;

import java.io.File;

public class fileIsRight {
	public fileIsRight() {
		File f = new File("/error.log");
		if (f.exists()) {
			System.out.println("文件已存在");
		}
		else {
			f.mkdir();
			System.out.println("文件不存在，但已创建");
		}
	}
}
