package app5;

public enum TerminalTypes {
    /** Epsilon
     */
    EMPTY,

    VARIABLE,
    NUMBER,

    /** high priority operator * and /
     */
    OPERATOR1,

    /** low priority operator + and -
     */
    OPERATOR2,

    PARAOPEN,
    PARACLOSE,
    EOF
}
