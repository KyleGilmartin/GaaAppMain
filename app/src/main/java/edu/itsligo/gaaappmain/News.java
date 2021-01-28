package edu.itsligo.gaaappmain;

public class News {
    private String title;
    private String desc;
    private String imageURL;
    private String link;

    public News(String title, String desc, String imageURL, String link) {
        this.title = title;
        this.desc = desc;
        this.imageURL = imageURL;
        this.link = link;
    }

    //    public News(String title, String desc, String imageURL) {
//        this.title = title;
//        this.desc = desc;
//        this
//        this.imageURL = imageURL;
//
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    //    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }

    public News(){

    }
}
