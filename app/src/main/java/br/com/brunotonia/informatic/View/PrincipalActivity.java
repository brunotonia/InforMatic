package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.brunotonia.informatic.R;

public class PrincipalActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;

    /* Easter Egg */
    private Integer contagem = 0;

    /* Declaração de elementos de interface */
    private Button btnClientes = null;
    private Button btnOServicos = null;
    private Button btnServicos = null;
    private ImageView imgLogo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        /* Inicialização de elementos de interface */
        btnClientes = (Button) findViewById(R.id.btnClientes);
        btnOServicos = (Button) findViewById(R.id.btnOServicos);
        btnServicos = (Button) findViewById(R.id.btnServicos);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);

        /* Listeners dos botões */
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contagem = 0;
                chamarTelaClientes();
            }
        });

        btnOServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contagem = 0;
                chamarTelaOServicos();
            }
        });

        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contagem = 0;
                chamarTelaServicos();
            }
        });

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contagem == 5) {
                    chamarTelaSobre();
                } else {
                    contagem++;
                }
            }
        });

    }

    /* Métodos para chamar novas telas */
    private void chamarTelaClientes () {
        it = new Intent(this, ClientesMenuActivity.class);
        startActivity(it);
    }

    private void chamarTelaOServicos () {
        it = new Intent(this, OSMenuActivity.class);
        startActivity(it);
    }

    private void chamarTelaServicos () {
        it = new Intent(this, ServicosMenuActivity.class);
        startActivity(it);
    }

    private void chamarTelaSobre () {
        it = new Intent(this, DesenvolvedoresActivity.class);
        startActivity(it);
    }
}
