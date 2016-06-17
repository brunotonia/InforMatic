package br.com.brunotonia.informatic.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import br.com.brunotonia.informatic.BO.OSServicosBO;
import br.com.brunotonia.informatic.BO.ServicosBO;
import br.com.brunotonia.informatic.R;
import br.com.brunotonia.informatic.VO.OSServicosVO;
import br.com.brunotonia.informatic.VO.ServicosVO;

public class OSServicosActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long osID = null;

    /* Declaração de elementos de interface */
    private Switch swAddDel = null;
    private ListView list = null;
    private ServicosVO servicosVO = null;
    private OSServicosVO osServicosVO = null;
    private List<ServicosVO> listaServicos = null;
    private List<OSServicosVO> listaOSServicos = null;
    private ArrayAdapter<ServicosVO> adapterServicos = null;
    private ArrayAdapter<OSServicosVO> adapterOSServicos = null;
    private Boolean adicionar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osservicos);

        /* Recuperar Params */
        recuperaParams();

        /* Inicializando elementos de Interface */
        swAddDel = (Switch) findViewById(R.id.swAddDel);
        list = (ListView) findViewById(R.id.list);

        /* Listener do Switch */
        swAddDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adicionar) {
                    adicionar = false;
                    carrgarLista();
                } else {
                    adicionar = true;
                    carrgarLista();
                }
            }
        });

        /* Listener da ListView */
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adicionar) {
                    servicosVO = (ServicosVO) list.getItemAtPosition(position);
                    adicionarOSServicos(OSServicosActivity.this);
                } else {

                }

            }
        });

    }

    /* Recuperar Params */
    private void recuperaParams() {
        adicionar = true;
        it = getIntent();
        params = it.getExtras();
        osID = params.getLong("osID");
        carrgarLista();
    }

    /* Carregar params */
    private void carregarParams() {
        params = new Bundle();
        params.putLong("osID", osID);
    }

    /* Carrgar listaServicos de Serviços */
    private void carregarServicos() {
        ServicosBO servicosBO = ServicosBO.getInstance();
        try {
            listaServicos = servicosBO.listar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listaServicos.isEmpty()) {
            /* Exibe mensagem de erro e retorna a tela anterior */
            Toast.makeText(this, "Erro! Não foi possível carregar a lista de Serviços", Toast.LENGTH_LONG).show();
            params = new Bundle();
            params.putLong("osID", osID);
            it.putExtras(params);
            it = new Intent(this, OSVisualizarActivity.class);
            startActivity(it);
        } else {
            list = (ListView) findViewById(R.id.list);
            adapterServicos = new ArrayAdapter<ServicosVO>(this, android.R.layout.simple_list_item_1, listaServicos);
            list.setAdapter(adapterServicos);
        }
    }

    private void carregarOSServicos() {
        OSServicosBO osServicosBO = OSServicosBO.getInstance();
        try {
            listaOSServicos = osServicosBO.listar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listaOSServicos.isEmpty()) {
            /* Exibe mensagem de erro e retorna a tela anterior */
            Toast.makeText(this, "Erro! Não foi possível carregar a lista de Serviços", Toast.LENGTH_LONG).show();
            params = new Bundle();
            params.putLong("osID", osID);
            it.putExtras(params);
            it = new Intent(this, OSVisualizarActivity.class);
            startActivity(it);
        } else {
            adapterOSServicos = new ArrayAdapter<OSServicosVO>(this, android.R.layout.simple_list_item_1, listaOSServicos);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapterOSServicos);
        }
    }

    private void carrgarLista() {
        if (adicionar) {
            carregarServicos();
        } else {
            carregarOSServicos();
        }
    }

    private void adicionarOSServicos(Context context) {
        OSServicosBO osServicosBO = OSServicosBO.getInstance();
        osServicosVO = new OSServicosVO(osID, servicosVO.getId(), servicosVO.getValor());
        Long resultado = -1L;
        try {
            resultado = osServicosBO.adicionar(context, osServicosVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultado != -1L) {
            carregarParams();
            it = new Intent(context, OSVisualizarActivity.class);
            it.putExtras(params);
            startActivity(it);
        } else {
            Toast.makeText(this, "Erro! Não foi possível adicionar o Serviço", Toast.LENGTH_LONG).show();
        }
    }

}
