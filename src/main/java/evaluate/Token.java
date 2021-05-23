package evaluate;

import lombok.Value;
import lombok.experimental.Accessors;

import static evaluate.TypeToken.*;

@Value
@Accessors(fluent = true)
public class Token {

    private TypeToken type;
    private String value;

    static Token number(String value) {
        return new Token(NUMBER, value);
    }

    static Token function(String value) {
        return new Token(FUNCTION, value);
    }

    static Token operator(String value) {
        return new Token(OPERATOR, value);
    }

    static Token leftParenthesis() {
        return new Token(LEFT_PARENTHESIS, "(");
    }

    static Token rightParenthesis() {
        return new Token(RIGHT_PARENTHESIS, ")");
    }

}
