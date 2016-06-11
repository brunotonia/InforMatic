package br.com.brunotonia.informatic.Constants;

/**
 * Created by bruno on 10/06/16.
 */
public interface ServicosConstants {
    String TABLE_NAME       = "servicos";
    String COLUMN_ID        = "id";
    String COLUMN_SERVICO   = "servico";
    String COLUMN_VALOR     = "valor";
    String COLUMN_DESCRICAO = "descricao";

    String CREATE_TABLE = "CREATE TABLE [" + TABLE_NAME + "] ( " +
            "[" + COLUMN_ID        + "] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "[" + COLUMN_SERVICO   + "] TEXT NOT NULL, " +
            "[" + COLUMN_VALOR     + "] NUMBER NOT NULL, " +
            "[" + COLUMN_DESCRICAO + "] TEXT NOT NULL)";

    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
