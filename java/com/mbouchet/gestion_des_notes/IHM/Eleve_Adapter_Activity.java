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

import METIER.Classe;
import METIER.Eleve;



public class Eleve_Adapter_Activity extends ArrayAdapter<Eleve> {

    private int resourceLayout;
    private Context mContext;
    public Eleve_Adapter_Activity(Context context, int resource, List<Eleve> uneList) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_eleve,parent, false);
        }

        Eleve_Adapter_Activity.EleveViewHolder viewHolder = (Eleve_Adapter_Activity.EleveViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new Eleve_Adapter_Activity.EleveViewHolder();
            viewHolder.nomEleve = (TextView) convertView.findViewById(R.id.txtNomEleve);
            viewHolder.prenomEleve = (TextView) convertView.findViewById(R.id.txtPrenomEleve);
            viewHolder.dateNEleve = (TextView) convertView.findViewById(R.id.txtDateNEleve);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Eleve eleve = getItem(position);
        Log.v("matiere", eleve.getNomEleve()+ eleve.getPrenomEleve()+eleve.getDateNaissEleve());

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomEleve.setText(eleve.getNomEleve());
        viewHolder.prenomEleve.setText(eleve.getPrenomEleve());
        viewHolder.dateNEleve.setText(eleve.getDateNaissEleve());

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

    class EleveViewHolder {
        public TextView nomEleve;
        public TextView prenomEleve;
        public TextView dateNEleve;
    }
}

