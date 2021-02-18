package KwicIndices.outputFormat;

public abstract class OutputDecorator implements Output {
    @Override
    public abstract StringBuilder loadOutput(String[] body);

    protected Output output;

    public OutputDecorator(Output output) {
        this.output = output;
    }
}