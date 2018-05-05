package Pieces;

import Game.PlateauDeJeu;
import java.awt.Point;
import java.util.ArrayList;

public class Roi extends Piece{
    int X;
    int Y;
    boolean echec = false;
    
    public Roi(int X, int Y, Piece.color couleur) 
    {super (X, Y, couleur);  
     if (couleur == Piece.color.Blanc)
        {
            super.cheminImage = "images/RoiBlanc.png";
            super.cheminImage3D = "images/chess3D/roiblanc.png";
        }
        else
        {
            super.cheminImage = "images/RoiNoir.png";
            super.cheminImage3D = "images/chess3D/roinoir.png";
        }
     super.type = "Roi";
    }
    
    public boolean echec()
    {
        return echec;
    }
    public void setEchec(boolean bChoix)
    {
        echec = bChoix;
    }
    
    @Override public ArrayList<Point> CasesPossibles(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
     {
         boolean GoOn = true;
         if (couleur == Piece.color.Blanc)
         {
             couleur = Piece.color.Noir;
         }
         else
         {
             couleur = Piece.color.Blanc;
         }
         ArrayList<Point> ListeCase = new ArrayList<Point>();
        
            if (y-1 >= 0)
                {
                    if ((plateauJeu.TypeCase(x,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x,y-1).GetCouleur() == couleur))
                    {
                            for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x,y-1))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x,y-1))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x,y-1));
                            }
                    }
                }
                GoOn = true;
                if ((y-1 >= 0) && (x-1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x-1,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y-1).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x-1,y-1))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x-1,y-1))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x-1,y-1));
                            }
                    }
                }
                GoOn = true;
                if ((y-1 >= 0) && (x+1 <= plateauJeu.colonnes -1))
                {
                    if ((plateauJeu.TypeCase(x+1,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y-1).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x+1,y-1))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x+1,y-1))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x+1,y-1));
                            }
                    }
                }
                GoOn = true;
                if (x-1 >= 0)
                {
                    if ((plateauJeu.TypeCase(x-1,y).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x-1,y))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x-1,y))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x-1,y));
                            }
                    }
                }
                GoOn = true;
                if (x+1 <= plateauJeu.colonnes-1)
                {
                    if ((plateauJeu.TypeCase(x+1,y).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x+1,y))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x+1,y))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x+1,y));
                            }
                    }
                }
                GoOn = true;
                if ((y+1 <= plateauJeu.lignes-1) && (x-1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x-1,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y+1).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x-1,y+1))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x-1,y+1))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                    ListeCase.add(new Point(x-1,y+1));
                            }
                    }
                }
                GoOn = true;
                if ((y+1 <= plateauJeu.lignes-1) && (x+1 <= plateauJeu.colonnes-1))
                {
                    if ((plateauJeu.TypeCase(x+1,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y+1).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x+1,y+1))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x+1,y+1))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x+1,y+1));
                            }
                    }
                }
                GoOn = true;
                if (y+1 <= plateauJeu.lignes-1)
                {
                    if ((plateauJeu.TypeCase(x,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x,y+1).GetCouleur() == couleur))
                    {
                        for(int u =0; u < plateauJeu.lignes; u++) 
                            {
                                 for(int i =0; i < plateauJeu.colonnes; i++) 
                                 {
                                      if (plateauJeu.TypeCase(i, u).GetCouleur() == couleur)
                                      {
                                           if (plateauJeu.TypeCase(i, u).EchecCase(couleur, i, u, plateauJeu, x,y+1))
                                           {
                                               GoOn = false;
                                           }
                                           if (plateauJeu.TypeCase(i, u).ProtectCase(couleur, i, u, plateauJeu, x,y+1))
                                           {
                                               GoOn = false;
                                           }
                                      }
                                 }
                            }
                            if (GoOn)
                            {
                                ListeCase.add(new Point(x,y+1));
                            }
                    }
                }
        
        return ListeCase;
     }
    
    @Override public boolean ProtectCase(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean protect = false;
            if (y-1 >= 0)
                {
                    if ((plateauJeu.TypeCase(x,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x,y-1).GetCouleur() == couleur))
                    {
                        if ((x == caseX) && (y-1 == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if ((y-1 >= 0) && (x-1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x-1,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y-1).GetCouleur() == couleur))
                    {
                        if ((x-1 == caseX) && (y-1 == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if ((y-1 >= 0) && (x+1 <= plateauJeu.colonnes -1))
                {
                    if ((plateauJeu.TypeCase(x+1,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y-1).GetCouleur() == couleur))
                    {
                        if ((x+1 == caseX) && (y-1 == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if (x-1 >= 0)
                {
                    if ((plateauJeu.TypeCase(x-1,y).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y).GetCouleur() == couleur))
                    {
                        if ((x-1 == caseX) && (y == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if (x+1 <= plateauJeu.colonnes-1)
                {
                    if ((plateauJeu.TypeCase(x+1,y).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y).GetCouleur() == couleur))
                    {
                        if ((x+1 == caseX) && (y == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if ((y+1 <= plateauJeu.lignes-1) && (x-1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x-1,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y+1).GetCouleur() == couleur))
                    {
                        if ((x-1 == caseX) && (y+1 == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if ((y+1 <= plateauJeu.lignes-1) && (x+1 <= plateauJeu.colonnes-1))
                {
                    if ((plateauJeu.TypeCase(x+1,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y+1).GetCouleur() == couleur))
                    {
                        if ((x+1 == caseX) && (y+1 == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
                if (y+1 <= plateauJeu.lignes-1)
                {
                    if ((plateauJeu.TypeCase(x,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x,y+1).GetCouleur() == couleur))
                    {
                        if ((x == caseX) && (y+1 == caseY))
                        {
                            protect = true;
                        } 
                    }
                }
        return protect;
     }
     
     @Override public boolean EchecCase(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
         boolean echec = false;
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
                    if ((plateauJeu.TypeCase(x,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x,y-1).GetCouleur() == couleur))
                    {
                        if ((x == caseX) && (y-1 == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if ((y-1 >= 0) && (x-1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x-1,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y-1).GetCouleur() == couleur))
                    {
                        if ((x-1 == caseX) && (y-1 == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if ((y-1 >= 0) && (x+1 <= plateauJeu.colonnes -1))
                {
                    if ((plateauJeu.TypeCase(x+1,y-1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y-1).GetCouleur() == couleur))
                    {
                        if ((x+1 == caseX) && (y-1 == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if (x-1 >= 0)
                {
                    if ((plateauJeu.TypeCase(x-1,y).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y).GetCouleur() == couleur))
                    {
                        if ((x-1 == caseX) && (y == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if (x+1 <= plateauJeu.colonnes-1)
                {
                    if ((plateauJeu.TypeCase(x+1,y).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y).GetCouleur() == couleur))
                    {
                        if ((x+1 == caseX) && (y == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if ((y+1 <= plateauJeu.lignes-1) && (x-1 >= 0))
                {
                    if ((plateauJeu.TypeCase(x-1,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x-1,y+1).GetCouleur() == couleur))
                    {
                        if ((x-1 == caseX) && (y+1 == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if ((y+1 <= plateauJeu.lignes-1) && (x+1 <= plateauJeu.colonnes-1))
                {
                    if ((plateauJeu.TypeCase(x+1,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x+1,y+1).GetCouleur() == couleur))
                    {
                        if ((x+1 == caseX) && (y+1 == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
                if (y+1 <= plateauJeu.lignes-1)
                {
                    if ((plateauJeu.TypeCase(x,y+1).GetCouleur() == Piece.color.Vide) || (plateauJeu.TypeCase(x,y+1).GetCouleur() == couleur))
                    {
                        if ((x == caseX) && (y+1 == caseY))
                        {
                            echec = true;
                        } 
                    }
                }
        
         return echec;
     }
}

