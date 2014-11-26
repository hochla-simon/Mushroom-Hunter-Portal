//Vytvoří naši angulární aplikaci, ta má dependence na kontrolery pro jednotlivé UC
var app = angular.module('mushroomHunterApp', [
    'ngRoute',
    'mainControllers',
    'locationControllers',
    'locationServices'
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
                when('/location/:locationId', {
                    templateUrl: 'angular/location/locationDetail.html',
                    controller: 'LocationDetailCtrl'
                }).
                otherwise({
                    redirectTo: '/homepage'
                });
    }]);