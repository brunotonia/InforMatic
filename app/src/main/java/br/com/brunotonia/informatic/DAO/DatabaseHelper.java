package br.com.brunotonia.informatic.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

import br.com.brunotonia.informatic.Constants.ClientesConstants;
import br.com.brunotonia.informatic.Constants.DatabaseConstants;
import br.com.brunotonia.informatic.Constants.OSSituacaoConstants;
import br.com.brunotonia.informatic.Constants.ServicosConstants;

/**
 * Created by bruno on 10/06/16.
 */
public class DatabaseHelper implements Serializable {

    private DBHelper helper = null;
    private SQLiteDatabase db = null;

    private static final long serialVersionUID = 1L;

    public DatabaseHelper(Context context) {
        helper = new DBHelper(context);
    }

    public SQLiteDatabase open() {
        return db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DatabaseConstants.DB_NAME, null, DatabaseConstants.VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ClientesConstants.CREATE_TABLE);
            db.execSQL(ServicosConstants.CREATE_TABLE);
            db.execSQL(OSSituacaoConstants.CREATE_TABLE);
            //db.execSQL(OServicosConstants.CREATE_TABLE);
            /* Adiciona constantes do banco de dados */
            db.execSQL(OSSituacaoConstants.INSERT_SITUACAO_0);
            db.execSQL(OSSituacaoConstants.INSERT_SITUACAO_1);
            db.execSQL(OSSituacaoConstants.INSERT_SITUACAO_2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(ServicosConstants.DROP_TABLE);
            db.execSQL(OSSituacaoConstants.DROP_TABLE);
            //db.execSQL(OServicosConstants.DROP_TABLE);
            db.execSQL(ClientesConstants.DROP_TABLE);
            onCreate(db);
        }

    }

}
