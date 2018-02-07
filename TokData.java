package glenn.compiler.gh.lex;

import java.util.regex.Pattern;

/**
 * Created by glennhealy on 28/02/2016.
 */
public class TokData {

    private Pattern pattern;
    private TokType type;

    public TokData(Pattern pattern, TokType type){
        this.pattern = pattern;
        this.type = type;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public TokType getType() {
        return type;
    }
}
