var hunterControllers = angular.module('hunterControllers', []);

hunterControllers.controller('HunterListCtrl', ['$scope', '$window', 'HunterService', function ($scope, $window, HunterService) {

        $scope.hunters = HunterService("").query();

        $scope.refreshHunters = function () {
            HunterService("").query(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of hunter loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of hunter cannot be loaded.");
            });
        };

        $scope.showHunterDetail = function (hunterId) {
            $window.location.href = '/pa165/#/hunter/detail/' + hunterId;
        };

        $scope.goToCreateHunter = function () {
            $window.location.href = '/pa165/#/hunter/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/pa165/';
        };
    }]);


hunterControllers.controller('HunterDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'HunterService',
    function ($scope, $routeParams, $window, $log, HunterService) {
        $scope.validationErrors = {};
        $scope.hunter = HunterService($routeParams.hunterId).getHunterDetail(
                function (data, status, headers, config) {
                    $log.info("Hunter detail loaded.");
                    $scope.location = data;
                    $scope.locationBackup = angular.copy($scope.location);
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of hunter cannot be loaded.");
                });

        $scope.goToHunterList = function () {
            $window.location.href = '/pa165/#/hunter';
        };

        $scope.updateHunter = function (hunter) {
            $log.info("Saving hunter with ID: " + hunter.id);
            HunterService("").update(hunter,
                    function (data, status, headers, config) {
                        $log.info("Hunter updated");
                        $scope.validationErrors = {};
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be updated.");
                        $scope.validationErrors = data.data;
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

hunterControllers.controller('HunterCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'HunterService', function ($scope, $routeParams, $window, $log, HunterService) {
        $scope.validationErrors = {
            "fieldErrors": []
        }
        
        $scope.hunter = {
            "id": null,
            "firstName": "",
            "Surname": "",
            "description": "",
            "nick": ""
        };

        $scope.goToHunterList = function () {
            $window.location.href = '/pa165/#/hunter';
        };

        $scope.createHunter = function () {
            $log.info("Creating hunter with name: " + $scope.hunter.name);
            HunterService("").create($scope.hunter,
                    function (data, status, headers, config) {
                        $log.info("Hunter created");
                        $scope.validationErrors = {};
                        $scope.showHunterDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be created.");
                        $scope.validationErrors = data.data;
                    });
        };

        $scope.showHunterDetail = function (hunterId) {
            $window.location.href = '/pa165/#/hunter/detail/' + hunterId;
        };
    }]);

var hunterServices = angular.module('hunterServices', ['ngResource']);
hunterServices.factory('HunterService', ['$resource', function ($resource) {
        return function (hunter) {
            return $resource('rest/hunter/' + hunter + ":param", {}, {
                query: {method: 'GET', isArray: true},
                getHunterDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false}
            });
        };
    }])
        ;