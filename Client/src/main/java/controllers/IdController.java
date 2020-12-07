package controllers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Id;

public class IdController {
    Id myId;
    private TransactionController tc;

    public IdController() throws IOException {
        this.tc = new TransactionController();
    }

    public String getIds() {
        String data = "";

        try{
            data = tc.get("/ids").body().string();

        }catch(IOException e){
            e.printStackTrace();
        }

        return data;

    }

    public Id postId(Id id) throws IOException {
        String payload = new Gson().toJson(id);
        tc.post("/ids",payload);

        return id;
    }

    public Id putId(Id id) throws IOException {
        String payload = new Gson().toJson(id);
        tc.put("/ids",payload);
        return id;
    }
    public ArrayList<Id> parseIds(String payload) {
        Type listType = new TypeToken<List<Id>>() {}.getType();
        return new Gson().fromJson(payload, listType);
    }
    public Id findById(Id id) {
        ArrayList<Id> idList = parseIds(getIds());
        Id foundId = null;

        for (Id idInList : idList) {
            if (idInList.getGithub().equals(id.getGithub())) {
                foundId = idInList;
            }
        } return foundId;
    }
    public Id findByGitId(String gitId) {
        ArrayList<Id> idList = parseIds(getIds());
        Id foundId = null;

        for (Id idInList : idList) {
            if (gitId.equals(idInList.getGithub())) {
                foundId = idInList;
                break;
            }
        } return foundId;
    }
 
}