package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class SQLiteGestionNotes extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "GestionNote";
    private Context context = null;

    public SQLiteGestionNotes(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.v("test","connection");
            db.execSQL("DROP TABLE IF EXISTS MATIERE");
            db.execSQL("CREATE TABLE MATIERE (IDMATIERE INTEGER PRIMARY KEY AUTOINCREMENT, NOMMATIERE VARCHAR(100), COEFMATIERE REAL)");
            db.execSQL("INSERT INTO MATIERE (NOMMATIERE, COEFMATIERE) VALUES ('MATHEMATIQUES', 2)");
            db.execSQL("INSERT INTO MATIERE (NOMMATIERE, COEFMATIERE) VALUES('ANGLAIS', 2)");
            db.execSQL("INSERT INTO MATIERE (NOMMATIERE, COEFMATIERE) VALUES ('CGE', 3)");
            Cursor c = db.query("MATIERE",new String[]{"NOMMATIERE","COEFMATIERE"},null,null,null,null,null);
            c.moveToFirst();
            Log.v("Matiere",c.getString(0));
            db.execSQL("DROP TABLE IF EXISTS CLASSE");
            db.execSQL("CREATE TABLE CLASSE (IDCLASSE INTEGER PRIMARY KEY AUTOINCREMENT, LIBCLASSE VARCHAR(100), NBCLASSE INTEGER)");
            db.execSQL("INSERT INTO CLASSE (LIBCLASSE, NBCLASSE) VALUES ('SIAP2', 12)");
            db.execSQL("INSERT INTO CLASSE (LIBCLASSE, NBCLASSE) VALUES('STS2', 20)");
            db.execSQL("INSERT INTO CLASSE (LIBCLASSE, NBCLASSE) VALUES ('TERMINAL S', 30)");
            Cursor cursor = db.query("CLASSE",new String[]{"LIBCLASSE","NBCLASSE"},null,null,null,null,null);
            cursor.moveToFirst();
            Log.v("Classe",cursor.getString(0));
            db.execSQL("DROP TABLE IF EXISTS ELEVE");
            db.execSQL("CREATE TABLE ELEVE (IDELEVE INTEGER PRIMARY KEY AUTOINCREMENT, NOMELEVE VARCHAR(100),PRENOMELEVE VARCHAR(100), DATENELEVE VARCHAR(10), CLASSEELEVE INTEGER)");
            db.execSQL("INSERT INTO ELEVE (NOMELEVE, PRENOMELEVE,DATENELEVE,CLASSEELEVE) VALUES ('maelys','bouchet','13/02/1998' ,1)");
            db.execSQL("INSERT INTO ELEVE (NOMELEVE, PRENOMELEVE,DATENELEVE,CLASSEELEVE) VALUES ('mariannick','Chassier','10/07/1994' ,2)");
            db.execSQL("INSERT INTO ELEVE (NOMELEVE, PRENOMELEVE,DATENELEVE,CLASSEELEVE) VALUES ('test','test','14/12/2001' ,3)");
            Cursor cursorE = db.query("ELEVE",new String[]{"NOMELEVE","PRENOMELEVE","DATENELEVE","CLASSEELEVE"},null,null,null,null,null);
            cursorE.moveToFirst();
            Log.v("ELEVE",cursorE.getString(0));
        }catch (Exception e){
            Log.v("SQLError", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
