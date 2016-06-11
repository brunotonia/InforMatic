package br.com.brunotonia.informatic.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.brunotonia.informatic.Constants.ServicosConstants;
import br.com.brunotonia.informatic.VO.ServicosVO;

/**
 * Created by bruno on 10/06/16.
 */
public class ServicosDAO {

    public Long adicionar(SQLiteDatabase db, ServicosVO servicosVO) throws Exception {
        ContentValues values = new ContentValues();
        values.put(ServicosConstants.COLUMN_SERVICO, servicosVO.getServico());
        values.put(ServicosConstants.COLUMN_VALOR, servicosVO.getValor());
        values.put(ServicosConstants.COLUMN_DESCRICAO, servicosVO.getDescricao());
        return db.insert(ServicosConstants.TABLE_NAME, null, values);
    }

    public boolean editar(SQLiteDatabase db, ServicosVO servicosVO) throws Exception {
        ContentValues values = new ContentValues();
        values.put(ServicosConstants.COLUMN_SERVICO, servicosVO.getServico());
        values.put(ServicosConstants.COLUMN_VALOR, servicosVO.getValor());
        values.put(ServicosConstants.COLUMN_DESCRICAO, servicosVO.getDescricao());
        String busca = ServicosConstants.COLUMN_ID + " =? ";
        String[] dados = new String[] {servicosVO.getId().toString()};
        Integer l = db.update(ServicosConstants.TABLE_NAME, values, busca, dados);
        return (l > 0);
    }

    public List<ServicosVO> listar(SQLiteDatabase db) throws Exception {
        List<ServicosVO> clientes = new ArrayList<>();
        Cursor cursor = db.query(
                ServicosConstants.TABLE_NAME,
                new String[]{ServicosConstants.COLUMN_ID, ServicosConstants.COLUMN_SERVICO,
                        ServicosConstants.COLUMN_VALOR, ServicosConstants.COLUMN_DESCRICAO},
                null,
                null,
                null,
                null,
                ServicosConstants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(ServicosConstants.COLUMN_ID));
            String servico = cursor.getString(cursor.getColumnIndex(ServicosConstants.COLUMN_SERVICO));
            Float valor = cursor.getFloat(cursor.getColumnIndex(ServicosConstants.COLUMN_VALOR));
            String descricao = cursor.getString(cursor.getColumnIndex(ServicosConstants.COLUMN_DESCRICAO));
            ServicosVO cliente = new ServicosVO(id, servico, valor, descricao);
            clientes.add(cliente);
        }
        return clientes;
    }

    public ServicosVO selecionar(SQLiteDatabase db, Long id) throws Exception {
        ServicosVO cliente = null;
        Cursor cursor = db.query(
                ServicosConstants.TABLE_NAME,
                new String[]{ServicosConstants.COLUMN_ID, ServicosConstants.COLUMN_SERVICO,
                        ServicosConstants.COLUMN_VALOR, ServicosConstants.COLUMN_DESCRICAO},
                ServicosConstants.COLUMN_ID + " IS ? ",
                new String[]{id.toString()},
                null,
                null,
                ServicosConstants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            String servico = cursor.getString(cursor.getColumnIndex(ServicosConstants.COLUMN_SERVICO));
            Float valor = cursor.getFloat(cursor.getColumnIndex(ServicosConstants.COLUMN_VALOR));
            String descricao = cursor.getString(cursor.getColumnIndex(ServicosConstants.COLUMN_DESCRICAO));
            cliente = new ServicosVO(id, servico, valor, descricao);
        }
        return cliente;
    }

}
