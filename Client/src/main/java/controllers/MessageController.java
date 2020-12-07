package controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Id;
import models.Message;

public class MessageController {
    TransactionController tc;

    public MessageController() throws IOException {
        this.tc = new TransactionController();
    }

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() throws IOException {
        String response = tc.get("/messages").body().string();
        Type listType = new TypeToken<List<Message>>(){}.getType();
        return new Gson().fromJson(response, listType);
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) throws IOException {
        String payload = new Gson().toJson(msg);
        String gitHub = myId.getGithub();
        tc.post("/ids/" + gitHub + "/toId/" + toId.getGithub() +"/messages", payload);
        return msg;
    }

    public String toString(ArrayList<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            sb.append("sequence: " + message.getSequence() + "\n" +
                    "timestamp: " + message.getTimeStamp() + "\n" +
                    "fromid: " + message.getFromId() + "\n" +
                    "toid: " + message.getToId() + " \n" +
                    "message: " + message.getMessage() + "\n\n");
        } return sb.toString();
    }
 
}