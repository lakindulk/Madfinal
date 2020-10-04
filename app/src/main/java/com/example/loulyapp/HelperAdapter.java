package com.example.loulyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {

    List<User> userList;

    public HelperAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;

        User user = userList.get(position);

        viewHolderClass.name.setText(user.getName());
        viewHolderClass.id.setText(user.getID());
        //viewHolderClass.age.setText(user.getAge().toString());
        //viewHolderClass.gender.setText(user.getGender());
        //viewHolderClass.area.setText(user.getArea());
        //viewHolderClass.pw.setText(user.getPW());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView name;
        TextView id;
        //TextView age;
        //TextView gender;
        //TextView area;
        //TextView pw;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.forName);
            id = itemView.findViewById(R.id.forID);
            //age = itemView.findViewById(R.id.forAge);
            //gender = itemView.findViewById(R.id.forGender);
            //area = itemView.findViewById(R.id.forArea);
            //pw = itemView.findViewById(R.id.forPW);
        }
    }

}
