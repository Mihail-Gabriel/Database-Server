package Util;

import java.io.Serializable;

public class Request implements Serializable {
    private EventType type;
    public Object arg;

    public Request(EventType type, Object arg)
    {
        this.type =type;
        this.arg= arg;
    }

    public EventType getEventType()
    {
        return type;
    }

    public Object getObject()
    {
        return arg;
    }
}
