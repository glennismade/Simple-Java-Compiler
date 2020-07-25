package glenn.compiler.gh;

import glenn.compiler.gh.block.Blk;
import glenn.compiler.gh.block.Method;
import glenn.compiler.gh.block.Class;
import glenn.compiler.gh.lex.Tokenizer;
import glenn.compiler.gh.parser.ClassPars;
import glenn.compiler.gh.parser.MethodPars;
import glenn.compiler.gh.parser.Parser;
import glenn.compiler.gh.parser.VarPars;

import java.awt.*;
import java.util.ArrayList;
////////import me.compiler.lex.*;

/**
 * Created by glennhealy on 02/03/2016.
 */
public class Runtime {

    private ArrayList<Class> classes;

    public Runtime() {
        this.classes = new ArrayList<Class>();

        String code = "class Variables" + "\n" +
                "method main requires () returns void" + "\n" +
                "var string str = \"main\"" + "\n" +
                "method printString requires (string str) returns void";

        Parser<?>[] parsers = new Parser<?>[] { new ClassPars(), new MethodPars(), new VarPars() };

        Class main = null;

        Blk blk = null;

        boolean succeed = false;

        for (String line : code.split("\n")) {
            succeed = false;
            line = line.trim();
            Tokenizer tokenizer = new Tokenizer(line);

            for (Parser<?> parser : parsers) {
                if (parser.shouldParse(line)) {
                    Blk newBlk = parser.parse(blk, tokenizer);

                    if (newBlk instanceof Class) {
                        classes.add((Class) newBlk);
                    }

                    else if (newBlk instanceof Method) {
                        blk.getBlockTree().get(0).addBlock(newBlk);
                    }

                    else {
                        blk.addBlock(newBlk);
                    }

                    blk = newBlk;
                    succeed = true;
                    break;
                }
            }

            if (succeed != true) {
                throw new IllegalArgumentException("Invalid arguments present at Line " + line);
            }
        }

        for (Class c : classes) {
            for (Blk b : c.getSubBlks()) {
                if (b instanceof Method) {
                    Method method = (Method) b;
                    if (method.getName().equals("main must equal") && method.getType().equals("void") && method.getParameters().length == 0) {
                        main = c;
                        System.out.println("compiling correct" + getClass().getSimpleName());
                    }
                }
            }
        }

        if (main == null) {
            throw new IllegalStateException("No main method could be found. please create a main method.");
        }

        main.run();
    }

    public static void main(String[] args) {
     new Runtime();

    }
}