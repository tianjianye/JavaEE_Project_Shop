<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include.jsp" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>default</title>
	</head>
	<body>
	    <form action="updateCommande" method="post">
	        <table class="table table-striped">
		    	<tr>
			    	<td>Commande ID:</td>
			    	<td><input type="text" name="id" value="${commande.commandeId}" readonly="readonly" style="background-color:transparent;border:0"/></td>
			    </tr>
			    <tr>
			    	<td>Commande Number:</td>
			    	<td><input type="text" name="cnum" value="${commande.commandeNumber}"/></td>
			    </tr>
			    <tr>
			    	<td>Produit Name:</td>
					<td>
						<select id="pid" name="pid" required="required">
						<option value="${commande.getProduit().getPid()}">${commande.getProduit().getPname()}</option>
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
			    	<td><input type="text" name="quantity" value="${commande.quantity}"/></td>
			    </tr>
			     <tr>
			    	<td>Salesman:</td>
					<td>
						<%if (updateLogin.equals("admin")) {%>
							<select id="sid" name="sid" required="required">
							<option value="${commande.getSalesman().getSalesmanId()}">${commande.getSalesman().getSalesmanName()}</option>
							<%for(Salesman s:ls){
								%>
								<option value="<%=s.getSalesmanId()%>"><%=s.getSalesmanName()%></option> 
							<%}
							%>
							</select>
						<%}else{ %>
						<input type="text" name="updateLogin" required="required" readonly="readonly" style="background-color:transparent;border:0" value="<%=login %>">
						<%} %>
					</td>
			    </tr>
			</table>
			<button type="submit" class="btn btn-default">Update</button>
	    </form>
	</body>
</html>