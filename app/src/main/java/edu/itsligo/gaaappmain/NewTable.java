package edu.itsligo.gaaappmain;

public class NewTable {
    private String Title;
    private String Desc;
    private String imageURL;

    public NewTable(){

    }

    public NewTable(String title, String desc, String imageURL) {
        Title = title;
        Desc = desc;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
