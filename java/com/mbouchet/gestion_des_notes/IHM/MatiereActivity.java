package com.mbouchet.gestion_des_notes.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.mbouchet.gestion_des_notes.R;

import java.util.List;

import DAO.MatierDAO;
import METIER.Matier;

public class MatiereActivity extends AppCompatActivity {
    private Button btnRetour;
    private ListView listMatiere;
    private FloatingActionButton btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere);

        Object();
        List();
    }

    protected void onStart() {
        super.onStart();

        List();
    }

    private void Object(){
        btnRetour = (Button)findViewById(R.id.btn_retour);
        listMatiere = (ListView)findViewById(R.id.list_Matiere);
        btnAdd = (FloatingActionButton)findViewById(R.id.btnAdd);
    }

    private  void List(){
        btnRetour.setOnClickListener(retourListener);

        List<Matier> uneList;

        final MatierDAO uneMatiere = new MatierDAO(this);

        uneMatiere.open();

        uneList = uneMatiere.read();

        uneMatiere.close();
        //AdapterActivity adapter = new AdapterActivity(MatiereActivity.this, uneList);
        AdapterActivity adapter = new AdapterActivity(this, R.layout.activity_matiere, uneList);
        listMatiere.setAdapter(adapter);
        listMatiere.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                Matier laMatiere;
                uneMatiere.open();
                laMatiere = uneMatiere.readPostion(position);
                uneMatiere.close();
                intent = new Intent(getApplicationContext(), Matiere_Update_DeleteActivity.class);
                intent.putExtra("paramNom", laMatiere.getNomMetier());
                intent.putExtra("paramCoef", String.valueOf(laMatiere.getCoefMetier()));
                //intent.putExtra("paramCoef",laMatiere.getCoefMetier());
                intent.putExtra("paramId", String.valueOf(laMatiere.getIdMetier()));
                startActivity(intent);
            }

        });

        btnAdd.setOnClickListener(AddListener);
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
        intent = new Intent(this, Matiere_Add_Activity.class);
        startActivity(intent);
    }
}
