package com.example.nezarsaleh.headsup1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment_Custom extends Fragment {

    GridView Decks_Grid;
    ArrayList<CategoryDataModel> categories = new ArrayList<>();
    DatabaseHelper databaseHelper;
    Button New;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_custom, container, false);
        Decks_Grid = (GridView) view.findViewById(R.id.dick_grid);
        New = (Button) view.findViewById(R.id.new_Deck);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        categories.clear();
        Decks_Grid.setAdapter(new ImageAdapter(getActivity(), categories, 0));

        Cursor res = databaseHelper.getAllCat();
        if (res.getCount() != 0){
            while (res.moveToNext()){
                if (res.getInt(4)==1)
                categories.add(new CategoryDataModel(res.getInt(0), res.getInt(2), res.getString(1),res.getInt(3),res.getInt(4)));
            }
        }

        New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                int Coins = pref.getInt("Coins", -1);
                if (Coins >= 10) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("New Board");
                    alertDialog.setMessage("Enter Board Name");
                    final EditText input = new EditText(getActivity());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);
                    alertDialog.setIcon(R.drawable.card);

                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String Name = input.getText().toString();
                                    if (!Name.equals("")) {
//                                Toast.makeText(MainMenuActivity.this, Name, Toast.LENGTH_SHORT).show();
                                        fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentTransaction = fragmentManager.beginTransaction();
                                        Fragment_New fragment = new Fragment_New();
                                        fragment.setBoardName(Name);
                                        fragmentTransaction.replace(R.id.fragment, fragment);
                                        fragmentTransaction.commit();
                                    } else {
                                        Toast.makeText(getActivity(), "Invalid Board Name", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    alertDialog.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    alertDialog.show();
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Not Sufficient Coins");
                    alertDialog.setMessage("You need more Coins To Create new Board");
                    alertDialog.setIcon(R.drawable.card);
                    alertDialog.setPositiveButton("Play More",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fragmentManager = getActivity().getSupportFragmentManager();
                                    fragmentTransaction = fragmentManager.beginTransaction();
                                    Fragment_All fragment = new Fragment_All();
                                    fragmentTransaction.replace(R.id.fragment, fragment);
                                    fragmentTransaction.commit();
                                }
                            });

                    alertDialog.setNegativeButton("Share",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences myPrefs = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                                    SharedPreferences.Editor editor = myPrefs.edit();
                                    int Coins = myPrefs.getInt("Coins", -1);
                                    if (Coins != -1) {
                                        editor.putInt("Coins", Coins + 10);
                                        ((MainMenuActivity) getActivity()).updateCoins(Coins + 10);
                                        editor.apply();
                                    }
                                }
                            });

                    alertDialog.show();
                }
            }
        });

        Decks_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Get Ready");
                alertDialog.setMessage("This Board is " + categories.get(position).getCatName());
                alertDialog.setIcon(R.drawable.card);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent in = new Intent(getActivity(), QuickPlay.class);
                                in.putExtra("CatID", categories.get(position).getID());
                                in.putExtra("GameName", categories.get(position).getCatName());
                                startActivity(in);
                            }
                        });
                alertDialog.show();

            }
        });
    }
}
