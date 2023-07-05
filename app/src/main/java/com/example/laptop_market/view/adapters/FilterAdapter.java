package com.example.laptop_market.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laptop_market.utils.elses.PreferenceManager;
import com.example.laptop_market.utils.tables.Constants;
import com.example.laptop_market.utils.tables.SearchFilterPost;
import com.example.laptop_market.view.activities.FilterActivity;
import com.example.laptop_market.R;
import com.example.laptop_market.model.filter.Filter;
import com.example.laptop_market.view.adapters.PostSearchResult.PostSearchResult;
import com.example.laptop_market.view.fragments.HomeBaseFragment;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {
    private List<Filter> listFilter;
    private SearchFilterPost searchFilterPost;
    private PreferenceManager preferenceManager;
    private Context context;
    public FilterAdapter(List<Filter> listFilter, Context context){
        this.listFilter = listFilter;
        this.context = context;
        preferenceManager = new PreferenceManager(context);
    }
    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter,parent,false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {
        Filter filter = listFilter.get(position);
        if(filter==null) {
            return;
        }
        holder.btnFilter.setText(filter.getName());
        holder.btnFilter.setTag(filter.getName());
    }

    @Override
    public int getItemCount() {
        if(listFilter!=null){
            return listFilter.size();
        }
        return 0;
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder{
        private Button btnFilter;
        public FilterViewHolder(@NonNull View itemView){
            super(itemView);
           btnFilter = itemView.findViewById(R.id.btnFilter);

           btnFilter.setOnClickListener(v -> {
               if(preferenceManager.getSerializable(Constants.KEY_FILTER_SEARCH) != null)
               {
                   searchFilterPost = (SearchFilterPost) preferenceManager.getSerializable(Constants.KEY_FILTER_SEARCH);
               }
               Context context = btnFilter.getContext();
               Intent intent = new Intent(context,FilterActivity.class);
               intent.putExtra("filter",btnFilter.getTag().toString());
               intent.putExtra(SearchFilterPost.SEARCH_NAME, searchFilterPost);
               context.startActivity(intent);

           });
        }
    }
}

