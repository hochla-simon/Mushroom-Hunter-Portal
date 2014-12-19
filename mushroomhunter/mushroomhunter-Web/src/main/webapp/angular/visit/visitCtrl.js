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
            $window.location.href = '/pa165/#/visit/detail/' + visitId;
        };

        $scope.goToCreateVisit = function () {
            $window.location.href = '/pa165/#/visit/create';
        };

        $scope.goToHomePage = function () {
            $window.location.href = '/pa165/';
        };
    }]);

//visitControllers.controller

//
//  CREATE NEW VISIT CONTROLLER
//
visitControllers.controller('VisitCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', 'LocationService', 'MushroomService', 'HunterService', 'datepickerPopupConfig', '$translate', function ($scope, $routeParams, $window, $log, VisitService, LocationService, MushroomService, HunterService, datepickerPopupConfig, $translate) {
        $translate(['TODAY', 'CLEAR', 'CLOSE']).then(function (translations) {
            $scope.visit = {
                "id": null,
                "hunter": null,
                "date": null,
                "location": null,
                "foundMushrooms": null
            };

            $scope.hunters = HunterService("").query();

            $scope.locations = LocationService("").query();

            $scope.mushrooms = MushroomService("").query();

            $scope.goToCreateLocation = function () {
                $window.location.href = '/pa165/#/location/create';
            };

            $scope.goToVisitList = function () {
                $window.location.href = '/pa165/#/visit';
            };

            $scope.createVisit = function () {
                $log.info("Creating new visit");
                $scope.visit.location = $scope.location;
                $scope.visit.hunter = $scope.hunter;
                //$scope.visit.foundMushrooms = $scope.mushroomItems;
                //angular.forEach($scope.mushroomItems, function (item) {
                //    $scope.mush
                //});
                VisitService("").create($scope.visit,
                        function (data, status, headers, config) {
                            $log.info("Visit created");
                            $scope.showVisitDetail(data);
                        },
                        function (data, status, headers, config) {
                            $log.error("An error occurred on server! Visit cannot be created.");
                        });
            };

            $scope.showVisitDetail = function (visitId) {
                $window.location.href = '/pa165/#/visit/detail/' + visitId;
            };

            $scope.mushroomItems = [];
            
            $scope.mushroomMap = [];

            $scope.addMushroomItem = function () {

                $scope.mushroomItems.push({
                    name: $scope.mushroom,
                    amount: $scope.mushroomsAmount
                });

                // Clear input fields after push
                $scope.mushroom = "";
                $scope.mushroomsAmount = "";

            };

            // Remove selected mushroom from the list
            $scope.removeItem = function (index) {
                $scope.mushroomItems.splice(index, 1);
            };

            // Get total count of found mushrooms
            $scope.getTotalMushrooms = function () {
                return $scope.mushroomItems.length;
            };

            $scope.today = function () {
                $scope.visit.date = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.visit.date = null;
            };

            // Disable weekend selection
            //$scope.disabled = function (date, mode) {
            //    return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
            //};

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

            datepickerPopupConfig.currentText = translations.TODAY;
            datepickerPopupConfig.clearText = translations.CLEAR;
            datepickerPopupConfig.closeText = translations.CLOSE;
        });
    }]);

//
//  VISIT DETAIL CONTROLLER
//
//visitControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', function ($scope, $routeParams, $window, $log, VisitService) {
visitControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', 'LocationService', 'datepickerPopupConfig', '$translate', function ($scope, $routeParams, $window, $log, VisitService, LocationService, datepickerPopupConfig, $translate) {
        $translate(['TODAY', 'CLEAR', 'CLOSE']).then(function (translations) {

            $scope.visit = VisitService($routeParams.visitId).getVisitDetail(
                    function (data, status, headers, config) {
                        $log.info("Visit detail loaded.");
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Detail of visit cannot be loaded.");
                    });
            $scope.goToVisitList = function () {
                $window.location.href = '/pa165/#/visit';
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

            $scope.today = function () {
                $scope.visit.date = new Date();
            };
            $scope.today();
            $scope.clear = function () {
                $scope.visit.date = null;
            };
            // Disable weekend selection
            //$scope.disabled = function (date, mode) {
            //   return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
            //};
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
            datepickerPopupConfig.currentText = translations.TODAY;
            datepickerPopupConfig.clearText = translations.CLEAR;
            datepickerPopupConfig.closeText = translations.CLOSE;
        });
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