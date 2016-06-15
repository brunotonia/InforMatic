package br.com.brunotonia.informatic.Constants;

/**
 * Created by bruno on 15/06/16.
 */
public interface OSServicosContants {
    String TABLE_NAME     = "os_servicos";
    String COLUMN_ID      = "id";
    String COLUMN_OS      = "os";
    String COLUMN_SERVICO = "servico";
    String COLUMN_VALOR   = "valor";

    String CREATE_TABLE = "CREATE TABLE [" + TABLE_NAME + "] ( " +
            "[" + COLUMN_ID      + "] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "[" + COLUMN_OS      + "] TEXT NOT NULL, " +
            "[" + COLUMN_SERVICO + "] TEXT NOT NULL, " +
            "[" + COLUMN_VALOR   + "] NUMBER NOT NULL, " +
            "FOREIGN KEY([" + COLUMN_OS+"]) REFERENCES " + OrdemDeServicosConstants.TABLE_NAME +
            "(" +  OrdemDeServicosConstants.COLUMN_ID + ")," +
            "FOREIGN KEY([" + COLUMN_SERVICO +"]) REFERENCES " + ServicosConstants.TABLE_NAME +
            "(" +  ServicosConstants.COLUMN_ID + "))";

    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
