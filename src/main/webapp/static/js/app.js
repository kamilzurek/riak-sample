(function(angular) {

	var riak = angular.module('riak', []);

	riak.factory('RiakService', ['$http', function($http) {
		var service = {
				search: function(what) {
					return $http.get('../search?t=' + what)
					.error(function(error, status, headers, config) {
						console.log(error);
					});
				},
				details: function(id, isbn) {
					return $http.get('../details?id=' + id + "&isbn=" + isbn)
					.error(function(error, status, headers, config) {
						console.log(error);
					});
				}
		};
		return service;
	}]);

	riak.controller('RiakController', ['$scope', 'RiakService', function($scope, RiakService) {
		$scope.model = {};
		$scope.model.searchPharse = '';
		$scope.books = [];
		$scope.totalBooks = 0;
		$scope.book = {};

		$scope.search = function() {
			var d = RiakService.search($scope.model.searchPharse);
			d.then(function(resp) {
				$scope.books = resp.data;
				$scope.totalBooks = resp.data.length;
			});
		};

		$scope.details = function(id, isbn) {
			RiakService.details(id, isbn).then(function(resp) {
				$scope.book = resp.data;
			});
		};
	}]);

})(angular);