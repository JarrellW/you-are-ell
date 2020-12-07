package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {

    private String name;
    private String github;
    private String userid;

    @JsonCreator
    public Id (@JsonProperty("name") String name,
               @JsonProperty("github") String github,
               @JsonProperty("userid") String userId) {
        this.name = name;
        this.github = github;
        this.userid = userId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getUserID() {
        return userid;
    }

    public void setUserID(String userID) {
        this.userid = userID;
    }


    @Override
    public String toString() {
        return "Id{" +
                "name='" + name + '\'' +
                ", github='" + github + '\'' +
                ", userID='" + userid + '\'' +
                '}';
    }


}