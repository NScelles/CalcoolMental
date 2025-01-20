package fr.kcrunch.calcoolette.controller;

import android.content.Context;

public class ScoreBaseHelper extends DataBaseHelper {

    public ScoreBaseHelper(Context context) {
        super(context, "scoreboard.db", 1);
    }

    @Override
    protected String getCreationSql() {
        return "CREATE TABLE IF NOT EXISTS scores  (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                ScoreDao.clePseudo + " VARCHAR(255) NOT NULL," +
                ScoreDao.cleScore + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}