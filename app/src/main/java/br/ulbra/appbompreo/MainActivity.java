package br.ulbra.appbompreo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText edProduto, edVal, edQuantP, edPerc;
    private RadioButton rbCartao, rbBoleto;
    private RadioGroup rbPg;
    private Button btCalcular;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponente();
    }

    public void calcular(View view) {
        int quant;
        double valor, perc, valPrest = 0;
        String produto, resultado = "";
        DecimalFormat f = new DecimalFormat("0.00");
        if (edProduto.equals("") || edVal.equals("") || edQuantP.equals("")
                || edPerc.equals("")) {
            resultado = "Preencha os campos";
        } else {
            produto = edProduto.getText().toString();
            valor = Double.parseDouble(edVal.getText().toString());
            quant = Integer.parseInt(edQuantP.getText().toString());
            perc = Double.parseDouble(edPerc.getText().toString());
            switch (rbPg.getCheckedRadioButtonId()) {
                case R.id.rbcartao:
                    if (valor >= 500 && (quant == 0 || quant == 1)) {
                        valPrest = valor;

                    } else if (valor < 500 && (quant == 0 || quant == 1)) {
                        valPrest = valor;
                    } else if (valor >= 500 && quant > 1) {
                        valPrest = valor / quant;

                    } else if (valor < 500 && quant > 1) {
                        valPrest = (valor * (perc * quant / 100 + 1) / quant);
                    }

                    resultado = produto + " Custa R$" + valor + " parcelado em " + quant
                            + " X no cartão \na prestação sairá por R$" + f.format(valPrest)
                    +"\nValor total R$"+f.format(valPrest*quant);
                    txtResultado.setText(resultado);
                    break;
                case R.id.rbboleto:
                    if (valor >= 500 && (quant == 0 || quant == 1)) {
                        valPrest = valor * 1.02;

                    } else if (valor < 500 && (quant == 0 || quant == 1)) {
                        valPrest = valor*1.02;
                    } else if (valor >= 500 && quant > 1) {
                        valPrest = valor / quant * 1.02;

                    } else if (valor < 500 && quant > 1) {
                        valPrest = (valor * (perc * quant / 100 + 1) / quant) * 1.02;
                    }

                    resultado = produto + " Custa R$" + valor + " parcelado em " + quant
                            + " X no boleto (+2%) \na prestação sairá por R$" + f.format(valPrest)
                            +"\nValor total R$"+f.format(valPrest*quant);
                    txtResultado.setText(resultado);
                    break;
            }
        }

    }

    private void inicializarComponente() {
        edProduto = findViewById(R.id.edtprod);
        edVal = findViewById(R.id.edtvalor);
        edQuantP = findViewById(R.id.edtquantp);
        edPerc = findViewById(R.id.edttaxajuros);
        btCalcular = findViewById(R.id.btCalcular);
        rbPg = findViewById(R.id.rgpg);
        rbCartao = findViewById(R.id.rbcartao);
        rbBoleto = findViewById(R.id.rbboleto);
        txtResultado = findViewById(R.id.txtResult);
    }
}