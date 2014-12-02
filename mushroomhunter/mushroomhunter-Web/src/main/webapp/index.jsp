<!DOCTYPE html>
<html ng-app="mushroomHunterApp">
    <head>
        <title>{{ 'APPNAME' | translate }}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Mushroom Hunter Portal">
        <!-- JS -->
        <script src="https://code.angularjs.org/1.3.3/angular.min.js"></script>
        <script src="js/ui-bootstrap-tpls-0.12.0.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-route.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-resource.js"></script>
        <script src="angular/mushroomHunterApp.js"></script>
        <script src="angular/homepage/mainCtrl.js"></script>
        <script src="angular/lib/angular-xeditable-0.1.8/js/xeditable.js"></script>
        <script src="js/angular-translate.js"></script>
        <script>
            var lang = window.navigator.languages ? window.navigator.languages[0] : null;
            lang = lang || window.navigator.language || window.navigator.browserLanguage || window.navigator.userLanguage;
            if (lang.indexOf('-') !== -1)
                lang = lang.split('-')[0];
            if (lang.indexOf('_') !== -1)
                lang = lang.split('_')[0];

            if ( lang == "cs" ) {
                    document.write('<script src="http://code.angularjs.org/1.0.8/i18n/angular-locale_cs-cz.js"><\/script>');
                } else if (lang == "sk") {
                    document.write('<script src="http://code.angularjs.org/1.0.8/i18n/angular-locale_sk-sk.js"><\/script>');
                }
        </script>
        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!--Location JS -->
        <script src="angular/location/locationCtrl.js"></script>
        <!--Visit JS -->
        <script src="angular/visit/visitCtrl.js"></script>
        <!--Hunter JS -->
        <script src="angular/hunter/hunterCtrl.js"></script>
        <!--Mushroom JS -->
        <script src="angular/mushroom/mushroomCtrl.js"></script>

        <!-- Fonts -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">
        <!-- CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="angular/lib/angular-xeditable-0.1.8/css/xeditable.css">
        <link rel="stylesheet" href="angular/lib/angular-xeditable-0.1.8/css/xeditable.css">
        <link href="css/business-casual.css" rel="stylesheet">
        <!--Location CSS -->
        <script src="angular/location/location.css"></script>
    </head>
    <body>
        <div class="brand">{{ 'APPNAME' | translate }}</div>
        <div class="address-bar">{{ 'PROJECT' | translate }} | {{ 'COURSENAME' | translate }} | PA165</div>
        <!-- Navigation -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                    <a class="navbar-brand" href="index.html">Business Casual</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/pa165/#/homepage">{{ 'HOME' | translate }}</a>
                        </li>
                        <li>
                            <a href="/pa165/#/location">{{ 'LLOCATION' | translate }}</a>
                        </li>
                        <li>
                            <a href="/pa165/#/mushroom">{{ 'LMUSH' | translate }}</a>
                        </li>
                        <li>
                            <a href="/pa165/#/visit">{{ 'LVISIT' | translate }}</a>
                        </li>
                        <li>
                            <a href="/pa165/#/hunter">{{ 'LHUNTER' | translate }}</a>
                        </li>
                        <li>
                            <a href="/pa165/#/contact">{{ 'CONTACT' | translate }}</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>
        <div ng-view></div>
    </body>
</html>