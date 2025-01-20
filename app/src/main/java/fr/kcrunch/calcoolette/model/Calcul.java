package fr.kcrunch.calcoolette.model;

import fr.kcrunch.calcoolette.model.TypeOperationEnum;

public class Calcul {
    Integer premierElement;
    Integer deuxiemeElement;
    String symbol;
    Integer resultat;

    public Integer getPremierElement() {
        return premierElement;
    }

    public Integer getDeuxiemeElement() {
        return deuxiemeElement;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getResultat() {
        return resultat;
    }

    public void setPremierElement(Integer premierElement) {
        this.premierElement = premierElement;
    }

    public void setDeuxiemeElement(Integer deuxiemeElement) {
        this.deuxiemeElement = deuxiemeElement;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setResultat(Integer resultat) {
        this.resultat = resultat;
    }

    public Calcul(){

        int monSymbol = Math.round(Math.round(Math.random() * 10 + 3  ) % 3);
        if (monSymbol==0){
            this.symbol ="ADD";
            this.premierElement = Math.toIntExact(Math.round(Math.random() * 100 +2));
            this.deuxiemeElement =  Math.toIntExact(Math.round(Math.random() * 100 +2));
        }
        else if(monSymbol == 1) {
            this.symbol = "SUBSTRACT";
            do {
                this.premierElement = Math.toIntExact(Math.round(Math.random() * 100 + 2));
                this.deuxiemeElement = Math.toIntExact(Math.round(Math.random() * 100 + 2));
            } while (premierElement < deuxiemeElement);
        }
        else {
            this.symbol = "MULTIPLY";
            this.premierElement = Math.toIntExact(Math.round(Math.random() * 10 +2));
            this.deuxiemeElement =  Math.toIntExact(Math.round(Math.random() * 10 +2));
        }

        this.resultat = faisLeCalcul(symbol, this.premierElement,this.deuxiemeElement);
    }

    private Integer faisLeCalcul(String leSymbol, Integer premierNbr, Integer deuxiemeNbr){
        switch (leSymbol) {
            case "ADD":
                return premierNbr + deuxiemeNbr;
            case "SUBSTRACT":
                return premierNbr - deuxiemeNbr;
            case "MULTIPLY":
                return premierNbr * deuxiemeNbr;
        }
        return 0;
    }

}
