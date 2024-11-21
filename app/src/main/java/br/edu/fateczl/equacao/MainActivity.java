/*
 * @author: Nicolas Domingos
 */

package br.edu.fateczl.equacao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText etNumA;
    private EditText etNumB;
    private EditText etNumC;
    private TextView tvDelta;
    private TextView tvX1;
    private TextView tvX2;
    private TextView tvNoRaiz;
    private TextView tvNaoE;
    private Button bCalcular;
    private Button bLimpar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNumA = findViewById(R.id.numA);
        etNumB = findViewById(R.id.numB);
        etNumC = findViewById(R.id.numC);
        tvDelta = findViewById(R.id.tvDelta);
        tvX1 = findViewById(R.id.tvX1);
        tvX2 = findViewById(R.id.tvX2);
        tvNaoE = findViewById(R.id.tvNaoE);
        tvNoRaiz = findViewById(R.id.tvNoRaiz);

        bCalcular = findViewById(R.id.bCalcular);
        bLimpar = findViewById(R.id.bLimpar);

        bCalcular.setOnClickListener(click -> calcularDelta());
        bLimpar.setOnClickListener(click -> limparTela());
    }

    private void limparTela() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    private void calcularDelta() {
        int A = Integer.parseInt(etNumA.getText().toString());
        int B = Integer.parseInt(etNumB.getText().toString());
        int C = Integer.parseInt(etNumC.getText().toString());

        if(A == 0){
            tvNaoE.setVisibility(TextView.VISIBLE);
        }else {
            double delta = (B * B) - (4 * A * C);

            if (delta < 0) {
                tvNoRaiz.setVisibility(TextView.VISIBLE); //Não tem raiz
            } else if (delta == 0) {
                double x1 = (-B + delta) / (2 * A);
                String resul1 = Double.toString(x1);
                String xis1 = R.string.x1 + resul1;
                String sDelta = Double.toString(delta);

                tvDelta.setText(sDelta);
                tvX1.setText(xis1);
            } else {
                double x1 = (-B - delta) / (2 * A);
                double x2 = (-B + delta) / (2 * A);

                String resul1 = Double.toString(x1);
                String resul2 = Double.toString(x2);
                String xis1 = getString(R.string.x1) + " " + resul1;
                String xis2 = getString(R.string.x2) + " " + resul2;
                String sDelta = Double.toString(delta);

                tvDelta.setText(sDelta);
                tvX1.setText(xis1);
                tvX2.setText(xis2);
            }
        }
    }
}