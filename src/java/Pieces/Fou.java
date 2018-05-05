package Pieces;

import Game.PlateauDeJeu;
import java.awt.Point;
import java.util.ArrayList;

public class Fou extends Piece{
    int X;
    int Y;
            
    public Fou(int X, int Y, Piece.color couleur) 
    {super (X, Y, couleur);
        if (couleur == Piece.color.Blanc)
        {
            super.cheminImage = "images/FouBlanc.png";
            super.cheminImage3D = "images/chess3D/foublanc.png";
        }
        else
        {
            super.cheminImage = "images/FouNoir.png";
            super.cheminImage3D = "images/chess3D/founoir.png";
        }
        super.type = "Fou";
    }
    
    @Override public ArrayList<Point> CasesPossibles(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
     {
         if (couleur == Piece.color.Blanc)
         {
             couleur = Piece.color.Noir;
         }
         else
         {
             couleur = Piece.color.Blanc;
         }
         ArrayList<Point> ListeCase = new ArrayList<Point>();
                int currentPos = 1;
                boolean jouer = true;
                if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                        {
                            if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == Piece.color.Vide)
                            {
                                ListeCase.add(new Point(x - currentPos,y - currentPos));
                            }
                            else if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == couleur)
                            {
                                 ListeCase.add(new Point(x - currentPos,y - currentPos));
                                 jouer = false;
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                        {
                            if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == Piece.color.Vide)
                            {
                                ListeCase.add(new Point(x + currentPos,y - currentPos));
                            }
                            else if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == couleur)
                            {
                                 ListeCase.add(new Point(x + currentPos,y - currentPos));
                                 jouer = false;
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                {
                    while (jouer)
                    {
                        if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                        {
                           if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == Piece.color.Vide)
                            {
                                ListeCase.add(new Point(x - currentPos,y + currentPos));
                            }
                            else if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == couleur)
                            {
                                 ListeCase.add(new Point(x - currentPos,y + currentPos));
                                 jouer = false;
                            }
                           else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                        {
                            if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == Piece.color.Vide)
                            {
                                ListeCase.add(new Point(x  + currentPos,y + currentPos));
                            }
                            else if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == couleur)
                            {
                                 ListeCase.add(new Point(x + currentPos,y + currentPos));
                                 jouer = false;
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
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
                int currentPos = 1;
                boolean echec = false;
                boolean jouer = true;
                if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                        {   
                            if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == Piece.color.Vide)
                            {
                            }
                            else if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == couleur)
                            {
                                 if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetPiece() instanceof Pieces.Roi)
                                 {
                                     echec = true;
                                 }
                                 jouer = false;
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                        {
                            if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == Piece.color.Vide)
                            {
                            }
                            else if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == couleur)
                            {
                                 if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetPiece() instanceof Pieces.Roi)
                                 {
                                     echec = true;
                                 }
                                 jouer = false;
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                {
                    while (jouer)
                    {
                        if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                        {
                           if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == Piece.color.Vide)
                            {
                            }
                            else if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == couleur)
                            {
                                 if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetPiece() instanceof Pieces.Roi)
                                 {
                                     echec = true;
                                 }
                                 jouer = false;
                            }
                           else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                        {
                            if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == Piece.color.Vide)
                            {
                            }
                            else if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == couleur)
                            {
                                 if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetPiece() instanceof Pieces.Roi)
                                 {
                                     echec = true;
                                 }
                                 jouer = false;
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }
                return echec;
    }
    
    @Override public boolean EchecCase(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
                int currentPos = 1;
                boolean echec = false;
                boolean jouer = true;
                if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                        {   
                            if (((plateauJeu.TypeCase(x - currentPos,y - currentPos).GetPiece() instanceof Pieces.CaseVide) || (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetPiece() instanceof Pieces.Roi)) && (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() != couleur))
                            {
                                if ((x - currentPos == caseX) && (y - currentPos == caseY))
                                 {
                                     echec = true;
                                 }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                        {
                            if (((plateauJeu.TypeCase(x + currentPos,y - currentPos).GetPiece() instanceof Pieces.CaseVide) || (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetPiece() instanceof Pieces.Roi)) && (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() != couleur))
                            {
                                if ((x + currentPos == caseX) && (y - currentPos == caseY))
                                 {
                                     echec = true;
                                 }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                {
                    while (jouer)
                    {
                        if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                        {
                           if (((plateauJeu.TypeCase(x - currentPos,y + currentPos).GetPiece() instanceof Pieces.CaseVide)  || (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetPiece() instanceof Pieces.Roi)) && (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() != couleur))
                            {
                                if ((x - currentPos == caseX) && (y + currentPos == caseY))
                                 {
                                     echec = true;
                                 }
                            }
                           else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                        {
                            if (((plateauJeu.TypeCase(x + currentPos,y + currentPos).GetPiece() instanceof Pieces.CaseVide)  || (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetPiece() instanceof Pieces.Roi))  && (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() != couleur))
                            {
                                if ((x + currentPos == caseX) && (y + currentPos == caseY))
                                 {
                                     echec = true;
                                 }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }
                return echec;
     }
    @Override public boolean ProtectCase(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean protect = false;
        int currentPos = 1;
                boolean jouer = true;
                if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                        {   
                            if ((plateauJeu.TypeCase(x - currentPos,y - currentPos).GetPiece() instanceof Pieces.CaseVide) || (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == couleur)
                                {
                                    if ((x - currentPos == caseX) && (y - currentPos == caseY))
                                     {
                                         protect = true;
                                     }
                                    jouer = false;
                                }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                        {
                            if ((plateauJeu.TypeCase(x + currentPos,y - currentPos).GetPiece() instanceof Pieces.CaseVide)|| (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == couleur)
                                {
                                    if ((x + currentPos == caseX) && (y - currentPos == caseY))
                                     {
                                         protect = true;
                                     }
                                    jouer = false;
                                }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                {
                    while (jouer)
                    {
                        if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                        {
                           if ((plateauJeu.TypeCase(x - currentPos,y + currentPos).GetPiece() instanceof Pieces.CaseVide)|| (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == couleur)
                                {
                                    if ((x - currentPos == caseX) && (y + currentPos == caseY))
                                     {
                                         protect = true;
                                     }
                                    jouer = false;
                                }
                            }
                           else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                        {
                            if ((plateauJeu.TypeCase(x + currentPos,y + currentPos).GetPiece() instanceof Pieces.CaseVide)|| (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == couleur)
                                {
                                    if ((x + currentPos == caseX) && (y + currentPos == caseY))
                                     {
                                         protect = true;
                                     }
                                    jouer = false;
                                }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
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
        int currentPos = 1;
                boolean jouer = true;
                if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x - currentPos>= 0) && ( y - currentPos>= 0))
                        {   
                            if ((plateauJeu.TypeCase(x - currentPos,y - currentPos).GetPiece() instanceof Pieces.CaseVide) || (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x - currentPos,y - currentPos).GetCouleur() == couleur)
                                {
                                    if ((x - currentPos == caseX) && (y - currentPos == caseY))
                                     {
                                         AntiEchec = true;
                                     }
                                    jouer = false;
                                }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y - currentPos>= 0))
                        {
                            if ((plateauJeu.TypeCase(x + currentPos,y - currentPos).GetPiece() instanceof Pieces.CaseVide)|| (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x + currentPos,y - currentPos).GetCouleur() == couleur)
                                {
                                    if ((x + currentPos == caseX) && (y - currentPos == caseY))
                                     {
                                         AntiEchec = true;
                                     }
                                    jouer = false;
                                }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                {
                    while (jouer)
                    {
                        if (( x - currentPos >= 0) && ( y + currentPos <= plateauJeu.lignes-1))
                        {
                           if ((plateauJeu.TypeCase(x - currentPos,y + currentPos).GetPiece() instanceof Pieces.CaseVide)|| (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x - currentPos,y + currentPos).GetCouleur() == couleur)
                                {
                                    if ((x - currentPos == caseX) && (y + currentPos == caseY))
                                     {
                                         AntiEchec = true;
                                     }
                                    jouer = false;
                                }
                            }
                           else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }

                currentPos = 1;
                jouer = true;
                if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                {
                    while (jouer)
                    {
                        if (( x + currentPos <= plateauJeu.colonnes-1) && ( y + currentPos <= plateauJeu.colonnes-1))
                        {
                            if ((plateauJeu.TypeCase(x + currentPos,y + currentPos).GetPiece() instanceof Pieces.CaseVide)|| (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == couleur))
                            {
                                if (plateauJeu.TypeCase(x + currentPos,y + currentPos).GetCouleur() == couleur)
                                {
                                    if ((x + currentPos == caseX) && (y + currentPos == caseY))
                                     {
                                         AntiEchec = true;
                                     }
                                    jouer = false;
                                }
                            }
                            else
                            {
                                jouer = false;
                            }
                            currentPos++;
                        }
                        else 
                        {
                            jouer = false;
                        }
                    }
                }
    	 return AntiEchec;
     }
}
    
