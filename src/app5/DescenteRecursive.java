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
    Reader r = new Reader(in);
    lexicon = new AnalLex(r.toString());
  }


  /**
   * AnalSynt() effectue l'analyse syntaxique et construit l'AST.
   * Elle retourne une reference sur la racine de l'AST construit
   */
  public ElemAST AnalSynt() {
    lastTerminal = lexicon.prochainTerminal();
    ElemAST ret = E_();
    if(lastTerminal.Type() == TerminalTypes.PARACLOSE){
      ErreurSynt("Syntax error (level 0), related to closing parentheses");
    }
    return ret;
  }


  // Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...
  private ElemAST E_() {
    ElemAST b1 = null;
    ElemAST b2 = null;

    b1 = T_();
    switch (lastTerminal.Type()){
      case OPERATOR2 -> { // operator: +
        Terminal operator = lastTerminal; // save operator type, used to build noeu
        lastTerminal = lexicon.prochainTerminal(); // read next terminal
        b2 = E_();
        return new NoeudAST(operator, b1, b2);
      }
      case PARACLOSE, EOF -> { // operator: ), eof
        return b1;
      }
      default -> {
        ErreurSynt("Syntax error (level E_)");
      }
    }


    ErreurSynt("Syntax error (level E_) " + lastTerminal.Type().toString());
    return new FeuilleAST(new Terminal("NAN", TerminalTypes.NAN));
  }
  private ElemAST T_() {
    ElemAST b1 = null;
    ElemAST b2 = null;

    b1 = F_();
    switch (lastTerminal.Type()){
      case OPERATOR1 -> { // operator: *
        Terminal operator = lastTerminal; // save operator type, used to build noeu
        lastTerminal = lexicon.prochainTerminal(); // read next terminal
        b2 = T_();
        return new NoeudAST(operator, b1, b2);
      }
      case OPERATOR2, PARACLOSE, EOF -> { // operator: ), eof
        return b1;
      }
      default -> {
        ErreurSynt("Syntax error (level E_)");
      }
    }

    ErreurSynt("Syntax error (level T_) " + lastTerminal.Type().toString());
    return new FeuilleAST(new Terminal("NAN", TerminalTypes.NAN));
  }
  private ElemAST F_() {
    ElemAST b1 = null;
    ElemAST b2 = null;
    switch (lastTerminal.Type()){
      case NUMBER, VARIABLE -> {
        b1 = new FeuilleAST(lastTerminal); // put terminal (variable or number) in leaf
        lastTerminal = lexicon.prochainTerminal(); // read next terminal
        return b1;
      }
      case PARAOPEN -> {
        lastTerminal = lexicon.prochainTerminal(); // done with < ( > so read next terminal
        b1 = E_();
        if(lastTerminal.Type() != TerminalTypes.PARACLOSE){
          ErreurSynt("Syntax error (level F), related to closing parentheses");
        }
        lastTerminal = lexicon.prochainTerminal(); // catch closing parentheses
        return b1;
      }
      default -> {
        ErreurSynt("Syntax error (level F)");
      }
    }

    ErreurSynt("Syntax error (level F_) " + lastTerminal.Type().toString());
    return new FeuilleAST(new Terminal("NAN", TerminalTypes.NAN));
  }


  /**
   * ErreurSynt() envoie un message d'erreur syntaxique
   */
  public void ErreurSynt(String s) {
    System.out.println("Error in syntax: " + s);
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

