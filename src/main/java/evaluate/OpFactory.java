package evaluate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.math.BigDecimal.valueOf;

public class OpFactory {

    private final static Map<String, Operator> operators = createOperators();

    Operator create(String operator) {
        if (!operators.containsKey(operator)) {
            throw new UnknownOpFunct(operator);
        }
        return operators.get(operator);
    }

    private static Map<String, Operator> createOperators() {
        Map<String, Operator> operators = new HashMap<>();

        operators.put("+", BigDecimal::add);
        operators.put("-", BigDecimal::subtract);
        operators.put("*", BigDecimal::multiply);
        operators.put("/", BigDecimal::divide);
        operators.put("^", (a, b) -> valueOf(pow(a.doubleValue(), b.doubleValue())));

        return operators;
    }

}
