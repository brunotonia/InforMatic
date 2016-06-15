package br.com.brunotonia.informatic.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import br.com.brunotonia.informatic.BO.ClientesBO;
import br.com.brunotonia.informatic.BO.OrdemDeServicosBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.ClientesVO;
import br.com.brunotonia.informatic.VO.OrdemDeServicosVO;

public class OSServicosMenuActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long osID = null;
    private OrdemDeServicosVO ordemDeServicosVO = null;
    private ClientesVO clientesVO = null;

    /* Variáveis de Tela*/
    private TextView lblOSid = null;
    private ListView listServicos = null;
    private TextView lblClienteNome = null;
    private TextView lblClienteTelefone = null;
    private TextView lblClienteEmail = null;
    private TextView lblValor = null;
    private TextView lblClienteEndereco = null;
    private Button btnConcluir = null;
    private Button btnServicos = null;

    /* Outras Variáveis */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osservicos_menu);

        /* Recuperar params */
        recuperarParams(this);

        /* Iniciar Variáveis de Tela */
        lblOSid = (TextView) findViewById(R.id.lblOSid);
        lblClienteNome = (TextView) findViewById(R.id.lblClienteNome);
        lblClienteTelefone = (TextView) findViewById(R.id.lblClienteTelefone);
        lblClienteEmail = (TextView) findViewById(R.id.lblClienteEmail);
        lblClienteEndereco = (TextView) findViewById(R.id.lblClienteEndereco);
        lblValor = (TextView) findViewById(R.id.lblValor);
        listServicos = (ListView) findViewById(R.id.listServicos);
        btnConcluir = (Button) findViewById(R.id.btnConcluir);
        btnServicos = (Button) findViewById(R.id.btnServicos);

        /* Editar rótulos */
        editarRotulos();

    }

    /* Recuperar params */
    private void recuperarParams(Context context) {
        it = getIntent();
        params = it.getExtras();
        osID = params.getLong("osID");
        // chamar outros métodos aqui
        recuperarOS(context);
        recuperarCliente(context);

    }

    /* Carregar params */

    /* Recuperar OS */
    private void recuperarOS (Context context) {
        OrdemDeServicosBO ordemDeServicosBO = OrdemDeServicosBO.getInstance();
        try {
            ordemDeServicosVO = ordemDeServicosBO.selecionar(context, osID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Recuperar Cliente */
    private void recuperarCliente (Context context) {
        ClientesBO clientesBO = ClientesBO.getInstance();
        try {
            clientesVO = clientesBO.selecionar(context, ordemDeServicosVO.getCliente());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Recuperar Serviços */
    private void recuperarServicos(Context context){

    }

    /* Calcula Valor */
    private Float calculaValor() {
        return 0F;
    }

    /* Editar rótulos */
    private void editarRotulos() {
        lblOSid.setText(osID.toString());
        lblClienteNome.setText(clientesVO.getNome());
        lblClienteTelefone.setText(clientesVO.getTelefone());
        lblClienteEmail.setText(clientesVO.getEmail());
        lblClienteEndereco.setText(clientesVO.getEndereco());
        lblValor.setText(calculaValor().toString());
    }

    /* Verificar situação */
    private void verificarSituacao() {
        if (ordemDeServicosVO.getSituacao() > 1L) {
            btnConcluir.setEnabled(false);
            btnServicos.setEnabled(false);
        } else {
            btnConcluir.setEnabled(true);
            btnServicos.setEnabled(true);
        }
    }

}
