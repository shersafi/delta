package evaluate;

import static java.lang.String.format;

public class UnknownOpFunct extends RuntimeException {
    public UnknownOpFunct(String functionName) {
        super(format("Function or operator '%s' is not supported", functionName));
    }
}
