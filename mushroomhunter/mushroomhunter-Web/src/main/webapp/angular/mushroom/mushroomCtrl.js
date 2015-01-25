var mushroomControllers = angular.module('mushroomControllers', []);


mushroomControllers.controller('MushroomTypeCtrl', ['$scope', '$translate', function ($scope, $translate) {
  $translate(['EDIBLE', 'INEDIBLE', 'POISONOUS']).then(function (translations) {
    $scope.mushroomTypes = [
        {Key: 0, Value: translations.EDIBLE},
        {Key: 1, Value: translations.INEDIBLE},
        {Key: 2, Value: translations.POISONOUS}
    ];
    $scope.SelectType = {};
    
    $scope.SelectType.mushType = $scope.mushroom.type;
  });
}]);

mushroomControllers.directive('dateFix', function() {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attr, ngModel) {
            element.on('change', function() {
                scope.$apply(function () {
                    ngModel.$setViewValue(element.val());
                });         
            });
        }
    };
});


//
//  MUSHROOM LIST CONTROLLER
//
mushroomControllers.controller('MushroomListCtrl', ['$scope', '$window', '$log', 'MushroomService', 'userId', 'isAdmin', function ($scope, $window, $log, MushroomService, userId, isAdmin) {

    $scope.mushrooms = MushroomService("").query();
    
     $scope.userId = userId;
    $scope.isAdmin = isAdmin;
    
    $log.info("User info: userId=" + $scope.userId + ", isAdmin=" + $scope.isAdmin);

        $scope.refreshLocations = function () {
            MushroomService("").query(
                    function (data, status, headers, config) {
                        $scope.messages = data;
                        $log.info("List of mushroom loaded.");
                    }, function (data, status, headers, config) {
                $log.error("An error occurred on server! List of mushroom cannot be loaded.");
            });
        };

        $scope.showMushroomDetail = function (mushroomId) {
            $window.location.href = '/pa165/#/mushroom/detail/' + mushroomId;
        };
        
        $scope.goToCreateMushroom = function () {
            $window.location.href = '/pa165/#/mushroom/create';
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
        
        $scope.hasPermissionToModifyEntity = function () {
            if ($scope.isAdmin != "true") {
                return false;
            } else {
                return true;
            }
        };
    }]);

//
//  MUSHROOM DETAIL CONTROLLER
//
mushroomControllers.controller('MushroomDetailCtrl', ['$scope', '$routeParams', '$window', '$log', 'MushroomService', 'userId', 'isAdmin','$translate', function ($scope, $routeParams, $window, $log, MushroomService, userId, isAdmin, $translate) {
        $translate(['EDIBLE', 'INEDIBLE', 'POISONOUS']).then(function (translations) {

    $scope.mushroomTypes = [
        {Key: 'EDIBLE', Value: translations.EDIBLE},
        {Key: 'INEDIBLE', Value: translations.INEDIBLE},
        {Key: 'POISONOUS', Value: translations.POISONOUS}
    ];
    
    $scope.validationErrors = {};
    
    $scope.userId = userId;
    $scope.isAdmin = isAdmin;
    
    $log.info("User info: userId=" + $scope.userId + ", isAdmin=" + $scope.isAdmin);

    
        $scope.mushroom = MushroomService($routeParams.mushroomId).getMushroomDetail(
                function (data, status, headers, config) {
                    $log.info("Mushroom detail loaded.");
                     $scope.location = data;
                    $scope.locationBackup = angular.copy($scope.location);
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of mushroom cannot be loaded.");
                });
    
    $scope.startDate = new Date($scope.mushroom.startOfOccurence);
    
    $scope.updateType = function(mushroom) {
        $scope.updateMushroom(mushroom);
   
};

        $scope.refresh = function ()
        {
          $scope.mushroom = MushroomService($routeParams.mushroomId).getMushroomDetail(
                function (data, status, headers, config) {
                    $log.info("Mushroom detail loaded.");
                     $scope.location = data;
                    $scope.locationBackup = angular.copy($scope.location);
                },
                function (data, status, headers, config) {
                    $log.error("An error occurred on server! Detail of mushroom cannot be loaded.");
                });  
        };
    
        $scope.goToMushroomList = function () {
            $window.location.href = '/pa165/#/mushroom';
        };
        
        $scope.showMushroomDetail = function (mushroomId) {
            $window.location.href = '/pa165/#/mushroom/detail/' + mushroomId;
            };
            
        $scope.editMushroomDetail = function (mushroomId) {
            $window.location.href = '/pa165/#/mushroom/edit/' + mushroomId;
            };
            
        $scope.saveEditedMushroom = function (mushroom) {
            $scope.updateMushroom(mushroom);
            $scope.refresh();
            $scope.showMushroomDetail(mushroom.id);
            };

        $scope.updateMushroom = function (mushroom) {
            $log.info("Saving mushroom with ID: " + mushroom.id);
            MushroomService("").update(mushroom,
                    function (data, status, headers, config) {
                        $log.info("Mushroom updated");
                        $scope.validationErrors = {};
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Mushroom cannot be updated.");
                        $scope.validationErrors = data.data;
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
       });
       
       $scope.hasPermissionToModifyEntity = function () {
            if ($scope.isAdmin != "true") {
                return false;
            } else {
                return true;
            }
        };
    }]);

//
//  CREATE NEW MUSHROOM CONTROLLER
//
mushroomControllers.controller('MushroomCreateCtrl', ['$scope', '$routeParams', '$window', '$log', 'MushroomService', 'datepickerPopupConfig', function ($scope, $routeParams, $window, $log, MushroomService, datepickerPopupConfig) {
        $scope.mushroom = {
            "id":null,
            "name":"",
            "type": 'EDIBLE',
            "startOfOccurence": null,
            "endOfOccurence":null
        };
        
       $scope.mushroom.startOfOccurence = new Date();
       $scope.mushroom.endOfOccurence = new Date();

        $scope.goToMushroomList = function () {
            $window.location.href = '/pa165/#/mushroom';
        };
        
        $scope.createMushroom = function () {
            $log.info("Creating mushroom with name: " + $scope.mushroom.name);
            MushroomService("").create($scope.mushroom,
                    function (data, status, headers, config) {
                        $log.info("Mushroom created");
                        $scope.validationErrors = {};
                        $scope.showMushroomDetail(data);
                    },
                    function (data, status, headers, config) {
                        $log.error("An error occurred on server! Mushroom cannot be created.");
                        $scope.validationErrors = data.data;
                    });
        };
        
        $scope.showMushroomDetail = function (mushroomId) {
            $window.location.href = '/pa165/#/mushroom/detail/' + mushroomId;
            };
                  
             $scope.today = function () {
                $scope.mushroom.startOfOccurence = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.mushroom.startOfOccurence = null;
            };

            $scope.toggleMin = function () {
                $scope.minDate = $scope.minDate ? null : new Date();
            };
            $scope.toggleMin();

            $scope.dateOptions = {
                formatYear: 'yy',
                startingDay: 1
            };

            $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
            $scope.format = $scope.formats[0];

         
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