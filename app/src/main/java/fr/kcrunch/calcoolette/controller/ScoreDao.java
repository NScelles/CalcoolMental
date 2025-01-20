package fr.kcrunch.calcoolette.controller;

import android.content.ContentValues;
import android.database.Cursor;

import fr.kcrunch.calcoolette.model.Calcul;
import fr.kcrunch.calcoolette.model.Score;


public class ScoreDao extends BaseDao<Score> {
    static String clePseudo = "pseudo";
    static String cleScore="score";
    public ScoreDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "scores";
    }


    @Override
    protected void putValues(ContentValues values, Score entity) {
        values.put(clePseudo,entity.getPseudo());
        values.put(cleScore,entity.getScore());
    }

    @Override
    protected Score getEntity(Cursor cursor) {
        Integer indexPseudo = cursor.getColumnIndex(clePseudo);
        Integer indexScore = cursor.getColumnIndex(cleScore);
        Score score = new Score();
        score.setPseudo(cursor.getString(indexPseudo));
        score.setScore(cursor.getInt(indexScore));
        return score;
    }



}
