import java.io.File;

public class Tree{
	
//	  tree_id INT PRIMARY KEY,
//	    quote_id INT NOT NULL,
//	    size VARCHAR(20),
//	    height DECIMAL(8, 2),
//	    location VARCHAR(100) NOT NULL,
//	    isNearHouse BOOLEAN,
//	    note TEXT,
	
	
	protected int treeId;
	protected int quoteId;
	protected String size;
	protected double height;
	protected String location;
	protected boolean isNearHouse;
	protected String note;
	protected File[] images;
	
	public Tree(int quoteId, String size, double height, String location, boolean isNearHouse,String note) {
		this.quoteId = quoteId;
		this.size = size;
		this.height = height;
		this.location = location;
		this.isNearHouse = isNearHouse;
		this.note = note;
	}
	

	public Tree(int treeId, int quoteId, String size, double height, String location, boolean isNearHouse,String note) {
		this(quoteId, size, height,location,isNearHouse, note);
		this.treeId = treeId;
		
	}
	
	
	
	public int getTreeId() {
		return treeId;
	}
	public void setTreeId(int treeId) {
		this.treeId = treeId;
	}
	public int getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isNearHouse() {
		return isNearHouse;
	}
	public void setNearHouse(boolean isNearHouse) {
		this.isNearHouse = isNearHouse;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public File[] getImages() {
		return images;
	}


	public void setImages(File[] images) {
		this.images = images;
	}

	
	
	
	
}