package com.prakriti.triviaapp.controller;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prakriti.triviaapp.R;
import com.prakriti.triviaapp.model.QuizQuestion;
import com.prakriti.triviaapp.view.TriviaViewHolder;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<TriviaViewHolder> {

    private Context myContext;
    private List<QuizQuestion> myTriviaQuestions;
    private LayoutInflater myLayoutInflator;

    public CardStackAdapter(Context context, List<QuizQuestion> triviaNames) {
        myContext = context;
        myTriviaQuestions = triviaNames;
        myLayoutInflator = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TriviaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = myLayoutInflator.inflate(R.layout.quiz_view, parent, false);
        return new TriviaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewHolder holder, int position) {

        holder.getQuestion().setText(myTriviaQuestions.get(position).getQuestionText());

        holder.getTrue_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myTriviaQuestions.get(position).isAnswer()) {
                    Toast.makeText(myContext, "CORRECT!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(myContext, "Sorry, Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.getFalse_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myTriviaQuestions.get(position).isAnswer()) {
                    Toast.makeText(myContext, "Sorry, Wrong Answer!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(myContext, "CORRECT!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myTriviaQuestions.size();
    }
}
