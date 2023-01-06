package com.example.jeemap.Main.map;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jeemap.R;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{
    ArrayList<Person> items = new ArrayList<>();
    String ulatitude;
    String ulongitude;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChangeActivity.class);
                intent.putExtra("위도", item.getlatitude());
                intent.putExtra("경도", item.getlongitude());
                intent.putExtra("u위도", ulatitude);
                intent.putExtra("u경도", ulongitude);
                view.getContext().startActivity(intent);
            }
        });
    }
    public void userPoint(String ulatitude, String ulongitude){
        this.ulatitude = ulatitude;
        this.ulongitude = ulongitude;
    }
    public void addItem(Person item){
        items.add(item);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void setItem(Person item){
            textView = itemView.findViewById(R.id.personitem1);
            textView.setText(item.getMainText());
        }
    }
}

