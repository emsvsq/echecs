package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import Pieces.*;

public class PlateauDeJeu {
    public int colonnes;
    public int lignes;
    public Pieces.Piece sPlateformeJeu[][];
    public List<Pieces.Piece> listePieces = new ArrayList<>();
    public int tour;
    public boolean bPlaying = false;
    
    public int iCaseEchecX = -1;
    public int iCaseEchecY = -1;  
    
    public List<List<Pieces.Piece>> listeSauvegarde = new ArrayList<>();
    
    public Engine engine;
    public IA adversaire;
        
    public boolean bIA = false;
    public boolean bIAlost = false;
    public boolean bIAplay = false;
    
    public Pieces.Roi RoiBlanc = null;
    public Pieces.Roi RoiNoir = null;
    public boolean bEchecRoiBlanc = false;
    public boolean bEchecRoiNoir = false;
    
    public PlateauDeJeu(Engine engine)
    {
        this.lignes = 8;
        this.colonnes = 8;
        sPlateformeJeu = new Pieces.Piece[colonnes][lignes];
        tour = 0;
        this.engine = engine;
        adversaire = new IA(this);
        
        //Placement des Pions
        listePieces.add(new Pieces.Pion(0, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(1, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(2, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(3, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(4, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(5, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(6, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Pion(7, 6, Piece.color.Blanc));
        listePieces.add(new Pieces.Tour (0, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Tour (7, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Cheval(1, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Cheval(6, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Fou(2, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Fou(5, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Reine(3, 7, Piece.color.Blanc));
        listePieces.add(new Pieces.Roi(4, 7, Piece.color.Blanc));

        listePieces.add(new Pieces.Pion(0, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(1, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(2, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(3, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(4, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(5, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(6, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Pion(7, 1, Piece.color.Noir));
        listePieces.add(new Pieces.Tour(0, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Tour(7, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Cheval(1, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Cheval(6, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Fou(2, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Fou(5, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Reine(3, 0, Piece.color.Noir));
        listePieces.add(new Pieces.Roi(4, 0, Piece.color.Noir));
        
        Positionner();
        SaveGame();
    }
    
    public void Positionner() {
        for(int ligne = 0; ligne < lignes; ligne++) {
            for(int colonne = 0; colonne < colonnes; colonne++) {
                sPlateformeJeu[colonne][ligne] = new Pieces.CaseVide(colonne, ligne);
            }
        }
        
        for(int ligne = 0; ligne < lignes; ligne++) {
            for(int colonne = 0; colonne < colonnes; colonne++) {
               for(Pieces.Piece p : listePieces) {
                    if ((p.GetX() == colonne) && (p.GetY() == ligne)) {
                        sPlateformeJeu[colonne][ligne] = p;
                    }
                }
            }
        }
    }
    
    public String Mode() {
        String returnValue;
        if (bIA) {
            returnValue = "IA";
        } else {
            returnValue = "Multiplayer";
        }
        return returnValue;
    }
    
    public void SetParameters(String mode, int tour) {
        if (tour % 2 == 0) {
            this.tour = 0;
        } else {
            this.tour = 1;
        }
        bIA = ("IA").equals(mode);
        
    }
    
    public void Take(Point coord) {
        ////Déplacement Du pion
        for(int ligne = 0; ligne < lignes; ligne++)
        {
            for(int colonne = 0; colonne < colonnes; colonne++) {
                Pieces.Piece p = sPlateformeJeu[colonne][ligne];
                
                    if ((p.GetX() == coord.x) && (p.GetY() == coord.y)) {
                        if ((tour % 2 == 0) && (p.GetCouleur() == Piece.color.Blanc)) {
                            p.bOnTop = true;
                            bPlaying = true;
                        } else if ((tour % 2 != 0) && (p.GetCouleur()  == Piece.color.Noir)) {
                            p.bOnTop = true;
                            bPlaying = true;
                        }
                        break;
                    }
            }
        }
    }
    
    public void Move(Point coord) {
        //Pieces.Roi RoiBlanc = null;
        //Pieces.Roi RoiNoir = null;
        bEchecRoiBlanc = false;
        bEchecRoiNoir = false;
        boolean bPlayed = false;
        ////Mouvement au point cliqué
        for(int ligne = 0; ligne < lignes; ligne++)
        {
            for(int colonne = 0; colonne < colonnes; colonne++) {
                    Pieces.Piece p = sPlateformeJeu[colonne][ligne];
                    
                    if (p instanceof Pieces.Roi) {
                        if (p.GetCouleur()  == Piece.color.Blanc) {
                            RoiBlanc = (Pieces.Roi)p;
                            RoiBlanc.setEchec(false);
                        } else {
                            RoiNoir = (Pieces.Roi)p;
                            RoiNoir.setEchec(false);
                        }
                    }
                    
                    if (p.bOnTop) {
                        //ArrayList<Point> listCasesPossibles = p.CasesPossibles(p.GetCouleur(), p.GetX(), p.GetY(), this);
                        ArrayList<Point> listCasesPossibles = GetPieceCasePossibles(new Point(p.GetX(), p.GetY()));
                        
                         for(Point emplacement : listCasesPossibles) {
                            if ((coord.x == emplacement.x) && (coord.y == emplacement.y)) {
                                Point oldPosition = new Point(p.GetX(), p.GetY());
                                
                                p.SetPosition(emplacement.x, emplacement.y);
                                sPlateformeJeu[emplacement.x][emplacement.y].bDead = true;
                                sPlateformeJeu[emplacement.x][emplacement.y] = p;
                                sPlateformeJeu[oldPosition.x][oldPosition.y] = new Pieces.CaseVide(oldPosition.x, oldPosition.y);
                                tour++;
                                bPlayed = true;
                            }
                        }
                        p.bOnTop = false;
                        bPlaying = false;
                    }
                }
            
        }
        
        ////Test d'échec au roi
        for(int ligne =0; ligne < lignes; ligne++) 
        {
             for(int colonne =0; colonne < colonnes; colonne++) 
             {
                  if (!(TypeCase(colonne, ligne) instanceof Pieces.CaseVide))
                  {
                      //Pieces.Piece currentPiece = sPlateformeJeu[coord.x][coord.y];
                      Pieces.Piece currentPiece = sPlateformeJeu[colonne][ligne];
                      if (EchecRoi(currentPiece))
                      {
                          if (tour % 2 == 0)
                          {
                              RoiBlanc.setEchec(true);
                              bEchecRoiBlanc = true;
                          }
                          else
                          {
                              RoiNoir.setEchec(true);
                              bEchecRoiNoir = true;
                          }
                          iCaseEchecX = colonne;
                          iCaseEchecY = ligne;
                      }
                   }
              }
        }
        
        
        ////Gestion de l'échec au roi ou mat
        int NbrCasesPossibles = 0;
        ArrayList<Point> aTestEveryPoint = new ArrayList<>();
        Pieces.Piece CurrentPiece=new Pieces.Piece(0,0, Piece.color.Neutre);
        boolean bPieceProtege = false;
        boolean bTempEchec = false;
        ArrayList<Point> ListeCaseEchec = new ArrayList<Point>();
        if (bEchecRoiBlanc)
        {
            for(int ligne =0; ligne < lignes; ligne++) 
            {
                 for(int colonne = 0; colonne < colonnes; colonne++) 
                 {
                         ListeCaseEchec.clear();
                         if (TypeCase(colonne, ligne).GetCouleur()  == Piece.color.Blanc)
                         {
                           ListeCaseEchec.clear();
                           //aTestEveryPoint = ListeCasesPossibles(TypeCase(colonne, ligne), colonne, ligne);
                           Pieces.Piece currentP = TypeCase(colonne, ligne);
                           aTestEveryPoint = currentP.CasesPossibles(currentP.GetCouleur(), currentP.GetX(), currentP.GetY(), this);
                           for (Point coordonnee : aTestEveryPoint) 
                           {
                               bTempEchec = false;
                               Pieces.Piece CurrentPieceSelected = TypeCase(colonne, ligne);
                               Positionner(new Pieces.CaseVide(colonne, ligne));
                               Pieces.Piece PieceTestCase = TypeCase(coordonnee.x, coordonnee.y);
                               Positionner(new Pieces.Pion(coordonnee.x, coordonnee.y, CurrentPieceSelected.GetCouleur()));
                               //Positionner(new Pieces.Piece(coordonnee.x, coordonnee.y));
                               for (int intu = 0; intu < lignes; intu++) 
                               {
                                   for (int inti = 0; inti < colonnes; inti++) 
                                   {
                                       if (!(TypeCase(inti, intu) instanceof Pieces.CaseVide)) 
                                       {
                                           //if (EchecRoi(TypeCase(inti, intu), inti, intu)) 
                                           Pieces.Piece p = sPlateformeJeu[intu][inti];
                                           if (p.Echec(p.GetCouleur(), p.GetX(), p.GetY(), this))
                                           {
                                               bTempEchec = true;
                                           }
                                       }
                                   }
                               }
                               if ((!bTempEchec) && (TypeCase(coordonnee.x, coordonnee.y).GetCouleur()  == Piece.color.Blanc))
                               {
                                   ListeCaseEchec.add(new Point(coordonnee.x, coordonnee.y));
                               }
                               Positionner(CurrentPieceSelected);
                               Positionner(PieceTestCase);
                           }
                           NbrCasesPossibles = NbrCasesPossibles + ListeCaseEchec.size();
                        }
                 }
            }
            engine.DisplayEchec(NbrCasesPossibles);
        }
        else if (bEchecRoiNoir)
        {
            for(int u =0; u < lignes; u++) 
            {
                 for(int i =0; i < colonnes; i++) 
                 {
                         ListeCaseEchec.clear();
                         if (TypeCase(i, u).GetCouleur() == Piece.color.Noir)
                         {
                           ListeCaseEchec.clear();
                           aTestEveryPoint = ListeCasesPossibles(TypeCase(i, u));
                           for (Point coordonnee : aTestEveryPoint) 
                           {
                               bTempEchec = false;
                               Pieces.Piece CurrentPieceSelected = TypeCase(i, u);
                               Positionner(new Pieces.CaseVide(i, u));
                               Pieces.Piece PieceTestCase = TypeCase(coordonnee.x, coordonnee.y);
                               Positionner(new Pieces.Pion(coordonnee.x, coordonnee.y, CurrentPieceSelected.GetCouleur()));
                               for (int intu = 0; intu < lignes; intu++) 
                               {
                                   for (int inti = 0; inti < colonnes; inti++) 
                                   {
                                       if (!(TypeCase(inti, intu) instanceof Pieces.CaseVide)) 
                                       {
                                           if (EchecRoi(TypeCase(inti, intu))) 
                                           {
                                               bTempEchec = true;
                                           }
                                       }
                                   }
                               }
                               if ((!bTempEchec) && (TypeCase(coordonnee.x, coordonnee.y).GetCouleur() == Piece.color.Noir))
                               {
                                   ListeCaseEchec.add(new Point(coordonnee.x, coordonnee.y));
                               }
                               Positionner(CurrentPieceSelected);
                               Positionner(PieceTestCase);
                           }
                           NbrCasesPossibles = NbrCasesPossibles + ListeCaseEchec.size();
                        }
                 }
            }
            engine.DisplayEchec(NbrCasesPossibles);
        }
        
        
        if ((bIA) && (tour % 2 != 0)) {
            //L'IA joue
            IntelligenceArtificielle();
        }
        if (bPlayed) {
            SaveGame();
        }
    }
    
    public ArrayList<Point> GetPieceCasePossibles(Point coord) {
        ///étude de l'échec
        
        ////Trouver les rois
        for(int ligne = 0; ligne < lignes; ligne++)
        {
            for(int colonne = 0; colonne < colonnes; colonne++) {
                    Pieces.Piece p = sPlateformeJeu[colonne][ligne];
                    
                    if (p instanceof Pieces.Roi) {
                        if (p.GetCouleur()  == Piece.color.Blanc) {
                            RoiBlanc = (Pieces.Roi)p;
                        } else {
                            RoiNoir = (Pieces.Roi)p;
                        }
                    }
            }
        }
        
        
        ///Test de toute les cases de chaque possibilité de chaque pièce, si la case met en echec le roi, ou l'empêche d'être en echec
        Pieces.Piece CurrentPiece = new Pieces.Piece(0,0, Piece.color.Neutre);
        boolean bPieceProtege = false;
        final int oldX = coord.x;
        final int oldY = coord.y;
        
        ArrayList<Point> aTestEveryPoint = new ArrayList<>();
        //aTestEveryPoint = ListeCasesPossibles(TypeCase(oldX, oldY), oldX, oldY);
        Pieces.Piece piece = TypeCase(oldX, oldY);
        aTestEveryPoint = piece.CasesPossibles(piece.GetCouleur(), piece.GetX(), piece.GetY(), this);
        ArrayList<Point> ListeCase = new ArrayList<>();  
        ListeCase.clear();
        if (RoiBlanc.echec()) 
            {
             //if ((piece instanceof Pieces.Roi) || (AntiEchec(piece, oldX, oldY, iCaseEchecX, iCaseEchecY))) 
            /*if ((piece instanceof Pieces.Roi) && (AntiEchec(piece, iCaseEchecX, iCaseEchecY)))   
            {
                if (TypeCase(oldX, oldY) instanceof Pieces.Roi) 
                {
                    Pieces.Piece currentPiece = TypeCase(coord.x, coord.y);
                    ListeCase = currentPiece.CasesPossibles(currentPiece.GetCouleur(), currentPiece.GetX(), currentPiece.GetY(), this);
                } 
                else 
                {
                    //ListeCase.add(new Point(iCaseEchecX, iCaseEchecY));
                    Pieces.Piece currentPiece = TypeCase(coord.x, coord.y);
                    ListeCase = currentPiece.CasesPossibles(currentPiece.GetCouleur(), currentPiece.GetX(), currentPiece.GetY(), this);
                }
            }*/
            if (piece instanceof Pieces.Roi)  
            {
                    Pieces.Piece currentPiece = TypeCase(coord.x, coord.y);
                    ListeCase = currentPiece.CasesPossibles(currentPiece.GetCouleur(), currentPiece.GetX(), currentPiece.GetY(), this);
            }
            
             if (ListeCase.isEmpty()) 
             {
                for (Point coordonnee : aTestEveryPoint) {
                    Pieces.Piece CurrentPieceEchec = TypeCase(coordonnee.x, coordonnee.y);
                    Positionner(new Pieces.Pion(coordonnee.x, coordonnee.y, Piece.color.Blanc));
                    if (!(EchecRoi(TypeCase(iCaseEchecX, iCaseEchecY))))
                    {
                        CurrentPiece = TypeCase(oldX, oldY);
                        bPieceProtege = true;
                        ListeCase.add(new Point(coordonnee.x, coordonnee.y));
                    }
                    if (CurrentPieceEchec instanceof Pieces.CaseVide) 
                    {
                        Positionner(new Pieces.CaseVide(coordonnee.x, coordonnee.y));
                    } else 
                    {
                        Positionner(CurrentPieceEchec);
                    }
                }
             }
         } 
         else if (RoiNoir.echec()) 
         {
            /*if ((TypeCase(oldX, oldY) instanceof Pieces.Roi) || (AntiEchec(TypeCase(oldX, oldY), iCaseEchecX, iCaseEchecY))) 
            {
                if (TypeCase(oldX, oldY) instanceof Pieces.Roi) 
                {
                    ListeCase = ListeCasesPossibles(TypeCase(coord.x, coord.y));
                }
                else 
                {
                    //ListeCase.add(new Point(iCaseEchecX, iCaseEchecY));
                    Pieces.Piece currentPiece = TypeCase(coord.x, coord.y);
                    ListeCase = currentPiece.CasesPossibles(currentPiece.GetCouleur(), currentPiece.GetX(), currentPiece.GetY(), this);
                }
            }*/
             if (TypeCase(oldX, oldY) instanceof Pieces.Roi) 
            {
                Pieces.Piece currentPiece = TypeCase(coord.x, coord.y);
                ListeCase = currentPiece.CasesPossibles(currentPiece.GetCouleur(), currentPiece.GetX(), currentPiece.GetY(), this);
            }
             
             if (ListeCase.isEmpty()) 
             {
                 for (Point coordonnee : aTestEveryPoint) 
                 {
                     Pieces.Piece CurrentPieceEchec = TypeCase(coordonnee.x, coordonnee.y);
                     Positionner(new Pieces.Pion(coordonnee.x, coordonnee.y, Piece.color.Noir));
                     if (!(EchecRoi(TypeCase(iCaseEchecX, iCaseEchecY)))) 
                     {
                         CurrentPiece = TypeCase(oldX, oldY);
                         bPieceProtege = true;
                         ListeCase.add(new Point(coordonnee.x, coordonnee.y));
                     }
                     if (CurrentPieceEchec instanceof Pieces.CaseVide) 
                     {
                         Positionner(new Pieces.CaseVide(coordonnee.x, coordonnee.y));
                     } 
                     else 
                     {
                         Positionner(CurrentPieceEchec);
                     }
                 }
             }
         } 
         else 
         {
             boolean bTempEchec = false;
             for (Point coordonnee : aTestEveryPoint) 
             {
                 bTempEchec = false;
                 Pieces.Piece CurrentPieceSelected = TypeCase(oldX, oldY);
                 Positionner(new Pieces.CaseVide(oldX, oldY));
                 Pieces.Piece PieceTestCase = TypeCase(coordonnee.x, coordonnee.y);
                 Positionner(new Pieces.Pion(coordonnee.x, coordonnee.y, CurrentPieceSelected.GetCouleur()));
                 
                 for (int intu = 0; intu < lignes; intu++) 
                 {
                     for (int inti = 0; inti < colonnes; inti++) 
                     {
                         if (!(TypeCase(inti, intu) instanceof Pieces.CaseVide)) 
                         {
                             if (EchecRoi(TypeCase(inti, intu))) 
                             {
                                 if (((tour % 2 == 0) && (TypeCase(inti, intu).GetCouleur() == Piece.color.Noir)) || ((tour % 2 != 0) && (TypeCase(inti, intu).GetCouleur() == Piece.color.Blanc)))
                                 {
                                      bTempEchec = true;
                                      iCaseEchecX = inti;
                                      iCaseEchecY = intu;
                                 }
                             }
                         }
                     }
                 }
                 if ((!bTempEchec) || ((iCaseEchecX == coordonnee.x) && (iCaseEchecY == coordonnee.y)))
                 {
                     ListeCase.add(new Point(coordonnee.x, coordonnee.y));
                 }
                 Positionner(CurrentPieceSelected);
                 Positionner(PieceTestCase);
                 
             }
         }
         return ListeCase;
    }
    
    public void IntelligenceArtificielle()
    {
        ArrayList<Point> returnListeCoordonnees = adversaire.Jouer();
        try {
            int moveToX = returnListeCoordonnees.get(0).x;
            int moveToY = returnListeCoordonnees.get(0).y;
            
            int pieceX = returnListeCoordonnees.get(1).x;
            int pieceY = returnListeCoordonnees.get(1).y;
            
            Piece p = sPlateformeJeu[pieceX][pieceY];
            p.SetPosition(moveToX, moveToY);
            sPlateformeJeu[moveToX][moveToY].bDead = true;
            sPlateformeJeu[moveToX][moveToY] = p;
            sPlateformeJeu[pieceX][pieceY] = new Pieces.CaseVide(pieceX, pieceY);
            tour++;
        } catch(Exception e) {
        }
    }
    
    public boolean EchecRoi(Pieces.Piece p)
    {
        boolean echec;
        echec = p.Echec(p.GetCouleur(), p.GetX(), p.GetY(), this);
        return echec;
    }
    
    public boolean AntiEchec(Pieces.Piece p, int CaseX, int CaseY)
    {
        boolean antiechec;
        
        antiechec = p.AntiEchec(p.GetCouleur(), p.GetX(), p.GetY(), this, CaseX, CaseY);

        return antiechec;
    }
    
    public ArrayList<Point> ListeCasesPossibles(Pieces.Piece p)
    {
        ArrayList<Point> ListeCase = p.CasesPossibles(p.GetCouleur(), p.GetX(), p.GetY(), this);

        return ListeCase;
    }
    
    public int PieceCount()
    {
        int nbrPiece = 0; 
        for(int u = 0; u<lignes; u++)
        {
            for(int i = 0; i<colonnes; i++)
            {
                if((sPlateformeJeu[i][u] instanceof Pieces.Piece) &&  !(sPlateformeJeu[i][u] instanceof Pieces.CaseVide))
                {
                    nbrPiece++;
                }
            }
        }
        return nbrPiece;
    }
    
    public void SaveGame() {
        listeSauvegarde.add(this.copy());
    }
    
    
    public void Cancel() {
       if (listeSauvegarde.size() > 0) {
           List<Pieces.Piece> list = listeSauvegarde.get(listeSauvegarde.size() - 1);
           listePieces.clear();
           
            for (int i = 0; i < list.size(); i++) {
            
                String sTypePiece = list.get(i).GetName();
                int X = list.get(i).GetX();
                int Y = list.get(i).GetY();
                Piece.color sCouleur = list.get(i).GetCouleur();
                    
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
                    listePieces.add(pieceToAdd);
                }
            }
            
            Positionner();
            tour--;
            listeSauvegarde.remove(listeSauvegarde.size() - 1);
        }
    }
    
    public void Positionner(Pieces.Piece P)
    {
        sPlateformeJeu[P.GetX()][P.GetY()] = P;
    }
    public void Placer(Pieces.Piece P, int X, int Y)
    {
        sPlateformeJeu[X][Y] = P;
    }
    
    public Pieces.Piece TypeCase(int X, int Y)
    {
        return sPlateformeJeu[X][Y];
    }
    
    public String CaseImage(int X, int Y)
    {
        return  sPlateformeJeu[X][Y].GetImage();
    }
    
    public void Clear()
    {
        for(int i=0; i<lignes; i++)
        {
            for (int u=0; u< colonnes; u++)
            {
                sPlateformeJeu[u][i] = new Pieces.CaseVide(u,i);
            }
        }
    }
    public List<Pieces.Piece> copy()
    {
        List<Pieces.Piece> ListeACopier = new ArrayList<>();
        
            for(int ligne = 0; ligne < lignes; ligne++) 
            {
                 for(int colonne = 0; colonne < colonnes; colonne++) 
                 {
                     Pieces.Piece currentPiece = TypeCase(colonne, ligne);
                     if (currentPiece instanceof Pieces.Pion) {
                         ListeACopier.add(new Pieces.Pion(currentPiece.GetX(), currentPiece.GetY(), currentPiece.GetCouleur()));
                     } else if (currentPiece instanceof Pieces.Tour) {
                         ListeACopier.add(new Pieces.Tour(currentPiece.GetX(), currentPiece.GetY(), currentPiece.GetCouleur()));
                     }
                     else if (currentPiece instanceof Pieces.Cheval) {
                         ListeACopier.add(new Pieces.Cheval(currentPiece.GetX(), currentPiece.GetY(), currentPiece.GetCouleur()));
                     }
                     else if (currentPiece instanceof Pieces.Fou) {
                         ListeACopier.add(new Pieces.Fou(currentPiece.GetX(), currentPiece.GetY(), currentPiece.GetCouleur()));
                     }
                     else if (currentPiece instanceof Pieces.Reine) {
                         ListeACopier.add(new Pieces.Reine(currentPiece.GetX(), currentPiece.GetY(), currentPiece.GetCouleur()));
                     }
                     else if (currentPiece instanceof Pieces.Roi) {
                         ListeACopier.add(new Pieces.Roi(currentPiece.GetX(), currentPiece.GetY(), currentPiece.GetCouleur()));
                     }
                 }
            }   
        return ListeACopier;
    }
    
    public PlateauDeJeu copy(PlateauDeJeu Pl)
    {
        PlateauDeJeu PlateauACopier = new PlateauDeJeu(engine);
        PlateauACopier.Clear();
        for (int u = 0; u<PlateauACopier.lignes; u++)
        {
            for (int i = 0; i<PlateauACopier.colonnes; i++)
            {
                //PlateauACopier.Positionner(Pl.TypeCase(i, u)); 
                PlateauACopier.listePieces.add(Pl.TypeCase(i, u)); 
            }
        }
        PlateauACopier.Positionner();
        return PlateauACopier;
    }
    
    public void copy(Pieces.Piece[][] newTab) {
        for (int ligne = 0; ligne < lignes; ligne++) {
            for (int colonne = 0; colonne < colonnes; colonne++) {
                sPlateformeJeu[colonne][ligne] = newTab[colonne][ligne];
            }
        }
    }
    
    public Pieces.Piece[][] copyTab(Pieces.Piece[][] newTab) {
        Pieces.Piece[][] returnTab = new Pieces.Piece[colonnes][lignes];
        for (int ligne = 0; ligne < lignes; ligne++) {
            for (int colonne = 0; colonne < colonnes; colonne++) {
                Pieces.Piece p = newTab[colonne][ligne];
                returnTab[colonne][ligne] = newTab[colonne][ligne];
            }
        }
        return returnTab;
    }
    
    public String Afficher()
    {
        String texte= "";
        for (int ligne = 0; ligne < lignes; ligne++)
        {
            for (int colonne = 0; colonne<colonnes; colonne++)
            {
                if (sPlateformeJeu[colonne][ligne].GetPiece() instanceof Pieces.CaseVide)
                {
                    texte = texte + "[_]";
                }
                else if (sPlateformeJeu[colonne][ligne].GetPiece() instanceof Pieces.Pion)
                {
                    texte = texte + "[P]";
                }
                else
                {
                    texte = texte + "[ I ]";
                }
            }
            texte = texte + "\\n";
        }
        return texte;
    }
    
    public String Afficher(List<Pieces.Piece> listp)
    {
        String texte= "";
        for (int ligne = 0; ligne < lignes; ligne++)
        {
            for (int colonne = 0; colonne<colonnes; colonne++)
            {
                boolean bDisplayed = false;
                for (int i = 0; i < listp.size(); i++) {
                    Pieces.Piece currentPiece = listp.get(i);
                    if (currentPiece.GetX() == colonne && currentPiece.GetY() == ligne) {
                        if (currentPiece instanceof Pieces.Pion) {
                            texte = texte + "[P]";
                        } else {
                             texte = texte + "[ I ]";
                        }
                        bDisplayed = true;
                    }
                }
                if (bDisplayed == false)
                {
                    texte = texte + "[_]";
                }
            }
            texte = texte + "\\n";
        }
        return texte;
    }
    
}
