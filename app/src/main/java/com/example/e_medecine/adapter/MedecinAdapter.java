package com.example.e_medecine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
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
import com.example.e_medecine.model.Medecin;

import java.util.ArrayList;

public class MedecinAdapter extends RecyclerView.Adapter<MedecinAdapter.HolderMedecin> implements Filterable {
    private Context context;
    private ArrayList<Medecin> medecinList;
    private ArrayList<Medecin> medecinListSearch;
    private MedecinAdapter.OnMedecinListener mOnMedecinListener;
    public MedecinAdapter(Context context, ArrayList<Medecin> arrayList, OnMedecinListener onMedecinListener ) {
        this.context = context;
        this.medecinList = arrayList;
        medecinListSearch=new ArrayList<>(arrayList);
        this.mOnMedecinListener=onMedecinListener;
    }

    @NonNull
    @Override
    public MedecinAdapter.HolderMedecin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.medecin_item,parent,false);
        return new MedecinAdapter.HolderMedecin(view,mOnMedecinListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MedecinAdapter.HolderMedecin holder, int position) {
        Medecin medecin=medecinList.get(position);

        holder.medecinNom.setText("Dr."+medecin.getNom_user());
        holder.medecinPrenom.setText(medecin.getPrenom_user());
        holder.medecinSpecialite.setText(medecin.getLabel());
        holder.medecinFrais.setText(String.valueOf(medecin.getFrais())+" DH");
        holder.medecinExperience.setText(String.valueOf(medecin.getExperience())+" ans");
        byte[] byteArray =  Base64.decode(String.valueOf(medecin.getImage_user()), Base64.DEFAULT) ;
        Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        holder.medecinImage.setImageBitmap(bmp1);
    }

    @Override
    public int getItemCount() {
        return medecinList.size();
    }

    ////////////////////////////////////////////////////////////////////////ViewHolder
    public class HolderMedecin extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView medecinImage;
        TextView medecinNom, medecinPrenom, medecinSpecialite, medecinFrais,medecinExperience;

        MedecinAdapter.OnMedecinListener on_medecin_listener;

        public HolderMedecin(@NonNull View itemView, MedecinAdapter.OnMedecinListener on_medecin_listener) {
            super(itemView);
            medecinImage=itemView.findViewById(R.id.medecinImage);
            medecinNom=itemView.findViewById(R.id.medecinNom);
            medecinPrenom=itemView.findViewById(R.id.medecinPrenom);
            medecinSpecialite=itemView.findViewById(R.id.medecinSpecialite);
            medecinFrais=itemView.findViewById(R.id.frais);
            medecinExperience=itemView.findViewById(R.id.experience);
            this.on_medecin_listener=on_medecin_listener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            on_medecin_listener.onMedecinClick(getAdapterPosition());
        }

    }
    /////////////////////////////////////////////////////////////Filtred search
    @Override
    public Filter getFilter() {
        return medecinFilter;
    }
    private Filter medecinFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Medecin> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredList.addAll(medecinListSearch);
            }
            else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                for(Medecin item: medecinListSearch){
                    if(item.getNom_user().toLowerCase().startsWith(filterPattern) || item.getPrenom_user().toLowerCase().startsWith(filterPattern)){
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
            medecinList.clear();
            medecinList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };


    public interface OnMedecinListener{
        void onMedecinClick(int position);
    }

}
