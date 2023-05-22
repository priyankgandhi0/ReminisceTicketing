package com.example.reminisce_ticketing.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.model.HomeItemModel;
import com.example.reminisce_ticketing.ui.homedetails.HomeDetailsActivity;

import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {

    private List<HomeItemModel> itemList;

    public HomeItemAdapter(List<HomeItemModel> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeItemModel item = itemList.get(position);
        holder.itemTitle.setText(item.getTitle());
        holder.itemDescription.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        LinearLayout item_click;
        TextView itemDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDate);
            item_click = itemView.findViewById(R.id.item_click);
            item_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), HomeDetailsActivity.class);
                    view.getContext().startActivity(intent);

                }
            });
        }
    }
}

