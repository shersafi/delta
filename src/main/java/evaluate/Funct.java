package evaluate;

import java.math.BigDecimal;

@FunctionalInterface
interface Function {
    BigDecimal evaluate(BigDecimal x);
}
