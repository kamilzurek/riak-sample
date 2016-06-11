<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta charset="UTF-8">
	<script src="../static/js/jquery.js"></script>
	<link rel="stylesheet" href="../static/css/bootstrap.min.css">
	<link rel="stylesheet" href="../static/css/bootstrap-theme.min.css">
	<script src="../static/js/bootstrap.min.js"></script>
</head>
<body>
	<p></p>
	<div ng-app="riak" ng-controller="RiakController" style="padding: 10px;">
		<input type="text" class="form-control" ng-model="model.searchPharse" placeholder="Wyszukaj..." style="width:400px;display:inline;"/>
		<button type="button" class="btn btn-info" ng-click="search()">Szukaj</button>
		<p></p>
		<p>Wszystkich: {{totalBooks}}</p>
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
					<tr ng-repeat="book in books" data-toggle="modal" data-target="#bookDetailsModal" style="cursor: pointer;" ng-click="details(book.id, book.isbn)">
						<td>{{book.id}}</td>
						<td>{{book.title}}</td>
						<td>{{book.isbn}}</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div id="bookDetailsModal" class="modal fade" role="dialog">
		  <div class="modal-dialog" style="width:800px;">
		
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Szczegóły</h4>
		      </div>
		      <div class="modal-body">
		        <p><b>Tytuł: {{book.title}}</b></p>
		        <p><b>Autor: {{book.author}}</b></p>
		        <p>{{book.description}}</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
		      </div>
		    </div>
		
		  </div>
		</div>

	</div>
</body>

<script src="../static/js/angular.js"></script>
<script src="../static/js/app.js"></script>

</html>