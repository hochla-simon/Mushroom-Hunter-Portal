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
        <script src="angular/lib/angular-xeditable-0.1.8/js/xeditable.js"></script>
        
        <!--Location JS -->
        <script src="angular/location/locationCtrl.js"></script>
        
        <!-- CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="angular/lib/angular-xeditable-0.1.8/css/xeditable.css">
        <link rel="stylesheet" href="angular/lib/angular-xeditable-0.1.8/css/xeditable.css">
        
        <!--Location CSS -->
        <script src="angular/location/location.css"></script>
        
    </head>
    <body>
        <h1>Mushroom Hunter Portal</h1>
        
        <div ng-view></div>
    </body>
</html>
