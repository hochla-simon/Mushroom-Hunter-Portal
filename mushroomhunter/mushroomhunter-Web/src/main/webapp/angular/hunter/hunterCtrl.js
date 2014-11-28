//Skupina controllerů pro hunter, angulární aplikace (mushroomHunterApp.js) na ní má závislost
var hunterControllers = angular.module('hunterControllers', []);

//Controller pro zobrazení listu lokací (hunterList.html)
hunterControllers.controller('HunterListCtrl', ['$scope', '$window', 'HunterService', function ($scope, $window, HunterService) {
//Objekt zapsaný v JSONu

        $scope.hunters = HunterService("").query();

        $scope.refreshHunters = function () {
            HunterService("withMushroomOccurence").query(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of hunter loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of hunter cannot be loaded.");
            });
        };

        $scope.showHunterDetail = function (hunterId) {
            $window.hunter.href = '/mushroomhunter-web/#/hunter/' + hunterId;
        };
		
		$scope.goToCreateHunter = function () {
            $window.hunter.href = '/mushroomhunter-web/#/hunter/create';
        };
        
        $scope.goToHomePage = function () {
            $window.hunter.href = '/mushroomhunter-web/';
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
                create: {method: 'PUT'},
                delete: {method: 'DELETE'}
            });
        };
    }])
        ;