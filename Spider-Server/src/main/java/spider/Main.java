package spider;

import java.io.File;

public class Main {
	public Main() {
		if (new isUpdate().isupdate("culture")) {
			SpiderThread st1 = new SpiderThread("culture");
			st1.start();
		}
		
		if (new isUpdate().isupdate("domestic")) {
			SpiderThread st2 = new SpiderThread("domestic");
			st2.start();
		}
		
		if (new isUpdate().isupdate("ent")) {
			SpiderThread st3 = new SpiderThread("ent");
			st3.start();
		}
		
		if (new isUpdate().isupdate("finance")) {
			SpiderThread st4 = new SpiderThread("finance");
			st4.start();
		}
		
		if (new isUpdate().isupdate("health")) {
			SpiderThread st5 = new SpiderThread("health");
			st5.start();
		}
		
		if (new isUpdate().isupdate("learning")) {
			SpiderThread st6 = new SpiderThread("learning");
			st6.start();
		}
		
		if (new isUpdate().isupdate("sports")) {
			SpiderThread st7 = new SpiderThread("sports");
			st7.start();
		}
		
		if (new isUpdate().isupdate("tech")) {
			SpiderThread st8 = new SpiderThread("tech");
			st8.start();
		}
		
		if (new isUpdate().isupdate("war")) {
			SpiderThread st9 = new SpiderThread("war");
			st9.start();
		}
		if (new isUpdate().isupdate("america")) {
			SpiderThread st10 = new SpiderThread("america");
			st10.start();
		}
		if (new isUpdate().isupdate("japan")) {
			SpiderThread st11 = new SpiderThread("japan");
			st11.start();
		}
	}
//	public static void main(String[] args) {
//		new Main();
//		System.out.println("OK");
//	}
}
