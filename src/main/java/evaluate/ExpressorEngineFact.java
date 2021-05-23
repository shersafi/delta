package evaluate;

public class ExpressorEngineFact {

    public Expressor create() {
        return new Expressor(
                new Tokenizer(),
                new Alg(),
                new ReversePolish(
                        new OpFactory(),
                        new FunctFactory()));
    }
}
