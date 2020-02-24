package spider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class save_txt {
	private File input;
	private FileWriter out;
	private BufferedWriter write;
	public save_txt() {}
	public save_txt(String str,String filename) throws Exception {
		input = new File(filename);
		out = new FileWriter(input);
		write = new BufferedWriter(out);
		write.write(str,0,str.length());
		write.flush();
		write.close();
		out.close();
	}
}
