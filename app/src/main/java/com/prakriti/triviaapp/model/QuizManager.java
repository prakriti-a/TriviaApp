package com.prakriti.triviaapp.model;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.prakriti.triviaapp.R;
import com.prakriti.triviaapp.VolleySingleton;
import com.prakriti.triviaapp.controller.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {

    private RequestQueue myQueue;
    private String url;
    private CardStackView myCSV;
    private Context myContext;

    public QuizManager(Context context) {
        myContext = context;
        myQueue = VolleySingleton.getInstance().getRequestQueue();
        url = "https://opentdb.com/api.php?amount=10&category=12&type=boolean";
    }

    public void getQuizQuestions(Activity activity) {

        List<QuizQuestion> myQuizQuestions = new ArrayList<>();

        JsonObjectRequest myJsonTrivia = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray results = response.getJSONArray("results");

                            for(int i=0; i<results.length(); i++) {

                                JSONObject questionJSON = results.getJSONObject(i);
                                String ques = questionJSON.getString("question");
                                boolean ans = questionJSON.getBoolean("correct_answer");
                                Log.i("TRIVIA", ques + " - " + ans);
                                QuizQuestion myQuiz = new QuizQuestion(ques, ans);
                                myQuizQuestions.add(myQuiz);
                            }

                            myCSV = activity.findViewById(R.id.myCardStackView);
                            myCSV.setLayoutManager(new CardStackLayoutManager(myContext));
                            myCSV.setAdapter(new CardStackAdapter(myContext, myQuizQuestions));

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERR", error.getMessage());
            }
        });

        myQueue.add(myJsonTrivia);
    }
}
