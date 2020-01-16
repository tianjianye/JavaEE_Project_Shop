package model;

public class Mark {
	@Override
	public String toString() {
		return "Mark [mid=" + mid + ", mark=" + mark + ", category=" + category + "]";
	}
	private int mid;
	private String mark;
	private Category category;
	public Mark() {};
	public Mark(int mid,String mark,Category category) {
		this.mid=mid;
		this.mark=mark;
		this.setCategory(category);
	}
	public Mark(String mark,Category category) {
		this.mark=mark;
		this.setCategory(category);
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
