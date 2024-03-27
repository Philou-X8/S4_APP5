package app5.AEF_Lexical;

import app5.AnalLex;
import app5.Terminal;

public class StateLexDefault implements StateLex{

    private AnalLex context;
    private String strUL;
    private boolean isUnitOver;

    public StateLexDefault(AnalLex parent, String progressUL){
        context = parent;
        strUL = progressUL;
        isUnitOver = false;
    }

    @Override
    public void ReadNext(char nextChar) {
        switch (nextChar) {
            case '0' -> {
                strUL += "0";
                context.ChangeState(new StateLexDefault(context, strUL)); // TODO: change StateLexDefault for proper state
            }
            case '1' -> {
                strUL += "1";
                context.ChangeState(new StateLexDefault(context, strUL)); // TODO: change StateLexDefault for proper state
            }
            case '+' -> {
                isUnitOver = true;
            }
            default -> {
                context.ErreurLex("invalid character");
            }
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
