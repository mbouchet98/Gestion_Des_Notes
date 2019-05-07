package com.mbouchet.gestion_des_notes.IHM;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mbouchet.gestion_des_notes.R;

import DAO.ClasseDAO;
import DAO.MatierDAO;
import METIER.Classe;
import METIER.Matier;

public class Classe_Add_Activity extends AppCompatActivity {

    private Button btnAddClasse;
    private Button btnAnnuleClasse;
    private EditText txtboxAddLibClasse;
    private EditText txtboxAddNbClasse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_add);


        objet();

        btnAnnuleClasse.setOnClickListener(ClasseAddAnnuler);

        btnAddClasse.setOnClickListener(AddClasse);

    }

    private View.OnClickListener ClasseAddAnnuler = new View.OnClickListener(){
        public void onClick(View v){
            annulerAddClasse();
        }
    };

    private void annulerAddClasse(){
        finish();
    }

    private View.OnClickListener AddClasse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addClasse();
        }
    };

    private void addClasse(){
        Classe ClasseAdd = null;
        final ClasseDAO uneClasse = new ClasseDAO(this);
        uneClasse.open();

        String libC = String.valueOf(txtboxAddLibClasse.getText());
        String nbC = String.valueOf(txtboxAddNbClasse.getText());

        ClasseAdd = new Classe(libC, Integer.parseInt(nbC));

        uneClasse.insert(ClasseAdd);

        uneClasse.close();

        finish();
    }
    public void objet(){
        btnAddClasse = (Button)findViewById(R.id.btnAddClasseAdd);
        btnAnnuleClasse = (Button)findViewById(R.id.btnAddRetourClasse);
        txtboxAddLibClasse = (EditText)findViewById(R.id.txtAddLibClasse);
        txtboxAddNbClasse = (EditText)findViewById(R.id.txtboxAddNbClasse);
    }
}
