package Pieces;

import Game.PlateauDeJeu;
import java.awt.Point;
import java.util.ArrayList;

public class Pion extends Piece{
    private int X;
    private int Y;
            
    public Pion(int X, int Y, Piece.color couleur) 
    {super (X, Y, couleur);
        if (couleur == Piece.color.Blanc)
        {
            super.cheminImage = "images/PionBlanc.png";
            super.cheminImage3D = "images/chess3D/pionblanc.png";
        }
        else
        {
            super.cheminImage = "images/PionNoir.png";
            super.cheminImage3D = "images/chess3D/pionnoir.png";
        }
        super.type = "Pion";
    }
    
    @Override public ArrayList<Point> CasesPossibles(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
    {
        ArrayList<Point> ListeCase = new ArrayList<Point>();
        if (couleur == Piece.color.Blanc)
        {
            if (y-1 >= 0)
                {
                    if (plateauJeu.TypeCase(x,y-1).GetCouleur() == Piece.color.Vide)
                    {
                        ListeCase.add(new Point(x,y-1));
                    }
                    if ((y == 6) && (plateauJeu.TypeCase(x,y-1).GetCouleur() == Piece.color.Vide) && (plateauJeu.TypeCase(x,y-2).GetCouleur() == Piece.color.Vide))
                    {
                        ListeCase.add(new Point(x,y-2));
                    }
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y-1).GetCouleur() == Piece.color.Noir)
                        {
                           ListeCase.add(new Point(x-1,y-1));
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y-1).GetCouleur() == Piece.color.Noir)
                        {
                            ListeCase.add(new Point(x+1,y-1));
                        }
                    }
                }
        }
        else
            {
                if (y+1 < plateauJeu.lignes)
                {
                    if (plateauJeu.TypeCase(x,y+1).GetCouleur() == Piece.color.Vide)
                    {
                        ListeCase.add(new Point(x,y+1));
                    }
                    if ((y == 1) && (plateauJeu.TypeCase(x,y+1).GetCouleur() == Piece.color.Vide) && (plateauJeu.TypeCase(x,y+2).GetCouleur() == Piece.color.Vide))
                    {
                        ListeCase.add(new Point(x,y+2));
                    }
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y+1).GetCouleur() == Piece.color.Blanc)
                        {
                            ListeCase.add(new Point(x-1,y+1));
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y+1).GetCouleur() == Piece.color.Blanc)
                        {
                            ListeCase.add(new Point(x+1,y+1));
                        }
                    }
                }
            }
            
        return ListeCase;
    }
            
    @Override public boolean Echec(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
    {
        boolean echec = false;
        if (couleur == Piece.color.Blanc)
        {
            if (y-1 >= 0)
                {
                    if (x-1 >= 0)
                    {
                        if ((plateauJeu.TypeCase(x-1,y-1).GetCouleur() == Piece.color.Noir) && (plateauJeu.TypeCase(x-1,y-1).GetPiece() instanceof Pieces.Roi))
                        {
                           echec= true;
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if ((plateauJeu.TypeCase(x+1,y-1).GetCouleur() == Piece.color.Noir) && (plateauJeu.TypeCase(x+1,y-1).GetPiece() instanceof Pieces.Roi))
                        {
                            echec= true;
                        }
                    }
                }
        }
        else
            {
                if (y+1 < plateauJeu.lignes)
                {
                    if (x-1 >= 0)
                    {
                        if ((plateauJeu.TypeCase(x-1,y+1).GetCouleur() == Piece.color.Blanc) && (plateauJeu.TypeCase(x-1,y+1).GetPiece() instanceof Pieces.Roi))
                        {
                            echec= true;
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if ((plateauJeu.TypeCase(x+1,y+1).GetCouleur() == Piece.color.Blanc) && (plateauJeu.TypeCase(x+1,y+1).GetPiece() instanceof Pieces.Roi))
                        {
                            echec= true;
                        }
                    }
                }
            }
            
        return echec;
    }
    
    @Override public boolean EchecCase(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean echec = false;
        if (couleur == Piece.color.Blanc)
        {
            if (y-1 >= 0)
                {
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y-1).GetPiece() instanceof Pieces.CaseVide)
                        {
                           if ((x-1 == caseX) && (y-1 == caseY))
                           {
                                 echec = true;
                           } 
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y-1).GetPiece() instanceof Pieces.CaseVide)
                        {
                            if ((x+1 == caseX) && (y-1 == caseY))
                           {
                                 echec = true;
                           }
                        }
                    }
                }
        }
        else
            {
                if (y+1 < plateauJeu.lignes)
                {
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y+1).GetPiece() instanceof Pieces.CaseVide)
                        {
                           if ((x-1 == caseX) && (y+1 == caseY))
                           {
                                 echec = true;
                           }
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y+1).GetPiece() instanceof Pieces.CaseVide)
                        {
                            if ((x+1 == caseX) && (y+1 == caseY))
                           {
                                 echec = true;
                           }
                        }
                    }
                }
            }
            
        return echec;
     }
    @Override public boolean ProtectCase(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean protect = false;
        if (couleur == Piece.color.Blanc)
        {
            if (y-1 >= 0)
                {
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y-1).GetCouleur() == Piece.color.Blanc)
                        {
                           if ((x-1 == caseX) && (y-1 == caseY))
                           {
                                 protect = true;
                           } 
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y-1).GetCouleur() == Piece.color.Blanc)
                        {
                            if ((x+1 == caseX) && (y-1 == caseY))
                           {
                                 protect = true;
                           }
                        }
                    }
                }
        }
        else
            {
                if (y+1 < plateauJeu.lignes)
                {
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y+1).GetCouleur() == Piece.color.Noir)
                        {
                           if ((x-1 == caseX) && (y+1 == caseY))
                           {
                                 protect = true;
                           }
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y+1).GetCouleur() == Piece.color.Noir)
                        {
                            if ((x+1 == caseX) && (y+1 == caseY))
                           {
                                 protect = true;
                           }
                        }
                    }
                }
            }
            
        return protect;
     }
    @Override public boolean AntiEchec(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean AntiEchec = false;
        if (couleur == Piece.color.Blanc)
        {
         if (couleur == Piece.color.Blanc)
         {
             couleur = Piece.color.Noir;
         }
         else
         {
             couleur = Piece.color.Blanc;
         }
            if (y-1 >= 0)
                {
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y-1).GetCouleur() == couleur)
                        {
                           if ((x-1 == caseX) && (y-1 == caseY))
                           {
                                 AntiEchec = true;
                           } 
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y-1).GetCouleur() == couleur)
                        {
                            if ((x+1 == caseX) && (y-1 == caseY))
                           {
                                 AntiEchec = true;
                           }
                        }
                    }
                }
        }
        else
            {
                if (couleur == Piece.color.Blanc)
                {
                    couleur = Piece.color.Noir;
                }
                else
                {
                    couleur = Piece.color.Blanc;
                }
                if (y+1 < plateauJeu.lignes)
                {
                    if (x-1 >= 0)
                    {
                        if (plateauJeu.TypeCase(x-1,y+1).GetCouleur() == couleur)
                        {
                           if ((x-1 == caseX) && (y+1 == caseY))
                           {
                                 AntiEchec = true;
                           }
                        }
                    }
                    if (x+1 < plateauJeu.colonnes)
                    {
                        if (plateauJeu.TypeCase(x+1,y+1).GetCouleur() == couleur)
                        {
                            if ((x+1 == caseX) && (y+1 == caseY))
                           {
                                 AntiEchec = true;
                           }
                        }
                    }
                }
            }
        return AntiEchec;
     }
    
}
