package app5.AST_Syntax;

/** @author Ahmed Khoumsi */

import app5.Terminal;
import app5.TerminalTypes;

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
  private Terminal terminal;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal varTerminal) {  // avec arguments
      terminal = varTerminal;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
      if(terminal.Type() == TerminalTypes.NUMBER){
          try{
              return Integer.parseInt(terminal.chaine);
          } catch (NumberFormatException e){
              System.out.println("ERROR: Integer.parseInt failed on [" + terminal.chaine + "]");
          }

      }
      return 0;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
      //
      return terminal.chaine; // temp
  }

}
