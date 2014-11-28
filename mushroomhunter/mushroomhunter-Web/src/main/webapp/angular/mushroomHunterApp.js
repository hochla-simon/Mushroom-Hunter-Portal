//Vytvoří naši angulární aplikaci, ta má dependence na kontrolery pro jednotlivé UC
var app = angular.module('mushroomHunterApp', [
    'ngRoute',
    'xeditable',
    'mainControllers',
    'locationControllers',
    'locationServices',
    'visitControllers',
    'visitServices',
    'mushroomControllers',
    'mushroomServices',
    'hunterControllers',
    'hunterServices'
]);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                //říká, že když do URL zadám http://localhost:8080/mushroomhunter-web/#/homepage
                //tak mi angular do stránky vloží obsah z angular/homepage/homepage.html
                //Vnitřek homepage.html bude obládán kontrolerem MainCtrl
                when('/homepage', {
                    templateUrl: 'angular/homepage/homepage.html',
                    controller: 'MainCtrl'
                }).
                when('/location', {
                    templateUrl: 'angular/location/locationList.html',
                    controller: 'LocationListCtrl'
                }).
                when('/location/detail/:locationId', {
                    templateUrl: 'angular/location/locationDetail.html',
                    controller: 'LocationDetailCtrl'
                }).
                when('/location/create', {
                    templateUrl: 'angular/location/locationCreate.html',
                    controller: 'LocationCreateCtrl'
                }).
                when('/contact', {
                    templateUrl: 'angular/contact/contact.html',
                }).
                when('/visit', {
                    templateUrl: 'angular/visit/visitList.html',
                    controller: 'VisitListCtrl'
                }).
                when('/visit/create', {
                    templateUrl: 'angular/visit/visitCreate.html',
                    controller: 'VisitCreateCtrl'
                }).
                when('/visit/detail/:visitId', {
                    templateUrl: 'angular/visit/visitDetail.html',
                    controller: 'VisitDetailCtrl'
                }).
                when('/hunter', {
                    templateUrl: 'angular/hunter/hunterList.html',
                    controller: 'HunterListCtrl'
                }).
                when('/hunter/detail/:hunterId', {
                    templateUrl: 'angular/hunter/hunterDetail.html',
                    controller: 'HunterDetailCtrl'
                }).
                when('/hunter/create', {
                    templateUrl: 'angular/hunter/hunterCreate.html',
                    controller: 'HunterCreateCtrl'
                }). 
when('/mushroom', {
                    templateUrl: 'angular/mushroom/mushroomList.html',
                    controller: 'MushroomListCtrl'
                }).
                 when('/mushroom/create', {
                    templateUrl: 'angular/mushroom/mushroomCreate.html',
                    controller: 'MushroomCreateCtrl'
                }). 
                otherwise({
                    redirectTo: '/homepage'
                });
    }]);

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme.
});