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
import METIER.Classe;
import METIER.Eleve;

import static java.lang.Integer.parseInt;

public class Eleve_Update_delete_Activity extends AppCompatActivity {

    private Button buttonSupprimer;
    private Button buttonModifier;
    private Button buttonRetour;
    private EditText nomUpdateDelete;
    private EditText prenomUpdateDelete;
    private EditText dateNUpdateDelete;
    private Spinner classeUpdateDelete;
    private EditText idUpdateDelete;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleve_update_delete);

        objet();

        buttonRetour.setOnClickListener(retourListener);

        String paramNom = this.getIntent().getStringExtra("paramNom");
        String paramPrenom = this.getIntent().getStringExtra("paramPrenom");
        String paramDate = this.getIntent().getStringExtra("paramDate");
        String paramClasse = this.getIntent().getStringExtra("paramClasse");
        String paramId = this.getIntent().getStringExtra("paramId");

        nomUpdateDelete.setText(paramNom);
        prenomUpdateDelete.setText(paramPrenom);
        dateNUpdateDelete.setText(paramDate);
        classeUpdateDelete.setPrompt(paramClasse);
        idUpdateDelete.setText(paramId);

        list();

        buttonModifier.setOnClickListener(ModifListener);

    }

    private void objet() {
        buttonSupprimer = (Button) findViewById(R.id.buttonSupprimer);
        buttonModifier = (Button) findViewById(R.id.buttonModifier);
        buttonRetour = (Button) findViewById(R.id.buttonRetour);
        nomUpdateDelete = (EditText) findViewById(R.id.txtxboxnomUpdateDelete);
        prenomUpdateDelete = (EditText) findViewById(R.id.txtboxprenomupdateDelete);
        dateNUpdateDelete = (EditText) findViewById(R.id.txtboxDateNUpdatedelete);
        classeUpdateDelete = (Spinner) findViewById(R.id.txtboxclasseupdatedelete);
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

        classeUpdateDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>=0 && position <classeList.size()){

                    List<Eleve> newLisEleve = new ArrayList<Eleve>();
                    for (Eleve unEleve : uneList){
                        if (unEleve.getLaClasse().getIdClasse() == classeList.get(position).getIdClasse()) {
                            newLisEleve.add(unEleve);
                        }
                    }
                    /*Eleve_Adapter_Activity adapter = new Eleve_Adapter_Activity(Eleve_Activity.this, R.layout.activity_eleve, newLisEleve);
                    listEleve.setAdapter(adapter);*/
                }
                else {
                    Toast.makeText(getApplicationContext(),"Studen does not Exist!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        classeUpdateDelete.setAdapter(adapterSpinner);
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

    private View.OnClickListener ModifListener = new View.OnClickListener() {
        public void onClick(View v) {
            Modif();
        }
    };

    private void Modif() {
        Eleve eleveModif = null;
        // ma classe ne peut pas etre null, il faut qu j etrouve le moyen de recup ma classe entièrement.
        Classe maClasse;
        final EleveDAO unEleve = new EleveDAO(this);
        final ClasseDAO uneClasse = new ClasseDAO(this);
        unEleve.open();
        uneClasse.open();

       /// eleveModif = unEleve.read(parseInt(String.valueOf(idUpdateDelete.getText())));

        int idE = Integer.parseInt(String.valueOf(idUpdateDelete.getText()));
        String nomE = String.valueOf(nomUpdateDelete.getText());
        String prenomE = String.valueOf(prenomUpdateDelete.getText());
        String DateNaissE = String.valueOf(dateNUpdateDelete.getText());
        int classePosition = classeUpdateDelete.getSelectedItemPosition();

        Log.v("maClasse",String.valueOf(classePosition));


        Classe classeE = uneClasse.readPostion(classePosition);
        Log.v("coef444",classeE.getLibClasse());
       eleveModif = new Eleve(idE,nomE,prenomE,DateNaissE,classeE);

        unEleve.update(eleveModif);

        unEleve.close();
        uneClasse.close();

        Intent intent = new Intent(this,Eleve_Activity.class);
        startActivity(intent);

        finish();
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
    }
}
