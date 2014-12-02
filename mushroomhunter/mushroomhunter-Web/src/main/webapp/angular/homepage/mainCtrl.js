var mainControllers = angular.module('mainControllers', []);

mainControllers.controller('MainCtrl', [ '$scope', '$window', function ($scope, $window) {

    $scope.goToLocationList = function () {
        $window.location.href = '/pa165/#/location';
    };

    //For test only
    $scope.goToLocationDetail = function () {
        $window.location.href = '/pa165/#/location/10';
    };
    
    $scope.goToVisitList = function () {
        $window.location.href = '/pa165/#/visit';
    };

    $scope.goToHunterList = function () {
        $window.location.href = '/pa165/#/hunter';
    };

 $scope.goToMushroomList = function () {
       $window.location.href = '/pa165/#/mushroom';
    };


}]);