package com.example.roomdatabaseexample;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.roomdatabaseexample.DataBase.Entities.AppDatabase;
import com.example.roomdatabaseexample.DataBase.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name, lastName;
    private Button enter, delete;
    private ListView data;
    private List<String> userInfo = new ArrayList<>();
    private ArrayAdapter<String> userArrayAdapter;
    private AppDatabase db;
    private static String userData;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R
        name = findViewById(R.id.etName);
        lastName = findViewById(R.id.etLastName);
        data = findViewById(R.id.data);
        enter = findViewById(R.id.enterButton);
        delete = findViewById(R.id.deleteButton);

        //Database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_database").allowMainThreadQueries().build();

        //Listview adapter
        getUsers();

        //enter users in the db
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUser();
                userInfo.clear();
                getUsers();

            }
        });

        //clean db
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllUsers();
            }
        });

    }

    private void addUser() {

        User user = new User(0, name.getText().toString(), lastName.getText().toString());
        db.userDao().insertAll(user);

        //clean edit text
        name.setText("");
        lastName.setText("");

    }

    private void deleteAllUsers() {

        db.userDao().deleteAll();
        userInfo.clear();
        userArrayAdapter.notifyDataSetChanged();

    }

    private void getUsers() {

        userList = db.userDao().getAll();

        for (User user : userList) {

            userData = user.getFirstName() + " . " + user.getLastName();
            userInfo.add(userData);

        }

        userArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, userInfo);

        data.setAdapter(userArrayAdapter);
    }

}
