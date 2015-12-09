package com.example.nezarsaleh.headsup1;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_All extends Fragment {

    GridView Decks_Grid;
    ImageAdapter imageAdapter;
    ArrayList<CategoryDataModel> categories = new ArrayList<>();
    DatabaseHelper databaseHelper;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String[] Movies = {""};
    Button New;
    ShareDialog shareDialog;
    CallbackManager callbackManager;
//    LoginManager loginManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                SharedPreferences myPrefs = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                int Coins = myPrefs.getInt("Coins", -1);
                if (Coins != -1) {
                    editor.putInt("Coins", Coins + 10);
                    ((MainMenuActivity) getActivity()).updateCoins(Coins + 10);
                    editor.apply();
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Why Have You Canceled ?", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity(), "Some Error Happened", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all_layout, container, false);
        Decks_Grid = (GridView) view.findViewById(R.id.dick_grid);
        New = (Button) view.findViewById(R.id.new_Deck);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        categories.clear();
        imageAdapter = new ImageAdapter(getActivity(), categories,0);
        Decks_Grid.setAdapter(imageAdapter);

        Cursor res = databaseHelper.getAllCat();
        if (res.getCount() != 0){
            while (res.moveToNext()){
                categories.add(new CategoryDataModel(res.getInt(0), res.getInt(2), res.getString(1), res.getInt(3),res.getInt(4)));
            }
        }else {

            categories.add(new CategoryDataModel(databaseHelper.insertCat("أفلام", R.drawable.films, 0, 0), R.drawable.films, "أفلام"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("مسرحيات",R.drawable.emotion, 0, 0),R.drawable.emotion, "مسرحيات"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("حيوانات",R.drawable.animals, 0, 0),R.drawable.animals, "حيوانات"));
//            categories.add(new CategoryDataModel(databaseHelper.insertCat("Fairy Tales",R.drawable.fairytale, 0, 0),R.drawable.fairytale, "Fairy Tales"));
            categories.add(new CategoryDataModel(databaseHelper.insertCat("تمثيل",R.drawable.actor, 0, 0),R.drawable.actor, "تمثيل"));

        }

        New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                int Coins = pref.getInt("Coins", -1);
                if (Coins >= 10) {
                    SharedPreferences myPrefs = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                    SharedPreferences.Editor editor = myPrefs.edit();
                    editor.putInt("Coins", Coins - 10);
                    ((MainMenuActivity) getActivity()).updateCoins(Coins - 10);
                    editor.apply();

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
                                        int CatID = (int) databaseHelper.insertCat(Name,R.drawable.card,0,1);
                                        categories.add(new CategoryDataModel(CatID, R.drawable.card, Name,0,1));
                                        imageAdapter.notifyDataSetChanged();
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
                                    share();
                                }
                            });

                    alertDialog.show();
                }
            }
        });


        Decks_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                if (!databaseHelper.getItemsByCat(categories.get(position).getID()).moveToNext()) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Hold On !!");
                    alertDialog.setMessage("This Board is Empty ");
                    alertDialog.setIcon(R.drawable.card);
                    alertDialog.setPositiveButton("Add Cards",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fragmentManager = getActivity().getSupportFragmentManager();
                                    fragmentTransaction = fragmentManager.beginTransaction();
                                    Fragment_New fragment = new Fragment_New(categories.get(position));
                                    fragmentTransaction.replace(R.id.fragment, fragment);
                                    fragmentTransaction.commit();
                                }
                            });
                    alertDialog.show();
                } else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Get Ready");
                    alertDialog.setMessage("This Board is " + categories.get(position).getCatName());
                    alertDialog.setIcon(R.drawable.card);
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Write your code here to execute after dialog
                                    SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                                    int Coins = pref.getInt("Coins", -1);
                                    if (Coins >= 10) {
                                        SharedPreferences myPrefs = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                                        SharedPreferences.Editor editor = myPrefs.edit();
                                        editor.putInt("Coins", Coins - 10);
                                        ((MainMenuActivity) getActivity()).updateCoins(Coins - 10);
                                        editor.apply();

                                        Intent in = new Intent(getActivity(), QuickPlay.class);
                                        in.putExtra("CatID", categories.get(position).getID());
                                        in.putExtra("GameName", categories.get(position).getCatName());
                                        startActivity(in);
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
                                                        share();
                                                    }
                                                });

                                        alertDialog.show();
                                    }
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }

    private void share(){
//        ShareLinkContent content = new ShareLinkContent.Builder()
//                .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                .build();
//        ShareDialog.show(getActivity(), content);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hello Facebook")
                    .setContentDescription(
                            "The 'Hello Facebook' sample  showcases simple Facebook integration")
                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();

            shareDialog.show(linkContent);
        }
    }
}
