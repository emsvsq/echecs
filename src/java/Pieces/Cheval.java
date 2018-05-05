package Pieces;

import Game.PlateauDeJeu;
import java.awt.Point;
import java.util.ArrayList;

public class Cheval extends Piece{
    int X;
    int Y;
            
    public Cheval(int X, int Y, Piece.color couleur) 
    {super (X, Y, couleur);
        if (couleur == Piece.color.Blanc)
        {
            super.cheminImage = "images/ChevalBlanc.png";
            super.cheminImage3D = "images/chess3D/chevalblanc.png";
        }
        else
        {
            super.cheminImage = "images/ChevalNoir.png";
            super.cheminImage3D = "images/chess3D/chevalnoir.png";
        }
        super.type = "Cheval";
    }
    
    @Override public ArrayList<Point> CasesPossibles(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
     {
         ArrayList<Point> ListeCase = new ArrayList<Point>();
                if (( x - 1>= 0) && ( y - 2>= 0))
                {
                    if  (plateauJeu.TypeCase(x - 1,y - 2).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x - 1,y - 2));       
                    }
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y - 2 >= 0))
                {
                    if  (plateauJeu.TypeCase(x + 1,y - 2).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x + 1,y - 2)); 
                    }
                }
                if (( x - 2 >= 0) && ( y - 1 >= 0))
                {
                    if (plateauJeu.TypeCase(x - 2,y - 1).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x - 2,y - 1));        
                    }
                }
                if (( x + 2 <= plateauJeu.colonnes-1) && ( y - 1 >= 0))
                {
                    if  (plateauJeu.TypeCase(x + 2,y - 1).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x + 2,y - 1));        
                    }
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if (plateauJeu.TypeCase(x + 1,y + 2).GetCouleur() != couleur)
                    {
                    ListeCase.add(new Point(x + 1,y + 2));    
                    }
                }
                if ((x + 2 <= plateauJeu.colonnes-1) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if (plateauJeu.TypeCase(x + 2,y + 1).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x + 2,y + 1));  
                    }
                }
                if (( x - 2 >= 0) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if (plateauJeu.TypeCase(x -2,y + 1).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x - 2,y + 1));        
                    }
                }
                if (( x - 1 >= 0) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if (plateauJeu.TypeCase(x -1,y + 2).GetCouleur() != couleur)
                    {
                        ListeCase.add(new Point(x - 1,y + 2));        
                    }
                }
        return ListeCase;
     }
    
    @Override public boolean Echec(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
    {
        if (couleur == Piece.color.Blanc)
         {
             couleur = Piece.color.Noir;
         }
         else
         {
             couleur = Piece.color.Blanc;
         }
        boolean echec = false;
        if (( x - 1>= 0) && ( y - 2>= 0))
                {
                    if  ((plateauJeu.TypeCase(x - 1,y - 2).GetCouleur() == couleur) && (plateauJeu.TypeCase(x - 1,y - 2).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true;      
                    }
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y - 2 >= 0))
                {
                    if  ((plateauJeu.TypeCase(x + 1,y - 2).GetCouleur() == couleur) && (plateauJeu.TypeCase(x + 1,y - 2).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true; 
                    }
                }
                if (( x - 2 >= 0) && ( y - 1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x - 2,y - 1).GetCouleur() == couleur) && (plateauJeu.TypeCase(x - 2,y - 1).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true;        
                    }
                }
                if (( x + 2 <= plateauJeu.colonnes-1) && ( y - 1 >= 0))
                {
                    if  ((plateauJeu.TypeCase(x + 2,y - 1).GetCouleur() == couleur) && (plateauJeu.TypeCase(x + 2,y - 1).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true;         
                    }
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((plateauJeu.TypeCase(x + 1,y + 2).GetCouleur() == couleur) && (plateauJeu.TypeCase(x + 1,y + 2).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true;    
                    }
                }
                if ((x + 2 <= plateauJeu.colonnes-1) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((plateauJeu.TypeCase(x + 2,y + 1).GetCouleur() == couleur) && (plateauJeu.TypeCase(x + 2,y + 1).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true; 
                    }
                }
                if (( x - 2 >= 0) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((plateauJeu.TypeCase(x -2,y + 1).GetCouleur() == couleur) && (plateauJeu.TypeCase(x -2,y + 1).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true;       
                    }
                }
                if (( x - 1 >= 0) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((plateauJeu.TypeCase(x -1,y + 2).GetCouleur() == couleur) && (plateauJeu.TypeCase(x -1,y + 2).GetPiece() instanceof Pieces.Roi))
                    {
                        echec = true;       
                    }
                }
        return echec;
    }
    @Override public boolean EchecCase(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean echec = false;
                if (( x - 1>= 0) && ( y - 2>= 0))
                {
                    if ((x - 1 == caseX) && (y - 2 == caseY))
                    {
                         echec = true;
                    } 
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y - 2 >= 0))
                {
                    if ((x + 1 == caseX) && (y - 2 == caseY))
                    {
                         echec = true;
                    } 
                }
                if (( x - 2 >= 0) && ( y - 1 >= 0))
                {
                    if ((x - 2 == caseX) && (y - 1 == caseY))
                    {
                         echec = true;
                    } 
                }
                if (( x + 2 <= plateauJeu.colonnes-1) && ( y - 1 >= 0))
                {
                    if ((x + 2 == caseX) && (y - 1 == caseY))
                    {
                         echec = true;
                    } 
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((x + 1 == caseX) && (y + 2 == caseY))
                    {
                         echec = true;
                    } 
                }
                if ((x + 2 <= plateauJeu.colonnes-1) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((x + 2 == caseX) && (y + 1 == caseY))
                    {
                         echec = true;
                    } 
                }
                if (( x - 2 >= 0) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((x - 2 == caseX) && (y + 1 == caseY))
                    {
                         echec = true;
                    } 
                }
                if (( x - 1 >= 0) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((x - 1 == caseX) && (y + 2 == caseY))
                    {
                         echec = true;
                    } 
                }
        return echec;
     }
    @Override public boolean ProtectCase(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean protect = false;
        if (( x - 1>= 0) && ( y - 2>= 0))
                {
                    if ((x - 1 == caseX) && (y - 2 == caseY) && (plateauJeu.TypeCase(x - 1,y - 2).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y - 2 >= 0))
                {
                    if ((x + 1 == caseX) && (y - 2 == caseY) && (plateauJeu.TypeCase(x + 1,y - 2).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if (( x - 2 >= 0) && ( y - 1 >= 0))
                {
                    if ((x - 2 == caseX) && (y - 1 == caseY) && (plateauJeu.TypeCase(x - 2,y - 1).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if (( x + 2 <= plateauJeu.colonnes-1) && ( y - 1 >= 0))
                {
                    if ((x + 2 == caseX) && (y - 1 == caseY) && (plateauJeu.TypeCase(x + 2,y - 1).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((x + 1 == caseX) && (y + 2 == caseY) && (plateauJeu.TypeCase(x + 1,y + 2).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if ((x + 2 <= plateauJeu.colonnes-1) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((x + 2 == caseX) && (y + 1 == caseY) && (plateauJeu.TypeCase(x + 2,y + 1).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if (( x - 2 >= 0) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((x - 2 == caseX) && (y + 1 == caseY) && (plateauJeu.TypeCase(x - 2,y + 1).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
                if (( x - 1 >= 0) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((x - 1 == caseX) && (y + 2 == caseY) && (plateauJeu.TypeCase(x - 1,y + 2).GetCouleur() == couleur))
                    {
                         protect = true;
                    } 
                }
        return protect;
     }
    @Override public boolean AntiEchec(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
    {
        if (couleur == Piece.color.Blanc)
         {
             couleur = Piece.color.Noir;
         }
         else
         {
             couleur = Piece.color.Blanc;
         }
        boolean AntiEchec = false;
        if (( x - 1>= 0) && ( y - 2>= 0))
                {
                    if ((x - 1 == caseX) && (y - 2 == caseY) && (plateauJeu.TypeCase(x - 1,y - 2).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y - 2 >= 0))
                {
                    if ((x + 1 == caseX) && (y - 2 == caseY) && (plateauJeu.TypeCase(x + 1,y - 2).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if (( x - 2 >= 0) && ( y - 1 >= 0))
                {
                    if ((x - 2 == caseX) && (y - 1 == caseY) && (plateauJeu.TypeCase(x - 2,y - 1).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if (( x + 2 <= plateauJeu.colonnes-1) && ( y - 1 >= 0))
                {
                    if ((x + 2 == caseX) && (y - 1 == caseY) && (plateauJeu.TypeCase(x + 2,y - 1).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if (( x + 1 <= plateauJeu.colonnes-1) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((x + 1 == caseX) && (y + 2 == caseY) && (plateauJeu.TypeCase(x + 1,y + 2).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if ((x + 2 <= plateauJeu.colonnes-1) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((x + 2 == caseX) && (y + 1 == caseY) && (plateauJeu.TypeCase(x + 2,y + 1).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if (( x - 2 >= 0) && ( y + 1 <= plateauJeu.lignes-1))
                {
                    if ((x - 2 == caseX) && (y + 1 == caseY) && (plateauJeu.TypeCase(x - 2,y + 1).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
                if (( x - 1 >= 0) && ( y + 2 <= plateauJeu.lignes-1))
                {
                    if ((x - 1 == caseX) && (y + 2 == caseY) && (plateauJeu.TypeCase(x - 1,y + 2).GetCouleur() == couleur))
                    {
                         AntiEchec = true;
                    } 
                }
        return AntiEchec;
    }
}
