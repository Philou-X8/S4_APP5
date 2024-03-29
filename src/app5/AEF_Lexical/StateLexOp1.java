package app5.AEF_Lexical;

import app5.AnalLex;
import app5.Terminal;
import app5.TerminalTypes;

public class StateLexOp1 implements StateLex{

    private AnalLex context;
    private String strUL;
    private boolean isUnitOver;

    public StateLexOp1(AnalLex parent, String progressUL){
        context = parent;
        strUL = progressUL;
        isUnitOver = false;
    }

    @Override
    public void ReadNext(char nextChar) {

        if(nextChar == '*'){
            context.ErreurLex("Operator " + strUL + "+ not supported");
        }
        else if(nextChar == '/'){
            context.ErreurLex("Operator " + strUL + "- not supported");
        }
        else {
            isUnitOver = true;
        }

    }

    @Override
    public Terminal GetTerminal() {
        if(isUnitOver){
            return new Terminal(strUL, TerminalTypes.OPERATOR1);
        } else {
            return null;
        }
    }
}
