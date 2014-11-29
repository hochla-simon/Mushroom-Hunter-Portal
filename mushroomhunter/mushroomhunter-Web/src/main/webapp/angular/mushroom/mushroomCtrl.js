var mushroomControllers = angular.module('mushroomControllers', []);

//
//  MUSHROOM LIST CONTROLLER
//
mushroomControllers.controller('MushroomListCtrl', ['$scope', '$window', '$log', 'MushroomService', function ($scope, $window, $log, MushroomService) {


        $scope.showMushroomDetail = function (mushroomId) {
            $window.location.href = '/mushroomhunter-web/#/mushroom/detail/' + mushroomId;
        };
        
        $scope.goToCreateMushroom = function () {
            $window.location.href = '/mushroomhunter-web/#/mushroom/create';
        };
        
        $scope.goToHomePage = function () {
            $window.location.href = '/mushroomhunter-web/';
        };
    }]);

//
//  MUSHROOM DETAIL CONTROLLER
//
mushroomControllers.controller('MushroomDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'MushroomService', function ($scope, $routeParams, $window, $log, MushroomService) {
        $scope.mushroom = MushroomService($routeParams.mushroomId).getMushroomDetail(
                function (data, status, headers, config) {
                    $log.info("Mushroom detail loaded.");
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of mushroom cannot be loaded.");
                });

        $scope.goToMushroomList = function () {
            $window.location.href = '/mushroomhunter-web/#/mushroom';
        };

        $scope.updateMushroom = function (mushroom) {
            $log.info("Saving mushroom with ID: " + mushroom.id);
            MushroomService("").update(mushroom,
                    function (data, status, headers, config) {
                        $log.info("Mushroom updated");
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Mushroom cannot be updated.");
                    });
        };

        $scope.deleteMushroom = function (mushroom) {
            $log.info("Deleting mushroom with ID: " + mushroom.id);
            MushroomService(mushroom.id).delete(
                    function (data, status, headers, config) {
                        $log.info("Mushroom deleted");
                        $scope.goToMushroomList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Mushroom cannot be deleted.");
                    });
        };
    }]);

//
//  CREATE NEW MUSHROOM CONTROLLER
//
mushroomControllers.controller('MushroomCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'MushroomService', function ($scope, $routeParams, $window, $log, MushroomService) {
        $scope.mushroom = {
            "id":null,
            "name":"",
            "type": null,
            "startOfOccurence": null,
            "endOfOccurence":null
        };

        $scope.goToMushroomList = function () {
            $window.location.href = '/mushroomhunter-web/#/mushroom';
        };

        $scope.createMushroom = function () {
            $log.info("Creating mushroom with name: " + $scope.mushroom.name);
            MushroomService("").create($scope.mushroom,
                    function (data, status, headers, config) {
                        $log.info("Mushroom created");
                        $scope.showMushroomDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Mushroom cannot be created.");
                    });
        };
        
        $scope.showMushroomDetail = function (mushroomId) {
            $window.location.href = '/mushroomhunter-web/#/mushroom/detail/' + mushroomId;
        };
    }]);
//
//  MUSHROOM SERVICES
//
var mushroomServices = angular.module('mushroomServices', ['ngResource']);
mushroomServices.factory('MushroomService', ['$resource', function ($resource) {
        return function (mushroom) {
            return $resource('rest/mushroom/' + mushroom + ':param', {}, {
                query: {method: 'GET', isArray: true},
                getMushroomDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray:true},
                update: {method: 'PUT', isArray:false},
                delete: {method: 'DELETE', isArray:false}
            });
        };
    }])
        ;