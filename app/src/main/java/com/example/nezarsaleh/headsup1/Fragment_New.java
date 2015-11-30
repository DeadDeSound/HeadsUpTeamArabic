package com.example.nezarsaleh.headsup1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment_New extends Fragment {

    ImageView Actor,Animals,Emotion,Fairytale,Films,Music;
    EditText NameEdit,DataEdit;
    ListView DataList;
    Button DataSubmit,Done;
    ArrayList<String> Data = new ArrayList<>();
    DatabaseHelper MyDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_layout,container,false);

        MyDB = new DatabaseHelper(getActivity());

        Actor = (ImageView) view.findViewById(R.id.actor);
        Animals = (ImageView) view.findViewById(R.id.animals);
        Emotion = (ImageView) view.findViewById(R.id.emotion);
        Fairytale = (ImageView) view.findViewById(R.id.fairytale);
        Films = (ImageView) view.findViewById(R.id.films);
        Music = (ImageView) view.findViewById(R.id.music);

        NameEdit = (EditText) view.findViewById(R.id.editText);
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
                if (NameEdit.getText() != null && Data.size() !=0){
                    for (String item : Data){
                        Boolean result = MyDB.insertItems(item, NameEdit.getText().toString(), 0);
                        if (result)
                            Toast.makeText(getActivity(), "Data Added Successfully", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(), "Data Adding Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
