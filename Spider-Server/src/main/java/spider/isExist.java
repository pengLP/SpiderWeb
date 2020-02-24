package spider;

import java.util.List;

public class isExist {
	private SelectTitleFromDateBase db_title;
	private List<String> title;
	public isExist() {};
	
	public boolean isexist(String inputitle,String type) {
		db_title = new SelectTitleFromDateBase(type);
		title = db_title.Query();
		for (String l : title) {
			if (l.equals(inputitle)) {
				return true;
			}
		}
		return false;
	}
	
	public SelectTitleFromDateBase getDb_title() {
		return db_title;
	}
	public void setDb_title(SelectTitleFromDateBase db_title) {
		this.db_title = db_title;
	}
	public List<String> getTitle() {
		return title;
	}
	public void setTitle(List<String> title) {
		this.title = title;
	}
}
