package br.com.brunotonia.informatic.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        /* Listeners dos  botões */
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente(ClientesAddActivity.this);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clienteID != -1L) {
                    recuperarCliente(clienteID);
                } else {
                    txtNome.setText("");
                    txtTelefone.setText("");
                    txtEndereco.setText("");
                    txtEmail.setText("");
                }
            }
        });

    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        clienteID = params.getLong("clienteID");
        /* clienteID > -1L é edição de Cliente */
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
                it.putExtras(params);
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

    /* Salvar cliente */
    private void salvarCliente(Context context) {
        ClientesBO clientesBO = ClientesBO.getInstance();
        clientesVO = new ClientesVO(txtNome.getText().toString(), txtTelefone.getText().toString(),
                txtEndereco.getText().toString(), txtEmail.getText().toString());
        if (clienteID != -1l) {
            boolean resultado = false;
            try {
                resultado = clientesBO.editar(this, clientesVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (resultado) {
                /* Exibe mensagem de sucesso e retorna para tela ClientesMenuActivity */
                Toast.makeText(context, "Cliente editado com sucesso!", Toast.LENGTH_LONG).show();
                it = new Intent(context, ClientesMenuActivity.class);
                startActivity(it);
            } else {
                /* Exibe mensagem de erro */
                Toast.makeText(context, "Erro! Não foi possível editar os dados do Cliente", Toast.LENGTH_LONG).show();
            }
        } else {
            Long reposta = -1L;
            try {
                reposta = clientesBO.adicionar(context, clientesVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (reposta > -1L) {
                /* Exibe mensagem de sucesso e retorna para tela ClientesMenuActivity */
                Toast.makeText(context, "Cliente adicionado com sucesso!", Toast.LENGTH_LONG).show();
                it = new Intent(context, ClientesMenuActivity.class);
                startActivity(it);
            } else {
                /* Exibe mensagem de erro */
                Toast.makeText(context, "Erro! Não foi possível adicionar o Cliente", Toast.LENGTH_LONG).show();
            }
        }
    }

}
