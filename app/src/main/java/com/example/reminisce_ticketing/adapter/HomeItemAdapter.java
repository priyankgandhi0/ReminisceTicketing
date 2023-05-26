package com.example.reminisce_ticketing.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.model.EventListModel;
import com.example.reminisce_ticketing.ui.homedetails.HomeDetailsActivity;
import com.example.reminisce_ticketing.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> implements Filterable {

    private List<EventListModel.Data> itemList;
    private List<EventListModel.Data> filteredList;
    Context context;
    public HomeItemAdapter(List<EventListModel.Data> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventListModel.Data item = itemList.get(position);
        holder.itemTitle.setText(item.name);
        holder.itemDescription.setText(Utils.GetDateOnRequireFormat(item.getEventStartDate(), Constants.DATE_YYYY_MM_DD_FORMAT,Constants.DATE_DD_MMMM_YYYY_FORMAT));
        holder.item_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeDetailsActivity.class);
                intent.putExtra("slug",item.slug);
                view.getContext().startActivity(intent);
            }
        });

//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.placeholder) // Placeholder image while loading
//                .error(R.drawable.error) // Error image if the load fails
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(context)
//                .load(holder.eventImage)
//                .apply(options)
//                .into(holder.eventImage);
    }

    @Override
    public int getItemCount() {
        if (filteredList!=null){
            return filteredList.size();
        }else {
            return itemList.size();
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchQuery = constraint.toString().toLowerCase();
                FilterResults results = new FilterResults();
                if (searchQuery.isEmpty()) {
                    // If the search query is empty, display the original data
                    results.values = itemList;
                    results.count = itemList.size();
                } else {
                    // Perform filtering based on the search query
                    List<EventListModel.Data> filteredItems = new ArrayList<>();

                    for (EventListModel.Data item : itemList) {
                        // Customize this condition according to your search logic
                        if (item.getName().toLowerCase().contains(searchQuery)) {
                            filteredItems.add(item);
                        }
                    }

                    results.values = filteredItems;
                    results.count = filteredItems.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<EventListModel.Data>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }
    public void search(String query) {
        getFilter().filter(query);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ConstraintLayout item_click;
        TextView itemDescription;
        ImageView eventImage;
        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.tvItemTitle);
            itemDescription = itemView.findViewById(R.id.tvItemDate);
            item_click = itemView.findViewById(R.id.itemClick);
            eventImage = itemView.findViewById(R.id.ivImage);

        }
    }
}

