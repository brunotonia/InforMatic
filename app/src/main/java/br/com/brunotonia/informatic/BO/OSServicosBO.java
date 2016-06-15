package br.com.brunotonia.informatic.BO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.brunotonia.informatic.DAO.DatabaseHelper;
import br.com.brunotonia.informatic.DAO.OSServicosDAO;
import br.com.brunotonia.informatic.VO.OSServicosVO;

/**
 * Created by bruno on 15/06/16.
 */
public class OSServicosBO {

    /* Singleton */
    private static OSServicosBO instance = null;

    private OSServicosDAO osServicosDAO = null;

    private OSServicosBO() {
        osServicosDAO = new OSServicosDAO();
    }

    public static OSServicosBO getInstance() {
        if (instance == null) {
            instance = new OSServicosBO();
        }
        return instance;
    }

    public Long adicionar(Context context, OSServicosVO osServicosVO) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        Long servicoID = -1L;
        /* Grava OSServico no BD */
        try {
            db.beginTransaction();
            servicoID = osServicosDAO.adicionar(db, osServicosVO);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicoID;
    }

    public boolean editar(Context context, OSServicosVO osServicosVO) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        boolean resultado = false;
        /* Salva alterações da OSServicos no BD */
        try {
            db.beginTransaction();
            resultado = osServicosDAO.editar(db, osServicosVO);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return resultado;
    }

    public List<OSServicosVO> listar(Context context) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        List<OSServicosVO> servicos = null;
        /* Recupera Lista de OSServicos do BD */
        try {
            db.beginTransaction();
            servicos = osServicosDAO.listar(db);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicos;
    }

    public OSServicosVO selecionar(Context context, Long servicoID) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        OSServicosVO osServicosVO = null;
        try {
            db.beginTransaction();
            osServicosVO = osServicosDAO.selecionar(db, servicoID);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.endTransaction();
            helper.close();
        }
        return osServicosVO;
    }

}
