package model;

public class Commande {
	@Override
	public String toString() {
		return "Commande [commandeId=" + commandeId + ", commandeNumber=" + commandeNumber + ", produit=" + produit
				+ ", quantity=" + quantity + ", salesman=" + salesman + "]";
	}
	private int commandeId;
	private int commandeNumber;
	private Produit produit;
	private int quantity;
	private Salesman salesman;
	public Commande() {}
	public Commande(int commandeNumber, Produit produit, int quantity, Salesman salesman) {
		this.commandeNumber=commandeNumber;
		this.produit=produit;
		this.quantity=quantity;
		this.salesman=salesman;
	}
	public int getCommandeNumber() {
		return commandeNumber;
	}
	public void setCommandeNumber(int commandeNumber) {
		this.commandeNumber = commandeNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Salesman getSalesman() {
		return salesman;
	}
	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	public int getCommandeId() {
		return commandeId;
	}
	public void setCommandeId(int commandeId) {
		this.commandeId = commandeId;
	}
}
