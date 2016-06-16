package br.com.brunotonia.informatic.Constants;

/**
 * Created by bruno on 10/06/16.
 */
public interface ClientesConstants {
    String TABLE_NAME      = "clientes";
    String COLUMN_ID       = "id";
    String COLUMN_NOME     = "nome";
    String COLUMN_TELEFONE = "telefone";
    String COLUMN_ENDERECO = "endereco";
    String COLUMN_EMAIL    = "email";

    String CREATE_TABLE = "CREATE TABLE [" + TABLE_NAME + "] ( " +
            "[" + COLUMN_ID       + "] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            "[" + COLUMN_NOME     + "] TEXT NOT NULL, " +
            "[" + COLUMN_TELEFONE + "] TEXT NOT NULL, " +
            "[" + COLUMN_ENDERECO + "] TEXT NOT NULL, " +
            "[" + COLUMN_EMAIL    + "] TEXT NOT NULL)";

    String DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    Integer VETOR_ID[] = {1, 2 , 3};
    String VETOR_NOMES[] = {"Bruno Tonia", "João Roberto", "Eddie The Head"};
    String VETOR_TELEFONES[] = {"011-1406", "555-1234", "666-666"};
    String VETOR_ENDERECOS[] = {"Rua dos Bobos, 0", "Av. A, 1", "22, Acacia Avenue"};
    String VETOR_EMAILS[] = {"eu@brunotonia.com", "beto@beto.net", "eddie@666.hell"};

    String INSERT_CLIENTE_0 = "INSERT INTO " + TABLE_NAME + " VALUES (" + VETOR_ID[0].toString() + ", \"" + VETOR_NOMES[0] + ", \""
            + VETOR_TELEFONES[0] + VETOR_ENDERECOS[0] + "," +  VETOR_EMAILS[0] + "\")";
    String INSERT_CLIENTE_1 = "INSERT INTO " + TABLE_NAME + " VALUES (" + VETOR_ID[1].toString() + ", \"" + VETOR_NOMES[1] + ", \"" + VETOR_TELEFONES[1]
            + VETOR_ENDERECOS[1] + "," +  VETOR_EMAILS[1] + "\")";
    String INSERT_CLIENTE_2 = "INSERT INTO " + TABLE_NAME + " VALUES (" + VETOR_ID[2].toString() + ", \"" + VETOR_NOMES[2] + ", \"" + VETOR_TELEFONES[0]
            + VETOR_ENDERECOS[2] + "," +  VETOR_EMAILS[2] + "\")";
}
