package com.example.nezarsaleh.headsup1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
    ListView OldDataList,NewDataList;
    Button DataSubmit,Done;
    ArrayList<String> Data = new ArrayList<>();
    ArrayList<String> Data2 = new ArrayList<>();
    DatabaseHelper MyDB;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    String BoardName;
    int CatID;
    CategoryDataModel cat;
    public Fragment_New(CategoryDataModel cat){
        this.cat = cat;
    }

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
        DataEdit = (EditText) view.findViewById(R.id.editText2);
        OldDataList = (ListView) view.findViewById(R.id.list_view);
        NewDataList = (ListView) view.findViewById(R.id.listView2);

        DataSubmit = (Button) view.findViewById(R.id.submitData);
        Done = (Button) view.findViewById(R.id.done);
//        Actor = (ImageView) view.findViewById(R.id.actor);
//        Animals = (ImageView) view.findViewById(R.id.animals);
//        Emotion = (ImageView) view.findViewById(R.id.emotion);
//        Fairytale = (ImageView) view.findViewById(R.id.fairytale);
//        Films = (ImageView) view.findViewById(R.id.films);
//        Music = (ImageView) view.findViewById(R.id.music);
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Data);
        NewDataList.setAdapter(arrayAdapter2);
        NameText = (TextView) view.findViewById(R.id.textView);
        BoardName = cat.getCatName();
        CatID = cat.getID();
        if (BoardName != null){
            NameText.setText("New Board : " + BoardName);
        }
        if (CatID != 0) {
            Cursor res = MyDB.getItemsByCat(CatID);
            if (res.getCount() != -1) {
                while (res.moveToNext()) {
                    Data.add(res.getString(3));
                }
            }
        }
//        NameEdit = (EditText) view.findViewById(R.id.editText);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Data2);
        OldDataList.setAdapter(arrayAdapter);
        DataSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = DataEdit.getText().toString();
                if (!DataEdit.getText().toString().equals("")) {
                    Data2.add(item);
                    arrayAdapter.notifyDataSetChanged();
                }
                DataEdit.setText("");
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BoardName != null && Data2.size() !=0){
                    if (CatID != -1) {
                        for (String item : Data2) {
                            Boolean result = MyDB.insertItems(item, BoardName, CatID);
                            if (result) {
                                ((MainMenuActivity) getActivity()).setCustomFlag(0);
                                Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                                fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment_Custom fragment = new Fragment_Custom();
                                fragmentTransaction.replace(R.id.fragment, fragment);
                                fragmentTransaction.commit();
                            }else {
                                Toast.makeText(getActivity(), "Adding Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else
                        Toast.makeText(getActivity(), "Adding Failed", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("ARE YOU SURE?");
                    alertDialog.setMessage("you have made no changes !!");
                    alertDialog.setIcon(R.drawable.card);
                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    fragmentManager = getActivity().getSupportFragmentManager();
                                    fragmentTransaction = fragmentManager.beginTransaction();
                                    Fragment_Custom fragment = new Fragment_Custom();
                                    fragmentTransaction.replace(R.id.fragment, fragment);
                                    fragmentTransaction.commit();
                                }
                            });
                    alertDialog.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }
}
