package com.example.e_medecine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_medecine.R;
import com.example.e_medecine.model.Specialite;

import java.util.ArrayList;

public class SpecialitesAdapter extends RecyclerView.Adapter<SpecialitesAdapter.HolderSpecialite> implements Filterable {

    private Context context;
    private ArrayList<Specialite> specialitesList;
    private ArrayList<Specialite> specialitesListSearch;
    private OnSpecialiteListener mOnSpecialiteListener;
    public SpecialitesAdapter(Context context, ArrayList<Specialite> arrayList, OnSpecialiteListener onSpecialiteListener ) {
        this.context = context;
        this.specialitesList = arrayList;
        specialitesListSearch=new ArrayList<>(arrayList);
        this.mOnSpecialiteListener=onSpecialiteListener;
    }

    @NonNull
    @Override
    public HolderSpecialite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.specialite_item,parent,false);
        return new HolderSpecialite(view,mOnSpecialiteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSpecialite holder, int position) {
        Specialite specialite=specialitesList.get(position);
        String label=specialite.getLabe();
        holder.specialiteLabel.setText(label);
        holder.specialiteImage.setImageResource(specialite.getImageSpecialite());
    }

    @Override
    public int getItemCount() {
        return specialitesList.size();
    }

    ////////////////////////////////////////////////////////////////////////ViewHolder
    public class HolderSpecialite extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView specialiteImage;
        TextView specialiteLabel;
        OnSpecialiteListener on_specialite_listener;

        public HolderSpecialite(@NonNull View itemView, OnSpecialiteListener on_specialite_listener) {
            super(itemView);
            specialiteImage=itemView.findViewById(R.id.specialiteImage);
            specialiteLabel=itemView.findViewById(R.id.specialiteLabel);
            this.on_specialite_listener=on_specialite_listener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            on_specialite_listener.onSpecialiteClick(getAdapterPosition());
        }
    }
    /////////////////////////////////////////////////////////////Filtred search
    @Override
    public Filter getFilter() {
        return specialiteFilter;
    }
    private Filter specialiteFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Specialite> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredList.addAll(specialitesListSearch);
            }
            else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                for(Specialite item: specialitesListSearch){
                    if(item.getLabe().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            specialitesList.clear();
            specialitesList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public interface OnSpecialiteListener{
        void onSpecialiteClick(int position);
    }
}