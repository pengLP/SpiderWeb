package spider;

import foreign.America;
import foreign.japan;

public class SpiderThread extends Thread{
	private String spidername;
	public SpiderThread() {};
	public SpiderThread(String spidername) {
		this.spidername = spidername;
	}
	public void run() {
		if (spidername.equals("culture")) {
			new culture();
		} else if (spidername.equals("domestic")) {
			try {
				new domestic();
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		} else if (spidername.equals("ent")) {
			new ent();
		} else if (spidername.equals("finance")) {
			new finance();
		} else if (spidername.equals("health")) {
			new health();
		} else if (spidername.equals("learning")) {
			new learning();
		} else if (spidername.equals("sports")) {
			new sports();
		} else if (spidername.equals("tech")) {
			try {
				new tech();
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		} else if (spidername.equals("war")) {
			try {
				new war();
			} catch (Exception e) {
				new error_log(e);
				e.printStackTrace();
			}
		} else if(spidername.equals("america")) {
			new America();
		} else if(spidername.equals("japan")) {
			new japan();
		}
	}
}
