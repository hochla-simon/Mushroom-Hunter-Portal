//Skupina controllerů pro location, angulární aplikace (mushroomHunterApp.js) na ní má závislost
var locationControllers = angular.module('locationControllers', []);

//Controller pro zobrazení listu lokací (locationList.html)
locationControllers.controller('LocationListCtrl', ['$scope', '$window', 'LocationService', function ($scope, $window, LocationService) {
//Objekt zapsaný v JSONu

        $scope.locations = LocationService("").query();

        $scope.refreshLocations = function () {
            LocationService("withMushroomOccurence").query(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of location loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of location cannot be loaded.");
            });
        };

        $scope.showLocationDetail = function (locationId) {
            $window.location.href = '/mushroomhunter-web/#/location/' + locationId;
        }


//        var testLoc = LocationService().query({param: '10'}, function (data, status, headers, config) {
//            $scope.time = new Date();
//            $scope.messages = data;
//            $scope.checkNewMessages();
//        }, function (data, status, headers, config) {
//            $log.info("An error occurred on server!");
//        }
//        );
//        $scope.locations = [
//            {
//                "id": 1,
//                "name": "Brno"
//            },
//            {
//                "id": 2,
//                "name": "Praha"
//            }];
    }]);

//Controller pro zobrazení detailu lokací (locationDetail.html)
//Má dependenci na '$routeParams' a ta se mu pak předá jako parametr $routeParams
//Z $routeParams jde vytáhnout parametr z URL
locationControllers.controller('LocationDetailCtrl', ['$scope', '$routeParams', 'LocationService', function ($scope, $routeParams, LocationService) {
        $scope.location = LocationService($routeParams.locationId).getLocationDetail(
                function (data, status, headers, config) {
                    $log.info("Location detail loaded.");
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of location cannot be loaded.");
                });

        $scope.goToLocationList = function () {
            $window.location.href = '/mushroomhunter-web/#/location';
        };
    }]);


//
//  LOCATION SERVICES
//
var locationServices = angular.module('locationServices', ['ngResource']);
locationServices.factory('LocationService', ['$resource', function ($resource) {
        return function (location) {
            return $resource('rest/location/' + location + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getLocationDetail: {method: 'GET', isArray: false},
                create: {method: 'PUT'},
                delete: {method: 'DELETE'}
            });
        };
    }])
        ;