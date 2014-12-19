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
    'pascalprecht.translate',
    'ui.bootstrap'
]);

app.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
                //říká, že když do URL zadám http://localhost:8080/pa165/#/homepage
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
                when('/mushroom/detail/:mushroomId', {
                    templateUrl: 'angular/mushroom/mushroomDetail.html',
                    controller: 'MushroomDetailCtrl'
                }).
                        when('/index2.jsp', {
                    templateUrl: 'index2.jsp',
                    controller: 'CreateVisitCtrl'
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
    CREATENEWVISIT: 'Create new visit',
    BACKTOVL: 'Back to visit list',
    DELETEVISIT: 'Delete visit',
    
    EDIBLE: 'Edible',
    INEDIBLE: 'Inedble',
    POISONOUS: 'Poisonous',
    
    DELETE: 'Delete',
    REMOVE: 'Remove',
    AMOUNT: 'Amount',
    LISTOFFOUNDMUSHROOMS: 'List of found mushrooms',
    FOUNDTYPESAMOUNT: 'Number of found types',
    MUSHROOM: 'Mushroom',
    OR: 'Or',
    FOUNDMUSHROOM: 'Found mushroom',
    ADDTOLIST: 'Add to the list',
    
    CLEAR: 'Clear',
    TODAY: 'Today',
    CLOSE: 'Close',
    
    SETMUSHNAME: 'Set mushroom name',
    SETHUNTERNAME: 'Set hunter first name',
    SETHUNTERSURNAME: 'Set hunter surname',
    SETHUNTERNICK: 'Set hunter nick',
    SETLOCATIONNAME: 'Set location name',
    SETDESCRIPTION: 'Set description',
    SETNEARCITY: 'Set near city',
    
    FILTERMUSH: 'Filter mushrooms',
    FILTERHUNTER: 'Filter hunters',
    FILTERLOCATION: 'Filter locations',
    FILTERVISIT: 'Filter visits'
    
  })
  .translations('cs', {
    WELCOMETO: 'V\u00edtejte na',
    APPNAME: 'Port\u00e1l houba\u0159\u016f',
    MEMBERS: '\u010clenov\u00e9 t\u00fdmu',
    TASKDES: 'Popis \u00falohy',
    PROJECT: 'Projekt',
    PROJDES: 'Tato aplikace je \u0161koln\u00edm projektem, jeho\u017e c\u00edlem je vytvo\u0159en\u00ed jednoduch\u00e9ho port\u00e1lu, na kter\u00e9m si houba\u0159i mohou vym\u011bnovat informace o m\u00edstech bohat\u00fdch \u010di chud\u00fdch na ur\u010dit\u00fd druh hub. Houba\u0159 m\u00e1 k\u0159estn\u00ed jm\u00e9no, p\u0159\u00edjmen\u00ed a p\u0159ezd\u00edvku. M\u00edsto m\u00e1 jm\u00e9no, popis a nebli\u017e\u0161\u00ed m\u011bsto. N\u00e1v\u0161t\u011bva je relace mezi houba\u0159em a m\u00edstem, m\u00e1 datum n\u00e1vst\u011bvy a popis. Houba m\u00e1 jm\u00e9no, typ, za\u010d\u00e1tek a konec v\u00fdskytu.',
    COURSENAME: 'V\u00fdvoj programov\u00fdch s\u00fdst\u00e9m\u016f v jazyce Java',
    HOME: 'Uvodn\u00ed str\u00e1nka',
    LLOCATION: 'Seznam m\u00edst',
    LHUNTER: 'Seznam houba\u0159\u016f',
    LMUSH: 'Seznam hub',
    LVISIT: 'Seznam n\u00e1v\u0161t\u011bv',
    CONTACT: 'Kontakt',
    
    LOCNAME: 'N\u00e1zev lokace',
    LOCDETAIL: 'Detail lokace',
    DESCRIPTION: 'Popis',
    NEARC: 'Nejbli\u017e\u0161\u00ed m\u011bsto',
    MUSHOCCURENCE: 'V\u00fdskyt hub',
    SHOWDETAIL: 'Zobrazit detail',
    CREATENEWLOC: 'Vytvo\u0159it nov\u00e9 m\u00edsto',
    REFRESH: 'Obnovit',
    BACKTOHP: 'Zp\u011bt na \u00favodn\u00ed str\u00e1nku',
    PROPERTY: 'Polo\u017eka',
    VALUE: 'Hodnota',
    CREATELOC: 'Vyvo\u0159it m\u00edsto',
    BACKTOLL: 'Zp\u011bt na seznam m\u00edst',
    DELETELOC: 'Smazat m\u00edsto',
    
    HUNTERDETAIL: 'Detail houba\u0159e',
    FIRSTNAME: 'K\u0159estn\u00ed jm\u00e9no',
    SURNAME: 'P\u0159\u00edjmen\u00ed',
    NICKNAME: 'P\u0159ezd\u00edvka',
    CREATEHUNTER: 'Vytvo\u0159it houba\u0159e',
    CREATENEWHUNTER: 'Vytvo\u0159it nov\u00e9ho houba\u0159e',
    BACKTOHL: 'Zpet na seznam houba\u0159\u016f',
    DELETEHUNTER: 'Smazat houba\u0159e',
    
    MUSHDETAIL: 'Detail houby',
    NAME: 'Jm\u00e9no',
    TYPE: 'Typ',
    STARTOFO: 'Za\u010d\u00e1tek v\u00fdskytu',
    ENDOFO: 'Konec v\u00fdskytu',
    CREATEMUSH: 'Vytvo\u0159it houbu',
    CREATENEWMUSH: 'Vytvo\u0159it novou houbu',
    BACKTOML: 'Zp\u011bt na seznam hub',
    DELETEMUSH: 'Vymazat houbu',
    
    VISITDETAIL: 'Detail n\u00e1v\u0161t\u011bvy',
    DATE: 'Datum',
    LOC: 'M\u00edsto',
    HUNTER: 'Houba\u0159',
    CREATEVISIT: 'Vytvo\u0159it n\u00e1v\u0161t\u011bvu',
    CREATENEWVISIT: 'Vytvo\u0159it novou n\u00e1v\u0161t\u011bvu',
    BACKTOVL: 'Zp\u011bt na seznam n\u00e1v\u0161t\u011bv',
    DELETEVISIT: 'Smazat n\u00e1v\u0161t\u011bvu',
    
    EDIBLE: 'Jedl\u00e1',
    INEDIBLE: 'Nejedl\u00e1',
    POISONOUS: 'Jedovat\u00e1',
    
    DELETE: 'Smazat',
    REMOVE: 'Odebrat',
    AMOUNT: 'Mno\u017estv\u00ed',
    LISTOFFOUNDMUSHROOMS: 'Seznam nalezen\u00fdch hub',
    FOUNDTYPESAMOUNT: 'Po\u010det nalezen\u00fdch druh\u016f',
    MUSHROOM: 'Houba',
    OR: 'Nebo',
    FOUNDMUSHROOM: 'Nalezen\u00e1 houba',
    ADDTOLIST: 'P\u0159idat do seznamu',
    
    CLEAR: 'Vymazat',
    TODAY: 'Dnes',
    CLOSE: 'Zav\u0159\u00edt',
    
    SETMUSHNAME: 'Zadejte jm\u00e9no houby',
    SETHUNTERNAME: 'Zadejte jm\u00e9no houba\u0159e',
    SETHUNTERSURNAME: 'Zadejte p\u0159\u00edjmen\u00ed houba\u0159e',
    SETHUNTERNICK: 'Zadejte p\u0159ezd\u00edvku houba\u0159e',
    SETLOCATIONNAME: 'Zadejte jm\u00e9no m\u00edsta',
    SETDESCRIPTION: 'Zadejte popis',
    SETNEARCITY: 'Zadejte nejbli\u017e\u0161\u00ed m\u011bsto',
    
    FILTERMUSH: 'Filtr hub',
    FILTERHUNTER: 'Filtr houba\u0159\u016f',
    FILTERLOCATION: 'Filtr m\u00edst',
    FILTERVISIT: 'Filtr n\u00e1v\u0161t\u011bv'
    
    
    
   }).fallbackLanguage('en')
  .determinePreferredLanguage();
});

app.run(function (editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme.
});