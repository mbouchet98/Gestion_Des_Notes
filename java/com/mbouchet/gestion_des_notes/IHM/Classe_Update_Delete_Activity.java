package com.mbouchet.gestion_des_notes.IHM;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mbouchet.gestion_des_notes.R;

import DAO.ClasseDAO;
import DAO.MatierDAO;
import METIER.Classe;
import METIER.Matier;

public class Classe_Update_Delete_Activity extends AppCompatActivity {
    private Button btnSup;
    private Button btnModif;
    private Button btnRetour;
    private EditText txtboxLib;
    private EditText txtboxNb;
    private EditText txtboxId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_update_delete);

        objet();
        btnRetour.setOnClickListener(retourListener);

        String paramId = this.getIntent().getStringExtra("paramId");
        String paramLib = this.getIntent().getStringExtra("paramLib");
        String paramNb = this.getIntent().getStringExtra("paramId");
        //float paramCoef = this.getIntent().getFloatExtra("paramCoef",0);
        txtboxLib.setText(paramLib);
        txtboxNb.setText(paramNb);
        txtboxId.setText(paramId);

        btnModif.setOnClickListener(ModifMatiere);

        btnSup.setOnClickListener(SuppMatiere);

    }

    private void objet(){
        btnSup = (Button)findViewById(R.id.btnSupprimerClasse);
        btnModif = (Button)findViewById(R.id.btnModifierClasse);
        btnRetour = (Button)findViewById(R.id.btnAnnulerModification);
        txtboxLib = (EditText)findViewById(R.id.txtboxlibClasseModif);
        txtboxNb = (EditText)findViewById(R.id.txtboxNbClasseModif);
        txtboxId = (EditText)findViewById(R.id.txtboxIdModification);
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
        Classe ClasseModif = null;
        final ClasseDAO uneClasse = new ClasseDAO(this);

        uneClasse.open();

        String LibC = String.valueOf(txtboxLib.getText());
        String NbC = String.valueOf(txtboxNb.getText());
        String idC = String.valueOf(txtboxId.getText());

        Log.v("coef444", String.valueOf(NbC));
        ClasseModif = new Classe(Integer.parseInt(idC), LibC, Integer.parseInt(NbC));

        uneClasse.update(ClasseModif);

        uneClasse.close();

        finish();
    }

    private View.OnClickListener SuppMatiere = new View.OnClickListener(){
        public void onClick(View v){
            Supp();
        }
    };

    private void Supp(){
        Classe ClasseModif = null;
        final ClasseDAO uneClasse = new ClasseDAO(this);
        uneClasse.open();

        String LibC = String.valueOf(txtboxLib.getText());
        String NbC = String.valueOf(txtboxNb.getText());
        String idC = String.valueOf(txtboxId.getText());
        ClasseModif = new Classe(Integer.parseInt(idC), LibC, Integer.parseInt(NbC));

        uneClasse.delete(ClasseModif);
        uneClasse.close();
        finish();
    }
}
