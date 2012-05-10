package control;

import view.AmpelsteuerungEinfachView;

/**
 *
 * @author kremer
 */
public class HelloAmpelsteuerung {
    
   public static void main(String[] args) {
       System.out.println("Hello Ampelsteuerung!");
       AmpelsteuerungEinfachView view = new AmpelsteuerungEinfachView();
       view.setVisible(true);
   } 
    
}
