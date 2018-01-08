package com.inventyfy.architecture.ui.home.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.inventyfy.architecture.database.table.SearchTable;
import com.inventyfy.architecture.databinding.RowSearchItemBinding;

import java.util.List;

public class LastSearchAdapter extends RecyclerView.Adapter<LastSearchAdapter.SearchViewHolder> {

    private List<SearchTable> searchList;

    public LastSearchAdapter(List<SearchTable> data) {
        this.searchList = data;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RowSearchItemBinding rowSearchItemBinding = RowSearchItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchViewHolder(rowSearchItemBinding);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.searchItemBinding.txtLabel.setText(searchList.get(position).getSearchText());
    }

    @Override
    public int getItemCount() {
        return searchList != null ? searchList.size() : 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private RowSearchItemBinding searchItemBinding;

        public SearchViewHolder(RowSearchItemBinding searchItemBinding) {
            super(searchItemBinding.getRoot());
            this.searchItemBinding = searchItemBinding;
        }
    }
}
