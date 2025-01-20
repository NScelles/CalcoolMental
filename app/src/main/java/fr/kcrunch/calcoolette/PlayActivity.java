package fr.kcrunch.calcoolette;

import android.app.Activity;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CountDownTimer;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.kcrunch.calcoolette.model.Calcul;
import fr.kcrunch.calcoolette.model.TypeOperationEnum;
import fr.kcrunch.calcoolette.popup.EndGamePopUp;

public class PlayActivity extends AppCompatActivity {
    private Integer reponseJoueur = 0;
    private TypeOperationEnum typeOperation = null;
    private TextView textViewResultat;
    private Integer BORNE_HAUTE = 9999;
    private TextView textViewCalcul;
    private Calcul monCalcul;
    private TextView textViewReponse;
    private Integer coldStreak;
    private Integer score;
    private CountDownTimer timer;
    private long gameTime = 30000;
    private TextView textViewTempsRestant;
    private Activity activity;
    private TextView textViewScore;
    private TextView textViewErreur;
    private Integer erreur = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textViewErreur = findViewById(R.id.textviewErreur);
        textViewScore = findViewById(R.id.textViewScore);
        textViewResultat = findViewById(R.id.resultat);
        textViewCalcul = findViewById(R.id.textviewCalcul);
        textViewReponse = findViewById(R.id.retourReponse);
        textViewTempsRestant = findViewById(R.id.tempsRestantNombre);
        this.activity = this;
        Button button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(view -> ajouterNombre(1));
        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(view -> ajouterNombre(2));
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(view -> ajouterNombre(3));
        Button button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(view -> ajouterNombre(4));
        Button button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(view -> ajouterNombre(5));
        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(view -> ajouterNombre(6));
        Button button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(view -> ajouterNombre(7));
        Button button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(view -> ajouterNombre(8));
        Button button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(view -> ajouterNombre(9));
        Button button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(view -> ajouterNombre(0));
        ImageButton validateButton = findViewById(R.id.validateButton);
        validateButton.setOnClickListener(view -> verification());
        ImageButton deleteInputButton = findViewById(R.id.deleteInputButton);
        deleteInputButton.setOnClickListener(view -> delete());
        newGame();
    }

    private void newGame(){
        coldStreak = 0;
        score = 0;
        erreur=3;
        nouveauCalcul();
        updateScore();
        updateTimer();
        updateErreur();
        setUpTimer();
    }

    private void setUpTimer() {
        timer = new CountDownTimer(gameTime,1000) {
            @Override
            public void onTick(long left) {
                gameTime = left;
                updateTimer();
            }

            @Override
            public void onFinish() {
                creerPopUp();
            }

        }.start();
    }

    private void creerPopUp() {
        EndGamePopUp endGamePopUp = new EndGamePopUp(activity);
        endGamePopUp.setPointSubtitle(score.toString(),activity);
        endGamePopUp.setScore(score);
        endGamePopUp.setCanceledOnTouchOutside(false);
        endGamePopUp.setOwnerActivity(activity);
        endGamePopUp.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                finish();
            }
        });

        endGamePopUp.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                finish();
            }
        });
        endGamePopUp.build();
    }

    private void updateTimer() {
        int secondes = (int) gameTime / 1000;
        String temps = "" + secondes;
        textViewTempsRestant.setText(temps);
    }

    private void delete() {
        reponseJoueur = Math.round(reponseJoueur/10);
        majText();
    }


    private void updateScore(){
        textViewScore.setText("Score : " + score);
    }

    private void updateErreur(){
        textViewErreur.setText(getString(R.string.erreur) + " " + erreur.toString());
    }

    private void verification() {
        if(reponseJoueur==monCalcul.getResultat()) {
            coldStreak++;
            setColdStreakReponse();
            score += coldStreak;
            updateScore();
        }
        else {
            textViewReponse.setText(getString(R.string.mauvaise_reponse));
            coldStreak=0;
            erreur--;
            updateErreur();
            if(erreur==0) {
                timer.cancel();
                creerPopUp();
            }
        }
        effacer();
        nouveauCalcul();
    }

    private void setColdStreakReponse(){
        String multiplicateur = "";
        switch (coldStreak) {
            case 1:
                multiplicateur ="";
                break;
            case 2:
                multiplicateur = getString(R.string.multiplicateurDeux);
                break;
            case 3:
                multiplicateur = getString(R.string.multiplicateurTrois);
                break;
            case 4:
                multiplicateur = getString(R.string.multiplicateurQuatre);
                break;
            case 5:
                multiplicateur = getString(R.string.multiplicateurCinq);
                break;
            case 6:
                multiplicateur = getString(R.string.multiplicateurSix);
                break;
            case 7:
                multiplicateur = getString(R.string.multiplicateurSept);
                break;
            case 8:
                multiplicateur = getString(R.string.multiplicateurHuit);
                break;
            case 9:
                multiplicateur = getString(R.string.multiplicateurNeuf);
                break;
            case 10:
                multiplicateur = getString(R.string.multiplicateurDix);
                break;
            case 11:
                multiplicateur = getString(R.string.multiplicateurOnze);
                break;
            case 12:
                multiplicateur = getString(R.string.multiplicateurDouze);
                break;
            case 13:
                multiplicateur = getString(R.string.multiplicateurQuandTesTropFort);
                break;
            default:
                multiplicateur = getString(R.string.multiplicateurTrop);
                break;
        }

        textViewReponse.setText( multiplicateur + " " + getString(R.string.bonne_reponse));
    }

    private void nouveauCalcul(){
        String textAAfficher = "";
        monCalcul = new Calcul();
        switch (monCalcul.getSymbol()) {
            case "ADD":
                typeOperation = TypeOperationEnum.ADD;
                break;
            case "SUBSTRACT":
                typeOperation = TypeOperationEnum.SUBSTRACT;
                break;
            case "MULTIPLY":
                typeOperation = TypeOperationEnum.MULTIPLY;
                break;
        }
        textAAfficher = monCalcul.getPremierElement() + " " + typeOperation.getSymbol() + " " + monCalcul.getDeuxiemeElement();
        textViewCalcul.setText(textAAfficher);
    }

    private void ajouterNombre(Integer valeur) {
            if (10 * reponseJoueur + valeur > BORNE_HAUTE) {
                Toast.makeText(this, getString(R.string.ERROR_VALEUR_TROP_GRANDE), Toast.LENGTH_LONG).show();
            } else {
                reponseJoueur = 10 * reponseJoueur + valeur;
            }
        majText();
    }

    private void majText() {
        textViewResultat.setText(reponseJoueur.toString());
    }

    private void effacer(){
        textViewResultat.setText("");
        reponseJoueur = 0;
    }

    @Override
    public void onBackPressed () {

    }
}
