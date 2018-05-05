package Pieces;

import Game.PlateauDeJeu;
import java.awt.Point;
import java.util.ArrayList;

public class Piece {
    
    protected int X;
    protected int Y;
    protected String name;
    protected color couleur;
    protected String type;
    protected String cheminImage;
    protected String cheminImage3D;
    public boolean bOnTop = false;
    public boolean bDead = false;
    
    public enum color {
        Blanc,
        Noir,
        Vide,
        Neutre
    }
    
    public Piece(int X, int Y, color color)
    {
        this.X = X;
        this.Y = Y;
        this.couleur = color;
    }
    
    public int GetX()
    {
        return this.X;
    }
    public int GetY()
    {
        return this.Y;
    }
    public void SetX(int X)
    {
        this.X = X;
    }
    public void SetY(int Y)
    {
        this.Y = Y;
    }
    public void SetPosition(int X, int Y)
    {
        this.X= X;
        this.Y = Y;
    }
    public String ToString()
    {
        String coord = "" + this.X + ";" + this.Y;
        return coord;
    }
    public String GetName()
    {
        return type;
    }
    public color GetCouleur()
    {
        return couleur;
    }
    public String GetImage()
    {
        return cheminImage;
    }
    public String GetImage3D()
    {
        return cheminImage3D;
    }
    public Piece GetPiece()
    {
        return this;
    }
    //MUSTOVERRIDE
    public  ArrayList<Point> CasesPossibles(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
     {
         ArrayList<Point> WhereIsMustOverride = new ArrayList<>();
         WhereIsMustOverride.clear();
         return WhereIsMustOverride;
     }
     public boolean Echec(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu)
     {
         return false;
     }
     public boolean EchecCase(Piece.color couleur, int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
         return false;
     }
     public boolean ProtectCase(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
         return false;
     }
     
     public boolean AntiEchec(Piece.color couleur,int x, int y, PlateauDeJeu plateauJeu, int caseX, int caseY)
     {
    	 return false;
     }
}
