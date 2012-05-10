package control;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ampel;
import model.AutoAmpel;
import model.FussgaengerAmpel;

/**
 *
 * @author kremer
 */

/*
 * 
 * Einfache Ampelanlage für Straße mit Fußgängerüberweg
 * A => Ampel
 * 
 *               |A|
 *  =============| |===============
 *               | | A  
 *  -------------| |---------------
 *             A | |
 *  =============| |===============
 *               |A|
 * 
 */
public class AmpelsteuerungEinfach implements Runnable{
    
    //Anlegen der benötigten Ampelobjekte
    AutoAmpel autoAmpel1 = new AutoAmpel();
    AutoAmpel autoAmpel2 = new AutoAmpel();
    FussgaengerAmpel fussAmpel1 = new FussgaengerAmpel();
    FussgaengerAmpel fussAmpel2 = new FussgaengerAmpel();
    
    public AmpelsteuerungEinfach() {
        
        //Initialiserung
        autoAmpel1.setLicht(Ampel.Licht.GRUEN);
        autoAmpel2.setLicht(Ampel.Licht.GRUEN);
        fussAmpel1.setLicht(Ampel.Licht.ROT);
        fussAmpel2.setLicht(Ampel.Licht.ROT);    
    }

    public void run() {
       
        /*
         * Einfache Steuerung
         * ==================
         * 
         */
        int durchlaeufe = 0;
        
        while(!Thread.interrupted()) {
            
            System.out.println("Durchlauf: " + durchlaeufe++);
            debug();

        /*
         * Ist ==> Auto: grün ; Fußgänger: rot
         */
         try {
            //TODO: Globale Definition von Zeiten
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AmpelsteuerungEinfach.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
         
        /*
         * Wechsel ==> Auto: rot ; Fußgänger: grün
         */
         wechselAufRot(autoAmpel1, autoAmpel2);
         //Alle Ampeln rot
        try {
            //TODO: Globale Definition von Zeiten
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AmpelsteuerungEinfach.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        wechselAufGruen(fussAmpel1, fussAmpel2);
        
        debug();
        /*
         * Ist ==> Auto: rot ; Fußgänger: grün
         * 
         */
         try {
            //TODO: Globale Definition von Zeiten
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AmpelsteuerungEinfach.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
         
         /*
          * Wechsel ==> Auto: grün ; Fußgänger: rot
          */
         wechselAufRot(fussAmpel1, fussAmpel2);
         //Alle Ampeln rot
         try {
            //TODO: Globale Definition von Zeiten
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AmpelsteuerungEinfach.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
         wechselAufGruen(autoAmpel1, autoAmpel2);
         
        }//end while(true)
    }
    
    private void wechselAufGruen(Ampel... am) {
        
        //Alle übergebenen Ampel müssen der gleichen Klasse entsprechen
        String className = am[0].getClass().toString();
        for(int i = 1; i < am.length; i++)
            if(!className.equals(am[i].getClass().toString()))
                new Exception("Es wurden unterschiedliche Ampeltypen übergeben.");
        
        if(className.equals(FussgaengerAmpel.class.toString())) {
            for(Ampel a : am)
                a.setLicht(Ampel.Licht.GRUEN);
        } else if (className.equals(AutoAmpel.class.toString())) {
            for(Ampel a : am)
                a.setLicht(Ampel.Licht.ROTGELB);
            try {
                //TODO: Globale Definition von Zeiten
                debug();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(AmpelsteuerungEinfach.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            for(Ampel a : am)
                a.setLicht(Ampel.Licht.GRUEN);
        }
        debug();
    }
    
    private void wechselAufRot(Ampel... am ) {
        
        //Alle übergebenen Ampel müssen der gleichen Klasse entsprechen
        String className = am[0].getClass().toString();
        for(int i = 1; i < am.length; i++)
            if(!className.equals(am[i].getClass().toString()))
                new Exception("Es wurden unterschiedliche Ampeltypen übergeben.");
        
        if(className.equals(FussgaengerAmpel.class.toString())) {
            for(Ampel a : am)
                a.setLicht(Ampel.Licht.ROT);
        } else if (className.equals(AutoAmpel.class.toString())) {
            for(Ampel a : am)
                a.setLicht(Ampel.Licht.GELB);
            try {
                //TODO: Globale Definition von Zeiten
                debug();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AmpelsteuerungEinfach.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            for(Ampel a : am)
                a.setLicht(Ampel.Licht.ROT);
        }
        debug();
    }
    
    private void debug() {
        System.out.println("AutoAmpel1: " + autoAmpel1.toString());
        System.out.println("AutoAmpel2: " + autoAmpel2.toString());
        System.out.println("FussAmpel1: " + fussAmpel1.toString());
        System.out.println("FussAmpel2: " + fussAmpel2.toString() + "\n");
        
    }
    
    
    
}
