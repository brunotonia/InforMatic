package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.brunotonia.informatic.BO.ClientesBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.ClientesVO;


public class ClientesAddActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long clienteID = null;
    private ClientesVO clientesVO = null;

    /* Declaração de elementos de interface */
    TextView lblClientesAdd = null;
    EditText txtNome = null;
    EditText txtTelefone = null;
    EditText txtEndereco = null;
    EditText txtEmail = null;
    Button btnLimpar = null;
    Button btnSalvar    = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_add);

        /* Inicialização de elementos de interface */
        lblClientesAdd = (TextView) findViewById(R.id.lblClientesAdd);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        /* Recuperar params */
        recuperarParams();

    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        clienteID = params.getLong("clienteID");
        /* clienteID > 0 é edição de Cliente */
        if (clienteID > -1L) {
            lblClientesAdd.setText("Editar Cliente");
            recuperarCliente(clienteID);
            if (clientesVO != null) {
                povoarElementos(clientesVO);
            }
            else {
                /* Exibe mensagem de erro e volta a tela anterior */
                Toast.makeText(this, "Erro! Não foi possível carregar os dados do Cliente", Toast.LENGTH_LONG).show();
                params.putLong("clienteID", -2);
                it = new Intent(this, ClientesListarActivity.class);
                startActivity(it);
            }
        }
    }

    /* Recupera dados do cliente */
    private void recuperarCliente(Long clienteID) {
        ClientesBO clientesBO = ClientesBO.getInstance();
        try {
            clientesVO = clientesBO.selecionar(this, clienteID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (clientesVO != null) {
            povoarElementos(clientesVO);
        }
    }

    /* Povoa EditText */
    private void povoarElementos(ClientesVO cliente) {
        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
        txtEndereco.setText(cliente.getEndereco());
        txtEmail.setText(cliente.getEmail());
    }


}
