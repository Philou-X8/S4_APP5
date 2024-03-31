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
    ElemAST root = E();

    return root; //TEMP TODO
  }


  // Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...
  private ElemAST E() {
    ElemAST b1 = null;

    switch (lastTerminal.Type()){
      case NUMBER, VARIABLE, PARAOPEN -> {
        b1 = T();

      }
      default -> {
        ErreurSynt("Syntax error (level E)");
      }
    }

    return EE(b1);
  }
  private ElemAST EE(ElemAST upperBranch) {
    ElemAST b1 = null;
    ElemAST b2 = null;
    switch (lastTerminal.Type()){
      case OPERATOR2 -> { // operator: +
        Terminal operator = lastTerminal; // save operator type, used to build noeu
        lastTerminal = lexicon.prochainTerminal(); // read next terminal
        b1 = T();
        b2 = EE(b1);
        return new NoeudAST(operator, upperBranch, b2);
      }
      case PARACLOSE, EOF -> { // operator: ), eof
        return upperBranch;

      }
      default -> {
        ErreurSynt("Syntax error (level EE)");
      }
    }
    return new FeuilleAST(new Terminal("NAN", TerminalTypes.NAN));
  }

  private ElemAST T() {
    ElemAST b1 = null;

    switch (lastTerminal.Type()){
      case NUMBER, VARIABLE, PARAOPEN -> {
        b1 = F();

      }
      default -> {
        ErreurSynt("Syntax error (level T)");
      }
    }
    return TT(b1);
  }
  private ElemAST TT(ElemAST upperBranch) {
    ElemAST b1 = null;
    ElemAST b2 = null;
    switch (lastTerminal.Type()){
      case OPERATOR1 -> { // operator: *
        Terminal operator = lastTerminal; // save operator type, used to build noeu
        lastTerminal = lexicon.prochainTerminal(); // read next terminal
        b1 = F();
        b2 = TT(b1);
        return new NoeudAST(operator, upperBranch, b2);
      }
      case OPERATOR2, PARACLOSE, EOF -> { // operator: +, ), eof
        return upperBranch;

      }
      default -> {
        ErreurSynt("Syntax error (level TT)");
      }
    }
    return new FeuilleAST(new Terminal("NAN", TerminalTypes.NAN));
  }

  private ElemAST F() {
    switch (lastTerminal.Type()){
      case NUMBER, VARIABLE -> {
        ElemAST b1 = new FeuilleAST(lastTerminal); // put terminal (variable or number) in leaf
        lastTerminal = lexicon.prochainTerminal(); // read next terminal
        return b1;
      }
      case PARAOPEN -> {
        lastTerminal = lexicon.prochainTerminal(); // done with < ( > so read next terminal
        ElemAST b1 = E();
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

