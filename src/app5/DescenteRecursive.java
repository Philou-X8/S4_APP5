package app5;

/** @author Ahmed Khoumsi */

import app5.AST_Syntax.ElemAST;
import app5.AST_Syntax.FeuilleAST;
import app5.AST_Syntax.NoeudAST;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  private Terminal lastTerminal;
  private AnalLex lexicon;

  /**
   * Constructeur de DescenteRecursive :
   * - recoit en argument le nom du fichier contenant l'expression a analyser
   * - pour l'initalisation d'attribut(s)
   */
  public DescenteRecursive(String in) {
    lexicon = new AnalLex(in);

  }


  /**
   * AnalSynt() effectue l'analyse syntaxique et construit l'AST.
   * Elle retourne une reference sur la racine de l'AST construit
   */
  public ElemAST AnalSynt() {

    lastTerminal = lexicon.prochainTerminal();
    ElemAST root = E();

    return null; //TEMP TODO
  }


  // Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...
  private ElemAST E() {
    ElemAST b1 = null;
    ElemAST b2 = null;

    switch (lastTerminal.Type()){
      case NUMBER, VARIABLE, PARAOPEN -> {
        b1 = T();

        b2 = EE();
      }
      default -> {
        ErreurSynt("Syntax error");
      }
    }

    // return EE( b1 ); // give it right branch // <--------------------------------------------------------

    return new NoeudAST(lastTerminal, null, null); // TEMP TODO
  }
  private ElemAST EE() {

    return new NoeudAST(lastTerminal, null, null); // TEMP TODO
  }
  private ElemAST T() {
    ElemAST b1 = null;
    ElemAST b2 = null;

    switch (lastTerminal.Type()){
      case NUMBER, VARIABLE, PARAOPEN -> {
        b1 = F();

        b2 = TT();
      }
      default -> {
        ErreurSynt("Syntax error");
      }
    }

    if(b2.GetTerminal().Type() == TerminalTypes.EMPTY){
      return b1;
    }
    else {

    }

    return new NoeudAST(lastTerminal, null, null); // TEMP TODO
  }
  private ElemAST TT() {

    return new NoeudAST(lastTerminal, null, null); // TEMP TODO
  }
  private ElemAST F() {

    return new NoeudAST(lastTerminal, null, null); // TEMP TODO
  }


  /**
   * ErreurSynt() envoie un message d'erreur syntaxique
   */
  public void ErreurSynt(String s) {

  }


  //Methode principale a lancer pour tester l'analyseur syntaxique
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0) {
      args = new String[2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1], toWriteLect + toWriteEval); // Ecriture de toWrite
      // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

