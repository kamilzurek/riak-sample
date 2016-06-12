<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script src="../static/js/jquery.js"></script>
	<link rel="stylesheet" href="../static/css/bootstrap.min.css">
	<link rel="stylesheet" href="../static/css/bootstrap-theme.min.css">
	<script src="../static/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="../static/js/jquery.jqplot.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../static/css/jquery.jqplot.min.css"/>
	<script type="text/javascript" src="../static/js/jqplot.pieRenderer.min.js"></script>
</head>
<body>
	<h2 style="display: inline-block;">Kategorie</h2>
	<button type="button" class="btn btn-default btn-sm" style="display: inline-block;" data-toggle="collapse" data-target="#cats">+/-</button>
	<div id="myChart" style="height:500px; width:500px;"></div>
	<div id="cats" data-toggle="collapse" class="collapse">
		<c:if test="${not empty categories}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>LP</th>
						<th>Kategoria</th>
						<th>Liczba</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="category" varStatus="lp">
						<tr>
							<td>${lp.count}</td>
							<td>${category.key}</td>
							<td>${category.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	
	<h2 style="display: inline-block;">Podkategorie</h2>
	<button type="button" class="btn btn-default btn-sm" style="display: inline-block;" data-toggle="collapse" data-target="#subcats">+/-</button>
	<div id="myChart2" style="height:500px; width:500px;"></div>
	<div id="subcats" data-toggle="collapse" class="collapse">
		<c:if test="${not empty subcategories}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>LP</th>
						<th>Podkategoria</th>
						<th>Liczba</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${subcategories}" var="item" varStatus="lp">
						<tr>
							<td>${lp.count}</td>
							<td>${item.key}</td>
							<td>${item.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	
	<h2 style="display: inline-block;">Rok wydania</h2>
	<button type="button" class="btn btn-default btn-sm" style="display: inline-block;" data-toggle="collapse" data-target="#date">+/-</button>
	<div id="myChart3" style="height:500px; width:500px;"></div>
	<div id="date" data-toggle="collapse" class="collapse">
		<c:if test="${not empty dates}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>LP</th>
						<th>Rok wydania</th>
						<th>Liczba</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dates}" var="item" varStatus="lp">
						<tr>
							<td>${lp.count}</td>
							<td>${item.key}</td>
							<td>${item.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

	<h2 style="display: inline-block;">Ocena</h2>
	<button type="button" class="btn btn-default btn-sm" style="display: inline-block;" data-toggle="collapse" data-target="#rates">+/-</button>
	<div id="myChart4" style="height:500px; width:500px;"></div>
	<div id="rates" data-toggle="collapse" class="collapse">
		<c:if test="${not empty rates}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>LP</th>
						<th>Ocena</th>
						<th>Liczba</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rates}" var="item" varStatus="lp">
						<tr>
							<td>${lp.count}</td>
							<td>${item.key}</td>
							<td>${item.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>

<!-- 	<p></p> -->
<!-- 	<table style="border: 0; padding: 0;"> -->
<!-- 		<tr> -->
<!-- 			<td><h2>Kategorie</h2></td> -->
<!-- 			<td><h2>Podkategorie</h2></td> -->
<!-- 			<td><h2>Rok wydania</h2></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><div id="myChart" style="height:500px; width:500px;"></div></td> -->
<!-- 			<td><div id="myChart2" style="height:500px; width:500px;"></div></td> -->
<!-- 			<td><div id="myChart3" style="height:500px; width:500px;"></div></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><h2>Ocena</h2></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><div id="myChart4" style="height:500px; width:500px;"></div></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

</body>
</html>
<script>
$(document).ready(function(){
	var optionsLabels = {
			seriesDefaults: {
			renderer: jQuery.jqplot.PieRenderer,
			rendererOptions: {
			showDataLabels: true
			}
			},
            legend: {
                show: true,
                location: 'e',
                fontSize: 11,
                marginTop: 10,                                
            }
	},
	optionsNoLabels = {
			seriesDefaults: {
			renderer: jQuery.jqplot.PieRenderer,
			rendererOptions: {
			showDataLabels: true
			}
			},
            legend: {
                show: false,
                location: 'e',
                fontSize: 11,
                marginTop: 10,                                
            }
	};

	$.jqplot ('myChart', [ [${categoriesAsJS}] ], optionsLabels);
	$.jqplot ('myChart2', [ [${subcategoriesAsJS}] ], optionsNoLabels);
	$.jqplot ('myChart3', [ [${yearsAsJS}] ], optionsLabels);
	$.jqplot ('myChart4', [ [${ratesAsJS}] ], optionsLabels);
});
</script>