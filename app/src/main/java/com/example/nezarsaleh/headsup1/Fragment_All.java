package com.example.nezarsaleh.headsup1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Nezar Saleh on 11/19/2015.
 */
public class Fragment_All extends Fragment {

    GridView Decks_Grid;
    ArrayList<CategoryDataModel> categories = new ArrayList();
    DatabaseHelper databaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all_layout, container, false);
        Decks_Grid = (GridView) view.findViewById(R.id.dick_grid);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        categories.clear();
        Decks_Grid.setAdapter(new ImageAdapter(getActivity(), categories));
        categories.add(new CategoryDataModel(R.drawable.films, "Movies"));
        categories.add(new CategoryDataModel(R.drawable.music,"Music"));
        categories.add(new CategoryDataModel(R.drawable.emotion,"Emotions"));
        categories.add(new CategoryDataModel(R.drawable.animals,"Animals"));
        categories.add(new CategoryDataModel(R.drawable.fairytale,"Fairy Tales"));
        categories.add(new CategoryDataModel(R.drawable.actor, "Character"));
        Decks_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getActivity(),QuickPlay.class);
                in.putExtra("GameName",categories.get(position).catName);
                startActivity(in);
            }
        });
    }
}
