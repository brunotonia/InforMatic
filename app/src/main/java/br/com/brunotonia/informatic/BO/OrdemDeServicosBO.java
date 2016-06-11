package br.com.brunotonia.informatic.BO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

import br.com.brunotonia.informatic.Constants.DataBaseHelper;
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
        DataBaseHelper helper = new DataBaseHelper(context);
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
        DataBaseHelper helper = new DataBaseHelper(context);
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
}
