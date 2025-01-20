package fr.kcrunch.calcoolette.popup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.Console;

import fr.kcrunch.calcoolette.PlayActivity;
import fr.kcrunch.calcoolette.R;
import fr.kcrunch.calcoolette.controller.ScoreBaseHelper;
import fr.kcrunch.calcoolette.controller.ScoreDao;
import fr.kcrunch.calcoolette.controller.ScoreService;
import fr.kcrunch.calcoolette.model.Score;

public class EndGamePopUp extends Dialog {

    private String title;
    private String subtitle;
    private EditText pseudo;
    private Button register;
    private TextView titleTextView,subtitleTextView;
    private Integer score;
    private ScoreService scoreService;

    public EndGamePopUp(Activity activity){
        super(activity, R.style.Base_ThemeOverlay_Material3_Dialog);
        setContentView(R.layout.endgame_pop_up);
        title= activity.getString(R.string.pop_up_title);
        subtitle = activity.getString(R.string.pop_up_subtitle_1) + " " + activity.getString(R.string.pop_up_subtitle_2);
        pseudo=findViewById(R.id.pseudo_edit_text);
        register=findViewById(R.id.pop_up_button);
        titleTextView = findViewById(R.id.pop_up_title);
        subtitleTextView = findViewById(R.id.pop_up_subtitle);
        score = 0;
    }

    public void setPointSubtitle(String subtitle, Activity activity) {
        this.subtitle =  activity.getString(R.string.pop_up_subtitle_1) + " " + subtitle + " " + activity.getString(R.string.pop_up_subtitle_2) ;
    }

    public void setScore(Integer score){
        this.score = score;
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }

    public void build(){
        show();
        titleTextView.setText(title);
        subtitleTextView.setText(subtitle);

        register.setOnClickListener(view -> putInDB());
    }

    private void putInDB() {
        Score score = new Score();
        score.setScore(this.score);
        if (!pseudo.getText().toString().isEmpty())
            score.setPseudo(pseudo.getText().toString());
        else
            score.setPseudo("Anonymous");
        scoreService = new ScoreService(new ScoreDao(new ScoreBaseHelper(getOwnerActivity())));
        scoreService.storeInDB(score);
        dismiss();
    }
}
