package com.example.roomdatabaseexample;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.roomdatabaseexample.Adapters.UserAdapter;
import com.example.roomdatabaseexample.DataBase.Entities.AppDatabase;
import com.example.roomdatabaseexample.DataBase.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static RecyclerView recyclerView;
    public static UserAdapter userAdapter;
    private FloatingActionButton fab;
    public static AppDatabase db;
    private static List<User> thisUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database build
        db = Room.databaseBuilder(this, AppDatabase.class, "my_database").allowMainThreadQueries().build();

        //R
        recyclerView = findViewById(R.id.usersRecyclerView);
        fab = findViewById(R.id.add);

        //rv
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        //get users
        getAllUsers();

        //fab event
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);

            }
        });

    }

    public static void getAllUsers() {

        //set database data in array for recyclerview
        thisUsers = db.userDao().getAll();

        //setting data in rv
        userAdapter = new UserAdapter(thisUsers);
        recyclerView.setAdapter(userAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();

        getAllUsers();

    }
}
