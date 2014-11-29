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
    'hunterServices',
    'pascalprecht.translate'
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
                    templateUrl: 'angular/contact/contact.html'
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

app.config(function($translateProvider) {
    $translateProvider.translations('en', {
    WELCOMETO: 'Welcome to',
    APPNAME: 'Mushroom Hunter Portal',
    MEMBERS: 'Members',
    TASKDES: 'Task Description',
    PROJECT: 'Project',
    PROJDES: 'This is a school project, goal is to develop a simple mushroom hunter portal where mushroom hunters can exchange information about locations rich for mushrooms and also locations that are poor for mushrooms. The mushroom hunter has these attributes: the firstname, the surname, the nickname. A location has the name, the nearest city and the description of its locality. A visit of the location is a relationship between a mushroom hunter and a location and is described by a date of visit and note (e.g. "it was very dry, no mushrooms found"). A mushroom has the name, the type (edible, unedible, poisonous), the start of the occurence and the end of the occurence. The mushroom catalogue should be searchable, filterable and sortable.',
    COURSENAME: 'Enterprise Applications in Java',
    HOME: 'Home',
    LLOCATION: 'List of Locations',
    LHUNTER: 'List of Hunters',
    LMUSH: 'List of Mushrooms',
    LVISIT: 'List of Visits',
    CONTACT: 'Contact',
    
    LOCNAME: 'Location name',
    LOCDETAIL: 'Location detail',
    DESCRIPTION: 'Description',
    NEARC: 'Near city',
    MUSHOCCURENCE: 'Mushroom occurence',
    SHOWDETAIL: 'Show detail',
    CREATENEWLOC: 'Create new location',
    REFRESH: 'Refresh',
    BACKTOHP: 'Back to homepage',
    PROPERTY: 'Property',
    VALUE: 'Value',
    CREATELOC: 'Create location',
    BACKTOLL: 'Back to location list',
    DELETELOC: 'Delete location',
    
    HUNTERDETAIL: 'Hunter detail',
    FIRSTNAME: 'First name',
    SURNAME: 'Surname',
    NICKNAME: 'Nickname',
    CREATEHUNTER: 'Create hunter',
    CREATENEWHUNTER: 'Create new hunter',
    BACKTOHL: 'Back to hunter list',
    DELETEHUNTER: 'Delete hunter',
    
    MUSHDETAIL: 'Mushroom detail',
    NAME: 'Name',
    TYPE: 'Type',
    STARTOFO: 'Start of occurence',
    ENDOFO: 'End of occurence',
    CREATEMUSH: 'Create mushroom',
    CREATENEWMUSH: 'Create new mushroon',
    BACKTOML: 'Back to mushroom list',
    DELETEMUSH: 'Delete mushroom',
    
    VISITDETAIL: 'Visit detail',
    DATE: 'Date',
    LOC: 'Location',
    HUNTER: 'Hunter',
    CREATEVISIT: 'Create visit',
    CREATENEWVISIT: 'Create novou visit',
    BACKTOVL: 'Back to visit list',
    DELETEVISIT: 'Delete visit'
    
  })
  .translations('cs', {
    WELCOMETO: 'Vitejte na',
    APPNAME: 'Portal houbaru',
    MEMBERS: 'Clenove tymu',
    TASKDES: 'Popis ulohy',
    PROJECT: 'Projekt',
    PROJDES: 'Tato aplikace je skolnim projektem, jehoz cilem je vytvoreni jednoducheho portalu houvbaru, kde si houbari mohou vymenovat informace o mistech bohatych ci chudych na urcity druh hub. Houbar ma krestni jmeno, prijmeni, prezdivku, misto ma jmeno, popis, neblizsi mesto, navsteva je relace mezi houbarem a mistem, ma datum navstevy a popis. Houba ma jmeno, typ, zacatek a konec vyskytu.',
    COURSENAME: 'Vyvoj programovych systemu v jazyce Java',
    HOME: 'Uvodni stranka',
    LLOCATION: 'Seznam mist',
    LHUNTER: 'Seznam houbaru',
    LMUSH: 'Seznam hub',
    LVISIT: 'Seznam navstev',
    CONTACT: 'Kontakt',
    
    LOCNAME: 'Nazev lokace',
    LOCDETAIL: 'Detail lokace',
    DESCRIPTION: 'Popis',
    NEARC: 'Nejblizsi mesto',
    MUSHOCCURENCE: 'Vyskyt hub',
    SHOWDETAIL: 'Ukaz detail',
    CREATENEWLOC: 'Vytvorit nove misto',
    REFRESH: 'Obnovit',
    BACKTOHP: 'Zpet na uvodni stranku',
    PROPERTY: 'Polozka',
    VALUE: 'Hodnota',
    CREATELOC: 'Vyvorit misto',
    BACKTOLL: 'Zpet na seznam mist',
    DELETELOC: 'Smazat misto',
    
    HUNTERDETAIL: 'Detail houbare',
    FIRSTNAME: 'Krestni jmeno',
    SURNAME: 'Prijmeni',
    NICKNAME: 'Prezdivka',
    CREATEHUNTER: 'Vyvorit houbare',
    CREATENEWHUNTER: 'Vytvorit noveho houbare',
    BACKTOHL: 'Zpet na seznam houbaru',
    DELETEHUNTER: 'Smazat houbare',
    
    MUSHDETAIL: 'Detail houby',
    NAME: 'Jmeno',
    TYPE: 'Typ',
    STARTOFO: 'Zacatek vyskytu',
    ENDOFO: 'Konec vyskytu',
    CREATEMUSH: 'Vyvorit houbu',
    CREATENEWMUSH: 'Vyvorit novou houbu',
    BACKTOML: 'Zpet na seznam hub',
    DELETEMUSH: 'Vymazat houbu',
    
    VISITDETAIL: 'Detail navstevy',
    DATE: 'Datum',
    LOC: 'Misto',
    HUNTER: 'Houbar',
    CREATEVISIT: 'Vyvorit navstevu',
    CREATENEWVISIT: 'Vytvorit novou navstevu',
    BACKTOVL: 'Zpet na seznam navstev',
    DELETEVISIT: 'Smazat navstevu'
    
    
    
    
  });
  $translateProvider.determinePreferredLanguage();
});

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme.
});