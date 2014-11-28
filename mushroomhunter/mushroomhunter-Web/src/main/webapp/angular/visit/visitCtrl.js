var visitControllers = angular.module('visitControllers', []);

//
//  VISIT LIST CONTROLLER
//
visitControllers.controller('VisitListCtrl', ['$scope', '$window', '$log', 'VisitService', function ($scope, $window, $log, VisitService) {

        $scope.visits = VisitService("").query();

        $scope.refreshVisits = function () {
            
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


//
//  CREATE NEW Visit CONTROLLER
//
visitControllers.controller('VisitCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', function ($scope, $routeParams, $window, $log, VisitService) {
        $scope.visit = {
            "id":null,
            "hunter": null,
            "date": null,
            "location": null,
            "foundMushrooms":null
        };

        $scope.goToVisitList = function () {
            $window.location.href = '/mushroomhunter-web/#/visit';
        };

        $scope.createVisit = function () {
            $log.info("Creating new visit");
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
                create: {method: 'POST', isArray:true},
                update: {method: 'PUT', isArray:false},
                delete: {method: 'DELETE', isArray:false}
            });
        };
    }])
        ;