package DataLayer.models;

public class Question implements BaseModel {
    private long id;
    private QuestionType type;
    private String text;

     public void setId(long id) {
        this.id = id;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public long getId() {
        return id;
    }

    public QuestionType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setTypeCode(int typeCode){
        type = QuestionType.getByValue(typeCode);
    }

    public int getTypeCode(){
        return type.getCode();
    }

    public String toString(){
        return "Question # " + id + " | " + type + " | " + text;
    }

}
