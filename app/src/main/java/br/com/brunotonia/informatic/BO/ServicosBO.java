package br.com.brunotonia.informatic.BO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

import br.com.brunotonia.informatic.DAO.DatabaseHelper;
import br.com.brunotonia.informatic.DAO.ServicosDAO;
import br.com.brunotonia.informatic.VO.ServicosVO;

/**
 * Created by bruno on 10/06/16.
 */
public class ServicosBO implements Serializable {

    /* Singleton */
    private static ServicosBO instance = null;

    private ServicosDAO servicosDAO = null;

    private ServicosBO() {
        servicosDAO = new ServicosDAO();
    }

    public static ServicosBO getInstance() {
        if (instance == null) {
            instance = new ServicosBO();
        }
        return instance;
    }

    public Long adicionar(Context context, ServicosVO servicosVO) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        Long servicoID = -1L;
        /* Grava Servico no BD */
        try {
            db.beginTransaction();
            servicoID = servicosDAO.adicionar(db, servicosVO);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicoID;
    }

    public boolean editar(Context context, ServicosVO servicosVO) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        boolean resultado = false;
        /* Salva alterações do Servico no BD */
        try {
            db.beginTransaction();
            resultado = servicosDAO.editar(db, servicosVO);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return resultado;
    }

    public List<ServicosVO> listar(Context context) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        List<ServicosVO> servicos = null;
        /* Recupera Lista de Servicos do BD */
        try {
            db.beginTransaction();
            servicos = servicosDAO.listar(db);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicos;
    }

    public ServicosVO selecionar(Context context, Long servicoID) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        ServicosVO servicosVO = null;
        try {
            db.beginTransaction();
            servicosVO = servicosDAO.selecionar(db, servicoID);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicosVO;
    }

}
