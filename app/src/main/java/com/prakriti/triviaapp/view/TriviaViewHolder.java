package com.prakriti.triviaapp.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prakriti.triviaapp.R;

public class TriviaViewHolder extends RecyclerView.ViewHolder {

    private TextView question;
    private ImageButton true_button, false_button;

    public TriviaViewHolder(@NonNull View itemView) {
        super(itemView);

        question = itemView.findViewById(R.id.question);
        true_button = itemView.findViewById(R.id.true_button);
        false_button = itemView.findViewById(R.id.false_button);
    }

    public TextView getQuestion() {
        return question;
    }

    public ImageButton getTrue_button() {
        return true_button;
    }

    public ImageButton getFalse_button() {
        return false_button;
    }

}
