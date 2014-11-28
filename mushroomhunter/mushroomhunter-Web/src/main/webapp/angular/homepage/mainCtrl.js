var mainControllers = angular.module('mainControllers', []);

mainControllers.controller('MainCtrl', [ '$scope', '$window', function ($scope, $window) {

    $scope.goToLocationList = function () {
        $window.location.href = '/mushroomhunter-web/#/location';
    };

    //For test only
    $scope.goToLocationDetail = function () {
        $window.location.href = '/mushroomhunter-web/#/location/10';
    };
    
    $scope.goToVisitList = function () {
        $window.visit.href = '/mushroomhunter-web/#/visit';
    };

    $scope.goToHunterList = function () {
        $window.location.href = '/mushroomhunter-web/#/hunter';
    };

 $scope.goToMushroomList = function () {
        	$window.mushroom.href = '/mushroomhunter-web/#/mushroom';
    };

    //For test only
    $scope.goToHunterDetail = function () {
        $window.location.href = '/mushroomhunter-web/#/hunter/10';
    };

}]);