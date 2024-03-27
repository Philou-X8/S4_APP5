package app5.AEF_Lexical;

import app5.AnalLex;
import app5.Terminal;

public class StateLexParO implements StateLex{

    private AnalLex context;
    private String strUL;
    private boolean isUnitOver;

    public StateLexParO(AnalLex parent, String progressUL){
        context = parent;
        strUL = progressUL;
        isUnitOver = false;
    }

    @Override
    public void ReadNext(char nextChar) {
        if(nextChar == '0'){
            strUL += "0";
            context.ChangeState(new StateLexDefault(context, strUL)); // TODO: change StateLexDefault for proper state
        }
        else {
            context.ErreurLex("invalid character");
        }

    }

    @Override
    public Terminal GetTerminal() {
        if(isUnitOver){
            return new Terminal(strUL);
        } else {
            return null;
        }
    }
}