<%@page import="java.util.List"%>  
<%@page import="model.*"%> 
<%@page import="dao.*"%> 
<%@page import="test.*"%> 
<%@page import="java.util.ArrayList"%> 
<% String login=(String)request.getAttribute("salesman"); 
String updateLogin=(String)request.getAttribute("updateLogin"); 
@SuppressWarnings("unchecked")
List<Commande> lc=(List<Commande>) request.getAttribute("listCommande");
ProduitDao pd=new ProduitDao();
List<Produit> lp=pd.listProduit();
SalesmanDao sd=new SalesmanDao();
List<Salesman> ls=sd.listSalesmanWithoutAdmin();
CommandeDao cod=new CommandeDao();
%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" >    
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script type='text/javascript'>
	function AfficherAddCommande(id){
		var btn=document.getElementById(id);
		var a=document.getElementById("addCommande").style.display;
		if(a=='none'){
			document.getElementById("addCommande").style.display = 'block';
			btn.innerHTML="Hide add commande";
		}
		else{
			document.getElementById("addCommande").style.display = 'none';
			btn.innerHTML="Show Add Commande";
		}
	}
</script>