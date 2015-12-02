package com.example.nezarsaleh.headsup1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Fragment_All extends Fragment {

    GridView Decks_Grid;
    ArrayList<CategoryDataModel> categories = new ArrayList<>();
    DatabaseHelper databaseHelper;

    String[] Movies = {""};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        Cursor res = databaseHelper.getAllCat();
        if (res.getCount() != 0){
            while (res.moveToNext()){
                categories.add(new CategoryDataModel(res.getInt(0), res.getInt(2), res.getString(1), res.getInt(3),res.getInt(4)));
            }
        }else {

            categories.add(new CategoryDataModel(databaseHelper.insertCat("Movies", R.drawable.films, 0, 0), R.drawable.films, "Movies"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("Emotions",R.drawable.emotion, 0, 0),R.drawable.emotion, "Emotions"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("Animals",R.drawable.animals, 0, 0),R.drawable.animals, "Animals"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("Fairy Tales",R.drawable.fairytale, 0, 0),R.drawable.fairytale, "Fairy Tales"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("Character",R.drawable.actor, 0, 0),R.drawable.actor, "Character"));

        }

        Decks_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // Setting Dialog Title
                alertDialog.setTitle("Get Ready");
                // Setting Dialog Message
                alertDialog.setMessage("This Board is " + categories.get(position).getCatName());
                //alertDialog.setView(input);
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.card);

                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Intent in = new Intent(getActivity(), QuickPlay.class);
                                in.putExtra("CatID",categories.get(position).getID());
                                in.putExtra("GameName", categories.get(position).getCatName());
                                startActivity(in);
                            }
                        });
                // Showing Alert Message
                alertDialog.show();

            }
        });
    }
}
