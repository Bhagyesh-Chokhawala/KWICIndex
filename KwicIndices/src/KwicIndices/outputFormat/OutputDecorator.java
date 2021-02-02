package KwicIndices.outputFormat;

abstract class OutputDecorator implements Output {
    @Override
    public abstract StringBuilder loadOutput(String[] body);
}