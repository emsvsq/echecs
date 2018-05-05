package Game;

import Pieces.CaseVide;
import Pieces.Cheval;
import Pieces.Fou;
import Pieces.Piece;
import Pieces.Pion;
import Pieces.Reine;
import Pieces.Roi;
import Pieces.Tour;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class IA {
    
    public PlateauDeJeu game;
    
    public IA(PlateauDeJeu plateau) {
        this.game = plateau;
    }
    
    
    public ArrayList<Point> Jouer()
    {
        ArrayList<Point> returnListeCoordonnees = new ArrayList<>();
        
        ArrayList<Point> ListeCase = new ArrayList<>();
        int BestShot = -1;
        Pieces.Piece PieceToMove = null;
        Point CoordPieceToMove = new Point(0,0);
        int CurrentShot = -1;
        int nbrtotalcasepossible = 0;
        
        //TimerCount.scheduleAtFixedRate(TaskTimer, 0, 1000);
        //ECHEC ET MAT
        for (int intj = 0; intj < game.lignes; intj++) 
            {
                for (int inti = 0; inti < game.colonnes; inti++) 
                {
                    if (game.TypeCase(inti, intj).GetCouleur() == Piece.color.Noir) 
                    {
                        ArrayList<Point> aTestEveryPoint = new ArrayList<>();
                        aTestEveryPoint = game.ListeCasesPossibles(game.TypeCase(inti, intj));
                        boolean bTempEchec = false;
                        for (Point coordonnee : aTestEveryPoint) 
                        {
                            bTempEchec = false;
                            Pieces.Piece CurrentPieceSelected = game.TypeCase(inti, intj);
                            game.Positionner(new Pieces.CaseVide(inti, intj));
                            Piece PieceTestCase = game.TypeCase(coordonnee.x, coordonnee.y);
                            game.Positionner(new Pion(coordonnee.x, coordonnee.y, CurrentPieceSelected.GetCouleur()));
                            for (int intu = 0; intu < game.colonnes; intu++) 
                            {
                                for (int intiu = 0; intiu < game.lignes; intiu++) 
                                {
                                    if (!(game.TypeCase(intiu, intu) instanceof CaseVide)) 
                                    {
                                        if (game.EchecRoi(game.TypeCase(intiu, intu))) 
                                        {
                                            if (((game.tour % 2 == 0) && (game.TypeCase(intiu, intu).GetCouleur() == Piece.color.Noir)) || ((game.tour % 2 != 0) &&
                                                    (game.TypeCase(intiu, intu).GetCouleur() == Piece.color.Blanc))) 
                                            {
                                                bTempEchec = true;
                                                game.iCaseEchecX = intiu;
                                                game.iCaseEchecY = intu;
                                            }
                                        }
                                    }
                                }
                            }
                            
                            game.Positionner(CurrentPieceSelected);
                            game.Positionner(PieceTestCase);
                            if ((!bTempEchec) || ((game.iCaseEchecX == coordonnee.x) && (game.iCaseEchecY == coordonnee.y))) 
                            {
                                nbrtotalcasepossible=nbrtotalcasepossible +1;
                            }
                        }
                    }
                }
            }
        
        
        for (int intj = 0; intj < game.lignes; intj++) 
            {
                for (int inti = 0; inti < game.colonnes; inti++) 
                {
                    if (game.TypeCase(inti, intj).GetCouleur() == Piece.color.Noir) 
                    {
                        ArrayList<Point> aTestEveryPoint = new ArrayList<>();
                        aTestEveryPoint = game.ListeCasesPossibles(game.TypeCase(inti, intj));
                        boolean bTempEchec = false;
                        for (Point coordonnee : aTestEveryPoint) 
                        {
                            bTempEchec = false;
                            Piece CurrentPieceSelected = game.TypeCase(inti, intj);
                            game.Positionner(new CaseVide(inti, intj));
                            Piece PieceTestCase = game.TypeCase(coordonnee.x, coordonnee.y);
                            game.Positionner(new Pion(coordonnee.x, coordonnee.y, CurrentPieceSelected.GetCouleur()));
                            for (int intu = 0; intu < game.colonnes; intu++) 
                            {
                                for (int intiu = 0; intiu < game.lignes; intiu++) 
                                {
                                    if (!(game.TypeCase(intiu, intu) instanceof CaseVide)) 
                                    {
                                        if (game.EchecRoi(game.TypeCase(intiu, intu))) 
                                        {
                                            if (((game.tour % 2 == 0) && (game.TypeCase(intiu, intu).GetCouleur() == Piece.color.Noir)) || ((game.tour % 2 != 0) && 
                                                    (game.TypeCase(intiu, intu).GetCouleur() == Piece.color.Blanc))) 
                                            {
                                                bTempEchec = true;
                                                game.iCaseEchecX = intiu;
                                                game.iCaseEchecY = intu;
                                            }
                                        }
                                    }
                                }
                            }
                            
                            game.Positionner(CurrentPieceSelected);
                            game.Positionner(PieceTestCase);
                            if ((!bTempEchec) || ((game.iCaseEchecX == coordonnee.x) && (game.iCaseEchecY == coordonnee.y))) 
                            {
                                if ((game.TypeCase(coordonnee.x, coordonnee.y).GetPiece() instanceof Pion) && 
                                        (game.TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Blanc))
                                {
                                     CurrentShot = 1;
                                }
                                else if ((game.TypeCase(coordonnee.x, coordonnee.y).GetPiece() instanceof Cheval) && 
                                        (game.TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Blanc))
                                {
                                     CurrentShot = 3;
                                }
                                else if ((game.TypeCase(coordonnee.x, coordonnee.y).GetPiece() instanceof Fou) && 
                                        (game.TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Blanc))
                                {
                                     CurrentShot = 3;
                                }
                                else if ((game.TypeCase(coordonnee.x, coordonnee.y).GetPiece() instanceof Reine) && 
                                        (game.TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Blanc))
                                {
                                     CurrentShot = 10;
                                }
                                else if ((game.TypeCase(coordonnee.x, coordonnee.y).GetPiece() instanceof Roi) && 
                                        (game.TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Blanc))
                                {
                                     CurrentShot = 6;
                                }
                                else if ((game.TypeCase(coordonnee.x, coordonnee.y).GetPiece() instanceof Tour) && 
                                        (game.TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Blanc))
                                {
                                     CurrentShot = 5;
                                }
                                
                                if (CurrentShot > BestShot)
                                {
                                     PieceToMove = CurrentPieceSelected;
                                     CoordPieceToMove = new Point(coordonnee.x, coordonnee.y);
                                     BestShot = CurrentShot;
                                }
                            }
                        }
                    }
                }
            }
        if (nbrtotalcasepossible == 0)
        {
            game.bIAplay=false;
            game.bIAlost = true;
        }
        else if (BestShot > -1)
        {   
            game.bIAplay=false;
            ListeCase = game.ListeCasesPossibles(game.TypeCase(PieceToMove.GetX(), PieceToMove.GetY()));
            returnListeCoordonnees.add(new Point(CoordPieceToMove.x, CoordPieceToMove.y));
            returnListeCoordonnees.add(new Point(PieceToMove.GetX(), PieceToMove.GetY()));
        }
        else
        {
            ArrayList<Point> cases = new ArrayList<>();
            cases.clear();
            while (cases.size() == 0) 
            {
                for (int intj = 0; intj < game.lignes; intj++) 
                {
                    for (int inti = 0; inti < game.colonnes; inti++) 
                    {
                        if (game.TypeCase(inti, intj).GetCouleur() == Piece.color.Noir) 
                        {
                            Point route = new Point();
                            route.x = inti;
                            route.y = intj;
                            cases.add(route);
                        }
                    }
                }
                Random Rat = new Random();
                int nbr = Rat.nextInt(cases.size());
                Point piececoord = (Point) (cases.get(nbr));
                Piece p = game.TypeCase(piececoord.x, piececoord.y);
                cases.clear();
                ArrayList<Point> aTestEveryPoint = new ArrayList<>();
                ListeCase = game.ListeCasesPossibles(game.TypeCase(piececoord.x, piececoord.y));
                aTestEveryPoint = game.ListeCasesPossibles(game.TypeCase(piececoord.x, piececoord.y));
                boolean bTempEchec = false;
                for (Point coordonnee : aTestEveryPoint) 
                {
                    bTempEchec = false;
                    Piece CurrentPieceSelected = game.TypeCase(piececoord.x, piececoord.y);
                    game.Positionner(new CaseVide(piececoord.x, piececoord.y));
                    Piece PieceTestCase = game.TypeCase(coordonnee.x, coordonnee.y);
                    game.Positionner(new Pion(coordonnee.x, coordonnee.y, CurrentPieceSelected.GetCouleur()));
                    for (int intu = 0; intu < game.lignes; intu++) 
                    {
                        for (int inti = 0; inti < game.colonnes; inti++) 
                        {
                            if (!(game.TypeCase(inti, intu) instanceof CaseVide)) 
                            {
                                if (game.EchecRoi(game.TypeCase(inti, intu))) 
                                {
                                    if (((game.tour % 2 == 0) && (game.TypeCase(inti, intu).GetCouleur() == Piece.color.Noir)) || ((game.tour % 2 != 0) && 
                                            (game.TypeCase(inti, intu).GetCouleur() == Piece.color.Blanc))) 
                                    {
                                        bTempEchec = true;
                                        game.iCaseEchecX = inti;
                                        game.iCaseEchecY = intu;
                                    }
                                }
                            }
                        }
                    }
                    if ((!bTempEchec) || ((game.iCaseEchecX == coordonnee.x) && (game.iCaseEchecY == coordonnee.y))) 
                    {
                        cases.add(new Point(coordonnee.x, coordonnee.y));
                    }
                    game.Positionner(CurrentPieceSelected);
                    game.Positionner(PieceTestCase);
                }
                if (cases.size() > 0) 
                {

                    int newnbr = Rat.nextInt(cases.size());
                    int coordX = cases.get(newnbr).x;
                    int coordY = cases.get(newnbr).y;
                    
                    returnListeCoordonnees.add(new Point(coordX, coordY));
                    returnListeCoordonnees.add(new Point(p.GetX(), p.GetY()));
                    
                    game.bIAplay=false;
                }
            }
        }
        
        return returnListeCoordonnees;
    }
}
