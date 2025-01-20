package fr.kcrunch.calcoolette.controller;


import android.database.Cursor;

import java.util.List;

import fr.kcrunch.calcoolette.model.Calcul;
import fr.kcrunch.calcoolette.model.Score;

public class ScoreService {
    private ScoreDao scoreDao;

    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public Long getComputeCount(){
        return scoreDao.count();
    }

    public void storeInDB(Score score){
        scoreDao.create(score);
    }

    public void close() {
        scoreDao.close();
    }

    public Score lastOrNull() {
        return scoreDao.lastOrNull();
    }

    public List<Score> getDataInDB(String ordre,String limit){
        return scoreDao.query(null, null,ordre,limit);
    }

    public void delete(String condition){
        scoreDao.delete(condition);
    }

}
