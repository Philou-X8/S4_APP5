package app5;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {


// Constantes et attributs
//  ....
  public String chaine;
  private TerminalTypes type;

/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal( ) {   // arguments possibles
     //
    chaine = "epsilon";
    type = TerminalTypes.EMPTY;
  }
  public Terminal(String text, TerminalTypes tType) {   // arguments possibles
     //
    chaine = text;
    type = tType;
  }

  public TerminalTypes Type(){
    return type;
  }

}
