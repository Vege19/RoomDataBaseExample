package com.example.roomdatabaseexample.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabaseexample.DataBase.Entities.AppDatabase;
import com.example.roomdatabaseexample.DataBase.Entities.User;
import com.example.roomdatabaseexample.EditUserActivity;
import com.example.roomdatabaseexample.MainActivity;
import com.example.roomdatabaseexample.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList) {
        this.userList = userList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final User user = userList.get(i);

        viewHolder.userId.setText("# " + String.valueOf(user.getId()));
        viewHolder.userName.setText(user.getFirstName());
        viewHolder.userLastName.setText(user.getLastName());

        viewHolder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.userDao().delete(user);
                Toast.makeText(context, user.getFirstName() + " ha sido eliminado.", Toast.LENGTH_SHORT).show();
                MainActivity.getAllUsers();

            }
        });

        viewHolder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUserActivity.class);
                intent.putExtra("user_parcelable", user);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName, userId, userLastName;
        private ImageView deleteItem, editItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userId = itemView.findViewById(R.id.userId);
            userLastName = itemView.findViewById(R.id.userLastName);
            deleteItem = itemView.findViewById(R.id.deleteItem);
            editItem = itemView.findViewById(R.id.editItem);

        }

    }


}
