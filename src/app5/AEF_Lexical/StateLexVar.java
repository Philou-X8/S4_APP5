package app5.AEF_Lexical;

import app5.AnalLex;
import app5.Terminal;
import app5.TerminalTypes;

public class StateLexVar implements StateLex{

    private AnalLex context;
    private String strUL;
    private boolean isUnitOver;

    public StateLexVar(AnalLex parent, String progressUL){
        context = parent;
        strUL = progressUL;
        isUnitOver = false;
    }

    @Override
    public void ReadNext(char nextChar) {
        if(nextChar >= 'A' && nextChar <= 'Z'){
            strUL += nextChar;
        }
        else if(nextChar >= 'a' && nextChar <= 'z'){
            strUL += nextChar;
        }
        else if(nextChar == '_'){
            strUL += nextChar;
            context.ChangeState(new StateLexUnd(context, strUL));
        }
        else {
            isUnitOver = true;
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
