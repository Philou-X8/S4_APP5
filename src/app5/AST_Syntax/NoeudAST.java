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
    if(terminal.chaine.equals("+")){
      return branchL.EvalAST() + branchR.EvalAST();
    }
    else if(terminal.chaine.equals("-")){
      return branchL.EvalAST() - branchR.EvalAST();
    }
    else if(terminal.chaine.equals("*")){
      return branchL.EvalAST() * branchR.EvalAST();
    }
    else if(terminal.chaine.equals("/")){
      return branchL.EvalAST() / branchR.EvalAST();
    }

    return 0; // TEMP
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     //

    return "( " + branchL.LectAST() + " " + terminal.chaine + " " + branchR.LectAST() + " )"; // temp
  }

}


