package dataStructure;

        import event.InputReceivedEvent;
        import framework.EventDispatcher;
        import handler.InputReceivedEventHandler;

        import java.util.*;


@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class LineStore {
    private static LineStore singleInstance = null;
    private final List<String> _shiftedLines;
    private final List<String> _inputLines;
    public int totalInputCount;
    final EventDispatcher dispatcher;

    private LineStore() {
         dispatcher = new EventDispatcher();
        dispatcher.registerHandler(InputReceivedEvent.class,new InputReceivedEventHandler());
        this._shiftedLines = new ArrayList<>();
        this._inputLines = new ArrayList<>();
    }
    public static LineStore getInstance()
    {
        if (singleInstance == null)
            singleInstance = new LineStore();

        return singleInstance;
    }
public void addInputLine(String aLine)
{
    assert(aLine != null);
    this._inputLines.add(aLine);
    dispatcher.dispatch(new InputReceivedEvent(aLine));
}
    public void addShiftedLines(String[] aLine)
    {
        assert(aLine != null);
        this._shiftedLines.addAll(Arrays.asList(aLine));
    }

    public String[] getShiftedLines() {
        return this._shiftedLines.toArray(new String[0]);
    }

}
