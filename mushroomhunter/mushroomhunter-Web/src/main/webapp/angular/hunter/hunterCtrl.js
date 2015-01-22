var hunterControllers = angular.module('hunterControllers', []);

hunterControllers.controller('HunterListCtrl', ['$scope', '$window', 'HunterService', function ($scope, $window, HunterService) {

        //Table will be ordered by location nick by default
        $scope.orderByField = 'nick';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;
        //Array of locations will be stored here
        $scope.hunters = {};

        //refresh hunter list
        $scope.refreshHunters = function () {
            HunterService("").query(
                    function (data, status, headers, config) {
                        $scope.hunters = data;
                        $log.info("List of hunter loaded.");
                    }, function (data, status, headers, config) {
                $log.info("An error occurred on server! List of hunter cannot be loaded.");
            });
        };

        //Call refresh function after app start
        $scope.refreshHunters();
        
        $scope.showHunterDetail = function (hunterId) {
            $window.location.href = '/pa165/#/hunter/detail/' + hunterId;
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
        
        $scope.goToCreateHunter = function () {
            $window.location.href = '/pa165/#/hunter/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/pa165/';
        };
    }]);


hunterControllers.controller('HunterDetailCtrl', ['$scope', '$routeParams', '$window', '$log',
    'HunterService', 'userId', 'isAdmin',
    function ($scope, $routeParams, $window, $log, HunterService, userId, isAdmin) {
        $scope.errorMessages = {};
        
        $scope.userId = userId;
        $scope.isAdmin = isAdmin;
        $log.error("User info: userId=" + $scope.userId + ", isAdmin=" + $scope.isAdmin);
        
        $scope.hunter = HunterService($routeParams.hunterId).getHunterDetail(
                function (data, status, headers, config) {
                    $log.info("Hunter detail loaded.");
                    $scope.hunter = data;
                    $scope.hunterBackup = angular.copy($scope.hunter);
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
                        $scope.errorMessages = {};
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be updated.");
                        $scope.errorMessages = data.data;
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
        
        $scope.hasPermissionToModifyEntity = function (hunter) {
            if ($scope.isAdmin != "true" && hunter.id != $scope.userId) {
                return false;
            } else {
                return true;
            }
        };
    }]);

hunterControllers.controller('HunterCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'HunterService', function ($scope, $routeParams, $window, $log, HunterService) {
        $scope.errorMessages = {
            "fieldErrors": []
        };
        
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
                        $scope.errorMessages = {};
                        $scope.showHunterDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Hunter cannot be created.");
                        $scope.errorMessages = data.data;
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