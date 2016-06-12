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

import br.com.brunotonia.informatic.BO.ServicosBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.ServicosVO;


public class ServicosAddActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long servicoID = null;
    private ServicosVO servicosVO = null;

    /* Declaração de elementos de interface */
    TextView lblServicosAdd = null;
    EditText txtServico = null;
    EditText txtValor = null;
    EditText txtDescricao = null;
    Button btnLimpar = null;
    Button btnSalvar    = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_add);

        /* Inicialização de elementos de interface */
        lblServicosAdd = (TextView) findViewById(R.id.lblServicosAdd);
        txtServico = (EditText) findViewById(R.id.txtServico);
        txtValor = (EditText) findViewById(R.id.txtValor);
        txtDescricao = (EditText) findViewById(R.id.txtDescircao);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        /* Recuperar params */
        recuperarParams();

        /* Listeners dos  botões */
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCliente(ServicosAddActivity.this);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (servicoID != -1L) {
                    recuperarServico(servicoID);
                } else {
                    txtServico.setText("");
                    txtValor.setText("");
                    txtDescricao.setText("");
                }
            }
        });

    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        servicoID = params.getLong("servicoID");
        /* clienteID > -1L é edição de Cliente */
        if (servicoID > -1L) {
            lblServicosAdd.setText("Editar Serviço");
            recuperarServico(servicoID);
            if (servicosVO != null) {
                povoarElementos(servicosVO);
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
    private void recuperarServico(Long clienteID) {
        ServicosBO servicosBO = ServicosBO.getInstance();
        try {
            servicosVO = servicosBO.selecionar(this, clienteID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (servicosVO != null) {
            povoarElementos(servicosVO);
        }
    }

    /* Povoa EditText */
    private void povoarElementos(ServicosVO servico) {
        txtServico.setText(servico.getServico());
        txtValor.setText(servico.getValor().toString());
        txtDescricao.setText(servico.getDescricao());
    }

    /* Salvar cliente */
    private void salvarCliente(Context context) {
        ServicosBO servicosBO = ServicosBO.getInstance();
        servicosVO = new ServicosVO(txtDescricao.getText().toString(), new Float(txtValor.getText().toString()),
                txtDescricao.getText().toString());
        if (servicoID != -1l) {
            boolean resultado = false;
            servicosVO.setId(servicoID);
            try {
                resultado = servicosBO.editar(this, servicosVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (resultado) {
                /* Exibe mensagem de sucesso e retorna para tela ClientesMenuActivity */
                Toast.makeText(context, "Serviço editado com sucesso!", Toast.LENGTH_LONG).show();
                it = new Intent(context, ServicosMenuActivity.class);
                startActivity(it);
            } else {
                /* Exibe mensagem de erro */
                Toast.makeText(context, "Erro! Não foi possível editar os dados do Serviço", Toast.LENGTH_LONG).show();
            }
        } else {
            Long reposta = -1L;
            try {
                reposta = servicosBO.adicionar(context, servicosVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (reposta != -1L) {
                /* Exibe mensagem de sucesso e retorna para tela ClientesMenuActivity */
                Toast.makeText(context, "Serviço adicionado com sucesso!", Toast.LENGTH_LONG).show();
                it = new Intent(context, ServicosMenuActivity.class);
                startActivity(it);
            } else {
                /* Exibe mensagem de erro */
                Toast.makeText(context, "Erro! Não foi possível adicionar o Serviço", Toast.LENGTH_LONG).show();
            }
        }
    }
}
