package app5.AST_Syntax;

/** @author Ahmed Khoumsi */

import app5.Terminal;

/** Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {

  public abstract Terminal GetTerminal();

  /** Evaluation d'AST
   */
  public abstract int EvalAST();


  /** Lecture d'AST
   */
  public abstract String LectAST();
  public abstract String Postfix();


/** ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
 */  
  public void ErreurEvalAST(String s) {	
    // 
  }

}
