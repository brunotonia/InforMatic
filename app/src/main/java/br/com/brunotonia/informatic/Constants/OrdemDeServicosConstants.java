package br.com.brunotonia.informatic.Constants;

/**
 * Created by bruno on 10/06/16.
 */
public interface OrdemDeServicosConstants {
    String TABLE_NAME        = "ordemdeservico";
    String COLUMN_ID         = "id";
    String COLUMN_CLIENTE    = "cliente";
    String COLUMN_SITUACAO   = "situacao";

    String CREATE_TABLE = "CREATE TABLE [" + TABLE_NAME + "] ( " +
            "[" + COLUMN_ID      + "] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "[" + COLUMN_CLIENTE + "] INTEGER NOT NULL, " +
            "[" + COLUMN_SITUACAO + "] INTEGER NOT NULL, " +
            "FOREIGN KEY([" + COLUMN_CLIENTE +"]) REFERENCES " + ClientesConstants.TABLE_NAME +
            "(" +  ClientesConstants.COLUMN_ID + ")," +
            "FOREIGN KEY([" + COLUMN_SITUACAO +"]) REFERENCES " + OSSituacaoConstants.TABLE_NAME +
            "(" +  ClientesConstants.COLUMN_ID + "))";

    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
