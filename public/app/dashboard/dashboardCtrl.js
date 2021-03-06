'use strict';

define([], function() {
	return ['$scope', 'dashboardService', function($scope, dashboardService) {

    function getUserDashBoardDetails() {
      dashboardService.userDashBoardDetails()
        .success(function (dashboardData) {
          $scope.userDashBoardData = dashboardData;
        })
        .error(function (error) {
          $scope.errorMessage = 'Unable to process your request';
        });
    }

    getUserDashBoardDetails();
		$scope.$apply();
	}];
});