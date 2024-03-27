package app5.AEF_Lexical;

import app5.Terminal;

public interface StateLex {

    /**
     *
     * @param nextChar
     * @return Null if no UL found yet
     */
    public void ReadNext(char nextChar);
    public Terminal GetTerminal();


}
