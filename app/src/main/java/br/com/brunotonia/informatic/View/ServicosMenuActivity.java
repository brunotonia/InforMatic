package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.brunotonia.informatic.R;

public class ServicosMenuActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;

    /* Declaração de elementos de interface */
    Button btnAdicionar = null;
    Button btnEditar    = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_menu);

        /* Inicialização de elementos de interface */
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnEditar = (Button) findViewById(R.id.btnEditar);

        /* Listeners dos botões */
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaClientesAdd();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaClientesListar();
            }
        });

    }

    /* Métodos para chamar novas telas */
    private void chamarTelaClientesAdd () {
        params = new Bundle();
        it = new Intent(this, ServicosAddActivity.class);
        it.putExtras(params);
        startActivity(it);
    }

    private void chamarTelaClientesListar () {
        params = new Bundle();
        params.putLong("servicoAcao", 1L);
        it = new Intent(this, ServicosListarActivity.class);
        it.putExtras(params);
        startActivity(it);
    }
}
