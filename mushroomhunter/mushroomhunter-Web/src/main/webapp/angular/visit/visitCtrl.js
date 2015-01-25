var visitControllers = angular.module('visitControllers', []);

//
//  VISIT LIST CONTROLLER
//
visitControllers.controller('VisitListCtrl', ['$scope', '$window', '$log', 'VisitService', 'userId', 'isAdmin', function ($scope, $window, $log, VisitService, userId, isAdmin) {

        //Table will be ordered by visit id by default
        $scope.orderByField = 'id';
        //Table will be ordered ascending by default
        $scope.reverseSort = false;

        $scope.visits = VisitService("").query();

        $scope.userId = userId;
        $scope.isAdmin = isAdmin;

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

        $scope.deleteVisit = function (visit) {
            $log.info("Deleting visit with ID: " + visit.id);
            VisitService(visit.id).delete(
                    function (data, status, headers, config) {
                        $log.info("Visit deleted");
                        $scope.goToVisitList();
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Visit cannot be deleted.");
                    });
            $scope.visits.splice(visit, 1);
            $scope.$apply();
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

//visitControllers.controller

//
//  CREATE NEW VISIT CONTROLLER
//
visitControllers.controller('VisitCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', 'LocationService', 'MushroomService', 'HunterService', 'datepickerPopupConfig', '$translate', 'userId', 'isAdmin', function ($scope, $routeParams, $window, $log, VisitService, LocationService, MushroomService, HunterService, datepickerPopupConfig, $translate, userId, isAdmin) {
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

            $scope.hunter = HunterService(userId).getHunterDetail(
                    function (data, status, headers, config) {
                        $log.info("Hunter detail loaded.");
                        $scope.currentUser = data;
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Detail of current user cannot be loaded.");
                    });

            $scope.validationErrors = {};

            $scope.userId = userId;
            $scope.isAdmin = isAdmin;

            $log.info("User info: userId=" + $scope.userId + ", isAdmin=" + $scope.isAdmin);

            $scope.goToCreateLocation = function () {
                $window.location.href = '/pa165/#/location/create';
            };

            $scope.goToCreateHunter = function () {
                $window.location.href = '/pa165/#/hunter/create';
            };

            $scope.goToVisitList = function () {
                $window.location.href = '/pa165/#/visit';
            };

            var arr = {}; // {} will create an object

            $scope.createVisit = function () {
                $log.info("Creating new visit");
                $scope.visit.location = $scope.location;

                if (!isAdmin) {
                    $scope.visit.hunter = $scope.currentUser;
                } else {
                    $scope.visit.hunter = $scope.hunter;
                }

                $scope.visit.foundMushrooms = arr;


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

            $scope.addMushroomItem = function () {

                $scope.mushroomItems.push({
                    name: $scope.mushroom,
                    amount: $scope.mushroomsAmount
                });

                if (arr[$scope.mushroom.id] == null) {
                    arr[$scope.mushroom.id] = $scope.mushroomsAmount;
                } else {
                    arr[$scope.mushroom.id] = arr[$scope.mushroom.id] + $scope.mushroomsAmount;
                }

                // Clear input fields after push
                $scope.mushroomsAmount = "";

            };

            // Remove selected mushroom from the list
            $scope.removeItem = function (index, id, amount) {
                $scope.mushroomItems.splice(index, 1);
                arr[id] = arr[id] - amount;
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
                //$scope.minDate = $scope.minDate ? null : new Date();
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
visitControllers.controller('VisitDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'VisitService', 'MushroomService', 'datepickerPopupConfig', '$translate', 'userId', 'isAdmin', function ($scope, $routeParams, $window, $log, VisitService, MushroomService, datepickerPopupConfig, $translate, userId, isAdmin) {
        $translate(['TODAY', 'CLEAR', 'CLOSE']).then(function (translations) {

            //$scope.mushrooms = MushroomService("").query();

            //$scope.getMushroomById = function (mushroomId) {
            //    $scope.mushroom = MushroomService(4).getMushroomDetail();
            //    return $scope.mushroom;
            //};

            $scope.userId = userId;
            $scope.isAdmin = isAdmin;

            $scope.visit = VisitService($routeParams.visitId).getVisitDetail(
                    function (data, status, headers, config) {
                        $log.info("Visit detail loaded.");
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Detail of visit cannot be loaded.");
                    });

            if (visit.hunter.id == userId || isAdmin) {
                $scope.showDeleteBtn = true;
            } else {
                $scope.showDeleteBtn = false;
            }


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

            $scope.showHunterDetail = function (hunterId) {
                $window.location.href = '/pa165/#/hunter/detail/' + hunterId;
            };

            $scope.deleteVisit = function (visit) {
                $log.info("Deleting visit with ID: " + visit.id);
                VisitService(visit.id).delete(
                        function (data, status, headers, config) {
                            $log.info("Visit deleted");
                            $scope.goToVisitList();
                        },
                        function (data, status, headers, config) {
                            $log.error("An error occurred on server! Visit cannot be deleted.");
                        });
            };


            $scope.showLocationDetail = function (locationId) {
                $window.location.href = '/pa165/#/location/detail/' + locationId;
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
                //$scope.minDate = $scope.minDate ? null : new Date();
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