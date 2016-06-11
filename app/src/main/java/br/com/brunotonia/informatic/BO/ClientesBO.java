package br.com.brunotonia.informatic.BO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.List;

import br.com.brunotonia.informatic.Constants.DataBaseHelper;
import br.com.brunotonia.informatic.DAO.ClientesDAO;
import br.com.brunotonia.informatic.VO.ClientesVO;

/**
 * Created by bruno on 10/06/16.
 */
public class ClientesBO implements Serializable {

    /* Singleton */
    private static ClientesBO instance = null;

    private ClientesDAO clientesDAO = null;

    private ClientesBO() {
        clientesDAO = new ClientesDAO();
    }

    public static ClientesBO getInstance() {
        if (instance == null) {
            instance = new ClientesBO();
        }
        return instance;
    }

    public Long adicionar(Context context, ClientesVO cliente) throws Exception {
        /* Variáveis do BD */
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        Long clienteID = -1L;
        /* Grava Cliente no BD */
        try {
            db.beginTransaction();
            clienteID = clientesDAO.adicionar(db, cliente);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            db.endTransaction();
            helper.close();
        }
        return clienteID;
    }

    public boolean editar(Context context, ClientesVO cliente) throws Exception {
        /* Variáveis do BD */
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        boolean resultado = false;
        /* Edita Cliente no BD */
        try {
            db.beginTransaction();
            resultado = clientesDAO.editar(db, cliente);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
        return resultado;
    }

    public List<ClientesVO> listar(Context context) throws Exception {
        /* Variáveis do BD */
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        List<ClientesVO> clientes = null;
        /* Recupera Lista de Clientes do BD */
        try {
            db.beginTransaction();
            clientes = clientesDAO.listar(db);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            db.endTransaction();
            helper.close();
        }
        return clientes;
    }

    public ClientesVO selecionar(Context context, Long clienteID) throws Exception {
        /* Variáveis do BD */
        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase db = helper.open();
        /* Variáveis do Método*/
        ClientesVO cliente = null;
        try {
            db.beginTransaction();
            cliente = clientesDAO.selecionar(db, clienteID);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            //throw e;
            return null;
        } finally {
            db.endTransaction();
            helper.close();
        }
        return cliente;
    }

}