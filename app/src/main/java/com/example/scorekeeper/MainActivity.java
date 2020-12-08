package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private TextView teamAPoint;
    private TextView teamBPoint;
    private TextView winningPoint;

    private Button edit;
    private Button teamAMinus;
    private Button teamBMinus;
    private Button teamAPlus;
    private Button teamBPlus;
    private Button reset;
    private int winScore;
    private int teamAScore;
    private int teamBScore;

    private int tempA;
    private int tempB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset = findViewById(R.id.btnReset);
        edit = findViewById(R.id.btnEdit);

//Winning point
        winningPoint = findViewById(R.id.tvWinningPoint);
//        Both Team Points
        teamAPoint = findViewById(R.id.tvTeamAPoint);
        teamBPoint = findViewById(R.id.tvTeamBPoint);
//Team A Button
        teamAPlus = findViewById(R.id.btnTeamAPlus);
        teamAMinus = findViewById(R.id.btnTeamAMinus);
//        team B Button
        teamBPlus = findViewById(R.id.btnTeamBPlus);
        teamBMinus = findViewById(R.id.btnTeamBMinus);
        TeamAPlusClick teamAPlusClick = new TeamAPlusClick();
        teamAPlus.setOnClickListener(teamAPlusClick);
        TeamBPlusClick teamBPlusClick = new TeamBPlusClick();
        teamBPlus.setOnClickListener(teamBPlusClick);

        TeamAMinusClick teamAMinusClick = new TeamAMinusClick();
        teamAMinus.setOnClickListener(teamAMinusClick);

        TeamBMinusClick teamBMinusClick = new TeamBMinusClick();
        teamBMinus.setOnClickListener(teamBMinusClick);

        ResetBtnClick resetBtnClick = new ResetBtnClick();
        reset.setOnClickListener(resetBtnClick);

        EditBtnClick editBtnClick = new EditBtnClick();
        edit.setOnClickListener(editBtnClick);


    }

    class TeamAPlusClick implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {

            if (winScore == 0) {
                Toast.makeText(context, "Please set winning point", Toast.LENGTH_SHORT).show();
            }else if(teamBScore== winScore){
                Toast.makeText(context, "TEAM B already Won the Game.", Toast.LENGTH_SHORT).show();
            }
            else {
                tempA = teamAScore + 1;
                if(teamAScore == teamBScore && (winScore-teamAScore)==1){
                    teamAScore++;
                    winScore++;
                    teamAPoint.setText(" " + teamAScore);
                    winningPoint.setText(" " + winScore);
                }else if (tempA == winScore && tempA > teamBScore) {
                    teamAScore++;
                    Toast.makeText(context, "TEAM A Won the game.", Toast.LENGTH_SHORT).show();
                    teamAPoint.setText(" " + teamAScore);
                }


                else if (tempA < winScore) {
                    teamAScore++;
                    teamAPoint.setText(" " + teamAScore);
                    Toast.makeText(context, "Team A got 1 Point.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Game Finished. Please reset the score.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class TeamBPlusClick implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            if (winScore == 0) {
                Toast.makeText(context, "Please set winning point", Toast.LENGTH_SHORT).show();
            }else if(teamAScore== winScore){
                Toast.makeText(context, "TEAM A already Won the Game.", Toast.LENGTH_SHORT).show();
            }
            else {
                tempB = teamBScore + 1;
                if(teamBScore == teamAScore && (winScore-teamBScore)==1){
                    teamBScore++;
                    winScore++;
                    teamBPoint.setText(" " + teamBScore);
                    winningPoint.setText(" " + winScore);
                }else if (tempB == winScore && tempB > teamAScore) {
                    teamBScore++;
                    Toast.makeText(context, "TEAM B Won the game.", Toast.LENGTH_SHORT).show();
                    teamBPoint.setText(" " + teamBScore);
                }

                else if (tempB < winScore) {
                    teamBScore++;
                    teamBPoint.setText(" " + teamBScore);
                    Toast.makeText(context, "Team B got 1 Point.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Game Finished. Please reset the score.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class TeamAMinusClick implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {

            teamAScore = teamAScore - 1;
            teamAPoint.setText(" " + teamAScore);
        }
    }

    class TeamBMinusClick implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            teamBScore = teamBScore - 1;
            teamBPoint.setText(" " + teamBScore);
        }
    }

    class ResetBtnClick implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {

            teamAScore = 0;
            teamBScore = 0;
            winScore = 0;
            teamAPoint.setText("" + teamAScore);
            teamBPoint.setText("" + teamBScore);
            winningPoint.setText("" + winScore);
        }
    }


    class EditBtnClick implements View.OnClickListener {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.edit, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.editWinningPoint);

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // get user input and set it to result
                                    // edit text
                                    winningPoint.setText(userInput.getText());
                                    String temp = userInput.getText().toString();
                                    int value = 0;
                                    if (!"".equals(temp)) {
                                        winScore = Integer.parseInt(temp);

                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


//                teamAScore=0;
//                teamBScore=0;
//                winScore=0;
//                teamAPoint.setText(""+teamAScore);
//                teamBPoint.setText(""+teamBScore);
//                winningPoint.setText(""+winScore);
        }
    }

}