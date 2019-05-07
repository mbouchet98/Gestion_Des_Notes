package com.mbouchet.gestion_des_notes.IHM;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mbouchet.gestion_des_notes.R;

import java.util.List;

import DAO.MatierDAO;
import METIER.Matier;

public class Matiere_Update_DeleteActivity extends AppCompatActivity{
    private Button btnSup;
    private Button btnModif;
    private Button btnRetour;
    private EditText txtboxNom;
    private EditText txtboxCoef;
    private EditText txtboxId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matiere_update_dalate);

        Object();

        btnRetour.setOnClickListener(retourListener);


        String paramId = this.getIntent().getStringExtra("paramId");
        String paramNom = this.getIntent().getStringExtra("paramNom");
        String paramCoef = this.getIntent().getStringExtra("paramCoef");
        //float paramCoef = this.getIntent().getFloatExtra("paramCoef",0);
        txtboxNom.setText(paramNom);
        txtboxCoef.setText(paramCoef);
        txtboxId.setText(paramId);

        /* btn Modif*/
        btnModif.setOnClickListener(ModifMatiere);

        /*btn Delete*/
        btnSup.setOnClickListener(SuppMatiere);
    }

    private void Object(){
        btnSup = (Button)findViewById(R.id.btnSup);
        btnModif = (Button)findViewById(R.id.btnModif);
        btnRetour = (Button)findViewById(R.id.btnRetour);
        txtboxNom = (EditText)findViewById(R.id.txtboxNom);
        txtboxCoef = (EditText)findViewById(R.id.txtboxCoef);
        txtboxId = (EditText)findViewById(R.id.txtboxId);


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

    private View.OnClickListener ModifMatiere = new View.OnClickListener(){
        public void onClick(View v){
            Modif();
        }
    };

    private void Modif(){
        Matier matiereModif = null;
        final MatierDAO uneMatiere = new MatierDAO(this);

        uneMatiere.open();

        String nomM = String.valueOf(txtboxNom.getText());
        String coefM = String.valueOf(txtboxCoef.getText());
        String idM = String.valueOf(txtboxId.getText());

        Log.v("coef444", String.valueOf(coefM));
        matiereModif = new Matier(Integer.parseInt(idM), nomM, Float.parseFloat(coefM));

        uneMatiere.update(matiereModif);

        uneMatiere.close();

        finish();
    }

    private View.OnClickListener SuppMatiere = new View.OnClickListener(){
        public void onClick(View v){
            Supp();
        }
    };

    private void Supp(){
        Matier matiereModif = null;
        final MatierDAO uneMatiere = new MatierDAO(this);
        uneMatiere.open();

        String nomM = String.valueOf(txtboxNom.getText());
        String coefM = String.valueOf(txtboxCoef.getText());
        String idM = String.valueOf(txtboxId.getText());
        matiereModif = new Matier(Integer.parseInt(idM), nomM, Float.parseFloat(coefM));

        uneMatiere.delete(matiereModif);
        uneMatiere.close();
        finish();
    }
}

