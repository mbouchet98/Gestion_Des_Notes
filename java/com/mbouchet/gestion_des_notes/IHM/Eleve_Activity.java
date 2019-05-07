package com.mbouchet.gestion_des_notes.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mbouchet.gestion_des_notes.R;

import java.util.ArrayList;
import java.util.List;

import DAO.ClasseDAO;
import DAO.EleveDAO;
import METIER.Classe;
import METIER.Eleve;

public class Eleve_Activity extends AppCompatActivity {

    private Button btnRetourEleve;
    private ListView listEleve;
    private FloatingActionButton btnAddEleve;
    private Spinner dropList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleve);


        Object();


        List();

    }

    private  void List(){
        btnRetourEleve.setOnClickListener(retourListener);

        final List<Eleve> uneList;
        final List<Classe> classeList;
        final List<Eleve> newLisEleve = new ArrayList<Eleve>();
        final int[] positionClasse = new int[1];

        final EleveDAO eleveDAO = new EleveDAO(this);
        final ClasseDAO classeDAO = new ClasseDAO(this);

        eleveDAO.open();
        classeDAO.open();
        uneList = eleveDAO.read();
        classeList = classeDAO.read();
        eleveDAO.close();
        classeDAO.open();

        //dropList.setOnItemClickListener(ListeClasseEleve);

        Classe_Adapter_Activity adapterSpinner = new Classe_Adapter_Activity(this,R.layout.activity_adapter_classe,classeList);
        // Specify the layout to use when the list of choices appears
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        dropList.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               //
                positionClasse[0] = position;
                if (position>=0 && position <classeList.size()){


                    for (Eleve unEleve : uneList){
                        if (unEleve.getLaClasse().getIdClasse() == classeList.get(position).getIdClasse()) {
                            newLisEleve.add(unEleve);
                        }
                    }
                    Eleve_Adapter_Activity adapter = new Eleve_Adapter_Activity(Eleve_Activity.this, R.layout.activity_eleve, newLisEleve);
                    listEleve.setAdapter(adapter);
                }
                else {
                    Toast.makeText(Eleve_Activity.this,"Studen does not Exist!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        dropList.setAdapter(adapterSpinner);


        Eleve_Adapter_Activity adapter = new Eleve_Adapter_Activity(this, R.layout.activity_eleve, uneList);
        listEleve.setAdapter(adapter);

        // quand on clique dessus.
        listEleve.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                Eleve lEleve;
                final ClasseDAO uneClasse = new ClasseDAO(Eleve_Activity.this);
                uneClasse.open();
                Log.v("idClasseList",Integer.toString(classeList.get(position).getIdClasse()));
                Log.v("positionClasseList",Integer.toString(position));
                Classe classeE = classeList.get(positionClasse[0]);
                eleveDAO.open();
                lEleve = (Eleve) eleveDAO.readByClasse(classeE.getIdClasse(),position);
                eleveDAO.close();

                uneClasse.close();




                intent = new Intent(getApplicationContext(), Eleve_Update_delete_Activity.class);
                intent.putExtra("paramNom", lEleve.getNomEleve());
                intent.putExtra("paramPrenom",lEleve.getPrenomEleve());
                intent.putExtra("paramDate",lEleve.getDateNaissEleve());
                intent.putExtra("paramClasse",lEleve.getLaClasse().getIdClasse());
                intent.putExtra("paramId", String.valueOf(lEleve.getIdEleve()));
                startActivity(intent);
               // finish();
            }

        });

        btnAddEleve.setOnClickListener(AddListener);
    }


    private void Object(){
        btnRetourEleve = (Button)findViewById(R.id.btnEleveRetour);
        listEleve = (ListView)findViewById(R.id.idListeEleve);
        btnAddEleve = (FloatingActionButton)findViewById(R.id.btnAjouterEleve);
        dropList = (Spinner)findViewById(R.id.droptxtEleve);
    }

    private View.OnClickListener retourListener = new View.OnClickListener(){
        public void onClick(View v){
            Retour();
        }
    };

    private void Retour(){

        AlertDialog.Builder alertRetour = new AlertDialog.Builder(this);
        //AlertDialog fenetre = new AlertDialog();
        alertRetour.setMessage("Voulez vous vraiment revenir en arrière ? ");
        alertRetour.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // retour la la page précédente.
               /* Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);*/
                finish();
            }
        });
        alertRetour.setNegativeButton("non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog fenetre = alertRetour.create();
        fenetre.show();

    }

    private View.OnClickListener AddListener = new View.OnClickListener(){
        public void onClick(View v){
            add();
        }
    };

    private void add(){
        Intent intent;
        intent = new Intent(this, Eleve_Add_Activity.class);
        startActivity(intent);
        finish();
    }
}
