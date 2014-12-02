var visitControllers = angular.module('visitControllers', []);

//
//  VISIT LIST CONTROLLER
//
visitControllers.controller('VisitListCtrl', ['$scope', '$window', '$log', 'VisitService', function ($scope, $window, $log, VisitService) {

        $scope.visits = VisitService("").query();

        $scope.refreshVisits = function () {
            VisitService("").query(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of visits loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of visits cannot be loaded.");
            });
        };

        $scope.showVisitDetail = function (visitId) {
            $window.location.href = '/mushroomhunter-web/#/visit/detail/' + visitId;
        };

        $scope.goToCreateVisit = function () {
            $window.location.href = '/mushroomhunter-web/#/visit/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/mushroomhunter-web/';
        };
    }]);

//visitControllers.controller

//
//  CREATE NEW VISIT CONTROLLER
//
visitControllers.controller('VisitCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', 'LocationService', 'datepickerPopupConfig', function ($scope, $routeParams, $window, $log, VisitService, LocationService, datepickerPopupConfig) {
        $scope.visit = {
            "id": null,
            "hunter": null,
            "date": null,
            "location": null,
            "foundMushrooms": null
        };

        $scope.locations = LocationService("").query();

        $scope.goToCreateLocation = function () {
            $window.location.href = '/mushroomhunter-web/#/location/create';
        };

        $scope.goToVisitList = function () {
            $window.location.href = '/mushroomhunter-web/#/visit';
        };

        $scope.createVisit = function () {
            $log.info("Creating new visit");
            $scope.visit.location = $scope.location;
            VisitService("").create($scope.visit,
                    function (data, status, headers, config) {
                        $log.info("Visit created");
                        $scope.showLocationDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Visit cannot be created.");
                    });
        };

        $scope.showVisitDetail = function (visitId) {
            $window.location.href = '/mushroomhunter-web/#/visit/detail/' + visitId;
        };

        $scope.today = function () {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };

        // Disable weekend selection
        $scope.disabled = function (date, mode) {
            return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
        };

        $scope.toggleMin = function () {
            $scope.minDate = $scope.minDate ? null : new Date();
        };
        $scope.toggleMin();

        $scope.open = function ($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

        $scope.dateOptions = {
            formatYear: 'yy',
            startingDay: 1
        };

        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[0];
        
        datepickerPopupConfig.currentText = 'Aujourd\'hui';

    }]);

//
//  VISIT DETAIL CONTROLLER
//
visitControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', function ($scope, $routeParams, $window, $log, VisitService) {

        $scope.visit = VisitService($routeParams.visitId).getVisitDetail(
                function (data, status, headers, config) {
                    $log.info("Visit detail loaded.");
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of visit cannot be loaded.");
                });

        $scope.goToVisitList = function () {
            $window.location.href = '/mushroomhunter-web/#/visit';
        };

        $scope.updateVisit = function (visit) {
            $log.info("Saving visit with ID: " + visit.id);
            VisitService("").update(visit,
                    function (data, status, headers, config) {
                        $log.info("Visit updated");
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Visit cannot be updated.");
                    });
        };

        $scope.deleteVisit = function (visit) {
            $log.info("Deleting location with ID: " + visit.id);
            VisitService(visit.id).delete(
                    function (data, status, headers, config) {
                        $log.info("Visit deleted");
                        $scope.goToVisitList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Visit cannot be deleted.");
                    });
        };
    }]);

//
//  VISIT SERVICES
//
var visitServices = angular.module('visitServices', ['ngResource']);
visitServices.factory('VisitService', ['$resource', function ($resource) {
        return function (visit) {
            return $resource('rest/visit/' + visit + ':param', {}, {
                query: {method: 'GET', isArray: true},
                getVisitDetail: {method: 'GET', isArray: false},
                create: {method: 'POST', isArray: true},
                update: {method: 'PUT', isArray: false},
                delete: {method: 'DELETE', isArray: false}
            });
        };
    }]);