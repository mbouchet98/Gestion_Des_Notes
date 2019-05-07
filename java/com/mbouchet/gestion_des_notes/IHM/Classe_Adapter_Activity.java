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
import METIER.Matier;


public class Classe_Adapter_Activity<S> extends ArrayAdapter<Classe> {

    private int resourceLayout;
    private Context mContext;

    public Classe_Adapter_Activity(Context context, int resource, List<Classe> uneList) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_classe,parent, false);
        }

        Classe_Adapter_Activity.ClasseViewHolder viewHolder = (Classe_Adapter_Activity.ClasseViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new Classe_Adapter_Activity.ClasseViewHolder();
            viewHolder.libClasse = (TextView) convertView.findViewById(R.id.idLibClasse);
            viewHolder.nbClasse = (TextView) convertView.findViewById(R.id.idNbClasse);
            convertView.setTag(viewHolder);
        }


        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Classe classe = getItem(position);
        Log.v("matiereClassAdapter", classe.getLibClasse()+ classe.getNbClasse());

        //il ne reste plus qu'à remplir notre vue
        viewHolder.libClasse.setText(classe.getLibClasse());
        viewHolder.nbClasse.setText(Integer.toString(classe.getNbClasse()));

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

    class ClasseViewHolder {
        public TextView libClasse;
        public TextView nbClasse;
    }
}
