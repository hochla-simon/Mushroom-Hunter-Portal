<!DOCTYPE html>
<html ng-app="mushroomHunterApp">
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- JS -->
        <script src="https://code.angularjs.org/1.3.3/angular.min.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-route.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-resource.js"></script>
        <script src="angular/mushroomHunterApp.js"></script>
        <script src="angular/homepage/mainCtrl.js"></script>
        <script src="angular/location/locationCtrl.js"></script>
        
        <!-- CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.css">
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <!-- Tento div se bude nahrazovat obsahem různých stránek, na které bude uživatel přecházet. Např domovská stránka, list lokací atd.-->
        <div ng-view></div>
    </body>
</html>
