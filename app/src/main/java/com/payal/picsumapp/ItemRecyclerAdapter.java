package com.payal.picsumapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.myViewHolder> {
    List<OneItem> itemList;
    Context context;
    @NonNull
    @Override
    public ItemRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_of_list, parent, false);

        return new ItemRecyclerAdapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRecyclerAdapter.myViewHolder holder, int position) {
        final OneItem item = itemList.get(position);
//        holder.imageView.setImageResource(item.getImage());
        holder.title_view.setText(item.getTitle());
        Glide.with(context).load(item.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    ItemRecyclerAdapter(Context context,List<OneItem> oneItemList){
        itemList = oneItemList;
        this.context = context;
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title_view;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title_view=itemView.findViewById(R.id.title);

        }
    }

}
