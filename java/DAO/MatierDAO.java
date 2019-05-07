package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import METIER.Matier;

import java.util.ArrayList;
import java.util.List;

public class MatierDAO extends DAO<Matier> {

    private SQLiteGestionNotes dbGestionNotes;

    // déclaration des outils nécessaire à la base.
    private static final String TABLE_MATIERE = "MATIERE";
    private static final String COL_ID_MATIERE = "IDMATIERE";
    private static final String COL_NOMMATIERE = "NOMMATIERE";
    private static final String COL_COEFMATIERE = "COEFMATIERE";

    private SQLiteDatabase db;

    public MatierDAO (Context context){
        dbGestionNotes = new SQLiteGestionNotes(context);
    }

    public void open(){
        db = dbGestionNotes.getWritableDatabase();
    }

    public void close(){
        dbGestionNotes.close();

    }

    @Override
    public boolean insert(Matier ma) {
        // insertion de la matière dans la base
        try{
            Log.v("ok","ok");
            ContentValues valeurIns = new ContentValues();
            valeurIns.put(COL_NOMMATIERE,ma.getNomMetier());
            valeurIns.put(COL_COEFMATIERE,ma.getCoefMetier());
            long x = db.insert("MATIERE", null, valeurIns);
            Log.v("indentifiant",String.valueOf(x));
            return true;
        }
        catch (Exception e){
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    @Override
    public boolean update(Matier obj) {
        // modification du nom et coefficient de la matière en fonction du numéro
        try {
            ContentValues valeur = new ContentValues();
            valeur.put(COL_NOMMATIERE,obj.getNomMetier());
            valeur.put(COL_COEFMATIERE,obj.getCoefMetier());
            db.update("MATIERE", valeur, COL_ID_MATIERE+"=?", new String[] {String.valueOf(obj.getIdMetier())});
            return true;
        }
        catch (Exception e){
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    @Override
    public boolean delete(Matier obj) {
        // suppression de la matière en fonction de son numéro
       try{
            db.delete("MATIERE", COL_ID_MATIERE+"=?", new String[] {String.valueOf(obj.getIdMetier())});
            return true;
       }
       catch (Exception e){
            Log.v("SQLError", e.toString());
            return false;
       }
    }

    public Matier read(long id){
        // recherche le numéro de matiere dans la base et la retourne
        Matier uneMatiere = null;
        Cursor cursor;
        String NomMatiere;
        float CoefMatiere;

        cursor = db.query(TABLE_MATIERE, null,COL_ID_MATIERE +"="+ id,null,null, null,null);

        cursor.moveToFirst();

        if (!cursor.isAfterLast()){
                NomMatiere = cursor.getString(1);
                CoefMatiere = cursor.getFloat(2);
                uneMatiere = new Matier((int) id,NomMatiere,CoefMatiere);
        }

        return uneMatiere;
    }

    public List<Matier> read(){
        // retourne la liste de tout les matieres enregistrées dans la base.
        List<Matier> liste = null;
        //declaratio variable et cursor
        Matier uneMatiere;
        Cursor cursor;

        int IdMatiere;
        String NomMatiere;
        float CoefMatiere;

        liste = new ArrayList<Matier>();
        //requete
        cursor = db.query(TABLE_MATIERE, null,null,null,null, null,null);
        // fetch
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            IdMatiere = cursor.getInt(0);
            NomMatiere = cursor.getString(1);
            CoefMatiere = cursor.getFloat(2);

            uneMatiere = new Matier(IdMatiere,NomMatiere,CoefMatiere);
            liste.add(uneMatiere);

            cursor.moveToNext();

        }

        cursor.close();


        return liste;
    }

    public Matier readPostion(int position){
        // retourne la liste de tout les matieres enregistrées dans la base.

        //declaratio variable et cursor
        Matier uneMatiere;
        Cursor cursor;
        int i;

        int IdMatiere;
        String NomMatiere;
        float CoefMatiere;
Log.v("position",String.valueOf(position));
        //requete
        cursor = db.query(TABLE_MATIERE, null,null,null,null, null,null);
        // fetch
        cursor.moveToFirst();
        for (i=0;i<position;i++) {
            cursor.moveToNext();
            Log.v("Positioni",String.valueOf(i));
        }

        IdMatiere = cursor.getInt(0);
        NomMatiere = cursor.getString(1);
        CoefMatiere = cursor.getFloat(2);

        uneMatiere = new Matier(IdMatiere,NomMatiere,CoefMatiere);
        Log.v("coefRecup",String.valueOf(uneMatiere.getCoefMetier()));

        cursor.close();


        return uneMatiere;
    }
}
