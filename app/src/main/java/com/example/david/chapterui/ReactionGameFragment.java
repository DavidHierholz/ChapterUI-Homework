package com.example.david.chapterui;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by david on 22.02.18.
 */

public class ReactionGameFragment extends Fragment implements View.OnClickListener {

    private TextView textView;
    private boolean isPlaying = false;
    private View view;
    private int frameId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_reaction_game, null, false);

        for (int i = 1; i <=15; i++) {
            String frameIdString = "frame" + i;
            Resources res = getActivity().getResources();
            frameId = res.getIdentifier(frameIdString, "id", getContext().getPackageName());
            FrameLayout frameLayout = (FrameLayout) view.findViewById(frameId);
            frameLayout.setOnClickListener(this);
            frameLayout.setBackgroundColor(Color.WHITE);
        }

        textView = view.findViewById(R.id.textViewGame);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (!isPlaying) {
            startGame();
        } else if (view.getId() == frameId) {
            textView.setText("Good! Start again by tapping on the screen");
            isPlaying = false;
            FrameLayout frameLayout = (FrameLayout) view.findViewById(frameId);
            frameLayout.setBackgroundColor(Color.WHITE);
        }
    }

    private void startGame() {
        isPlaying = true;
        textView.setText("Tap on the colored field!");
        Random random = new Random();
        int frameNumber = random.nextInt(15) + 1;
        String frameIdString = "frame" + frameNumber;
        Resources res = getActivity().getResources();
        frameId = res.getIdentifier(frameIdString, "id", getContext().getPackageName());
        FrameLayout frameLayout = (FrameLayout) view.findViewById(frameId);
        frameLayout.setBackgroundColor(Color.RED);
    }
}
