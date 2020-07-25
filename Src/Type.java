package glenn.compiler.gh;

/**
 * Created by glennhealy on 02/03/2016.
 */
public interface Type {

    public static Type match(String str) {
        try {
            return BuiltInType.valueOf(str.toUpperCase());
        }

        catch (Exception e) {
            // TODO: Match str to a class.
            return null;
        }
    }
}