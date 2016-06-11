package br.com.brunotonia.informatic.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.brunotonia.informatic.Constants.OrdemDeServicosConstants;
import br.com.brunotonia.informatic.Constants.ServicosConstants;
import br.com.brunotonia.informatic.VO.OrdemDeServicosVO;
import br.com.brunotonia.informatic.VO.ServicosVO;

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

    public List<OrdemDeServicosVO> listar(SQLiteDatabase db) throws Exception {
        List<OrdemDeServicosVO> listaOS = new ArrayList<>();
        Cursor cursor = db.query(
                ServicosConstants.TABLE_NAME,
                new String[]{OrdemDeServicosConstants.COLUMN_ID, OrdemDeServicosConstants.COLUMN_CLIENTE,
                        OrdemDeServicosConstants.COLUMN_SITUACAO},
                null,
                null,
                null,
                null,
                OrdemDeServicosConstants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_ID));
            Long cliente = cursor.getLong(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_CLIENTE));
            Integer situacao = cursor.getInt(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_SITUACAO));
            OrdemDeServicosVO ordemDeServicosVO = new OrdemDeServicosVO(id, cliente, situacao);
            listaOS.add(ordemDeServicosVO);
        }
        return listaOS;
    }

    public List<OrdemDeServicosVO> listar(SQLiteDatabase db, Integer situacao) throws Exception {
        List<OrdemDeServicosVO> listaOS = new ArrayList<>();
        Cursor cursor = db.query(
                OrdemDeServicosConstants.TABLE_NAME,
                new String[]{OrdemDeServicosConstants.COLUMN_ID, OrdemDeServicosConstants.COLUMN_CLIENTE,
                        OrdemDeServicosConstants.COLUMN_SITUACAO},
                OrdemDeServicosConstants.COLUMN_SITUACAO + " IS ? ",
                new String[]{situacao.toString()},
                null,
                null,
                OrdemDeServicosConstants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_ID));
            Long cliente = cursor.getLong(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_CLIENTE));
            OrdemDeServicosVO ordemDeServicosVO = new OrdemDeServicosVO(id, cliente, situacao);
            listaOS.add(ordemDeServicosVO);
        }
        return listaOS;
    }

    public OrdemDeServicosVO selecionar(SQLiteDatabase db, Long id) throws Exception {
        OrdemDeServicosVO ordemDeServicosVO = null;
        Cursor cursor = db.query(
                OrdemDeServicosConstants.TABLE_NAME,
                new String[]{ServicosConstants.COLUMN_ID, ServicosConstants.COLUMN_SERVICO,
                        ServicosConstants.COLUMN_VALOR, ServicosConstants.COLUMN_DESCRICAO},
                ServicosConstants.COLUMN_ID + " IS ? ",
                new String[]{id.toString()},
                null,
                null,
                OrdemDeServicosConstants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            Long cliente = cursor.getLong(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_CLIENTE));
            Integer situacao = cursor.getInt(cursor.getColumnIndex(OrdemDeServicosConstants.COLUMN_SITUACAO));
            ordemDeServicosVO = new OrdemDeServicosVO(id, cliente, situacao);
        }
        return ordemDeServicosVO;
    }

}
