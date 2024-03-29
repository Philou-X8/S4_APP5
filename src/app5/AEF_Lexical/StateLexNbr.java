package app5.AEF_Lexical;

import app5.AnalLex;
import app5.Terminal;
import app5.TerminalTypes;

public class StateLexNbr implements StateLex{

    private AnalLex context;
    private String strUL;
    private boolean isUnitOver;

    public StateLexNbr(AnalLex parent, String progressUL){
        context = parent;
        strUL = progressUL;
        isUnitOver = false;
    }

    @Override
    public void ReadNext(char nextChar) {
        if(nextChar >= '0' && nextChar <= '9'){
            strUL += nextChar;
            //context.ChangeState(new StateLexDefault(context, strUL)); // no need to change state
        }
        else {
            isUnitOver = true;
        }

    }

    @Override
    public Terminal GetTerminal() {
        if(isUnitOver){
            return new Terminal(strUL, TerminalTypes.NUMBER);
        } else {
            return null;
        }
    }
}
