package evaluate;

import lombok.Value;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static evaluate.TypeToken.LEFT_PARENTHESIS;

public class Alg {

    List<Token> makePostfix(List<Token> input) {
        List<Token> output = new ArrayList<>(input.size());
        Stack<OperatorToken> operatorStack = new Stack<>();

        input.forEach(token -> {
            switch (token.type()) {
                case NUMBER:
                    output.add(token);
                    break;
                case FUNCTION:
                case LEFT_PARENTHESIS:
                    operatorStack.add(new OperatorToken(token));
                    break;
                case OPERATOR:
                    OperatorToken operatorToken = new OperatorToken(token);
                    while (shouldPopFromStack(operatorToken, operatorStack)) {
                        output.add(operatorStack.pop().token());
                    }
                    operatorStack.push(operatorToken);
                    break;
                case RIGHT_PARENTHESIS:
                    while (operatorStack.peek().token().type() != LEFT_PARENTHESIS) {
                        output.add(operatorStack.pop().token());
                    }
                    if (!operatorStack.empty() && operatorStack.peek().token.type() == LEFT_PARENTHESIS) {
                        operatorStack.pop();
                    }
                    break;
            }
        });

        while (!operatorStack.empty()) {
            output.add(operatorStack.pop().token());
        }

        return output;
    }

    private boolean shouldPopFromStack(OperatorToken current, Stack<OperatorToken> stack) {
        if (stack.empty()) {
            return false;
        }
        OperatorToken top = stack.peek();
        return (top.isFunction()
                || (top.isOperator() && top.precedence() > current.precedence())
                || (top.precedence() == current.precedence() && top.associativity() == Associativity.LEFT)
        ) && top.token.type() != LEFT_PARENTHESIS;
    }

    @Value
    @Accessors(fluent = true)
    private static class OperatorToken {
        private Token token;

        int precedence() {
            switch (token.value().charAt(0)) {
                case '^':
                    return 4;
                case '*':
                case '/':
                    return 3;
                default:
                    return 2;
            }
        }

        Associativity associativity() {
            if (token.value().charAt(0) == '^') {
                return Associativity.RIGHT;
            }
            return Associativity.LEFT;
        }

        boolean isOperator() {
            return Tokenizer.OPERATOR.matcher(token.value()).matches();
        }

        boolean isFunction() {
            return !Tokenizer.OPERATOR.matcher(token.value()).matches();
        }
    }

    private enum Associativity {
        RIGHT,
        LEFT
    }

}
