package com.inventyfy.architecture.ui.home.search.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inventyfy.architecture.database.table.ResultTable;
import com.inventyfy.architecture.databinding.RowResultItemBinding;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<ResultTable> resultDetails;

    public ResultAdapter(final List<ResultTable> resultDetails) {
        this.resultDetails = resultDetails;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowResultItemBinding resultItemBinding = RowResultItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ResultViewHolder(resultItemBinding);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {
        holder.resultItemBinding.setResultDetails(resultDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return resultDetails != null ? resultDetails.size() : 0;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private RowResultItemBinding resultItemBinding;

        public ResultViewHolder(RowResultItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.resultItemBinding = itemBinding;
        }
    }
}
