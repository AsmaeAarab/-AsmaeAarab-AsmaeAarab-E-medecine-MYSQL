package com.example.e_medecine.Docteurs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_medecine.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RendezvousAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Rendezvous> rdvItem;

    public RendezvousAdapter(Context context, int layout, ArrayList<Rendezvous> rdvItem) {
        this.context = context;
        this.layout = layout;
        this.rdvItem = rdvItem;
    }

    @Override
    public int getCount() {
        return rdvItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rdvItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView txtnom,txtprenom,txttitrerdv,txtdate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();
        if (row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.imageView = (ImageView) row.findViewById(R.id.ImagePatient);
            holder.txtnom = (TextView) row.findViewById(R.id.TextViewNom);
            holder.txtprenom = (TextView) row.findViewById(R.id.TextViewPrenom);
            holder.txttitrerdv = (TextView) row.findViewById(R.id.TextViewTitreRDV);
            holder.txtdate = (TextView) row.findViewById(R.id.TextViewDate);
            row.setTag(holder);
        }else {
            holder = (RendezvousAdapter.ViewHolder) row.getTag();
        }
        Rendezvous rendezvous = rdvItem.get(position);
        /*byte[] Rdvimg = rendezvous.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Rdvimg,0,Rdvimg.length);*/
        String photo = rendezvous.getImagenew();
        Bitmap bitmap = StringToBitMap(photo);
        holder.imageView.setImageBitmap(bitmap);
        holder.txtnom.setText(rendezvous.getNom());
        holder.txtprenom.setText(rendezvous.getPrenom());
        holder.txttitrerdv.setText(rendezvous.getTitreRdv());
        //DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        //String date = dateFormat.format(rendezvous.getDate());
        holder.txtdate.setText(rendezvous.getDate());
        return row;
    }
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
