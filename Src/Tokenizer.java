package glenn.compiler.gh.lex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by glennhealy on 28/02/2016.
 */
public class Tokenizer {

    private ArrayList<TokData> tokDatas;

    private String str;

    private Tok lastToken;
    private boolean pushBack;

    public Tokenizer(String str) {
        this.tokDatas = new ArrayList<TokData>();
        this.str = str;

        tokDatas.add(new TokData(Pattern.compile("^([a-zA-Z][a-zA-Z0-9]*)"), TokType.IDENTIFIER));
        tokDatas.add(new TokData(Pattern.compile("^((-)?[0-9]+)"), TokType.INTEGER_LITERAL));
        tokDatas.add(new TokData(Pattern.compile("^([+-]?\\d*\\.?\\d*)$"), TokType.FLOAT_LITERAL));
        tokDatas.add(new TokData(Pattern.compile("^(\".*\")"), TokType.STRING_LITERAL));


        for (String t : new String[] { "=", "\\(", "\\)", "\\.", "\\," }) {
            tokDatas.add(new TokData(Pattern.compile("^(" + t + ")"), TokType.TOKEN));
        }
    }

    public Tok nextTok() {
        str = str.trim();

        if (pushBack) {
            pushBack = false;
            return lastToken;
        }

        if (str.isEmpty()) {
            return (lastToken = new Tok("", TokType.EMPTY));
        }

        for (TokData data : tokDatas) {
            Matcher matcher = data.getPattern().matcher(str);

            if (matcher.find()) {
                String token = matcher.group().trim();
                str = matcher.replaceFirst("");

                if (data.getType() == TokType.STRING_LITERAL) {
                    return (lastToken = new Tok(token.substring(1, token.length() - 1), TokType.STRING_LITERAL));
                }

                else {
                    return (lastToken = new Tok(token, data.getType()));
                }
            }
        }

        throw new IllegalStateException("Could not parse " + str);
    }

    public boolean hasNextToken() {
        return !str.isEmpty();
    }

    public void pushBack() {
        if (lastToken != null) {
            this.pushBack = true;
        }
    }
}