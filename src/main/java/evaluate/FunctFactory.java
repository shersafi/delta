package evaluate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;
import static java.math.BigDecimal.valueOf;

class FunctFactory {
    private final static Map<String, Function> functions = createFunctions();

    Function create(String function) {
        if (!functions.containsKey(function)) {
            throw new UnknownOpFunct(function);
        }
        return functions.get(function);
    }

    private static Map<String, Function> createFunctions() {
        Map<String, Function> functions = new HashMap<>();

        functions.put("sgn", x -> valueOf(signum(x.doubleValue())));

        functions.put("sin", x -> valueOf(sin(x.doubleValue())));
        functions.put("cos", x -> valueOf(cos(x.doubleValue())));
        functions.put("tan", x -> valueOf(tan(x.doubleValue())));
        functions.put("atan", x -> valueOf(atan(x.doubleValue())));
        functions.put("tg", x -> valueOf(tan(x.doubleValue())));
        functions.put("ctg", x -> valueOf(atan(x.doubleValue())));

        return functions;
    }
}
