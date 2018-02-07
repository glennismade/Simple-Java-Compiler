package glenn.compiler.gh.parser;

import glenn.compiler.gh.block.Blk;
import glenn.compiler.gh.block.Class;
import glenn.compiler.gh.lex.Tokenizer;

/**
 * Created by glennhealy on 01/03/2016.
 */

public class ClassPars extends Parser<Class> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("class [a-zA-Z][a-zA-Z0-9]*");
    }

    @Override
    public Class parse(Blk superBlk, Tokenizer tokenizer) {
        tokenizer.nextTok(); // Skip the class token.

        String name = tokenizer.nextTok().getToken(); // Get the string value of the next token.

        return new Class(name);
    }
}