package br.com.brunotonia.informatic.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.brunotonia.informatic.Constants.OSServicosContants;
import br.com.brunotonia.informatic.VO.OSServicosVO;

/**
 * Created by bruno on 15/06/16.
 */
public class OSServicosDAO {

    public Long adicionar(SQLiteDatabase db, OSServicosVO osServicosVO) throws Exception {
        ContentValues values = new ContentValues();
        values.put(OSServicosContants.COLUMN_ID, osServicosVO.getId());
        values.put(OSServicosContants.COLUMN_OS, osServicosVO.getOs());
        values.put(OSServicosContants.COLUMN_SERVICO, osServicosVO.getServico());
        values.put(OSServicosContants.COLUMN_VALOR, osServicosVO.getValor().toString());
        return db.insert(OSServicosContants.TABLE_NAME, null, values);
    }

    public boolean editar(SQLiteDatabase db, OSServicosVO osServicosVO) throws Exception {
        ContentValues values = new ContentValues();
        values.put(OSServicosContants.COLUMN_ID, osServicosVO.getId());
        values.put(OSServicosContants.COLUMN_OS, osServicosVO.getOs());
        values.put(OSServicosContants.COLUMN_SERVICO, osServicosVO.getServico());
        values.put(OSServicosContants.COLUMN_VALOR, osServicosVO.getValor().toString());
        String busca = OSServicosContants.COLUMN_ID + " =? ";
        String[] dados = new String[] {osServicosVO.getId().toString()};
        Integer resultado = db.update(OSServicosContants.TABLE_NAME, values, busca, dados);
        return (resultado != 0);
    }

    public List<OSServicosVO> listar(SQLiteDatabase db) throws Exception {
        List<OSServicosVO> osServicosVOs = new ArrayList<>();
        Cursor cursor = db.query(
                OSServicosContants.TABLE_NAME,
                new String[]{OSServicosContants.COLUMN_ID, OSServicosContants.COLUMN_OS,
                        OSServicosContants.COLUMN_SERVICO, OSServicosContants.COLUMN_VALOR},
                null,
                null,
                null,
                null,
                OSServicosContants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_ID));
            Long os = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_OS));
            Long servico = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_SERVICO));
            Float valor = cursor.getFloat(cursor.getColumnIndex(OSServicosContants.COLUMN_VALOR));
            OSServicosVO cliente = new OSServicosVO(id, servico, valor);
            osServicosVOs.add(cliente);
        }
        return osServicosVOs;
    }

    public List<OSServicosVO> listar(SQLiteDatabase db, Long osID) throws Exception {
        List<OSServicosVO> osServicosVOs = new ArrayList<>();
        Cursor cursor = db.query(
                OSServicosContants.TABLE_NAME,
                new String[]{OSServicosContants.COLUMN_ID, OSServicosContants.COLUMN_OS,
                        OSServicosContants.COLUMN_SERVICO, OSServicosContants.COLUMN_VALOR},
                OSServicosContants.COLUMN_OS + " IS ? ",
                new String[]{osID.toString()},
                null,
                null,
                OSServicosContants.COLUMN_ID
        );
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_ID));
            Long servico = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_SERVICO));
            Float valor = cursor.getFloat(cursor.getColumnIndex(OSServicosContants.COLUMN_VALOR));
            OSServicosVO cliente = new OSServicosVO(id, osID, servico, valor);
            osServicosVOs.add(cliente);
        }
        return osServicosVOs;
    }

    public OSServicosVO selecionar(SQLiteDatabase db, Long id) throws Exception {
        OSServicosVO osServicosVO = null;
        Cursor cursor = db.query(
                OSServicosContants.TABLE_NAME,
                new String[]{OSServicosContants.COLUMN_ID, OSServicosContants.COLUMN_OS,
                        OSServicosContants.COLUMN_SERVICO, OSServicosContants.COLUMN_VALOR},
                OSServicosContants.COLUMN_ID + " IS ? ",
                new String[]{id.toString()},
                null,
                null,
                OSServicosContants.COLUMN_ID
        );
        while (cursor.moveToNext()) {;
            Long os = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_OS));
            Long servico = cursor.getLong(cursor.getColumnIndex(OSServicosContants.COLUMN_SERVICO));
            Float valor = cursor.getFloat(cursor.getColumnIndex(OSServicosContants.COLUMN_VALOR));
            osServicosVO = new OSServicosVO(id, os, servico, valor);
        }
        return osServicosVO;
    }

}
