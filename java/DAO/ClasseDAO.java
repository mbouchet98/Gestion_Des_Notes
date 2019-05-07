package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import METIER.Classe;
import METIER.Eleve;
import METIER.Matier;

public class ClasseDAO extends DAO<Classe> {
    private SQLiteGestionNotes dbGestionNotes;

    // déclaration des outils nécessaire à la base.
    private static final String TABLE_CLASSE = "CLASSE";
    private static final String COL_ID_CLASSE = "IDCLASSE";
    private static final String COL_LIBCLASSE = "LIBCLASSE";
    private static final String COL_NBCLASSE = "NBCLASSE";


    private SQLiteDatabase db;

    public ClasseDAO(Context context) {
        dbGestionNotes = new SQLiteGestionNotes(context);
    }

    public void open() {
        db = dbGestionNotes.getWritableDatabase();
    }

    public void close() {
        dbGestionNotes.close();

    }

    @Override
    public boolean insert(Classe obj) {
        // insertion de la matière dans la base
        try {
            Log.v("ok", "ok");
            ContentValues valeurIns = new ContentValues();
            valeurIns.put(COL_LIBCLASSE, obj.getLibClasse());
            valeurIns.put(COL_NBCLASSE, obj.getNbClasse());
            long x = db.insert(TABLE_CLASSE, null, valeurIns);
            Log.v("indentifiant", String.valueOf(x));
            return true;
        } catch (Exception e) {
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    @Override
    public boolean update(Classe obj) {
        // modification du nom et coefficient de la matière en fonction du numéro
        try {
            ContentValues valeur = new ContentValues();
            valeur.put(COL_LIBCLASSE, obj.getLibClasse());
            valeur.put(COL_NBCLASSE, obj.getNbClasse());
            db.update(TABLE_CLASSE, valeur, COL_ID_CLASSE + "=?", new String[]{String.valueOf(obj.getIdClasse())});
            return true;
        } catch (Exception e) {
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    @Override
    public boolean delete(Classe obj) {
        // suppression de la matière en fonction de son numéro
        try {
            db.delete(TABLE_CLASSE, COL_ID_CLASSE + "=?", new String[]{String.valueOf(obj.getIdClasse())});
            return true;
        } catch (Exception e) {
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    public Classe read(long id) {
        // recherche le numéro de matiere dans la base et la retourne
        Classe uneClasse = null;
        Cursor cursor;
        String libClasse;
        int NbClasse;


        cursor = db.query(TABLE_CLASSE, null, COL_ID_CLASSE + "=" + id, null, null, null, null);

        cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            libClasse = cursor.getString(1);
            NbClasse = cursor.getInt(2);

            uneClasse = new Classe((int) id, libClasse, NbClasse);
        }

        return uneClasse;
    }


    public List<Classe> read() {
        // retourne la liste de tout les matieres enregistrées dans la base.
        List<Classe> liste = null;
        //declaratio variable et cursor
        Classe uneClasse;
        Cursor cursor;

        int IdClasse;
        String libClasse;
        int NbClasse;


        liste = new ArrayList<Classe>();
        //requete
        cursor = db.query(TABLE_CLASSE, null, null, null, null, null, null);
        // fetch
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            IdClasse = cursor.getInt(0);
            libClasse = cursor.getString(1);
            NbClasse = cursor.getInt(2);

            uneClasse = new Classe(IdClasse, libClasse, NbClasse);
            liste.add(uneClasse);

            cursor.moveToNext();

        }

        cursor.close();


        return liste;
    }


    public Classe readPostion(int position){
        // retourne la liste de tout les matieres enregistrées dans la base.

        //declaratio variable et cursor
        Classe uneClasse;
        Cursor cursor;
        int i;

        int IdClasse;
        String libClasse;
        int nbClasse;

        Log.v("position",String.valueOf(position));
        //requete
        cursor = db.query(TABLE_CLASSE, null,null,null,null, null,null);
        // fetch
        cursor.moveToFirst();
        for (i=0;i<position;i++) {
            cursor.moveToNext();
            Log.v("Positioni",String.valueOf(i));
        }

        IdClasse = cursor.getInt(0);
        libClasse = cursor.getString(1);
        nbClasse = cursor.getInt(2);


        uneClasse = new Classe(IdClasse,libClasse,nbClasse);
        Log.v("coefRecup",String.valueOf(uneClasse.getNbClasse()));

        cursor.close();


        return uneClasse;
    }

    public Classe read(CharSequence prompt) {
        // recherche le numéro de matiere dans la base et la retourne
        Classe uneClasse = null;
        Cursor cursor;
        String libClasse;
        int id;
        int NbClasse;


        cursor = db.query(TABLE_CLASSE, null,
                COL_LIBCLASSE+ "=" + prompt, null, null, null, null);

        cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            id = cursor.getInt(0);
            libClasse = cursor.getString(1);
            NbClasse = cursor.getInt(2);

            uneClasse = new Classe((int) id, libClasse, NbClasse);
        }

        return uneClasse;
    }
}
