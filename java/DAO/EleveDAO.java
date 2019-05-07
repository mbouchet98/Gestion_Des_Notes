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

import static java.lang.String.*;

public class EleveDAO extends DAO<Eleve> {

    private SQLiteGestionNotes dbGestionNotes;

    // déclaration des outils nécessaire à la base.
    private static final String TABLE_ELEVE = "ELEVE";
    private static final String COL_ID_ELEVE = "IDELEVE";
    private static final String COL_NOMELEVE = "NOMELEVE";
    private static final String COL_PRENOMELEVE = "PRENOMELEVE";
    private static final String COL_DATENELEVE = "DATENELEVE";
    private static final String COL_ID_CLASSE = "CLASSEELEVE";

    private Context moncontext;
    private SQLiteDatabase db;

    public EleveDAO(Context context) {
        dbGestionNotes = new SQLiteGestionNotes(context);
        moncontext = context;
    }

    public void open() {
        db = dbGestionNotes.getWritableDatabase();
    }

    public void close() {
        dbGestionNotes.close();

    }

    @Override
    public boolean insert(Eleve obj) {
        // insertion de l'éléve dans la base
        Log.v("classeselect",  valueOf(obj.getNomEleve()));
        try {
            Log.v("ok", "ok");
            ContentValues valeurIns = new ContentValues();
            valeurIns.put(COL_NOMELEVE, obj.getNomEleve());
            valeurIns.put(COL_PRENOMELEVE, obj.getPrenomEleve());
            valeurIns.put(COL_DATENELEVE, obj.getDateNaissEleve());
            valeurIns.put(COL_ID_CLASSE, valueOf(obj.getLaClasse().getIdClasse()));

            long x = db.insert(TABLE_ELEVE, null, valeurIns);
            Log.v("indentifiant", valueOf(x));
            return true;
        } catch (Exception e) {
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    @Override
    public boolean update(Eleve obj) {
        // modification du nom et coefficient de la matière en fonction du numéro
        try {
            ContentValues valeur = new ContentValues();
            valeur.put(COL_NOMELEVE, obj.getNomEleve());
            valeur.put(COL_PRENOMELEVE, obj.getPrenomEleve());
            valeur.put(COL_DATENELEVE, obj.getDateNaissEleve());
            valeur.put(COL_ID_CLASSE, valueOf(obj.getLaClasse().getIdClasse()));
            db.update(TABLE_ELEVE, valeur, COL_ID_ELEVE + "=?", new String[]{valueOf(obj.getIdEleve())});
            return true;
        } catch (Exception e) {
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    @Override
    public boolean delete(Eleve obj) {
        // suppression de la matière en fonction de son numéro
        try {
            db.delete(TABLE_ELEVE, COL_ID_ELEVE + "=?", new String[]{valueOf(obj.getIdEleve())});
            return true;
        } catch (Exception e) {
            Log.v("SQLError", e.toString());
            return false;
        }
    }

    public Eleve read(long id) {
        // recherche le numéro de matiere dans la base et la retourne
        Eleve unEleve = null;
        Cursor cursor;
        String NomEleve;
        String prenomEleve;
        String dateNEleve;
        Classe classeEleve;
        ClasseDAO maClasse;

        cursor = db.query(TABLE_ELEVE, null, COL_ID_ELEVE + "=" + id, null, null, null, null);

        cursor.moveToFirst();

        if (!cursor.isAfterLast()) {
            NomEleve = cursor.getString(1);
            prenomEleve = cursor.getString(2);
            dateNEleve = cursor.getString(3);
            maClasse = new ClasseDAO(moncontext);
            maClasse.open();
            classeEleve = maClasse.read(cursor.getInt(4));
            maClasse.close();
            unEleve = new Eleve((int) id, NomEleve, prenomEleve, dateNEleve, classeEleve);
        }

        return unEleve;
    }

    public List<Eleve> read() {
        // retourne la liste de tout les matieres enregistrées dans la base.
        List<Eleve> liste = null;
        //declaratio variable et cursor
        Eleve unEleve;
        Cursor cursor;

        int IdEleve;
        String NomEleve;
        String prenomEleve;
        String dateNEleve;
        Classe classeEleve;
        ClasseDAO maClasse;

        liste = new ArrayList<Eleve>();
        //requete
        cursor = db.query(TABLE_ELEVE, null, null, null, null, null, null);
        // fetch
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            IdEleve = cursor.getInt(0);
            NomEleve = cursor.getString(1);
            prenomEleve = cursor.getString(2);
            dateNEleve = cursor.getString(3);
            maClasse = new ClasseDAO(moncontext);
            maClasse.open();
            classeEleve = maClasse.read(cursor.getInt(4));
            maClasse.close();
            unEleve = new Eleve(IdEleve, NomEleve, prenomEleve, dateNEleve, classeEleve);
            liste.add(unEleve);

            cursor.moveToNext();

        }

        cursor.close();


        return liste;
    }

    public Eleve readPostion(int position) {
        // retourne la liste de tout les matieres enregistrées dans la base.

        //declaratio variable et cursor
        Eleve uneEleve;
        Cursor cursor;
        int i;

        int IdElevee;
        String NomEleve;
        String prenomEleve;
        String dateNEleve;
        Classe classeEleve;
        ClasseDAO maClasse;

        Log.v("position", valueOf(position));
        //requete
        cursor = db.query(TABLE_ELEVE, null, null, null, null, null, null);
        // fetch
        cursor.moveToFirst();
        for (i = 0; i < position; i++) {
            cursor.moveToNext();
            Log.v("Positioni", valueOf(i));
        }

        IdElevee = cursor.getInt(0);
        NomEleve = cursor.getString(1);
        prenomEleve = cursor.getString(2);
        dateNEleve = cursor.getString(3);
        maClasse = new ClasseDAO(moncontext);
        maClasse.open();
        classeEleve = maClasse.read(cursor.getInt(4));
        maClasse.close();

        uneEleve = new Eleve(IdElevee, NomEleve, prenomEleve, dateNEleve, classeEleve);
        Log.v("ClasseRecup", valueOf(uneEleve.getLaClasse()));

        cursor.close();


        return uneEleve;
    }

    public Eleve readByClasse(int idClasse, int position){
        // retourne la liste de tout les matieres enregistrées dans la base.
        List<Eleve> liste = null;
        ClasseDAO classeDAO;
        //declaratio variable et cursor
        Eleve unEleve;
        Cursor cursor;

        int IdEleve;
        String NomEleve;
        String prenomEleve;
        String dateNEleve;
        Classe classeEleve;
        ClasseDAO maClasse;

        liste = new ArrayList<Eleve>();
        //requete
        cursor = db.query(TABLE_ELEVE,null,COL_ID_CLASSE +"=?",new String[]{Integer.toString(idClasse)},null,null,null);
        // fetch
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            IdEleve = cursor.getInt(0);
            NomEleve = cursor.getString(1);
            prenomEleve = cursor.getString(2);
            dateNEleve = cursor.getString(3);
            maClasse = new ClasseDAO(moncontext);
            maClasse.open();
            classeEleve = maClasse.read(cursor.getInt(4));
            maClasse.close();
            unEleve = new Eleve(IdEleve, NomEleve, prenomEleve, dateNEleve, classeEleve);
            liste.add(unEleve);

            cursor.moveToNext();

        }
        for (Eleve unEleve1 : liste){
            Log.v("eleve",unEleve1.toString());
        }

        cursor.close();
Log.v("positionList",Integer.toString(position));

        return liste.get(position);
    }
}
