package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.brunotonia.informatic.R;

public class OSServicosMenuActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;
    private Long clienteID = null;
    private Long osID = null;
    private Integer osAcao = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osservicos_menu);

        /* Recuperar params */
        recuperarParams();


    }

    /* Recuperar params */
    private void recuperarParams() {
        it = getIntent();
        params = it.getExtras();
        clienteID = params.getLong("clienteID");
        osID = params.getLong("osID");
        osAcao = params.getInt("osAção");
    }

}
