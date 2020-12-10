package edu.itsligo.gaaappmain;

public class NewTable {
    private String Title;
    private String Desc;

    public NewTable(){

    }

    public NewTable(String title, String desc) {
        Title = title;
        Desc = desc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
