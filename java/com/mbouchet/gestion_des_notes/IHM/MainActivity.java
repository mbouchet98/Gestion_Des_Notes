package com.mbouchet.gestion_des_notes.IHM;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.support.v7.app.AlertDialog;
import android.view.View.*;
import android.view.View;
import android.widget.ListView;

import com.mbouchet.gestion_des_notes.R;

import DAO.MatierDAO;
import METIER.Matier;

public class MainActivity extends AppCompatActivity {




   private Button btnQuitter;
   private Button btnDevoir;
   private Button btnEleve;
   private Button btnClasse;
   private Button btnMatier;

 //  private ListView listMatiere;

    private OnClickListener quitterListener = new OnClickListener(){
      public void onClick(View v){
         quitter();
      }
   };
    private OnClickListener MatiereListener = new OnClickListener(){
        public void onClick(View v){
            afficherMatiere();
        }
    };
    private OnClickListener ClasseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            afficherClasse();
        }
    };
    private OnClickListener EleveListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            afficherEleve();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Object();

        btnQuitter.setOnClickListener(quitterListener);

       /* MatierDAO matier = new MatierDAO(this);
        matier.open();*/

         btnMatier.setOnClickListener (MatiereListener);

         btnClasse.setOnClickListener(ClasseListener);

         btnEleve.setOnClickListener(EleveListener);

    }


    private void Object(){
       btnQuitter = (Button)findViewById(R.id.btn_quitter);
       btnMatier = (Button)findViewById(R.id.btn_Matier);
       btnClasse = (Button)findViewById(R.id.btn_Classe);
       btnEleve = (Button)findViewById(R.id.btn_Eleve);
      // listMatiere = (ListView)findViewById(R.id.list_Matiere);
    }

    private void quitter(){
        AlertDialog.Builder alertQuitter = new AlertDialog.Builder(this);
        //AlertDialog fenetre = new AlertDialog();
        alertQuitter.setMessage("Voulez vous vraiment quitter ? ");
        alertQuitter.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertQuitter.setNegativeButton("non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog fenetre = alertQuitter.create();
        fenetre.show();
    }

    /*private void afficherMatiere(){
        Matier uneMatiere;
        MatierDAO uneMatierDAO;
        uneMatiere = new Matier(2, "EDM", 2);
        uneMatierDAO = new MatierDAO(this);
        uneMatierDAO.open();
        uneMatierDAO.insert(uneMatiere);
        uneMatierDAO.close();
    }*/


   /* private void afficherMatiere(){
        Matier uneMatiere;
        MatierDAO uneMatierDAO;
        uneMatiere = new Matier(2, "SLAM3", 2);
        uneMatierDAO = new MatierDAO(this);
        uneMatierDAO.open();
        uneMatierDAO.update(uneMatiere);
        uneMatierDAO.close();
    }*/

    /*private void afficherMatiere(){
        Matier uneMatiere;
        MatierDAO uneMatierDAO;
        uneMatiere = new Matier(4, "SLAM3", 2);
        uneMatierDAO = new MatierDAO(this);
        uneMatierDAO.open();
        uneMatierDAO.delete(uneMatiere);
        uneMatierDAO.close();
    }*/

    private void afficherMatiere(){
        // retour la la page précédente.
        Intent intent;
        intent = new Intent(this,MatiereActivity.class);
        startActivity(intent);

    }

    private void afficherClasse(){
        Intent intent;
        intent = new Intent(this,ClasseActivity.class);
        startActivity(intent);

    }

    private void afficherEleve(){
        Intent intent;
        intent = new Intent(this,Eleve_Activity.class);
        startActivity(intent);
       // finish();
    }
}
