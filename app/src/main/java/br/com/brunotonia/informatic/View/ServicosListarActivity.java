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

import br.com.brunotonia.informatic.BO.ServicosBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.ServicosVO;

public class ServicosListarActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long servicoID = null;

    /* Outras Variáveis */
    private ServicosVO servicosVO = null;
    private List<ServicosVO> lista = null;
    private ArrayAdapter<ServicosVO> adapter = null;

    /* Variáveis de Tela*/
    private ListView listServicos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_listar);

        /* Inicialização de elementos de interface */
        listServicos = (ListView) findViewById(R.id.listServicos);

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
            adapter = new ArrayAdapter<ServicosVO>(this, android.R.layout.simple_list_item_1, lista);
            listServicos.setAdapter(adapter);
        }

        /* Listeners */
        listServicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                servicosVO = (ServicosVO) listServicos.getItemAtPosition(position);
                chamarTelaServicosEditar(servicosVO);
            }
        });

    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        servicoID = params.getLong("servicoID");
        /* servicoID > -1L é edição de Cliente */
        if (servicoID == -2L) {
            carregarServicos();
        } else {
            /* Exibe mensagem de erro e volta a tela anterior */
            Toast.makeText(this, "Erro! Não foi possível carregar a lista de Clientes", Toast.LENGTH_LONG).show();
            params = new Bundle();
            it.putExtras(params);
            it = new Intent(this, ServicosMenuActivity.class);
            startActivity(it);
        }
    }

    /* Carrgar lista de Clientes */
    private void carregarServicos() {
        ServicosBO servicosBO = ServicosBO.getInstance();
        try {
            this.lista = servicosBO.listar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chamarTelaServicosEditar(ServicosVO servicosVO) {
        params = new Bundle();
        params.putLong("servicoID", servicosVO.getId());
        it = new Intent(this, ServicosAddActivity.class);
        it.putExtras(params);
        startActivity(it);
    }

}
