home.component('home', {

	templateUrl: '/js/angular/home/home.html',
	controller: [

		'$http', '$scope', function homeController($http, $scope) {

			$scope.list = [];
			// $scope.status = "confirmed";
			$scope.orderProp = "country";
			$scope.statusName = "Countrywise List";
			$scope.totalConfirmedCounts = 0;
			$scope.changesSincelastDayForConfirmed = 0;
			$scope.totalDeathsCounts = 0;
			$scope.changesSincelastDayForDeaths = 0;
			$scope.totalRecoveredCounts = 0;
			$scope.percentRecoveredFromConfirmed = 0;
			$scope.changesSincelastDayForRecovered = 0;



			$scope.submitStatus = function checkSelectedStatus() {

				// for Confirmed Data

				// $http({
				// method : 'get',
				// url : '/angular/confirmed'
				//
				// })
				// .then(
				// function success(response) {
				//
				// // $scope.list =
				// // response.data;
				// $
				// .each(
				// response.data,
				// function(
				// k,
				// v) {
				//
				// $scope.totalConfirmedCounts += v.totalCases;
				// $scope.changesSincelastDayForConfirmed += v.changesSinceLastDay;
				//
				// });
				//
				// },
				// function error(response) {
				//
				// console
				// .log('error while request!');
				//
				// });
				//
				// // for Deaths Data
				//
				// $http({
				// method : 'get',
				// url : '/angular/deaths'
				//
				// })
				// .then(
				// function success(response) {
				//
				// $
				// .each(
				// response.data,
				// function(
				// k,
				// v) {
				//
				// $scope.totalDeathsCounts += v.totalCases;
				// $scope.changesSincelastDayForDeaths += v.changesSinceLastDay;
				//
				// });
				//
				// },
				// function error(response) {
				//
				// console
				// .log('error while request!');
				//
				// });
				//
				// // for Recovered Data
				//
				// $http({
				// method : 'get',
				// url : '/angular/recovered'
				//
				// })
				// .then(
				// function success(response) {
				//
				// $
				// .each(
				// response.data,
				// function(
				// k,
				// v) {
				//
				// $scope.totalRecoveredCounts += v.totalCases;
				// $scope.changesSincelastDayForRecovered += v.changesSinceLastDay;
				//
				// });
				//
				// },
				// function error(response) {
				//
				// console
				// .log('error while request!');
				//
				// });

				// consolidate list

				$http({
					method: 'get',
					url: '/angular/consolidatedList'

				}).then(function success(response) {

					$scope.list = response.data;
					$.each(response.data, function (k, v) {

						$.each(v.confirmed, function (e, t) {

							$scope.totalConfirmedCounts += parseInt(e);
							$scope.changesSincelastDayForConfirmed += t;

						});

						$.each(v.deaths, function (e, t) {

							$scope.totalDeathsCounts += parseInt(e);
							$scope.changesSincelastDayForDeaths += t;

						});

						$.each(v.recovered, function (e, t) {

							$scope.totalRecoveredCounts += parseInt(e);
							$scope.changesSincelastDayForRecovered += t;

						});

					})


					$scope.percentRecoveredFromConfirmed = (($scope.totalRecoveredCounts / $scope.totalConfirmedCounts) * 100).toFixed(2);
					console.log($scope.percentRecoveredFromConfirmed);

				}, function error(response) {

					console.log('error while request!');

				});

				// if ($scope.status == "confirmed") {
				// $http({
				//
				// method : "get",
				// url : "/angular/confirmed"
				//
				// }).then(function(response) {
				//
				// $scope.statusName = "Confirmed List";
				// $scope.list = response.data;
				// $scope.totalCases = 0;
				// $scope.changesSinceLastDay = 0;
				// $.each($scope.list, function(k, v) {
				//
				// $scope.totalCases += v.totalCases;
				// $scope.changesSinceLastDay +=
				// v.changesSinceLastDay;
				//
				// });
				// }, function(response) {
				//
				// });
				// }
				// if ($scope.status == "deaths") {
				// $http({
				//
				// method : "get",
				// url : "/angular/deaths"
				//
				// }).then(function(response) {
				//
				// $scope.statusName = "Deaths List";
				// $scope.list = response.data;
				// $scope.totalCases = 0;
				// $scope.changesSinceLastDay = 0;
				// $.each($scope.list, function(k, v) {
				//
				// $scope.totalCases += v.totalCases;
				// $scope.changesSinceLastDay +=
				// v.changesSinceLastDay;
				//
				// });
				// }, function(response) {
				//
				// });
				// }
				// if ($scope.status == "recovered") {
				// $http({
				//
				// method : "get",
				// url : "/angular/recovered"
				//
				// }).then(function(response) {
				//
				// $scope.statusName = "Recovered List";
				// $scope.list = response.data;
				// $scope.totalCases = 0;
				// $scope.changesSinceLastDay = 0;
				// $.each($scope.list, function(k, v) {
				//
				// $scope.totalCases += v.totalCases;
				// $scope.changesSinceLastDay +=
				// v.changesSinceLastDay;
				//
				// });
				//
				// }, function(response) {
				//
				// });
				//
				// }

			}
			$scope.submitStatus();

		}]

});