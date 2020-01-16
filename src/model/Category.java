package model;

public class Category {
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", category=" + category + "]";
	}
	private int cid;
	private String category;
	public Category() {
	}
	public Category(int cid,String category) {
		this.cid=cid;
		this.category=category;
	}
	public Category(String category) {
		this.category=category;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
