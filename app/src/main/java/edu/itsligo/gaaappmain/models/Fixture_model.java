package edu.itsligo.gaaappmain.models;

public class Fixture_model {

    String  name;
    String date;
String type;
String img_url;

    public Fixture_model() {
    }

    public Fixture_model(String name, String date, String type) {
        this.name = name;
        this.date = date;
        this.type = type;

    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
