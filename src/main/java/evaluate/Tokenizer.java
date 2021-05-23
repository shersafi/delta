package evaluate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static evaluate.Token.*;
import static java.lang.String.valueOf;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

public class Tokenizer {

    final static Pattern NUMBER = compile("(\\d*\\.\\d*|\\d*|\\d*\\.|\\.\\d*|\\.)");
    final static Pattern OPERATOR = compile("[+\\-*/^]");
    final static Pattern FUNCTION = compile("[a-z]+", CASE_INSENSITIVE);

    List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        State state = State.FREE;

        for (int i = 0; i < input.length(); ++i) {
            String cs = valueOf(input.charAt(i));

            switch (state) {
                case FREE:
                    if (Character.isWhitespace(cs.charAt(0))) {
                        continue;
                    }

                    if (NUMBER.matcher(cs).matches()) {
                        state = State.NUMBER;
                        buffer.append(cs);
                        continue;
                    }

                    if (FUNCTION.matcher(cs).matches()) {
                        state = State.FUNCTION;
                        buffer.append(cs);
                        continue;
                    }

                    if (OPERATOR.matcher(cs).matches()) {
                        tokens.add(operator(cs));
                        continue;
                    }

                    if (cs.equals("(")) {
                        tokens.add(leftParenthesis());
                        continue;
                    }

                    if (cs.equals(")")) {
                        tokens.add(rightParenthesis());
                        continue;
                    }

                    break;

                case NUMBER:
                    if (NUMBER.matcher(cs).matches() && !(cs.equals(".") && buffer.indexOf(".") != -1)) {
                        buffer.append(cs);
                        continue;
                    }

                    tokens.add(number(buffer.toString()));
                    buffer.delete(0, buffer.length());
                    state = State.FREE;
                    i--;
                    continue;

                case FUNCTION:
                    if (FUNCTION.matcher(cs).matches()) {
                        buffer.append(cs);
                        continue;
                    }

                    tokens.add(function(buffer.toString()));
                    buffer.delete(0, buffer.length());
                    state = State.FREE;
                    i--;
                    continue;
            }
        }

        if (buffer.length() > 0) {
            switch (state) {
                case NUMBER:
                    tokens.add(number(buffer.toString()));
                    break;
                case FUNCTION:
                    tokens.add(function(buffer.toString()));
                    break;
            }
        }

        return tokens;
    }

    private enum State {
        FREE,
        NUMBER,
        FUNCTION
    }

}
