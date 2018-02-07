package glenn.compiler.gh;

/**
 * Created by glennhealy on 01/03/2016.
 */
public class Parameter {

    private String name;
    private Type type;

    public Parameter(Type type, String name){
        this.type = type;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Type getType(){
        return type;
    }
}
