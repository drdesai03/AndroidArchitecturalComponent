package com.inventyfy.architecture.ui.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.inventyfy.architecture.databinding.RowSearchItemBinding;

public class LastSearchAdapter extends RecyclerView.Adapter<LastSearchAdapter.SearchViewHolder> {

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RowSearchItemBinding rowSearchItemBinding = RowSearchItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchViewHolder(rowSearchItemBinding);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private RowSearchItemBinding searchItemBinding;

        public SearchViewHolder(RowSearchItemBinding searchItemBinding) {
            super(searchItemBinding.getRoot());
            this.searchItemBinding = searchItemBinding;
        }
    }
}
