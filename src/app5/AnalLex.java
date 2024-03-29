package app5;

/** @author Ahmed Khoumsi */

import app5.AEF_Lexical.StateLex;
import app5.AEF_Lexical.StateLexDefault;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
//  ...
  private StateLex stateLex;
  private int cursor;
  private String equationStr;
	
  /**
   * Constructeur pour l'initialisation d'attribut(s)
   */
  public AnalLex( ) {  // arguments possibles
    //
    stateLex = new StateLexDefault(this, "");
    equationStr = "";
    cursor = 0;
  }
  public AnalLex(String str) {  // arguments possibles
    //
    stateLex = new StateLexDefault(this, "");
    equationStr = str;
    cursor = 0;
  }

  /** resteTerminal() retourne :
   *  false  si tous les terminaux de l'expression arithmetique ont ete retournes
   *  true s'il reste encore au moins un terminal qui n'a pas ete retourne
   */
  public boolean resteTerminal( ) {
    //


    return true; // temp return
  }
  
  
  /** prochainTerminal() retourne le prochain terminal
   *  Cette methode est une implementation d'un AEF
   */
  public Terminal prochainTerminal( ) {
     //
    Terminal terminal = null;
    while (terminal == null){
      stateLex.ReadNext(GetNextChar());
      terminal = stateLex.GetTerminal();
    }

    cursor--; // move back
    stateLex = new StateLexDefault(this, ""); // reset state machine for next UL
    return terminal; //temp return
  }
  public void ChangeState(StateLex nextState){
    stateLex = nextState;
  }
  private char GetNextChar(){

    if(cursor < equationStr.length()) {
      char nextChar = equationStr.charAt(cursor);
      cursor++;
      return nextChar;
    } else {
      return ';';
    }

  }
 
  /**
   * ErreurLex() envoie un message d'erreur lexicale
   */
  public void ErreurLex(String s) {	
     //
    System.out.println("ERROR near character " + cursor + ", " + s);
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);

    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      toWrite +=t.chaine + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
