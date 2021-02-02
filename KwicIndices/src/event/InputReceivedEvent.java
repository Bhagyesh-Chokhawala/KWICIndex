package event;


public class InputReceivedEvent extends AbstractEvent {

    private final String input;

    public InputReceivedEvent(String aInput) {
        this.input = aInput;
    }

    public String getInputString() {
        return input;
    }
}
