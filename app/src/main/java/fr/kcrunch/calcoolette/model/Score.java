package fr.kcrunch.calcoolette.model;

public class Score extends BaseEntity{
    private Integer score;
    private  String pseudo;

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getScore() {
        return score;
    }

    public String getPseudo() {
        return pseudo;
    }
}
