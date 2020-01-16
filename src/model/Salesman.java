package model;

public class Salesman {
	@Override
	public String toString() {
		return "Salesman [salesmanId=" + salesmanId + ", salesmanName=" + salesmanName + ", pwd=" + pwd + "]";
	}
	private int salesmanId;
	private String salesmanName;
	private String pwd;
	
	public Salesman() {
		
	}
	public Salesman(int id, String name,String password) {
		this.salesmanId=id;
		this.salesmanName=name;
		this.pwd=password;
	}
	public Salesman(String name,String password) {
		this.salesmanName=name;
		this.pwd=password;
	}
	
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public String getSalesmanName() {
		return salesmanName;
	}
	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
