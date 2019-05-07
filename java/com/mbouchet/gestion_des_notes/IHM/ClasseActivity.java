package com.mbouchet.gestion_des_notes.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mbouchet.gestion_des_notes.R;

import java.util.List;

import DAO.ClasseDAO;
import METIER.Classe;

public class ClasseActivity extends AppCompatActivity {

    private Button btnRetour;
    private ListView listClasse;
    private FloatingActionButton btnAddClasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe);

        Object();
        List();

    }

    protected void onStart() {
        super.onStart();

        List();
    }

    private  void List(){
        btnRetour.setOnClickListener(retourListener);

        List<Classe> uneListClasse;

        final ClasseDAO uneClasse = new ClasseDAO(this);

        uneClasse.open();

        uneListClasse = uneClasse.read();

        uneClasse.close();
        //AdapterActivity adapter = new AdapterActivity(MatiereActivity.this, uneList);
        Classe_Adapter_Activity adapter = new Classe_Adapter_Activity(this, R.layout.activity_classe, uneListClasse);
        listClasse.setAdapter(adapter);
        listClasse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                Classe laClasse;
                uneClasse.open();
                laClasse = uneClasse.readPostion(position);
                uneClasse.close();
                intent = new Intent(getApplicationContext(), Classe_Update_Delete_Activity.class);
                intent.putExtra("paramLib", laClasse.getLibClasse());
                intent.putExtra("paramNb", String.valueOf(laClasse.getNbClasse()));
                //intent.putExtra("paramCoef",laMatiere.getCoefMetier());
                intent.putExtra("paramId", String.valueOf(laClasse.getIdClasse()));
                startActivity(intent);


            }



        });

        btnAddClasse.setOnClickListener(AddClasseListener);
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

    private View.OnClickListener AddClasseListener = new View.OnClickListener(){
        public void onClick(View v){
            add();
        }
    };

    private void add(){
        Intent intent;
        intent = new Intent(this, Classe_Add_Activity.class);
        startActivity(intent);
    }

    private void Object(){
        btnRetour = (Button)findViewById(R.id.BTNRetour);
        listClasse = (ListView)findViewById(R.id.idListClasse);
        btnAddClasse = (FloatingActionButton)findViewById(R.id.btnAddClasse);
    }
}
