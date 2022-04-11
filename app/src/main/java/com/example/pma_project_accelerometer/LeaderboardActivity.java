package com.example.pma_project_accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LeaderboardActivity extends AppCompatActivity {

    public static void main(String args[]){
        int temp, size;
        int array[] = {10, 20, 25, 63, 96, 57};
        size = array.length;

        for(int i = 0; i<size; i++ ){
            for(int j = i+1; j<size; j++){
                if(array[i]>array[j]){
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("Smallest element of the array is:: "+array[0]);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
    }
}