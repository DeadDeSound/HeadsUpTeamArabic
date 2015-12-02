package com.example.nezarsaleh.headsup1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment_New extends Fragment {

//    ImageView Actor,Animals,Emotion,Fairytale,Films,Music;
    EditText DataEdit;
    TextView NameText;
    ListView DataList;
    Button DataSubmit,Done;
    ArrayList<String> Data = new ArrayList<>();
    DatabaseHelper MyDB;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public void setBoardName(String boardName) {
        BoardName = boardName;
    }

    String BoardName;

    @Override
    public void onStart() {
        super.onStart();
        ((MainMenuActivity) getActivity()).setCustomFlag(1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_layout,container,false);

        MyDB = new DatabaseHelper(getActivity());

//        Actor = (ImageView) view.findViewById(R.id.actor);
//        Animals = (ImageView) view.findViewById(R.id.animals);
//        Emotion = (ImageView) view.findViewById(R.id.emotion);
//        Fairytale = (ImageView) view.findViewById(R.id.fairytale);
//        Films = (ImageView) view.findViewById(R.id.films);
//        Music = (ImageView) view.findViewById(R.id.music);
        NameText = (TextView) view.findViewById(R.id.textView);
        if (BoardName != null){
            NameText.setText("New Board : " + BoardName);
        }
//        NameEdit = (EditText) view.findViewById(R.id.editText);
        DataEdit = (EditText) view.findViewById(R.id.editText2);
        DataList = (ListView) view.findViewById(R.id.list_view);

        DataSubmit = (Button) view.findViewById(R.id.submitData);
        Done = (Button) view.findViewById(R.id.done);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Data);
        DataList.setAdapter(arrayAdapter);
        DataSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = DataEdit.getText().toString();
                Data.add(item);
                DataList.requestLayout();
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BoardName != null && Data.size() !=0){
                    int CatID = (int) MyDB.insertCat(BoardName,R.drawable.card,0,1);
                    if (CatID != -1) {
                        for (String item : Data) {
                            Boolean result = MyDB.insertItems(item, BoardName, CatID);
                            if (result) {
                                Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_Custom fragment = new Fragment_Custom();
                                fragmentTransaction.replace(R.id.fragment, fragment);
                                fragmentTransaction.commit();
                                SharedPreferences myPrefs = getActivity().getApplicationContext().getSharedPreferences("myPrefs", 0);
                                SharedPreferences.Editor editor = myPrefs.edit();
                                int Coins = myPrefs.getInt("Coins",-1);
                                if (Coins != 0) {
                                    editor.putInt("Coins", Coins - 10);
                                    ((MainMenuActivity) getActivity()).updateCoins(Coins - 10);
                                    editor.apply();
                                }
                            }else {
                                Toast.makeText(getActivity(), "Adding Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else
                        Toast.makeText(getActivity(), "Adding Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
