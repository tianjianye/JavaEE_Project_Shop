<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="include.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<button type="button" class="btn btn-primary" onclick='window.location.href="login.jsp";'>Deconnexion</button>
	<div id="listCommande">
		<h1 style="text-align:center">List Commandes</h1>
		<form action="listCommande" method="post">
			<div style="text-align:center"><input type="text" id="login" name="login" required="required" readonly="readonly" style="text-align:center;font-size:48px;background-color:transparent;border:0" value="<%=login %>"/></div>
			<table class="table table-striped">
				<tr>
					<td>Commande ID</td>
					<td>Commande Number</td>
					<td>Produit Name</td>
					<td>Produit Price</td>
					<td>Quantity</td>
					<td>Salesman Name</td>
					<td>Update</td>
					<td>Delete</td>
				</tr>
				<%
					if (lc!=null){
						for(Commande c:lc){
				%>
							<tr>
								<td><%=c.getCommandeId()%></td>
								<td><%=c.getCommandeNumber()%></td>
								<td><%=c.getProduit().getPname()%></td>
								<td><%=c.getProduit().getPrice()%></td>
								<td><%=c.getQuantity()%></td>
								<td><%=c.getSalesman().getSalesmanName()%></td>
								<td><a href="updateCommande?id=<%=c.getCommandeId()%>&updateLogin=<%=login%>">Update</a></td>
								<td><a href="deleteCommande?id=<%=c.getCommandeId()%>&login=<%=login%>">Delete</a></td>
							</tr>
				<%		}
					}
				%>
			</table>
			<button id="btnAdd" type="button" class="btn btn-primary" onclick="AfficherAddCommande(this.id);">Show Add Commande</button>
		</form>
	</div>
	<div id="addCommande" style="display:none">
		<h1 style="text-align:center">Add Commandes</h1>
		<form action="addCommande" method="post">
			<table class="table table-striped">
				<tr>
					<td>Commande Number:</td>
					<td><input type="text" name="cnum" required="required">
					</td>
				</tr>
				<tr>
					<td>Produit Name:</td>
					<td>
						<select id="pid" name="pid" required="required">
						<%for(Produit p:lp){
							%>
							<option value="<%=p.getPid()%>"><%=p.getPname()%></option> 
						<%}
						%>
						</select>
					</td>
				</tr>
				<tr>
					<td>Quantity:</td>
					<td><input type="text" name="quantity" required="required">
					</td>
				</tr>
				<tr>
					<td>Salesman:</td>
					<td>
						<%if (login.equals("admin")) {%>
							<select id="add_sid" name="add_sid" required="required">
							<%for(Salesman s:ls){
								%>
								<option value="<%=s.getSalesmanId()%>"><%=s.getSalesmanName()%></option> 
							<%}
							%>
							</select>
						<%}else{ %>
						<input type="text" name="add_sname" required="required" readonly="readonly" style="background-color:transparent;border:0" value="<%=login %>">
						<%} %>
					</td>
				</tr>
			</table>
			<button type="submit" class="btn btn-default">Add</button>
		</form>
	</div>
</body>
</html>