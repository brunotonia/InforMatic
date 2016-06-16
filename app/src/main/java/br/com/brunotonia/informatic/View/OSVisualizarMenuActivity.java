package br.com.brunotonia.informatic.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import br.com.brunotonia.informatic.R;

public class OSVisualizarMenuActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;
    private Bundle params = null;

    /* Variáveis de Tela*/
    private Button btnAbertas = null;
    private Button btnConcluidas = null;
    private Button btnCanceladas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osvisualizar_menu);

        /* Iniciar Variáveis de Tela */
        btnAbertas = (Button) findViewById(R.id.btnAbertas);
        btnConcluidas = (Button) findViewById(R.id.btnConcluidas);
        btnCanceladas = (Button) findViewById(R.id.btnCanceladas);

        /* Listeners dos  botões */
        btnAbertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params = new Bundle();
                params.putLong("osSituacao", 0L);
                chamarTelaOSListar(OSVisualizarMenuActivity.this);
            }
        });

        btnConcluidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params = new Bundle();
                params.putLong("osSituacao", 1L);
                chamarTelaOSListar(OSVisualizarMenuActivity.this);
            }
        });

        btnCanceladas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params = new Bundle();
                params.putLong("osSituacao", 2L);
                chamarTelaOSListar(OSVisualizarMenuActivity.this);
            }
        });



    }

    /* Chamar tela OSListar */
    private void chamarTelaOSListar(Context context) {
        it = new Intent(this, OSListarActivity.class);
        it.putExtras(params);
        startActivity(it);
    }
}
