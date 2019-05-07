package com.mbouchet.gestion_des_notes.IHM;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mbouchet.gestion_des_notes.R;

import DAO.MatierDAO;
import METIER.Matier;

public class Matiere_Add_Activity extends AppCompatActivity {

    private Button btnAdd;
    private Button btnAnnule;
    private EditText txtboxNom;
    private EditText txtboxCoef;
    private EditText txtboxId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maetiere_add);

        objet();

        btnAnnule.setOnClickListener(AnnulerMaetiere);

        btnAdd.setOnClickListener(AddMatiere);

    }

    private View.OnClickListener AnnulerMaetiere = new View.OnClickListener(){
        public void onClick(View v){
            annulerAddMatiere();
        }
    };

    private View.OnClickListener AddMatiere = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addMatiere();
        }
    };


    private void objet(){
        btnAdd = (Button)findViewById(R.id.btnMatiereAdd);
        btnAnnule = (Button)findViewById(R.id.btnAnnulerAdd);
        txtboxNom = (EditText)findViewById(R.id.txtboxAddNom);
        txtboxCoef = (EditText)findViewById(R.id.txtboxAddCoef);
        txtboxId = (EditText)findViewById(R.id.txtboxId2);
    }

    private void annulerAddMatiere(){
        finish();
    }


    private void addMatiere(){
        Matier matiereAdd = null;
        final MatierDAO uneMatiere = new MatierDAO(this);
        uneMatiere.open();

        String nomM = String.valueOf(txtboxNom.getText());
        String coefM = String.valueOf(txtboxCoef.getText());

        matiereAdd = new Matier(nomM, Float.parseFloat(coefM));

        uneMatiere.insert(matiereAdd);

        uneMatiere.close();

        finish();
    }
}
