package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.brunotonia.informatic.R;

public class OSMenuActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;

    /* Declaração de elementos de interface */
    private Button btnAdicionar = null;
    private Button btnVisualizar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osmenu);

        /* Inicialização de elementos de interface */
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnVisualizar = (Button) findViewById(R.id.btnVisualizar);

        /* Listeners dos botões */
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaAdicionarOS();
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaVisualizarOS();
            }
        });

    }

    /* Métodos para chamar novas telas */
    private void chamarTelaAdicionarOS() {
        it = new Intent(this, OSClientesSelecionarActivity.class);
        startActivity(it);
    }

    private void chamarTelaOServicos () {
        it = new Intent(this, OSListarActivity.class);
        startActivity(it);
    }

    private void chamarTelaVisualizarOS() {
        it = new Intent(this, OSVisualizarMenuActivity.class);
        startActivity(it);
    }

}
