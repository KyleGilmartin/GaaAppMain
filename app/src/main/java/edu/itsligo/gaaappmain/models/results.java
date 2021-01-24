package edu.itsligo.gaaappmain.models;

public class results {

    String score1;
    String score2;
    String team1;
    String team2;
    String team1_image;
    String team2_image;


    public results() {
    }

    public results(String score1, String score2, String team1, String team2, String team1_image, String team2_image) {
        this.score1 = score1;
        this.score2 = score2;
        this.team1 = team1;
        this.team2 = team2;
        this.team1_image = team1_image;
        this.team2_image = team2_image;
    }


    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1_image() {
        return team1_image;
    }

    public void setTeam1_image(String team1_image) {
        this.team1_image = team1_image;
    }

    public String getTeam2_image() {
        return team2_image;
    }

    public void setTeam2_image(String team2_image) {
        this.team2_image = team2_image;
    }
}
