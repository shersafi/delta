package evaluate;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
public class Notation {

    private final OpFactory operatorFactory;
    private final FunctFactory functionFactory;

    BigDecimal evaluate(List<Token> tokens) {
        Stack<BigDecimal> stack = new Stack<>();
        tokens.forEach(token -> {
            switch (token.type()) {
                case OPERATOR:
                    BigDecimal right = stack.pop();
                    BigDecimal left = stack.pop();
                    Operator operator = operatorFactory.create(token.value());
                    stack.push(operator.evaluate(left, right));
                    break;
                case FUNCTION:
                    Function function = functionFactory.create(token.value());
                    stack.push(function.evaluate(stack.pop()));
                    break;
                default:
                    stack.push(new BigDecimal(token.value()));
            }
        });
        return stack.pop();
    }

}
