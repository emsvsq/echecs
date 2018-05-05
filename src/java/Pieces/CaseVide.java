package Pieces;

import Game.PlateauDeJeu;
import java.awt.Point;
import java.util.ArrayList;

public class CaseVide extends Piece{
    int X;
    int Y;
            
    public CaseVide(int X, int Y) 
    {super (X, Y, color.Vide);
        super.cheminImage ="images/casevide.png";
        super.cheminImage3D ="images/casevide.png";
        super.type = "CaseVide";
        super.name = "CaseVide";
    }
    @Override public boolean EchecCase(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
         return false;
     }
    @Override public boolean ProtectCase(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
        boolean protect = false;
        return protect;
     }
}
