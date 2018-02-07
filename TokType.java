package glenn.compiler.gh.lex;

/**
 * Created by glennhealy on 28/02/2016.
 */
public enum TokType {

    EMPTY, //this is empty, absolutly nothing.
    TOKEN, //this repersents a token e.g. (), =, */, ", +, -"
    IDENTIFIER, // if first character is a letter, any proceeding characters are letters or numbers
    INTEGER_LITERAL, //a number like 0, 1,2,3,4,5 etc.
    FLOAT_LITERAL, // a IEEE floating point number such as 0.1, 0.02 or -1, -0.1, infinity, -infinity and NaN.
    STRING_LITERAL, // anything encloses in double quotes ""

}
