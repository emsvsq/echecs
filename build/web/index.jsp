<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
if (session.getAttribute("Engine") == null) {
    Game.Engine engine = new Game.Engine();
    session.setAttribute("Engine", engine); 
    session.setMaxInactiveInterval(3600);
}
%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Echecs</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script src="main.js"></script>
    </head>
    
    <body>
        
        <div id="BackgroundMenuPC"><ul id="menuPC" class="ulItems"> 
            <li><p class="MenuItem">Fichier</p>
                <ul class="ulItems">
                    <li><p>Nouvelle partie</p>
                        <ul class="ulItems">
                            <li><p onclick='SetOption("singleplayer")'>Jouer contre l'ordinateur</p></li>
                            <li><p onclick='SetOption("multiplayer")'>Multijoueur</p></li>
                            <li><p onclick='createDialog("joinmultiplayer")'>Rejoindre une partie multijoueur</p></li>
                        </ul>
                    </li>
                    <li><p onclick='createDialog("save");'>Sauvegarder</p></li>
                    <li><p onclick='createDialog("load");'>Charger</p></li>
                </ul>
            </li>

            <li><p class="MenuItem">Paramètres</p>
                <ul class="ulItems">
                    <li><p id="aide" onclick='SetOption("aide")'>Masquer aide</p></li>
                    <li><p onclick='SetOption("zoomplus")'>Zoom+</p></li>
                    <li><p onclick='SetOption("zoommoins")'>Zoom-</p></li>
                </ul>
            </li>
            
            <li><p class="MenuItem">Action</p>
                <ul class="ulItems">
                    <li><p onclick='SetOption("cancel")'>Annuler le coup</p></li>
                </ul>
            </li>

        </div>
        
         <!--<canvas id="canvas"></canvas>-->
         <div id="game"></div>
         
          
    </body>
</html>