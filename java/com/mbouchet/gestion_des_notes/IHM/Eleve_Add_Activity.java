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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mbouchet.gestion_des_notes.R;

import java.util.ArrayList;
import java.util.List;

import DAO.ClasseDAO;
import DAO.EleveDAO;
import DAO.MatierDAO;
import METIER.Classe;
import METIER.Eleve;
import METIER.Matier;

public class Eleve_Add_Activity extends AppCompatActivity {

    private Button buttonRetour;
    private Button buttonAjouter;
    private EditText nomAdd;
    private EditText prenomAdd;
    private EditText dateNadd;
    private Spinner classeAdd;
    private EditText idUpdateDelete;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleve_add);

        objet();
        list();
        buttonRetour.setOnClickListener(retourListener);
        buttonAjouter.setOnClickListener(AddListener);

    }

    private void objet() {

        buttonAjouter = (Button) findViewById(R.id.buttonaddeleve);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);
        nomAdd = (EditText) findViewById(R.id.txtxboxnomAdd);
        prenomAdd = (EditText) findViewById(R.id.txtboxprenomAdd);
        dateNadd = (EditText) findViewById(R.id.txtboxDateNAdd);
        classeAdd = (Spinner) findViewById(R.id.txtboxclasseAdd);
        idUpdateDelete = (EditText) findViewById(R.id.txtboxidUpdateDelete);
    }

    private void list() {
        final List<Eleve> uneList;
        final List<Classe> classeList;

        final EleveDAO eleveDAO = new EleveDAO(this);
        final ClasseDAO classeDAO = new ClasseDAO(this);

        eleveDAO.open();
        classeDAO.open();
        uneList = eleveDAO.read();
        classeList = classeDAO.read();
        eleveDAO.close();
        classeDAO.open();

        //dropList.setOnItemClickListener(ListeClasseEleve);

        Classe_Adapter_Activity adapterSpinner = new Classe_Adapter_Activity(this, R.layout.activity_adapter_classe, classeList);
        // Specify the layout to use when the list of choices appears
        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        classeAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>=0 && position <classeList.size()){

                    List<Eleve> newLisEleve = new ArrayList<Eleve>();
                    for (Eleve unEleve : uneList){
                        if (unEleve.getLaClasse().getIdClasse() == classeList.get(position).getIdClasse()) {
                            newLisEleve.add(unEleve);
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Studen does not Exist!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        classeAdd.setAdapter(adapterSpinner);
    }

    private View.OnClickListener retourListener = new View.OnClickListener() {
        public void onClick(View v) {
            Retour();
        }
    };

    private void Retour() {

        AlertDialog.Builder alertRetour = new AlertDialog.Builder(this);
        //AlertDialog fenetre = new AlertDialog();
        alertRetour.setMessage("Voulez vous vraiment revenir en arrière ? ");
        alertRetour.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // retour la la page précédente.
                /*Intent intent;
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

    private View.OnClickListener AddListener = new View.OnClickListener() {
        public void onClick(View v) {
            add();
        }
    };

    public void add(){
        Eleve eleveAdd = null;
        final EleveDAO unEleve = new EleveDAO(this);
        final ClasseDAO uneClasse = new ClasseDAO(this);
        unEleve.open();
        uneClasse.open();

        String nomE = String.valueOf(nomAdd.getText());

        String prenomE = String.valueOf(prenomAdd.getText());

        String DateNE = String.valueOf(dateNadd.getText());

        // ce referai au update
        int classePosition = classeAdd.getSelectedItemPosition();

        Classe classeE = uneClasse.readPostion(classePosition);

        eleveAdd = new Eleve(nomE,prenomE,DateNE,classeE);

       unEleve.insert(eleveAdd);

        unEleve.close();
        uneClasse.close();
        Intent intent;
        intent = new Intent(this,Eleve_Activity.class);
        startActivity(intent);

       // finish();
    }

}
