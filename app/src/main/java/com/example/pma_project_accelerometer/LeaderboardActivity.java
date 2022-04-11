package com.example.pma_project_accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {

    private Button btn_main;
    private TextView results;
    private static ArrayList<Integer> listOfResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        results = findViewById(R.id.results);
        btn_main = findViewById(R.id.btn_back);

        //Gå til Leaderboards
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LeaderboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //addTestIntegers();
        sortIntegerList(listOfResults);
        results.setText(createResultsString());
    }

private void addTestIntegers(){
        listOfResults.add(3);
    listOfResults.add(15);
    listOfResults.add(189);
    listOfResults.add(1);
    listOfResults.add(4);
}
    private ArrayList<Integer> sortIntegerList(ArrayList<Integer> listToSort){
        Collections.sort(listToSort);
        Collections.reverse(listToSort);
        return listToSort;
    }

    private String createResultsString(){
        String stringToPrint = "";
        for(int i = 0; i<listOfResults.size();i++){
        stringToPrint += Integer.toString(i+1) + " : " + Integer.toString(listOfResults.get(i)) + "\n";
        }
        if (listOfResults.size() == 0) stringToPrint += "No results yet";
    return  stringToPrint;
    }

    public static void addToResultsList(Integer resultToAdd){
        listOfResults.add(resultToAdd);
    }
}