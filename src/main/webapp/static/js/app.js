(function(angular) {
	
	var riak = angular.module('riak', []);
	
	riak.factory('RiakService', ['$http', function($http) {
		var service = {
				search: function(what) {
					return $http.get('search?t=' + what)
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
		
		$scope.search = function() {
			var d = RiakService.search($scope.model.searchPharse);
			d.then(function(resp) {
				$scope.show(resp);
			});
		};
		
		$scope.show = function(data) {
			console.log(data.data);
			$scope.books = data.data;
		}
	}]);

})(angular);