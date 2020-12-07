package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/*
 * POJO for an Message object
 */
public class Message {
    @JsonProperty
    private String sequence;
    @JsonProperty
    private String timeStamp;
    @JsonProperty
    private String fromId;
    @JsonProperty
    private String toId;
    @JsonProperty
    private String message;


    @JsonCreator
    public Message(@JsonProperty("sequence") String sequence,
                   @JsonProperty("timestamp") String timeStamp,
                   @JsonProperty("fromId") String fromID,
                   @JsonProperty("toId") String toId,
                   @JsonProperty("message") String message){
        this.sequence = sequence;
        this.timeStamp = timeStamp;
        this.fromId = fromID;
        this.toId = toId;
        this.message = message;
    }

    public Message (String fromId, String toId,String message) {
        this("-", "_", fromId, toId, message);
    }
    public Message(String fromID, String message){
        this("-", "_", fromID ,"", message);
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getTimeStamp() {
        return timeStamp;
    }

}