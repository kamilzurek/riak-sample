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
	<h1>Książki</h1>
	<!-- 
	<div>
		<c:if test="${not empty books}">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>LP</th>
						<th>ID</th>
						<th>Tytuł</th>
						<th>Autor</th>
						<th>Kategoria</th>
						<th>Stron</th>
						<th>Rok wydania</th>
						<th>ISBN</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${books}" var="book" varStatus="lp">
						<tr>
							<td>${lp.count}</td>
							<td>${book.id}</td>
							<td>${book.title}</td>
							<td>${book.author}</td>
							<td>${book.category}</td>
							<td>${book.pages}</td>
							<td>${book.issue_date}</td>
							<td>${book.isbn}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
-->

	<div>
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

	<p></p>
	<table style="border: 0; padding: 0;">
		<tr>
			<td><h2>Kategorie</h2></td>
			<td><h2>Podkategorie</h2></td>
			<td><h2>Rok wydania</h2></td>
		</tr>
		<tr>
			<td><div id="myChart" style="height:500px; width:500px;"></div></td>
			<td><div id="myChart2" style="height:500px; width:500px;"></div></td>
			<td><div id="myChart3" style="height:500px; width:500px;"></div></td>
		</tr>
		<tr>
			<td><h2>Ocena</h2></td>
		</tr>
		<tr>
			<td><div id="myChart4" style="height:500px; width:500px;"></div></td>
		</tr>
	</table>

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