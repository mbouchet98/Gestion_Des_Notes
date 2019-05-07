package com.mbouchet.gestion_des_notes.IHM;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mbouchet.gestion_des_notes.R;

import java.util.List;

import METIER.Matier;

public class AdapterActivity extends ArrayAdapter<Matier> {
    private int resourceLayout;
    private Context mContext;
    public AdapterActivity(Context context, int resource, List<Matier> uneList) {
        super(context, resource, uneList);

        this.resourceLayout = resource;
        this.mContext = context;

    }
    //convertView est notre vue recyclée
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Android nous fournit un convertView null lorsqu'il nous demande de la créer
        //dans le cas contraire, cela veux dire qu'il nous fournit une vue recyclée
        if(convertView == null){
            //Nous récupérons notre row_tweet via un LayoutInflater,
            //qui va charger un layout xml dans un objet View
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter,parent, false);
        }

        MatiereViewHolder viewHolder = (MatiereViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MatiereViewHolder();
            viewHolder.nomMatiere = (TextView) convertView.findViewById(R.id.txtNomMatiere);
            viewHolder.coefMatiere = (TextView) convertView.findViewById(R.id.txtCoefMatiere);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Matier matiere = getItem(position);
        Log.v("matiere", matiere.getNomMetier()+ matiere.getCoefMetier());

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomMatiere.setText(matiere.getNomMetier());
        viewHolder.coefMatiere.setText(Float.toString(matiere.getCoefMetier()));

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

class MatiereViewHolder {
    public TextView nomMatiere;
    public TextView coefMatiere;
}
}
