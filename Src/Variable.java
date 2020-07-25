package glenn.compiler.gh;

import glenn.compiler.gh.block.Blk;

/**
 * Created by glennhealy on 02/03/2016.
 */
public class Variable extends Value {

    private Blk blk;
    private String name;

    public Variable(Blk blk, Type type, String name, Object value) {
        super(type, value);

        this.blk = blk;
        this.name = name;
    }

    public Blk getBlk() {
        return blk;
    }

    public String getName() {
        return name;
    }
}
