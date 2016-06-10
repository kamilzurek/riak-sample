<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<script src="static/js/jquery.js"></script>
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
	<script src="static/js/bootstrap.min.js"></script>
</head>
<body>
	<a href="books">Raporty książek</a>
	<div ng-app="riak" ng-controller="RiakController">
		<input ng-model="model.searchPharse" />
		<button ng-click="search()">Szukaj</button>
		<p></p>
		<div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tytuł</th>
						<th>ISBN</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="book in books">
						<td>{{book.id}}</td>
						<td>{{book.title}}</td>
						<td>{{book.isbn}}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>

<script src="static/js/angular.js"></script>
<script src="static/js/app.js"></script>

</html>