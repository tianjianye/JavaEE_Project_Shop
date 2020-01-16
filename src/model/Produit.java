package model;

public class Produit {
	@Override
	public String toString() {
		return "Produit [pid=" + pid + ", pname=" + pname + ", price=" + price + ", mark=" + mark + "]";
	}
	private int pid;
	private String pname;
	private double price;
	private Mark mark;
	public Produit() {
		
	}
	public Produit(String pname, double price,Mark mark) {
		this.pname=pname;
		this.price=price;
		this.setMark(mark);
	}
	public Produit(int pid, String pname, double price,Mark mark) {
		this.pid=pid;
		this.pname=pname;
		this.price=price;
		this.setMark(mark);
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Mark getMark() {
		return mark;
	}
	public void setMark(Mark mark) {
		this.mark = mark;
	}
}
