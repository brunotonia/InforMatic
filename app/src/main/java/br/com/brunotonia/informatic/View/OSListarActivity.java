package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.brunotonia.informatic.BO.OrdemDeServicosBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.OrdemDeServicosVO;


public class OSListarActivity extends AppCompatActivity {

    //osID = params.getLong("osID");
    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long osSituacao = null;

    /* Variáveis de Tela*/
    private ListView listOSs = null;

    /* Outras Variáveis */
    private List<OrdemDeServicosVO> lista = null;
    private OrdemDeServicosVO ordemDeServicosVO = null;
    private ArrayAdapter<OrdemDeServicosVO> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oslistar);

        /* Inicialização de elementos de interface */
        listOSs = (ListView) findViewById(R.id.listClientes);

        /* Recuperar params */
        recuperarParams();

        /* Listeners */
        listOSs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ordemDeServicosVO = (OrdemDeServicosVO) listOSs.getItemAtPosition(position);
                chamarTelaOSVisualizar(ordemDeServicosVO);
            }
        });

    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        osSituacao = params.getLong("osSituacao");
        carregarOSs(osSituacao);

    }

    /* Carrgar lista de Clientes */
    private void carregarOSs(Long osSituacao) {
        OrdemDeServicosBO ordemDeServicosBO = OrdemDeServicosBO.getInstance();
        try {
            this.lista = ordemDeServicosBO.listar(this, osSituacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chamarTelaOSVisualizar(OrdemDeServicosVO ordemDeServicosVO) {
        params = new Bundle();
        params.putLong("osID", ordemDeServicosVO.getId());
        it = new Intent(this, ClientesAddActivity.class);
        it.putExtras(params);
        startActivity(it);
    }

}
