package com.example.roomdatabaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabaseexample.DataBase.Entities.User;

public class EditUserActivity extends AppCompatActivity {

    private EditText editName, editLastName;
    private Button save;
    private Intent intent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //R
        editName = findViewById(R.id.editName);
        editLastName = findViewById(R.id.editLastName);
        save = findViewById(R.id.saveButton);

        //get parcelable
        intent = getIntent();
        user = intent.getParcelableExtra("user_parcelable");

        //get name and lastname in edittext
        editName.setText(user.getFirstName());
        editLastName.setText(user.getLastName());

        //save event
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.userDao().updateUser(editName.getText().toString(),
                        editLastName.getText().toString(),
                        user.getId());

                Toast.makeText(EditUserActivity.this, "Se ha actualizado el usuario.", Toast.LENGTH_SHORT).show();
                EditUserActivity.this.finish();

            }
        });

    }
}
