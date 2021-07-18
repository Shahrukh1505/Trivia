package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.data.AnswerListAsyncResponse;
import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

private ActivityMainBinding binding;
private int currentQuestionIndex = 0;
    List<Question> questionList;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

   questionList = new Repository().getQuestions(questionArrayList -> binding.questionTextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer()));



binding.buttonNext.setOnClickListener(v -> {
currentQuestionIndex = (currentQuestionIndex + 1)%questionList.size();
updateQuestion();
});
binding.buttonTrue.setOnClickListener(v -> {
checkAnswer(true);
});
binding.buttonFalse.setOnClickListener(v -> {
checkAnswer(false);
});
    }

    private void checkAnswer(boolean userChoiceCorrect) {
    boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
    int snackMessageId = 0;
    if(userChoiceCorrect == answer){
        snackMessageId = R.string.correct_answer;
    }
    else {
        snackMessageId = R.string.incorrect;
    }
        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT).show();
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.textviewOutOf.setText("Question: " + currentQuestionIndex + "/" + questionArrayList.size());
    }
//setting the text of cardview that is question
    //calling update counter to show question number out of total number of question
    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();

    binding.questionTextview.setText((question));
    updateCounter((ArrayList<Question>) questionList);
    }



}

