package Service;

import Service.models.BaseModel;

public class Question implements BaseModel {
    private long id;
    private String type;
    private String text;

    public Question() {
    }

    public Question(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public long getId() {

        return id;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String toString(){
        return "Question # " + id + "| " + type + " | " + text;
    }


}
