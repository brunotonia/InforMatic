package br.com.brunotonia.informatic.BO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

import br.com.brunotonia.informatic.DAO.DatabaseHelper;
import br.com.brunotonia.informatic.DAO.OrdemDeServicosDAO;
import br.com.brunotonia.informatic.VO.OrdemDeServicosVO;

/**
 * Created by bruno on 10/06/16.
 */
public class OrdemDeServicosBO implements Serializable {

    /* Singleton */
    private static OrdemDeServicosBO instance = null;

    private OrdemDeServicosDAO ordemDeServicosDAO = null;

    private OrdemDeServicosBO() {
        ordemDeServicosDAO = new OrdemDeServicosDAO();
    }

    public static OrdemDeServicosBO getInstance() {
        if (instance == null) {
            instance = new OrdemDeServicosBO();
        }
        return instance;
    }

    public Long adicionar(Context context, OrdemDeServicosVO ordemDeServicosVO) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        Long servicoID = -1L;
        /* Grava OrdemDeServico no BD */
        try {
            db.beginTransaction();
            servicoID = ordemDeServicosDAO.adicionar(db, ordemDeServicosVO);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicoID;
    }

    public boolean editar(Context context, OrdemDeServicosVO ordemDeServicosVO) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        boolean resultado = false;
        /* Salva alterações do Servico no BD */
        try {
            db.beginTransaction();
            resultado = ordemDeServicosDAO.editar(db, ordemDeServicosVO);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return resultado;
    }

    public OrdemDeServicosVO selecionar(Context context, Long osID) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        OrdemDeServicosVO ordemDeServicosVO = null;
        try {
            db.beginTransaction();
            ordemDeServicosVO = ordemDeServicosDAO.selecionar(db, osID);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.endTransaction();
            helper.close();
        }
        return ordemDeServicosVO;
    }

    public List<OrdemDeServicosVO> listar(Context context) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        List<OrdemDeServicosVO> servicos = null;
        /* Recupera Lista de OSServicos do BD */
        try {
            db.beginTransaction();
            servicos = ordemDeServicosDAO.listar(db);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicos;
    }

    public List<OrdemDeServicosVO> listar(Context context, Long situacao) throws Exception {
        /* Variáveis do BD */
        DatabaseHelper helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        List<OrdemDeServicosVO> servicos = null;
        /* Recupera Lista de OSServicos do BD */
        try {
            db.beginTransaction();
            servicos = ordemDeServicosDAO.listar(db, situacao);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return servicos;
    }

}
