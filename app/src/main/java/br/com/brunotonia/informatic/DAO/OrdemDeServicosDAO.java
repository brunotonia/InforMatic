package br.com.brunotonia.informatic.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import br.com.brunotonia.informatic.Constants.OrdemDeServicosConstants;
import br.com.brunotonia.informatic.VO.OrdemDeServicosVO;

/**
 * Created by bruno on 10/06/16.
 */
public class OrdemDeServicosDAO {

    public Long adicionar(SQLiteDatabase db, OrdemDeServicosVO ordemDeServicosVO) throws Exception {
        ContentValues values = new ContentValues();
        values.put(OrdemDeServicosConstants.COLUMN_CLIENTE, ordemDeServicosVO.getCliente());
        values.put(OrdemDeServicosConstants.COLUMN_SITUACAO, ordemDeServicosVO.getSituacao());
        return db.insert(OrdemDeServicosConstants.TABLE_NAME, null, values);
    }

    public boolean editar(SQLiteDatabase db, OrdemDeServicosVO ordemDeServicosVO) throws Exception {
        ContentValues values = new ContentValues();
        values.put(OrdemDeServicosConstants.COLUMN_CLIENTE, ordemDeServicosVO.getCliente());
        values.put(OrdemDeServicosConstants.COLUMN_SITUACAO, ordemDeServicosVO.getSituacao());
        String busca = OrdemDeServicosConstants.COLUMN_ID + " =? ";
        String[] dados = new String[] {ordemDeServicosVO.getId().toString()};
        Integer l = db.update(OrdemDeServicosConstants.TABLE_NAME, values, busca, dados);
        return (l > 0);
    }
}
