package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.brunotonia.informatic.BO.ClientesBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.ClientesVO;

public class ClientesSelecionarActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long clienteID = null;

    /* Outras Variáveis */
    private List<ClientesVO> lista = null;
    private ClientesVO clientesVO = null;
    private ArrayAdapter<ClientesVO> adapter = null;

    /* Variáveis de Tela*/
    private ListView listClientes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_selecionar);

        /* Inicialização de elementos de interface */
        listClientes = (ListView) findViewById(R.id.listClientes);

        /* Recuperar params */
        recuperarParams();

        if (lista.isEmpty()) {
            /* Exibe mensagem de erro e retorna a tela anterior */
            Toast.makeText(this, "Erro! Não foi possível carregar a lista de Clientes", Toast.LENGTH_LONG).show();
            params = new Bundle();
            it.putExtras(params);
            it = new Intent(this, ClientesMenuActivity.class);
            startActivity(it);
        } else {
            adapter = new ArrayAdapter<ClientesVO>(this, android.R.layout.simple_list_item_1, lista);
            listClientes.setAdapter(adapter);
        }

        /* Listeners */
        listClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clientesVO = (ClientesVO) listClientes.getItemAtPosition(position);
                chamarTelaClientesEditar(clientesVO);
            }
        });
    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        clienteID = params.getLong("clienteID");
        /* clienteID > -1L é edição de Cliente */
        if (clienteID == -2L) {
            carregarClientes();
        } else {
            /* Exibe mensagem de erro e volta a tela anterior */
            Toast.makeText(this, "Erro! Não foi possível carregar a lista de Clientes", Toast.LENGTH_LONG).show();
            params = new Bundle();
            it.putExtras(params);
            it = new Intent(this, ClientesMenuActivity.class);
            startActivity(it);
        }
    }

    /* Carrgar lista de Clientes */
    private void carregarClientes() {
        ClientesBO clientesBO = ClientesBO.getInstance();
        try {
            this.lista = clientesBO.listar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Metodo para chamar telas */
    private void chamarTelaClientesEditar(ClientesVO clientesVO) {
        params = new Bundle();
        params.putLong("clienteID", clientesVO.getId());
        it = new Intent(this, ClientesAddActivity.class);
        it.putExtras(params);
        startActivity(it);
    }
}
