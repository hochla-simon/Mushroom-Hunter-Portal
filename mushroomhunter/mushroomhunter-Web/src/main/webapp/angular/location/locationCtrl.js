var locationControllers = angular.module('locationControllers', []);

//
//  LOCATION LIST CONTROLLER
//
locationControllers.controller('LocationListCtrl', ['$scope', '$window', '$log', 'LocationService', function ($scope, $window, $log, LocationService) {

        $scope.locations = LocationService("").query();

        $scope.refreshLocations = function () {
            LocationService("").getLocationWithMushroomOccurence(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of location loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of location cannot be loaded.");
            });
        };

        $scope.showLocationDetail = function (locationId) {
            $window.location.href = '/mushroomhunter-web/#/location/detail/' + locationId;
        };
        
        $scope.goToCreateLocation = function () {
            $window.location.href = '/mushroomhunter-web/#/location/create';
        };
        
        $scope.goToHomePage = function () {
            $window.location.href = '/mushroomhunter-web/';
        };
    }]);

//
//  LOCATION DETAIL CONTROLLER
//
locationControllers.controller('LocationDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'LocationService', function ($scope, $routeParams, $window, $log, LocationService) {
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

        $scope.updateLocation = function (location) {
            $log.info("Saving location with ID: " + location.id);
            LocationService("").update(location,
                    function (data, status, headers, config) {
                        $log.info("Location updated");
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Location cannot be updated.");
                    });
        };

        $scope.deleteLocation = function (location) {
            $log.info("Deleting location with ID: " + location.id);
            LocationService(location.id).delete(
                    function (data, status, headers, config) {
                        $log.info("Location deleted");
                        $scope.goToLocationList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Location cannot be deleted.");
                    });
        };
    }]);

//
//  CREATE NEW LOCATION CONTROLLER
//
locationControllers.controller('LocationCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'LocationService', function ($scope, $routeParams, $window, $log, LocationService) {
        $scope.location = {
            "id":null,
            "name":"",
            "description": null,
            "nearCity": null,
            "mushroomOccurence":null
        };

        $scope.goToLocationList = function () {
            $window.location.href = '/mushroomhunter-web/#/location';
        };

        $scope.createLocation = function () {
            $log.info("Creating location with name: " + $scope.location.name);
            LocationService("").create($scope.location,
                    function (data, status, headers, config) {
                        $log.info("Location created");
                        $scope.showLocationDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Location cannot be created.");
                    });
        };
        
        $scope.showLocationDetail = function (locationId) {
            $window.location.href = '/mushroomhunter-web/#/location/detail/' + locationId;
        };
    }]);
//
//  LOCATION SERVICES
//
var locationServices = angular.module('locationServices', ['ngResource']);
locationServices.factory('LocationService', ['$resource', function ($resource) {
        return function (location) {
            return $resource('rest/location/' + location + ':param', {}, {
                query: {method: 'GET', isArray: true},
                getLocationWithMushroomOccurence: {url: 'rest/location/withMushroomOccurence' + ':param', method: 'GET', isArray: true},
                getLocationDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray:true},
                update: {method: 'PUT', isArray:false},
                delete: {method: 'DELETE', isArray:false}
            });
        };
    }])
        ;