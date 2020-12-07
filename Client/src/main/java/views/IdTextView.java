package views;

import models.Id;

public class IdTextView {
    private Id id;

    public IdTextView(Id idToDisplay) {
        this.id = idToDisplay;

    }
    @Override public String toString() {
        return String.format("Name: %s\nGitHub ID: %s\n",id.getName(),id.getGithub());
    } 
}