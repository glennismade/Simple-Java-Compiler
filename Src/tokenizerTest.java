package glenn.compiler.gh.lex;

/**
 * Created by glennhealy on 28/02/2016.
 */
public class tokenizerTest {

    public static void main(String[] args) {
        String code =
                 "class HelloWorld\n" +
                 "method main requires()\n" +
                 "print \"hello\""
        ;

        Tokenizer tokenizer = new Tokenizer(code);

        while (tokenizer.hasNextToken()) {
            System.out.println(tokenizer.nextTok().getToken());
        }
    }
}
