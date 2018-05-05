package Game;

import Pieces.CaseVide;
import Pieces.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {
    
   public Data() {
       
   } 
   static Connection connexion = null;
   static Statement instruction = null;
   static Exception connexionException = null;
	
    static public boolean Connection()
    {
        boolean bSucces = false;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //ACCES DB LOCALE
                //connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/webechecs", "root", "");
                
                
                //ACCES DB WEBSITE
                //connexion = DriverManager.getConnection("jdbc:mysql://www.teebbo.com:3306/heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                //connexion = DriverManager.getConnection("jdbc:mysql://www.teebbo.com/heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
               /* String url = "jdbc:mysql://www.teebbo.com:heeboooccqharvey.mysql.db/";
                url = "jdbc:mysql://www.teebbo.com:3306/";
                url = "jdbc:mysql://www.teebbo.com/heeboooccqharvey.mysql.db/";
                
                url = "jdbc:mysql://heeboooccqharvey.mysql.db/";
                url = "jdbc:mysql://213.186.33.28:3306/";
                
                String dbName = "heeboooccqharvey";
                String userName = "heeboooccqharvey"; 
                String password = "zS295mbw89"; */
                //connexion = DriverManager.getConnection("jdbc:mysql://www.teebbo.com:3306/heeboooccqharvey.mysql.db", "heeboooccqharvey", "zS295mbw89");
                //connexion = DriverManager.getConnection("jdbc:mysql://www.teebbo.com:80/heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                //connexion = DriverManager.getConnection("jdbc:mysql://fr.teebbo.com:80/heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                //connexion = DriverManager.getConnection(url+dbName,userName,password);
                ////////////////////////////////////////////
                // String url="jdbc:mysql://ks36516.kimsufi.com/nom_de_la_base";
                //String url="jdbc:mysql://heeboooccqharvey.mysql.db:3306/heeboooccqharvey";
                String url="jdbc:mysql://localhost:3306/heeboooccqharvey.mysql.db/heeboooccqharvey";
                String user="heeboooccqharvey";
                String password="zS295mbw89" ;
                //connexion = DriverManager.getConnection(url, user, password);
                //connexion = DriverManager.getConnection("jdbc:mysql://www.teebbo.com:80/heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                //connexion = DriverManager.getConnection("jdbc:mysql://
            
                //connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                
               // connexion = DriverManager.getConnection("jdbc:mysql://heeboooccqharvey.mysql.db:3306/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                connexion = DriverManager.getConnection("jdbc:mysql://heeboooccqharvey.mysql.db/heeboooccqharvey", "heeboooccqharvey", "zS295mbw89");
                
                instruction = connexion.createStatement();
                bSucces = true;
            }
            catch (Exception exc){
                connexionException = exc;
            }
        return bSucces;
    }
    
    public JsAnswer Save(String name, PlateauDeJeu game) {
        JsAnswer returnValue = new JsAnswer(false, null);
        if (Connection()) {
            try {
                //instruction.executeUpdate("INSERT INTO games(Ga_Name, Ga_Tour, Ga_Mode) VALUES ('" + name + "', " + game.tour + ", '" + game.Mode() + "')");
                
                PreparedStatement updategames = connexion.prepareStatement("INSERT INTO games(Ga_Name, Ga_Tour, Ga_Mode) VALUES (?, ?, ?)");
                updategames.setString(1, name);
                updategames.setInt(2, game.tour);
                updategames.setString(3, game.Mode());
                updategames.executeUpdate();
                
                //ResultSet SELECTidPartie = instruction.executeQuery("SELECT Ga_Id FROM games WHERE Ga_Name = '"+ name +"'");
                PreparedStatement preparedStatementPartie = connexion.prepareStatement("SELECT Ga_Id FROM games WHERE Ga_Name = ?");
		preparedStatementPartie.setString(1, name);

		ResultSet SELECTidPartie = preparedStatementPartie.executeQuery();
                String sIdPartie = "";
                
                while (SELECTidPartie.next()) 
                {
                    sIdPartie = SELECTidPartie.getString("Ga_Id");
                }
                SELECTidPartie.close();
                
                for(int ligne = 0; ligne < game.lignes; ligne++) {
                    for(int colonne = 0; colonne < game.colonnes; colonne++) {
                        Piece currentPiece = game.sPlateformeJeu[colonne][ligne];
                        if (!(currentPiece instanceof CaseVide)) {
                            String reqUpdate = "INSERT INTO savedgame(Sg_IdGame, Sg_Piece, Sg_X, Sg_Y, Sg_Couleur) VALUES "
                                    + "(" + sIdPartie + ", '" + currentPiece.GetName() + "'," + colonne + ", " + ligne + ", '" + currentPiece.GetCouleur() + "')";
                            instruction.executeUpdate(reqUpdate);
                        }
                    }
                }
                returnValue.returnBoolValue = true;
            } catch (SQLException ex) {
                returnValue.exception = ex;
            } finally {
                try {
                    instruction.close();
                    connexion.close();
                } catch (SQLException ex) {
                }
            }
        } else {
            returnValue.exception = connexionException;
        }
        return returnValue;
    }
    
    public PlateauDeJeu Load(Engine engine, int idRow) {
        PlateauDeJeu returnValue = new PlateauDeJeu(engine);
        if (Connection()) {
            try {
                ResultSet SELECTPartie = instruction.executeQuery("SELECT GA.Ga_Tour, GA.Ga_Mode, SG.Sg_Piece, SG.Sg_X, SG.Sg_Y, SG.Sg_Couleur  FROM savedgame SG, games GA WHERE SG.Sg_IdGame = " + idRow + " AND SG.Sg_IdGame = GA.Ga_Id");
                returnValue.listePieces.clear();
                while (SELECTPartie.next()) 
                {
                    String sTypePiece = SELECTPartie.getString("SG.Sg_Piece");
                    int X = SELECTPartie.getInt("SG.Sg_X");
                    int Y = SELECTPartie.getInt("SG.Sg_Y");
                    String sCouleur = SELECTPartie.getString("SG.Sg_Couleur").toLowerCase();
                    int tour = SELECTPartie.getInt("GA.Ga_Tour");
                    String mode = SELECTPartie.getString("GA.Ga_Mode");
                    returnValue.SetParameters(mode, tour);
                    
                    Piece.color currentColor = Piece.color.Vide;
                    if(("blanc").equals(sCouleur)) {
                        currentColor = Piece.color.Blanc;
                    } else if (("noir").equals(sCouleur)) {
                        currentColor = Piece.color.Noir;
                    }
                    Piece pieceToAdd = null;
                    switch (sTypePiece.toLowerCase()) {
                        case "pion":
                            pieceToAdd = new Pion(X, Y, currentColor);
                            break;
                        case "tour":
                            pieceToAdd = new Tour(X, Y, currentColor);
                            break;
                        case "cheval":
                            pieceToAdd = new Cheval(X, Y, currentColor);
                            break;
                        case "fou":
                            pieceToAdd = new Fou(X, Y, currentColor);
                            break;
                        case "reine":
                            pieceToAdd = new Reine(X, Y, currentColor);
                            break;
                        case "roi":
                            pieceToAdd = new Roi(X, Y, currentColor);
                            break;
                        default:
                            break;
                    }
                    if (pieceToAdd != null) {
                        returnValue.listePieces.add(pieceToAdd);
                    }
                }
                SELECTPartie.close();
                returnValue.Positionner();
                
            } catch (SQLException ex) {
            } finally {
                try {
                    instruction.close();
                    connexion.close();
                } catch (SQLException ex) {
                }
            }
        }
        return returnValue;
    }
        
   public List<Partie> getSavedGames() {
       ArrayList<Partie> returnArray = new ArrayList<>();
       
        if (Connection()) {
            try {
                ResultSet SELECTPartie = instruction.executeQuery("SELECT * FROM games");
                while (SELECTPartie.next()) 
                {
                    int sIdPartie = SELECTPartie.getInt("Ga_Id");
                    String sNamePartie = SELECTPartie.getString("Ga_Name");
                    int iTour = SELECTPartie.getInt("Ga_Tour");
                    String sMode = SELECTPartie.getString("Ga_Mode");
                    returnArray.add(new Partie(sIdPartie, sNamePartie, iTour, sMode));
                }
                SELECTPartie.close();
            } catch (SQLException ex) {
            } finally {
                try {
                    instruction.close();
                    connexion.close();
                } catch (SQLException ex) {
                }
            }
        }
        return returnArray;
   }
       
    public List<String> getCreatedGames() {
       return new ArrayList<String>();
   }
     
    public boolean DeleteRow(int idRow) {
        boolean returnValue = false;
         if (Connection()) {
            try {
                instruction.executeUpdate("DELETE FROM games WHERE Ga_Id = " + idRow);
                instruction.executeUpdate("DELETE FROM savedgame WHERE Sg_IdGame = " + idRow);
                returnValue = true;
            } catch (SQLException ex) {
            } finally {
                try {
                    instruction.close();
                    connexion.close();
                } catch (SQLException ex) {
                }
            }
        }
        return returnValue;
    }
}
