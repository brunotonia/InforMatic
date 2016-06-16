package br.com.brunotonia.informatic.View;

import android.content.Context;
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
import br.com.brunotonia.informatic.BO.OrdemDeServicosBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.ClientesVO;
import br.com.brunotonia.informatic.VO.OrdemDeServicosVO;

public class OSClientesSelecionarActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long osID = null;

    /* Outras Variáveis */
    private List<ClientesVO> lista = null;
    private ClientesVO clientesVO = null;
    private ArrayAdapter<ClientesVO> adapter = null;

    /* Variáveis de Tela*/
    private ListView listClientes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osclientes_selecionar);

        /* Inicialização de elementos de interface */
        listClientes = (ListView) findViewById(R.id.listClientes);

        /* Recuperar params */
        recuperarParams();

        if (lista.isEmpty()) {
            /* Exibe mensagem de erro e retorna a tela anterior */
            Toast.makeText(this, "Erro! Não foi possível carregar a lista de Clientes", Toast.LENGTH_LONG).show();
            params = new Bundle();
            it.putExtras(params);
            it = new Intent(this, OSMenuActivity.class);
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
                chamarTelaOSServicosMenu(OSClientesSelecionarActivity.this, clientesVO);
            }
        });
    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        carregarClientes();
    }

    /* Carregar params */
    private void carregarParams() {
        params = new Bundle();
        params.putLong("osID", osID);
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
    private void chamarTelaOSServicosMenu(Context context, ClientesVO clientesVO) {
        osID = gravarOrdemDeServico(context, clientesVO);
        if (osID != -1L) {
            carregarParams();
            it = new Intent(this, OSVisualizarActivity.class);
            it.putExtras(params);
            startActivity(it);
        } else {
            Toast.makeText(this, "Erro! Não foi possível adicionar a Ordem de Serviços", Toast.LENGTH_LONG).show();
        }
    }

    /* Salvar Ordem de Serviço */
    private Long gravarOrdemDeServico(Context context, ClientesVO clientesVO) {
        OrdemDeServicosVO ordemDeServicosVO = new OrdemDeServicosVO(clientesVO.getId(), 0L);
        OrdemDeServicosBO ordemDeServicosBO = OrdemDeServicosBO.getInstance();
        Long id = -1L;
        try {
            id = ordemDeServicosBO.adicionar(this, ordemDeServicosVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
