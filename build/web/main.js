var xmlhttp;
xmlhttp = new XMLHttpRequest();
//var address = "http://fr.teebbo.com:8080/echecs";
var address = "http://localhost:8080/AOSechecs";

var dimensionCase = 108;
var dimensionHauteurCase3D = 108;
var pasChess = 25;
var zoom = 1;
var chess3D = true;

var dimensionsCase0 = 97;
var dimensionsCase1 = 98;
var dimensionsCase2 = 99;
var dimensionsCase3 = 101;
var dimensionsCase4 = 103;
var dimensionsCase5 = 104;
var dimensionsCase6 = 105;
var dimensionsCase7 = 108;
    
function sendCoord(coord) {
        var game = document.querySelector("#game");
        var url = address + "/webresources/echec/play/" + coord.toString();
        xmlhttp.open('GET',url,true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {
         
               if (xmlhttp.readyState == 4) {
                 if ( xmlhttp.status == 200) {
                    var resp = eval( "(" +  xmlhttp.responseText + ")");
                    if (resp.html != null) {
                        //game.innerHTML = resp.html;
                       //alert(resp.html + "\n");
                        var ech = false;
                        if (resp.echec!= null) {
                            ech = true;
                        }
                        RenderOutput(resp.html, ech);
                    }
                    if (resp.echec != null) {
                        displayEchecDialog(resp.echec);
                    }
                 }
                 else {
                    alert("Error ->" + xmlhttp.responseText);
                 }
              }
        };
}

function refresh() {
        var game = document.querySelector("#game");
        var url = address + "/webresources/echec/status/begin";
        xmlhttp.open('GET',url);
        //xmlhttp.setRequestHeader('Access-Control-Allow-Origin', '*');
        //xmlhttp.setRequestHeader('Access-Control-Allow-Methods', 'GET');
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {
         
               if (xmlhttp.readyState == 4) {
                  if ( xmlhttp.status == 200) {
                       var det = eval( "(" +  xmlhttp.responseText + ")");
                       //Dessin de l'échuiquier
                       //game.innerHTML = det.html;
                       RenderOutput(det.html, false);
                       //alert(det.html);
                       //Positionnement de l'échiquier
                       var dimensions = det.zoom;
                       game.style.width = (dimensions * 8).toString() + "px";
                       game.style.height = (dimensions * 8).toString() + "px";
                       game.style.margin =  "0 auto";
                       game.style.left =  "0px";
                       game.style.right=  "0px";
                       
                 }
                 else {
                       alert("Error ->" + xmlhttp.responseText);
                 }
              }
        };
    }  

function SetOption(option) {
        var game = document.querySelector("#game");
        if (option == "zoomplus") {
            if (zoom < 1.2) {
                zoom += 0.1;
            }
        } else if (option == "zoommoins") {
            if (zoom > 0.6) {
                zoom -= 0.1; 
            }
        }
        var url = address + "/webresources/echec/options/" + option;
        xmlhttp.open('GET',url,true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {
               if (xmlhttp.readyState == 4) {
                  if ( xmlhttp.status == 200) {
                       var det = eval( "(" +  xmlhttp.responseText + ")");
                        //alert(det.test);
                       if (det.html != null) {
                           //game.innerHTML = det.html;
                           RenderOutput(det.html, false);
                           if (det.zoom != null) {
                               var dimensions = det.zoom;
                               game.style.width = (dimensions * 8).toString() + "px";
                               game.style.height = (dimensions * 8).toString() + "px";
                           }
                            /*game.style.margin =  "0 auto";
                            game.style.left =  "0px";
                            game.style.right=  "0px";*/
                       }
                       if (det.aide != null) {
                            if (det.aide == "true") {
                                document.querySelector("#aide").innerHTML = "Masquer aide";
                            } else {
                                document.querySelector("#aide").innerHTML = "Afficher aide";
                            }
                        }
                        if (det.zoom != null) {
                           // zoom = parseFloat(det.zoom / 8);
                        }
                       
                 }
                 else {
                       alert("Error ->" + xmlhttp.responseText);
                 }
              }
        };
}

function createDialog(option) {
        var url = address + "/webresources/echec/dialog/" + option.toLowerCase();
        xmlhttp.open('GET',url,true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {
               if (xmlhttp.readyState == 4) {
                  if (xmlhttp.status == 200) {
                       var det = eval( "(" +  xmlhttp.responseText + ")");
                       if (det.dialog != null) {
                            createDialogContent(option, det.dialog);
                       }
                       
                 }
                 else {
                    createDialogContent("Error ->" + xmlhttp.responseText);
                 }
              }
        };
}

function createDialogContent(titre, dialogContent) {
    var sTitle = titre;
    var sHTML = dialogContent;
    
    switch(titre.toLowerCase()) {
        case "save":
                sTitle = "Sauvegarder";
            break;
         case "load":
                sTitle = "Charger";
            break;    
        case "joinmultiplayer":
                sTitle = "Rejoindre une partie multijoueur";
            break;
    }
    
    closeDiv('shadow');
    closeDiv('dialog');
    
    var width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    var height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
    var x = width / 2 - 225; if (x < 0) { x = 0;}
    var y = height / 2 - 175; if (y < 0) { y = 0;}
    
    
    
    var dialog = document.createElement("div");
    dialog.innerHTML = "<div id='shadow' class='shadow'></div>"
    + "<div id='dialog' class='dialog' style='left:" + x + "px;top:" + y + "px'>"
    + "     <div class='dialogtitle' onmousedown=" + "dragId='dialog';onMouseDown(event)" + " onMouseover='isMouseover=true;' onMouseout='isMouseover=false;' onMouseMove='onMouseMove(event);'>"
    + "        <table border='0' width='100%' height='35' cellspacing='0' cellpadding='2'>"
    + "           <tr>"
    + "              <td style='cursor:move' width='100%'>"
    + "                  <div width='100%' >"
    + "                  " + sTitle + ""
    + "                  </div>"
    + "              </td>"
    + "              <td>"
    + "                 <div class='close' onclick=" + "closeDiv('shadow');closeDiv('dialog')" + ">X</div>"
    + "              </td>"
    + "           </tr>"
    + "        </table>"
    + "     </div>"
    + "     <div id='dialogcontent' style='position:relative'>"
    + "        " + sHTML + ""
    + "    </div>";
    + "</div>";
    document.body.appendChild(dialog);
}

function displayEchecDialog(txt) {
    if (!(document.getElementById("echecdiv"))) {
        var game = document.querySelector("#game");
        var iDiv = document.createElement('div');
        var x = (parseInt(game.style.width) / 2) - 100;
        var y = (parseInt(game.style.height) / 2) - 50;
        iDiv.innerHTML = "<div id='echecdiv' style='color:red; position:absolute;top:" + y + "px;left:" + x + "px; width:300px; height:100px;'><h1>" + txt + "</h1></div>";
        game.appendChild(iDiv);
        setTimeout(function(){
            if (document.getElementById("echecdiv")) {
                var element = document.getElementById("echecdiv");
                element.parentNode.removeChild(element);  
            }      
        },3000);
    }
}

function ExecuteDialogSave() {
    var savename = document.getElementById("savebutton").value;
    if(savename != null && savename.length > 0) {
        var url = address + "/webresources/echec/executedialogsave/" + savename;
            xmlhttp.open('PUT',url,true);
            xmlhttp.send(null);
            xmlhttp.onreadystatechange = function() {
                   if (xmlhttp.readyState == 4) {
                      if (xmlhttp.status == 200) {
                           var det = eval( "(" +  xmlhttp.responseText + ")");
                           if (det.dialog != null) {
                               if (det.dialog.toLowerCase() === "true") {
                                    closeDiv("shadow");
                                    closeDiv("dialog");
                               } else {
                                   alert(det.dialog);
                               }
                           }

                     }
                     else {
                        createDialogContent("Error ->" + xmlhttp.responseText);
                     }
                  }
            };
    }
}

function ExecuteDialogLoad() {
    var list = document.querySelector("#loadlist");
    var idRow = list.getAttribute("tag");
    var game = document.querySelector("#game");
    if(idRow >= 0) {
        var url = address + "/webresources/echec/executedialogload/" + idRow;
            xmlhttp.open('GET',url,true);
            xmlhttp.send(null);
            xmlhttp.onreadystatechange = function() {
                   if (xmlhttp.readyState == 4) {
                      if (xmlhttp.status == 200) {
                           var det = eval( "(" +  xmlhttp.responseText + ")");
                           if (det.loaded != null) {
                               if (det.loaded.toLowerCase() === "true") {
                                    //game.innerHTML = det.html;
                                    RenderOutput(det.html, false);
                                    closeDiv("shadow");
                                    closeDiv("dialog");
                               } else {
                                   alert(det.dialog);
                               }
                           }

                     }
                     else {
                        createDialogContent("Error ->" + xmlhttp.responseText);
                     }
                  }
            };
    }
}

/*function ExecuteDialogDelete() {
    var list = document.querySelector("#loadlist");
    var idRow = list.getAttribute("tag");
    if(idRow >= 0) {
        var url = address + "/webresources/echec/executedialogdelete/" + idRow;
            xmlhttp.open('DELETE',url,true);
            xmlhttp.send(null);
            xmlhttp.onreadystatechange = function() {
                   if (xmlhttp.readyState == 4) {
                        closeDiv("shadow");
                        closeDiv("dialog");
                        createDialog("load");
                  }
            };
    }
}*/

function ExecuteDialogDelete() {
    var list = document.querySelector("#loadlist");
    var idRow = list.getAttribute("tag");
    if(idRow >= 0) {
        var url = address + "/webresources/echec/executedialogdelete/" + idRow;
            xmlhttp.open('DELETE',url,true);
            xmlhttp.send(null);
            xmlhttp.onreadystatechange = function() {
                   if (xmlhttp.readyState == 4) {
                      if (xmlhttp.status == 200) {
                           var det = eval( "(" +  xmlhttp.responseText + ")");
                           if (det.loaded.toLowerCase() === "true") {
                                    closeDiv("shadow");
                                    closeDiv("dialog");
                                    createDialog("load");
                            } else {
                                   alert("Impossible de supprimer");
                           }

                     }
                     else {
                        createDialogContent("Error ->" + xmlhttp.responseText);
                     }
                  }
            };
    }
}

function closeDiv(id) {
    var div = document.querySelector("#" + id);
    if (div) {
        div.parentNode.removeChild(div);
    }
}

function onRowClick(rowId) {
    var list = document.querySelector("#loadlist");
    var tagId = list.getAttribute("tag");
    if (tagId != rowId) {
        var oldselectedId = "#loadlist_" + tagId;
        var selectedId = "#loadlist_" + rowId;
        document.querySelector(selectedId).className = "selectedRow";
        list.setAttribute("tag", rowId);
        if (tagId != -1) {
            document.querySelector(oldselectedId).className = "";
        }
    }
}

/*function wheelzoom(event) {
    var delta = Math.max(-1, Math.min(1, (event.wheelDelta || -event.detail)));
    if(delta==1){SetOption("zoomplus");}
    if(delta==-1){SetOption("zoommoins");}
}*/

// Inits
var dragId = '';
var isDragging = false;
var isMouseover = false;
var offsetX = 0;
var offsetY = 0;
var currentX = 0;
var currentY = 0;

function onMouseDown(event) {
    if (dragId != '') {
        var CurrentWindow = document.getElementById(dragId);
        offsetX = event.clientX;
        offsetY = event.clientY;
        currentX = parseInt(CurrentWindow.style.left);
        currentY = parseInt(CurrentWindow.style.top);
        isDragging = true;
    }
   
}

function onMouseMove(event) {
    if(dragId != '') {
        CurrentWindow = document.getElementById(dragId);
        if (!isDragging) {return;}
        else {
            CurrentWindow.style.left = currentX + event.clientX - offsetX + "px";
            //var menudiv = document.getElementById("RenderMenu");
            //if (parseInt(CurrentWindow.style.top) > parseInt(menudiv.offsetHeight)) {
                CurrentWindow.style.top = currentY + event.clientY - offsetY + "px";
                //}
            //document.onmouseup = Function("dragId='';isDragging=false;onMouseUp(" + CurrentWindow.id + ");");

            return false;
        }
    }
}
//document.onmouseup = function () { };
document.onmouseup = Function("dragId='';isDragging=false;");
/////////////////////////////////////////////////

//var Jeu = function () {
window.onload = function() {
	
    function Init() {
        //document.getElementById("game").addEventListener("mousewheel", wheelzoom);
        //document.getElementById("game").addEventListener("DOMMouseScroll", wheelzoom);
        refresh();
    }
    
    Init();
}

function RenderOutput(pieces, echec) {
    var sb = "";
    var game = document.querySelector("#game");
        
       try {
                  
            sb += "<img src='images/chess3D/chess2.png' alt='.' height='";
            sb += parseInt((8 * dimensionsCase7 + 30) * zoom);
            sb += "' width='";
            sb += parseInt((8 * dimensionsCase7 + 40) * zoom);
            sb += "' style='position:absolute; left:0px";
            sb += " top:0px;' >";
            
            var piece = pieces.split(";");
            for(var i = 0; i < piece.length; i++) {
                
                try {
                    
                    var line = piece[i];
                    var cPiece = line[0];
                    var iPiece = parseInt(cPiece);
                    var x = line[1];
                    var y = line[2];
                    var color = "o";
                    if (line.length > 3) {
                        color = line[3];
                    }
                    var isTop = "f";
                    if (line.length > 4) {
                        isTop = line[4];
                        
                    }
                    var image = "";
                    var top = parseInt(y * dimensionHauteurCase3D * zoom);
                    if (isTop === "t") {
                        top = top - 20;
                    }
                    var halo = false; 
                    
                    if (x != null && y != null) {
                        switch(iPiece) {
                            case 1 :
                                if(color === "b") {image = "images/chess3D/tourblanche.png";}
                                else {image = "images/chess3D/tournoire.png";}
                                break;
                            case 2 :
                                if(color === "b") {image = "images/chess3D/chevalblanc.png";}
                                else {image = "images/chess3D/chevalnoir.png";}
                                break;
                            case 3 :
                                if(color === "b") {image = "images/chess3D/foublanc.png";}
                                else {image = "images/chess3D/founoir.png";}
                                break;
                            case 4 :
                                if(color === "b") {image = "images/chess3D/reineblanche.png";}
                                else {image = "images/chess3D/reinenoire.png";}
                                break;
                            case 5 :
                                if(color === "b") {image = "images/chess3D/roiblanc.png";}
                                else {image = "images/chess3D/roinoir.png";}
                                break;
                            case 6 :
                                if(color === "b") {image = "images/chess3D/pionblanc.png";}
                                else {image = "images/chess3D/pionnoir.png";}
                                break;
                            case 7 :
                                image = "images/casevide.png";
                                break;
                            case 8 :

                            SetCurrentCaseSize(y);
                            var leftPiece = parseInt((pasChess + dimensionCase * x) * zoom);
                            var sHalopath = "images/halo.png";
                            if (echec) {
                                sHalopath = "images/halored.png";
                            }
                            sb += "<img src='";
                            sb += sHalopath;
                            sb += "' alt='' height='";
                            sb += parseInt(dimensionCase * zoom);
                            sb += "' width='";
                            sb += parseInt(dimensionCase * zoom);
                            sb += "' style='position:absolute; left:";
                            sb += leftPiece;
                            sb += "px; top:";
                            sb += top;
                            sb += "px;' >";  
                            halo = true;
                            break;

                        }

                        if (!halo) {
                            SetCurrentCaseSize(y);
                            var leftPiece = parseInt((pasChess + dimensionCase * x) * zoom);

                            sb += "<img onclick=" + "sendCoord('";
                            sb += x;
                            sb += y;
                            sb += "')" + " src='";
                            sb += image;
                            sb += "' alt='.' height='";
                            sb += parseInt((dimensionCase-10) * zoom);
                            sb += "' width='";
                            sb += parseInt((dimensionCase-10)*zoom);
                            sb += "' style='position:absolute; left:";
                            sb += leftPiece;
                            sb += "px; top:";
                            sb += top;
                            sb += "px;' >";
                        }
                    }
                    
                    

                    } catch (exc) {alert(exc);}

                }

            game.innerHTML = sb;
       } catch(e) {
           alert(e);
       }
    }
    
    function SetCurrentCaseSize(ligne) {
        var currentLine = parseInt(ligne)
         switch(currentLine) {
            case 0:
                dimensionCase = dimensionsCase0;
                pasChess = 60;
                break;
            case 1:
                dimensionCase = dimensionsCase1;
                pasChess = 55;
                break;
            case 2:
                dimensionCase = dimensionsCase2;
                pasChess = 50;
                break;
            case 3:
                dimensionCase = dimensionsCase3;
                pasChess = 45;
                break;
            case 4:
                dimensionCase = dimensionsCase4;
                pasChess = 40;
                break;
            case 5:
                dimensionCase = dimensionsCase5;
                pasChess = 35;
                break;
            case 6:
                dimensionCase = dimensionsCase6;
                pasChess = 30;
                break;
            case 7:
                dimensionCase = dimensionsCase7;
                pasChess = 25;
                break;
        }
    }