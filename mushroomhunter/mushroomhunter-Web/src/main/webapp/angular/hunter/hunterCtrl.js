var hunterControllers = angular.module('hunterControllers', []);

//
//HUNTER LIST CONTROLLER
///
hunterControllers.controller('HunterListCtrl', ['$scope', '$window', 'HunterService', function ($scope, $window, HunterService) {

        $scope.hunters = HunterService("").query();

        $scope.refreshHunters = function () {
            HunterService("").query(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of hunters loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of hunters cannot be loaded.");
            });
        };

        $scope.showHunterDetail = function (hunterId) {
            $window.hunter.href = '/mushroomhunter-web/#/hunter/detail/' + hunterId;
        };
		
	$scope.goToCreateHunter = function () {
            $window.hunter.href = '/mushroomhunter-web/#/hunter/create';
        };
        
        $scope.goToHomePage = function () {
            $window.hunter.href = '/mushroomhunter-web/';
        };
    }]);


//
//  CREATE NEW VISIT CONTROLLER
//
hunterControllers.controller('HunterCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'HunterService', function ($scope, $routeParams, $window, $log, HunterService) {
        $scope.visit = {
            "id":null,
            "nick": null,
            "firstName": null,
            "surname": null,
            "description":null
        };

        $scope.goToHunterList = function () {
            $window.location.href = '/mushroomhunter-web/#/hunter';
        };

        $scope.createHunter = function () {
            $log.info("Creating new hunter");
            HunterService("").create($scope.hunter,
                    function (data, status, headers, config) {
                        $log.info("Hunter created");
                        $scope.showHunterDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be created.");
                    });
        };
        
        $scope.showHunterDetail = function (hunterId) {
            $window.hunter.href = '/mushroomhunter-web/#/hunter/detail/' + hunterId;
        };
    }]);

hunterControllers.controller('HunterDetailCtrl', ['$scope', '$routeParams', 'HunterService', function ($scope, $routeParams, HunterService) {
        $scope.hunter = HunterService($routeParams.hunterId).getHunterDetail(
                function (data, status, headers, config) {
                    $log.info("Hunter detail loaded.");
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of hunter cannot be loaded.");
                });

        $scope.goToHunterList = function () {
            $window.hunter.href = '/mushroomhunter-web/#/hunter';
        };
		
		 $scope.updateHunter = function (hunter) {
            $log.info("Saving hunter with ID: " + hunter.id);
            HunterService("").update(hunter,
                    function (data, status, headers, config) {
                        $log.info("Hunter updated");
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be updated.");
                    });
        };

        $scope.deleteHunter = function (hunter) {
            $log.info("Deleting hunter with ID: " + hunter.id);
            HunterService(hunter.id).delete(
                    function (data, status, headers, config) {
                        $log.info("Hunter deleted");
                        $scope.goToHunterList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be deleted.");
                    });
        };
    }]);


//
//  HUNTER SERVICES
//
var hunterServices = angular.module('hunterServices', ['ngResource']);
hunterServices.factory('HunterService', ['$resource', function ($resource) {
        return function (hunter) {
            return $resource('rest/hunter/' + hunter + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getHunterDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray:true},
                update: {method: 'PUT'},
                delete: {method: 'DELETE'}
            });
        };
    }])
        ;