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
        if(nextChar >= '0' && nextChar <= '9'){
            strUL += nextChar;
            context.ChangeState(new StateLexNbr(context, strUL));
        }

        else if(nextChar >= 'A' && nextChar <= 'Z'){
            strUL += nextChar;
            context.ChangeState(new StateLexVar(context, strUL));
        }
        else if(nextChar >= 'a' && nextChar <= 'z'){
            context.ErreurLex("variables must start with a capitalised letter");
        }
        else if(nextChar == '_'){
            context.ErreurLex("variables must start with a capitalised letter");
        }

        else if(nextChar == '*' || nextChar == '/'){
            strUL += nextChar;
            context.ChangeState(new StateLexOp1(context, strUL));
        }
        else if(nextChar == '+' || nextChar == '-'){
            strUL += nextChar;
            context.ChangeState(new StateLexOp2(context, strUL));
        }

        else if(nextChar == '('){
            strUL += nextChar;
            context.ChangeState(new StateLexParO(context, strUL));
        }
        else if(nextChar == ')'){
            strUL += nextChar;
            context.ChangeState(new StateLexParC(context, strUL));
        }

        else if(nextChar == ' '){
            // ...
        }
        else if(nextChar == ';'){
            // ...
        }
        else if(nextChar == '\n'){
            // ...
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
