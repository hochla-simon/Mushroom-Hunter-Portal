var locationControllers = angular.module('locationControllers', []);

//
//  LOCATION LIST CONTROLLER
//
locationControllers.controller('LocationListCtrl', ['$scope', '$window', '$log', 'LocationService', function ($scope, $window, $log, LocationService) {

        //Table will be ordered by location name by default
        $scope.orderByField = 'name';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of locations will be stored here
        $scope.locations = {};

        //Refresh location list
        $scope.refreshLocations = function () {
            LocationService("").getLocationWithMushroomOccurence(
                    function (data, status, headers, config) {
                        $scope.locations = data;
                        $log.info("List of location loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of location cannot be loaded.");
            });
        };

        //Call refresh function after app start
        $scope.refreshLocations();

        $scope.showLocationDetail = function (locationId) {
            $window.location.href = '/pa165/#/location/detail/' + locationId;
        };

        $scope.goToCreateLocation = function () {
            $window.location.href = '/pa165/#/location/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/pa165/';
        };


        //Sort table by field(column) or switch asc/desc ordering
        $scope.sortByField = function (field) {
            //Switch between asc/desc ordering after click on column according which the table is already sorted.
            if ($scope.orderByField == field) {
                $scope.reverseSort = !$scope.reverseSort;
            }
            //Set column according which the table will be sorted
            $scope.orderByField = field;
        };

        //Set apropriatry icon to indicate ordering
        $scope.getOrderIcon = function (field) {
            if ($scope.orderByField == field) {
                if ($scope.reverseSort) {
                    return 'glyphicon glyphicon-sort-by-attributes-alt';
                }
                else {
                    return 'glyphicon glyphicon-sort-by-attributes';
                }
            }
        };
    }]);

//
//  LOCATION DETAIL CONTROLLER
//
locationControllers.controller('LocationDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'LocationService', 'userId', 'isAdmin', function ($scope, $routeParams, $window, $log, LocationService, userId, isAdmin) {
        $scope.errorMessages = {};

        $scope.userId = userId;
        $scope.isAdmin = isAdmin;

        $log.info("User info: userId=" + $scope.userId + ", isAdmin=" + $scope.isAdmin);

        $scope.location = LocationService($routeParams.locationId).getLocationDetail(
                function (data, status, headers, config) {
                    $log.info("Location detail loaded.");
                    $scope.location = data;
                    $scope.locationBackup = angular.copy($scope.location);
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of location cannot be loaded.");
                });

        $scope.goToLocationList = function () {
            $window.location.href = '/pa165/#/location';
        };

        $scope.updateLocation = function (location) {
            $log.info("Saving location with ID: " + location.id);
            LocationService("").update(location,
                    function (data, status, headers, config) {
                        $log.info("Location updated");
                        $scope.errorMessages = {};
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Location cannot be updated.");
                        $scope.errorMessages = data.data;
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

        $scope.hasPermissionToModifyEntity = function (location) {
            if ($scope.isAdmin != "true" && location.ownerId != $scope.userId) {
                return false;
            } else {
                return true;
            }
        };
    }]);

//
//  CREATE NEW LOCATION CONTROLLER
//
locationControllers.controller('LocationCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'LocationService', function ($scope, $routeParams, $window, $log, LocationService) {
        $scope.errorMessages = {
            "fieldErrors": []
        };

        $scope.location = {
            "id": null,
            "name": "",
            "description": "",
            "nearCity": "",
            "mushroomOccurence": null
        };

        $scope.goToLocationList = function () {
            $window.location.href = '/pa165/#/location';
        };

        $scope.createLocation = function () {
            $log.info("Creating location with name: " + $scope.location.name);
            LocationService("").create($scope.location,
                    function (data, status, headers, config) {
                        $log.info("Location created");
                        $scope.errorMessages = {};
                        $scope.showLocationDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Location cannot be created.");
                        $scope.errorMessages = data.data;
                    });
        };

        $scope.showLocationDetail = function (locationId) {
            $window.location.href = '/pa165/#/location/detail/' + locationId;
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
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false}
            });
        };
    }])
        ;


