package gr.seemslegit.trashbeast.Controllers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gr.seemslegit.trashbeast.Models.Village;
import gr.seemslegit.trashbeast.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Village> villagesDataSet;
    /**
     * Reference views for each dataItem
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public ViewHolder (TextView tv){
            super(tv);
            this.tv = tv;
        }
    }
    public RecyclerViewAdapter(List<Village> villages){
        villagesDataSet = villages;
    }

    /**
     * Create views based on the custom layout "results_bottom_sheet
     *
     */
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv  = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.results_dataset, parent,false);
        ViewHolder vh =new ViewHolder(tv);
        return vh;
    }

    /**
     * Replace contents of views
     *
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tv.setText(villagesDataSet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return villagesDataSet.size();
    }
}
