package br.com.brunotonia.informatic.Constants;

/**
 * Created by bruno on 10/06/16.
 */
public interface OSSituacaoConstants {
    String TABLE_NAME        = "os_situacao";
    String COLUMN_ID         = "id";
    String COLUMN_SITUACAO   = "situacao";

    String CREATE_TABLE = "CREATE TABLE [" + TABLE_NAME + "] ( " +
            "[" + COLUMN_ID      + "] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "[" + COLUMN_SITUACAO + "] TEXT NOT NULL)";

    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    Integer VETOR_ID[] = {0, 1 , 2};
    String VETOR_SITUACOES[] = {"Aberta", "Concluída", "Cancelada"};

    String INSERT_SITUACAO_0 = "INSERT INTO " + TABLE_NAME + " VALUES (" + VETOR_ID[0].toString() + ", \"" + VETOR_SITUACOES[0] + "\")";
    String INSERT_SITUACAO_1 = "INSERT INTO " + TABLE_NAME + " VALUES (" + VETOR_ID[1].toString() + ", \"" + VETOR_SITUACOES[1] + "\")";
    String INSERT_SITUACAO_2 = "INSERT INTO " + TABLE_NAME + " VALUES (" + VETOR_ID[2].toString() + ", \"" + VETOR_SITUACOES[2] + "\")";

}
