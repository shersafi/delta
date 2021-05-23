package evaluate;

import java.math.BigDecimal;

interface Operator {
    BigDecimal evaluate(BigDecimal left, BigDecimal right);
}
