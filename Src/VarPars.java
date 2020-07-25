package glenn.compiler.gh.parser;

//import Type;
//import Variable;
import glenn.compiler.gh.block.Blk;
import glenn.compiler.gh.block.VariableBlk;
import glenn.compiler.gh.lex.Tok;
import glenn.compiler.gh.lex.TokType;
import glenn.compiler.gh.lex.Tokenizer;

/**
 * Created by glennhealy on 02/03/2016.
 */public class VarPars extends Parser<Blk> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("var [a-zA-Z]+ [a-zA-Z]+ = (\")?[a-zA-Z0-9]*(\")?");
    }

    @Override
    public Blk parse(Blk superBlk, Tokenizer tokenizer) {
        tokenizer.nextTok(); // Skip the var token.

        String type = tokenizer.nextTok().getToken();

        String name = tokenizer.nextTok().getToken();

        tokenizer.nextTok(); // Skip the = token.

        Tok v = tokenizer.nextTok();
        Object value = null;

        if (v.getType() == TokType.INTEGER_LITERAL) {
            value = Integer.valueOf(v.getToken());
        }

        else if (v.getType() == TokType.FLOAT_LITERAL) {
            value = Float.valueOf(v.getToken()); //token type check float
        }

        else if (v.getType() == TokType.STRING_LITERAL) {
            value = v.getToken();
        }

        else /* if the token is a veriable itendifier  */ {
            value = superBlk.getVariable(v.getToken()).getValue();
        }

        return new VariableBlk(superBlk, type, name, value);
    }
}