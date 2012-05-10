package model;

/**
 *
 * @author kremer
 */
public class Ampel {
    
    public enum Licht {ROT, ROTGELB, GELB, GRUEN, STOERUNG, AUS};
    
    Licht licht = Licht.AUS;
   
    public Ampel() {

    }

    public Licht getLicht() {
        return licht;
    }

    public void setLicht(Licht licht) {
        this.licht = licht;
    }

    @Override
    public String toString() {
        return licht.toString();
    }
    
    
    
    
    
}
