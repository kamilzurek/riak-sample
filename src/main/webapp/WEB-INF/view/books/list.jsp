<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<!--[if lt IE 9]><script type="text/javascript" src="http://cdn.jsdelivr.net/excanvas/r3/excanvas.js">
</script><![endif]-->
<script type="text/javascript" src="http://cdn.jsdelivr.net/jqplot/1.0.8/jquery.jqplot.min.js">
</script>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/jqplot/1.0.8/jquery.jqplot.min.css"/>
<script type="text/javascript" src="http://cdn.jsdelivr.net/jqplot/1.0.8/plugins/jqplot.pieRenderer.min.js"></script>


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