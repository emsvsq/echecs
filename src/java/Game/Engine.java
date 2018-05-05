
package Game;

import Pieces.Cheval;
import Pieces.Fou;
import Pieces.Piece;
import Pieces.Pion;
import Pieces.Reine;
import Pieces.Roi;
import Pieces.Tour;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Engine {
    public PlateauDeJeu game;
    private int dimensionsCase = 110;
    
    private int dimensionCase = 108;
    private int pasChess = 25;
    
    final private int dimensionsCase0 = 97;
    final private int dimensionsCase1 = 98;
    final private int dimensionsCase2 = 99;
    final private int dimensionsCase3 = 101;
    final private int dimensionsCase4 = 103;
    final private int dimensionsCase5 = 104;
    final private int dimensionsCase6 = 105;
    final private int dimensionsCase7 = 108;
    
    final private double dimensionHauteurCase3D = 108;
    
    public double zoom = 1;
    
    
    final private boolean chess3D = true;
    
    public String sMessageEchec = "";
    private boolean bShowHelp = true;
    
    public Engine() {
        game = new PlateauDeJeu(this);
    }
    
    public boolean GetEchec() {
        return game.bEchecRoiBlanc || game.bEchecRoiNoir;
    }
    
    public void DisplayEchec(int NbrCasesPossibles)
    {
        if (NbrCasesPossibles == 0)
            {
                sMessageEchec = "Echec et mat";
            }
            else
            {
                 sMessageEchec = "Echec au roi";
            }
    }
    
    public int Zoom() {
        return dimensionsCase;
    }
    public void SetZoom(String zoomoption) {
        switch(zoomoption) {
            case "zoomplus" : 
                if (zoom < 1.2) {
                    dimensionsCase = dimensionsCase + 10;
                    zoom+= 0.1;
                }
                break;
            case "zoommoins" : 
                if (zoom > 0.6) {
                    dimensionsCase = dimensionsCase - 10;
                    zoom-= 0.1;
                }
                break;
        }
    }
    
    public boolean Help() {
        return bShowHelp;
    }
    
    public void SetHelp(boolean help) {
        bShowHelp = help;
    }
    
    public void Restart() {
        game = new PlateauDeJeu(this);
    }
    
    public void Cancel() {
       game.Cancel();
    }
    
    public void SetIa(boolean bIa) {
        game.bIA = bIa;
    }
    
    public String Sauvegarder() {
        Data data = new Data();
        List<Partie> listSaved;
        listSaved = data.getSavedGames();
        
        StringBuilder returnValue = new StringBuilder();
        returnValue.append("<div class=\"displaylist\">");
        returnValue.append("<table class=\"lists\">");
        returnValue.append("<thead>");
        returnValue.append("<tr>");
        returnValue.append("<th style=\"max-width:30px\">Numero</th>");
        returnValue.append("<th>Nom de la partie</th>");
        returnValue.append("<th>Mode</th>");
        returnValue.append("</tr>");
        returnValue.append("</thead>");
        returnValue.append("<tbody>");
        for(int i = 0; i < listSaved.size(); i++) {
            Partie partie = listSaved.get(i);
            returnValue.append("<tr>");
            returnValue.append("<td>").append(partie.GetId()).append("</td>");
            returnValue.append("<td>").append(partie.GetName()).append("</td>");
            returnValue.append("<td>").append(partie.GetMode()).append("</td>");
            returnValue.append("</tr>");
        }
        returnValue.append("</tbody>");
        returnValue.append("</table>");
        returnValue.append("</div>");
        returnValue.append("<div class=\"dialogbuttons\">");
        returnValue.append("<input id=\"savebutton\" type=\"text\" name=\"sauvegarder\" style=\"width:200px;\" />");
        //returnValue.append("<input onclick=\"proceedDialog('sauvegarder');\" type=\"button\" value=\"Sauvegarder\" style=\"width:100px;\"/>");
        returnValue.append("<button onclick=\"ExecuteDialogSave()\" style=\"width:100px;\" >Sauvegarder</button>");
        returnValue.append("</div>");
        return returnValue.toString();
    }
    
    public JsAnswer Sauvegarder(String savename) {
        Data data = new Data();
        return data.Save(savename, game);
    }
    
    public String Charger() {
        Data data = new Data();
        List<Partie> listSaved;
        listSaved = data.getSavedGames();
        
        StringBuilder returnValue = new StringBuilder();
        returnValue.append("<div class=\"displaylist\">");
        returnValue.append("<table id=\"loadlist\" class=\"lists loadlist\" tag=\"-1\">");
        returnValue.append("<thead>");
        returnValue.append("<tr>");
        returnValue.append("<th style=\"max-width:30px\">Numero</th>");
        returnValue.append("<th>Nom de la partie</th>");
        returnValue.append("<th>Mode</th>");
        returnValue.append("</tr>");
        returnValue.append("</thead>");
        returnValue.append("<tbody>");
        for(int i = 0; i < listSaved.size(); i++) {
            Partie partie = listSaved.get(i);
            returnValue.append("<tr id=\"loadlist_").append(partie.GetId()).append("\" onclick=\"onRowClick(").append(partie.GetId()).append(")\">");
            returnValue.append("<td>").append(partie.GetId()).append("</td>");
            returnValue.append("<td>").append(partie.GetName()).append("</td>");
            returnValue.append("<td>").append(partie.GetMode()).append("</td>");
            returnValue.append("</tr>");
        }
        returnValue.append("</tbody>");
        returnValue.append("</table>");
        returnValue.append("</div>");
        returnValue.append("<div class=\"dialogbuttons\">");
        //returnValue.append("<input type=\"button\" name=\"load\" value=\"Charger\" style=\"width:300px;\" />");
        returnValue.append("<button onclick=\"ExecuteDialogLoad()\" style=\"width:300px;\" >Charger</button>");
        returnValue.append("<button onclick=\"ExecuteDialogDelete()\" style=\"width:300px;\" >Supprimer</button>");
        returnValue.append("</div>");
        return returnValue.toString();
    }
    
    public boolean Charger(int idRow) {
        Data data = new Data();
        PlateauDeJeu jeu = data.Load(this, idRow);
        game.listePieces.clear();
        game.SetParameters(jeu.Mode(), jeu.tour);
        
        for (int i = 0; i < jeu.listePieces.size(); i++) {
            
            String sTypePiece = jeu.listePieces.get(i).GetName();
            int X = jeu.listePieces.get(i).GetX();
            int Y = jeu.listePieces.get(i).GetY();
            Piece.color sCouleur = jeu.listePieces.get(i).GetCouleur();
                    
                    Piece pieceToAdd = null;
                    switch (sTypePiece.toLowerCase()) {
                        case "pion":
                            pieceToAdd = new Pion(X, Y, sCouleur);
                            break;
                        case "tour":
                            pieceToAdd = new Tour(X, Y, sCouleur);
                            break;
                        case "cheval":
                            pieceToAdd = new Cheval(X, Y, sCouleur);
                            break;
                        case "fou":
                            pieceToAdd = new Fou(X, Y, sCouleur);
                            break;
                        case "reine":
                            pieceToAdd = new Reine(X, Y, sCouleur);
                            break;
                        case "roi":
                            pieceToAdd = new Roi(X, Y, sCouleur);
                            break;
                        default:
                            break;
                    }
            if (pieceToAdd != null) {
                game.listePieces.add(pieceToAdd);
            }
        }
        game.Positionner();
        
        return (game != null);
    }
    
    public boolean DeleteRow(int idRow) {
        Data data = new Data();
        return data.DeleteRow(idRow);
    }
    
    public String RejoindrePartie() {
        Data data = new Data();
        List<String> listSaved = new ArrayList<>();
        listSaved = data.getCreatedGames();
        
        StringBuilder returnValue = new StringBuilder();
        returnValue.append("<div class=\"displaylist\">");
        returnValue.append("<table class=\"lists\">");
        returnValue.append("<thead>");
        returnValue.append("<tr>");
        returnValue.append("<th style=\"max-width:30px\">Numero</th>");
        returnValue.append("<th>Nom de la partie</th>");
        returnValue.append("</tr>");
        returnValue.append("</thead>");
        returnValue.append("<tbody>");
        returnValue.append("<tr>");
        returnValue.append("<td>1</td>");
        returnValue.append("<td>Test Partie 1</td>");
        returnValue.append("</tr>");
        returnValue.append("<tr>");
        returnValue.append("<td>2</td>");
        returnValue.append("<td>Test Partie 2</td>");
        returnValue.append("</tr>");
        returnValue.append("<tr>");
        returnValue.append("<td>3</td>");
        returnValue.append("<td>Test Partie 3</td>");
        returnValue.append("</tr>");
        returnValue.append("<tr>");
        returnValue.append("<td>4</td>");
        returnValue.append("<td>Test Partie 4</td>");
        returnValue.append("</tr>");
        returnValue.append("<tr>");
        returnValue.append("<td>5</td>");
        returnValue.append("<td>Test Partie 5</td>");
        returnValue.append("</tr>");
        returnValue.append("</tbody>");
        returnValue.append("</table>");
        returnValue.append("</div>");
        returnValue.append("<div class=\"dialogbuttons\">");
        returnValue.append("<input type=\"text\" name=\"sauvegarder\" style=\"width:200px;\" />");
        returnValue.append("<input type=\"button\" name=\"join\" value=\"Créer\" style=\"width:100px;\" />");
        returnValue.append("<input type=\"button\" name=\"join\" value=\"Rejoindre\" style=\"width:300px;\" />");
        returnValue.append("</div>");
        return returnValue.toString();
    }
    
    public boolean Move(String coord) {
        boolean bMoving = false;
        ////Décryptage des coordonnées sous forme de String
        Point coordonnees = new Point();
        String x = "0";
        String y;
        if (coord.length() < 2) {
            y = "" + coord.charAt(0);
        } else {
            x = "" + coord.charAt(0);
            y = "" + coord.charAt(1);
        }

        coordonnees.x = Integer.parseInt(x);
        coordonnees.y = Integer.parseInt(y);
            
        if (game.bPlaying == false) {
            /////Prise en main du pion
            game.Take(coordonnees);
        } else {
            ////// Déplacement du pion
            game.Move(coordonnees);
            bMoving = true;
        }
        return bMoving;
    }
    
    public boolean isCaseVide(String coord) {
        boolean isCaseVide = false;
        ////Décryptage des coordonnées sous forme de String
        Point coordonnees = new Point();
        String x = "0";
        String y;
        if (coord.length() < 2) {
            y = "" + coord.charAt(0);
        } else {
            x = "" + coord.charAt(0);
            y = "" + coord.charAt(1);
        }

        coordonnees.x = Integer.parseInt(x);
        coordonnees.y = Integer.parseInt(y);
        
        return game.TypeCase(coordonnees.x, coordonnees.y) instanceof Pieces.CaseVide && game.bPlaying == false;
    }
    
    public String RenderOutput() {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        
        if (chess3D) {
              sb.append("<img src=\"images/chess3D/chess2.png\" alt=\".\" height=\"")
                            .append((int)(8 * dimensionsCase7 + 30) * zoom).append("\" width=\"").append((int)(8 * dimensionsCase7 + 40) * zoom)
                            .append("\" style=\"position:absolute; left:").append(0)
                            .append("px; top:").append(0).append("px;\" >");
        }
         
        List<Point> listCasesPossibles = new ArrayList();
        for(int ligne = 0; ligne < game.lignes; ligne++) {
            for(int colonne = 0; colonne < game.colonnes; colonne++) {
                
                if (chess3D == false) { 
                    if (i % 2 == 0) {
                        sb.append("<img src=\"images/caseblanche.jpg\" alt=\"caseBlanche\" height=\"")
                            .append(dimensionsCase).append("\" width=\"").append(dimensionsCase)
                            .append("\" style=\"position:absolute; left:").append(colonne * dimensionsCase)
                            .append("px; top:").append(ligne * dimensionsCase).append("px;\" >");
                    } else {
                        
                    
                        sb.append("<img src=\"images/casenoire.jpg\" alt=\"caseBlanche\" height=\"")
                            .append(dimensionsCase).append("\" width=\"").append(dimensionsCase)
                            .append("\" style=\"position:absolute; left:").append(colonne * dimensionsCase)
                            .append("px; top:").append(ligne * dimensionsCase).append("px;\" >");
                    }
                }
                
                Pieces.Piece p = game.sPlateformeJeu[colonne][ligne];
                if (p.bOnTop) {
                    listCasesPossibles = game.GetPieceCasePossibles(new Point(colonne, ligne));
                }
                
                i++;
                
            }
            i--;
        }
        
        for(int ligne = 0; ligne < game.lignes; ligne++) {
            for(int colonne = 0; colonne < game.colonnes; colonne++) {
                Pieces.Piece p = game.sPlateformeJeu[colonne][ligne];
                int top = ligne * dimensionsCase;
                if (chess3D) {
                    top = (int)(ligne * dimensionHauteurCase3D * zoom);
                }
                        
                if (bShowHelp) {
                    for(Point casePossibles: listCasesPossibles) {
                        if ((casePossibles.x == colonne) && (casePossibles.y == ligne)) {
                        SetCurrentCaseSize(ligne);
                        
                        int leftPiece = (int)((pasChess + dimensionCase * colonne) * zoom);
                        String sHalopath = "images/halo.png";
                        if (GetEchec()) {
                            sHalopath = "images/halored.png";
                        }
                            sb.append("<img src=\"").append(sHalopath).append("\" alt=\".\" height=\"");
                                    if (chess3D) {
                                        sb.append(dimensionCase * zoom);
                                    } else {
                                        sb.append(dimensionsCase * zoom);
                                    }
                                    sb.append("\" width=\"");
                                    if (chess3D) {
                                        sb.append(dimensionCase * zoom);
                                    } else {
                                        sb.append(dimensionsCase * zoom);
                                    }
                                    sb.append("\" style=\"position:absolute; left:");
                                    if (chess3D) {
                                        sb.append(leftPiece);
                                    } else {
                                        sb.append(colonne * dimensionsCase);
                                    }
                                    sb.append("px; top:");
                                    sb.append(top)
                                    .append("px;\" >");
                            break;
                        }
                    }
                }
                if (p.bOnTop) {
                    top = (int)(top - (30 * zoom));
                }
                if (chess3D) {
                    SetCurrentCaseSize(ligne);
                    int leftPiece = (int)((pasChess + dimensionCase * colonne) * zoom);
                    
                    sb.append("<img onclick="+"sendCoord(\"").append(p.GetX()).append(p.GetY()).append("\")"+" src=\"").append(p.GetImage3D()).append("\" alt=\".\" height=\"").append((int)(dimensionCase-10) * zoom).append("\" width=\"").append((int)(dimensionCase-10)*zoom)
                    .append("\" style=\"position:absolute; left:").append(leftPiece).append("px; top:").append(top).append("px;\" >");
                } else {
                    sb.append("<img onclick="+"sendCoord(\"").append(p.GetX()).append(p.GetY()).append("\")"+" src=\"").append(p.GetImage()).append("\" alt=\".\" height=\"").append(dimensionsCase).append("\" width=\"").append(dimensionsCase)
                    .append("\" style=\"position:absolute; left:").append(colonne * dimensionsCase).append("px; top:").append(top).append("px;\" >");
                }
                
            }
        }
         
        return sb.toString();
    }
    
    public void SetCurrentCaseSize(int ligne) {
         switch(ligne) {
            case 0:
                dimensionCase = dimensionsCase0;
                pasChess = 60;
                //pasLeft = 60;
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
    
    public String Stringify() {
        StringBuilder sb = new StringBuilder();
        List<Point> listCasesPossibles = new ArrayList();
        
        for(int ligne = 0; ligne < game.lignes; ligne++) {
            for(int colonne = 0; colonne < game.colonnes; colonne++) {
                Pieces.Piece p = game.sPlateformeJeu[colonne][ligne];
                if (p.bOnTop) {
                    listCasesPossibles = game.GetPieceCasePossibles(new Point(colonne, ligne));
                    break;
                }
            }
        }
        
        for(int ligne = 0; ligne < game.lignes; ligne++) {
            for(int colonne = 0; colonne < game.colonnes; colonne++) {
                
                if (bShowHelp) {
                    for(Point casePossibles: listCasesPossibles) {
                        if ((casePossibles.x == colonne) && (casePossibles.y == ligne)) {
                            sb.append("8");
                            sb.append(casePossibles.x );
                            sb.append(casePossibles.y);
                            sb.append(";");
                        }
                    }
                }
                
                
                Pieces.Piece p = game.sPlateformeJeu[colonne][ligne];
                 if (p instanceof Tour) {
                    sb.append("1");
                } else if (p instanceof Cheval) {
                    sb.append("2");
                } else if (p instanceof Fou) {
                    sb.append("3");
                } else if (p instanceof Reine) {
                    sb.append("4");
                } else if (p instanceof Roi) {
                    sb.append("5");
                } else if (p instanceof Pion) {
                    sb.append("6");
                } else if (p instanceof Pieces.CaseVide) {
                    sb.append("7");
                }
                 
                sb.append(p.GetX());
                sb.append(p.GetY());
                if (p.GetCouleur() == Piece.color.Blanc) {sb.append("b");}
                else if (p.GetCouleur() == Piece.color.Noir){sb.append("n");}
                if (p.bOnTop) {sb.append("t");}
                sb.append(";");
                
            }
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }
} 
