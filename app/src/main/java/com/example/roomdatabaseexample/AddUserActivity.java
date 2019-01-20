package com.example.roomdatabaseexample;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.roomdatabaseexample.DataBase.Entities.AppDatabase;
import com.example.roomdatabaseexample.DataBase.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    private EditText name, lastName;
    private Button enter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //R
        name = findViewById(R.id.etName);
        lastName = findViewById(R.id.etLastName);
        enter = findViewById(R.id.enterButton);

        //Database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_database").allowMainThreadQueries().build();

        //enter users in the db
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                Toast.makeText(AddUserActivity.this, "Se ha agregado el usuario.", Toast.LENGTH_SHORT).show();
                AddUserActivity.this.finish();

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

}
