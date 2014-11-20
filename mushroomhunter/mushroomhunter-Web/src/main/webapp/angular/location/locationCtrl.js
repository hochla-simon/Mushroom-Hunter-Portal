//Skupina controllerů pro location, angulární aplikace (mushroomHunterApp.js) na ní má závislost
var locationControllers = angular.module('locationControllers', []);

//Controller pro zobrazení listu lokací (locationList.html)
locationControllers.controller( 'LocationListCtrl', ['$scope', function( $scope ) {
//Objekt zapsaný v JSONu
$scope.locations = [
            {
                "id": 1,
                "name": "Brno"
            },
            {
                "id": 2,
                "name": "Praha"
            }];
    }]);

//Controller pro zobrazení detailu lokací (locationDetail.html)
//Má dependenci na '$routeParams' a ta se mu pak předá jako parametr $routeParams
//Z $routeParams jde vytáhnout parametr z URL
locationControllers.controller( 'LocationDetailCtrl', ['$scope', '$routeParams',function($scope, $routeParams ) {
        $scope.locationId = $routeParams.locationId;
}]);