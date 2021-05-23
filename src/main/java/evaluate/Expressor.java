package evaluate;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class Expressor {
    private final Tokenizer tokenizer;
    private final Alg shuntingYardAlgorithm;
    private final ReversePolish evaluator;

    public BigDecimal evaluate(String expression) {
        List<Token> infixTokens = tokenizer.tokenize(expression);
        List<Token> postfixTokens = shuntingYardAlgorithm.makePostfix(infixTokens);
        return evaluator.evaluate(postfixTokens);
    }
}
