package app5.AST_Syntax;

/** @author Ahmed Khoumsi */

import app5.Terminal;
import app5.TerminalTypes;

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  private Terminal terminal;
  private ElemAST branchL;
  private ElemAST branchR;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal opTerminal, ElemAST bL, ElemAST bR ) {
    terminal = opTerminal;
    branchL = bL;
    branchR = bR;
  }

  public Terminal GetTerminal(){
    return terminal;
  }
 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
     //
      return switch (terminal.chaine) {
          case "+" -> branchL.EvalAST() + branchR.EvalAST();
          case "-" -> branchL.EvalAST() - branchR.EvalAST();
          case "*" -> branchL.EvalAST() * branchR.EvalAST();
          case "/" -> branchL.EvalAST() / branchR.EvalAST();
          default -> 0;
      };

  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     //

    return "( " + branchL.LectAST() + " " + terminal.chaine + " " + branchR.LectAST() + " )"; // temp
  }
  public String Postfix( ) {
     //

    return branchL.LectAST() + " " + branchR.LectAST() + " " + terminal.chaine + " " ; // temp
  }

}


