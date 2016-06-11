package br.com.brunotonia.informatic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.brunotonia.informatic.R;

public class PrincipalActivity extends AppCompatActivity {

    /* Variáveis entre telas */
    private Intent it = null;

    /* Declaração de elementos de interface */
    Button btnClientes = null;
    Button btnOServicos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        /* Inicialização de elementos de interface */
        btnClientes = (Button) findViewById(R.id.btnClientes);
        btnOServicos = (Button) findViewById(R.id.btnOServicos);

        /* Listeners dos botões */
        btnClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaClientes();
            }
        });

        btnOServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarTelaClientes();
            }
        });

    }

    // Métodos para chamar novas telas
    private void chamarTelaClientes () {
        it = new Intent(this, ClientesMenuActivity.class);
        startActivity(it);
    }

    private void chamarTelaOServicos () {
        it = new Intent(this, OSMenuActivity.class);
        startActivity(it);
    }
}
