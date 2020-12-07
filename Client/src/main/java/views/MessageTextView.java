package views;

import models.Message;

public class MessageTextView {

    private Message displayMessage;

    public MessageTextView(Message msgToDisplay) {
        this.displayMessage = msgToDisplay;

    }
    @Override public String toString() {
        return String.format("From: %s\nTo: %s\n%s\n%s\n\n",
                this.displayMessage.getFromId(),
                this.displayMessage.getToId(),
                this.displayMessage.getTimeStamp(),
                this.displayMessage.getMessage());
    } 
}