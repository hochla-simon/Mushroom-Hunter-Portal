<!DOCTYPE html>
<html ng-app="mushroomHunterApp">
    <head>
        <title>Mushroom Hunter Portal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- JS -->
        <script src="https://code.angularjs.org/1.3.3/angular.min.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-route.js"></script>
        <script src="//code.angularjs.org/1.3.3/angular-resource.js"></script>
        <script src="angular/mushroomHunterApp.js"></script>
        <script src="angular/homepage/mainCtrl.js"></script>
        <script src="angular/lib/angular-xeditable-0.1.8/js/xeditable.js"></script>

        <!--Location JS -->
        <script src="angular/location/locationCtrl.js"></script>

        <!--Visit JS -->
        <script src="angular/visit/visitCtrl.js"></script>

        <!--Visit JS -->
        <script src="angular/hunter/hunterCtrl.js"></script>
        
        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Bootstrap Datepicker CSS -->
        <link href="css/bootstrap-datepicker.css" rel="stylesheet">

        <!-- Bootstrap Datepicker JS -->
        <script src="js/boostrap-datepicker.js"></script>

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
        <div class="brand">Mushroom Hunter Portal</div>
        <div class="address-bar">Projekt | Vývoj programových systém&#367 v jazyce Java | PA165</div>

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
                            <a href="/mushroomhunter-web/#/homepage">Home</a>
                        </li>
                        <li>
                            <a href="/mushroomhunter-web/#/location">List of Locations</a>
                        </li>
                        <li>
                            <a href="/mushroomhunter-web/#/visit">List of Visits</a>
                        </li>
                        <li>
                            <a href="/mushroomhunter-web/#/hunter">List of Hunters</a>
                        </li>
                        <li>
                            <a href="/mushroomhunter-web/#/contact">Contact</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <div ng-view></div>
        
        <!-- jQuery -->
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>

        <!-- Script to Activate the Carousel -->
        <script>
            $('.carousel').carousel({
                interval: 5000 //changes the speed
            })
        </script>


        <script>
            $.fn.datepicker.defaults.format = "mm/dd/yyyy";
            $('.datepicker').datepicker({
                startDate: '-3d'
            });
        </script>
    </body>
</html>
