package glenn.compiler.gh.lex;

/**
 * Created by glennhealy on 28/02/2016.
 */
public class Tok {
    private String token;
    private TokType type;

    public Tok(String token, TokType type) {
        this.token = token;
        this.type = type;

    }

    public String getToken() {
        return token;
    }

    public TokType getType() {
        return type;
    }
}
