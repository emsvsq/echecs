
package Game;

public class Partie {
    
    private int Ga_Id;
    private String Ga_Name;
    private int Ga_Tour;
    private String Ga_Mode ;

    public Partie(int id, String name, int tour, String mode) {
        Ga_Id = id;
        Ga_Name = name;
        Ga_Tour = tour;
        Ga_Mode = mode;
    }
    
    public int GetId() {
        return Ga_Id;
    }
    
    public String GetName() {
        return Ga_Name;
    }
    
    public int GetTour() {
        return Ga_Tour;
    }
    
    public String GetMode() {
        return Ga_Mode;
    }
    
}
