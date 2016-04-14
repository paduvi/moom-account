<html xmlns="http://www.w3.org/1999/xhtml"><head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="roussounelos nikos">

<link rel="stylesheet" href="css/main.css" type="text/css" media="screen, projection"> <!-- main stylesheet -->
<link rel="stylesheet" type="text/css" media="all" href="css/tipsy.css"> <!-- Tipsy implementation -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> <!-- jQuery implementation -->
<script type="text/javascript" src="scripts/custom-scripts.js"></script><!-- All of my custom scripts -->
<script type="text/javascript" src="scripts/jquery.tipsy.js"></script> <!-- Tipsy -->

<script type="text/javascript">

$(document).ready(function(){
			
	universalPreloader();
						   
});

$(window).load(function(){

	//remove Universal Preloader
	universalPreloaderRemove();
	
	rotate();
    dogRun();
	dogTalk();

	//Tipsy implementation
	$('.with-tooltip').tipsy({gravity: $.fn.tipsy.autoNS});
						   
});

</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>404 - Not found</title>
</head>

<body>

<!-- Universal preloader -->

<!-- Universal preloader -->

<div id="wrapper">
<!-- 404 graphic -->
	<div class="graphic">
        <img src="images/404.png" alt="404">
    </div>
<!-- 404 graphic -->

<!-- Text, search form and menu -->
<div class="top-left">
    <!-- Not found text -->
    	<div class="not-found-text">
        	<h1 class="not-found-text">File not found, sorry!</h1>
        </div>
    <!-- Not found text -->

    <!-- search form -->
    <!-- search form -->

    <!-- top menu -->
    <div class="top-menu">
    	<a original-title="Return to the home page" href="#" class="with-tooltip">Home</a> | <a original-title="Navigate through our sitemap" href="#" class="with-tooltip">Sitemap</a> | <a original-title="Contact us!" href="#" class="with-tooltip">Contact</a> | <a original-title="Request additional help" href="#" class="with-tooltip">Help</a>
    </div>
    <!-- top menu -->
</div>
<!-- Text, search form and menu -->

<!-- planet at the bottom -->
	<div class="planet">
        <div class="dog-wrapper">
        <!-- dog running -->
            <div style="background-position: 0px 0px;" class="dog"></div>
        <!-- dog running -->
            
        <!-- dog bubble talking -->
            <div style="opacity: 1; bottom: 10px;" class="dog-bubble"><p>
                    Are you lost, bud? No worries, I'm an excellent guide!
                </p></div>
            
            <!-- The dog bubble rotates these -->
            <div class="bubble-options">
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    Are you lost, bud? No worries, I'm an excellent guide!
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    <br>
                    Arf! Arf!
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    <br>
                    Don't worry! I'm on it!
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    I wish I had a cookie<br><img style="margin-top:8px" src="images/cookie.png" alt="cookie">
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    <br>
                    Geez! This is pretty tiresome!
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    <br>
                    Am I getting close?
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    Or am I just going in circles? Nah...
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    <br>
                    OK, I'm officially lost now...
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    I think I saw a <br><img style="margin-top:8px" src="images/cat.png" alt="cat">
                </p>
                <p style="opacity: 1; bottom: 10px;" class="dog-bubble">
                    What are we supposed to be looking for, anyway? @_@
                </p>
            </div>
            <!-- The dog bubble rotates these -->
        <!-- dog bubble talking -->
        </div>

        <!-- planet image -->
        <img style="transform: rotate(-767.4deg);" src="images/planet.png" alt="planet">
        <!-- planet image -->
    </div>
<!-- planet at the bottom -->
</div>



</body></html>