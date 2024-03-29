package app5.AEF_Lexical;

import app5.AnalLex;
import app5.Terminal;
import app5.TerminalTypes;

public class StateLexUnd implements StateLex{

    private AnalLex context;
    private String strUL;
    private boolean isUnitOver;

    public StateLexUnd(AnalLex parent, String progressUL){
        context = parent;
        strUL = progressUL;
        isUnitOver = false;
    }

    @Override
    public void ReadNext(char nextChar) {
        if(nextChar >= 'A' && nextChar <= 'Z'){
            strUL += nextChar;
            context.ChangeState(new StateLexVar(context, strUL));
        }
        else if(nextChar >= 'a' && nextChar <= 'z'){
            strUL += nextChar;
            context.ChangeState(new StateLexVar(context, strUL));
        }
        else if(nextChar == '_'){
            context.ErreurLex("variables cannot have 2 underscore in a row");
        }
        else {
            context.ErreurLex("variables cannot end with an underscore");
        }

    }

    @Override
    public Terminal GetTerminal() {
        if(isUnitOver){
            return new Terminal(strUL, TerminalTypes.VARIABLE);
        } else {
            return null;
        }
    }
}
