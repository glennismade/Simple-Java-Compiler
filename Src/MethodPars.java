package glenn.compiler.gh.parser;

import java.util.ArrayList;

import glenn.compiler.gh.BuiltInType;
import glenn.compiler.gh.block.Blk;
import glenn.compiler.gh.block.Method;
import glenn.compiler.gh.Parameter;
import glenn.compiler.gh.lex.Tok;
import glenn.compiler.gh.lex.Tokenizer;

/**
 * Created by glennhealy on 01/03/2016.
 */
public class MethodPars extends Parser<Method> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("method [a-zA-Z][a-zA-Z0-9]* requires \\(([a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*)*\\) returns [a-zA-Z][a-zA-Z0-9]*");
    }

    @Override
    public Method parse(Blk superBlk, Tokenizer tokenizer) {
        tokenizer.nextTok(); // Skip the method token.

        String name = tokenizer.nextTok().getToken();

        tokenizer.nextTok(); // Skip the requires token.
        tokenizer.nextTok(); // Skip the ( token.

        Tok first = tokenizer.nextTok();

        ArrayList<Parameter> params = new ArrayList<>();

        if (!first.getToken().equals(")")) {
            String[] paramData = new String[] { first.getToken(), null }; // 0 = type, 1 = value

            while (tokenizer.hasNextToken()) {
                Tok tok = tokenizer.nextTok();

                if (tok.getToken().equals(")")) {
                    break;
                }

                if (paramData[0] == null) {
                    paramData[0] = tok.getToken();
                }

                else {
                    paramData[1] = tok.getToken();

                    params.add(new Parameter(BuiltInType.valueOf(paramData[0].toUpperCase()), paramData[1]));

                    paramData = new String[2];
                }
            }
        }

        tokenizer.nextTok(); // Skip the returns token.

        String returnType = tokenizer.nextTok().getToken();

        return new Method(superBlk, name, returnType, params.toArray(new Parameter[params.size()]));
    }
}