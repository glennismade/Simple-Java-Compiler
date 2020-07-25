package glenn.compiler.gh.parser;

import glenn.compiler.gh.lex.Tokenizer;
import glenn.compiler.gh.block.Blk;

/**
 * Created by glennhealy on 29/02/2016.
 */
public abstract class Parser<T extends Blk> {

    /**
     * Takes a line and checks to see if it is for this parser by using regex.
     */
    public abstract boolean shouldParse(String line);

    /**
     * Take the superBlock and the tokenizer for the line and return a block of this parser's type.
     */
    public abstract T parse(Blk superBlk, Tokenizer tokenizer);
}