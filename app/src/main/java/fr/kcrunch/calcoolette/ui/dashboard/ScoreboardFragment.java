package fr.kcrunch.calcoolette.ui.dashboard;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import fr.kcrunch.calcoolette.R;
import fr.kcrunch.calcoolette.controller.ScoreBaseHelper;
import fr.kcrunch.calcoolette.controller.ScoreDao;
import fr.kcrunch.calcoolette.controller.ScoreService;
import fr.kcrunch.calcoolette.databinding.FragmentDashboardBinding;
import fr.kcrunch.calcoolette.model.Score;

public class ScoreboardFragment extends Fragment {

    private ScoreboardViewModel scoreboardViewModel;
    private FragmentDashboardBinding binding;
    private ScoreService scoreService;
    private ListView scoreboard;
    private String ordre = "score DESC";
    private String limit = "10";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scoreboardViewModel =
                new ViewModelProvider(this).get(ScoreboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        scoreboard = binding.scoreboardTable;
        scoreboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                scoreService = new ScoreService(new ScoreDao(new ScoreBaseHelper(getContext())));
                getValuesTable();
            }
        });
        return root;
    }

    private void getValuesTable(){



        List<Score> listScores = scoreService.getDataInDB(ordre,limit);
        scoreService.close();
        String[] columns = new String[]{"_id", "col1", "col2"};

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        matrixCursor.addRow(new Object[]{0,"Pseudo","score"});
        if (scoreService.getComputeCount()!=0) {


            int nbScore= 0;
            for (Score score: listScores) {
                nbScore++;
                matrixCursor.addRow(new Object[]{nbScore,score.getPseudo(), score.getScore().toString()});
            }



        }

        String[] from = new String[]{"_id", "col1", "col2"};
        int[] to = new int[]{R.id.textViewCol0, R.id.textViewCol1, R.id.textViewCol2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getContext(), R.layout.scoreboard_table, matrixCursor,from,to,0);
        scoreboard.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}