package Util;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
@JsonRootName(value="request")
public class Request implements Serializable {
    @JsonProperty("eventType")
    private EventType type;
    @JsonProperty("object")
    private Object arg;


    @JsonCreator
    public Request(@JsonProperty("eventType") EventType type,@JsonProperty("object") Object arg)
    {
        this.type =type;
        this.arg= arg;
    }

    @JsonGetter
    public EventType getEventType()
    {
        return type;
    }

    @JsonSetter
    public void setType(EventType type) {
        this.type = type;
    }
    @JsonSetter
    public void setArg(Object arg) {
        this.arg = arg;
    }
    @JsonGetter
    public Object getObject()
    {
        return arg;
    }
}
