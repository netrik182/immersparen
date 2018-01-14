package com.github.netrik182.immersparen;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int tam;
    double precoA = 0;
    double precoB = 0;
    double precoFinalA = 0;
    double precoFinalB = 0;
    double desconto_A;
    double desconto_B;
    double qtdA = 1;
    double qtdB = 1;
    int fatorA = 1;
    int fatorB = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editPrecoA = (EditText) findViewById(R.id.preco_A);
        final EditText editPrecoB = (EditText) findViewById(R.id.preco_B);
        final EditText editQtdA = (EditText) findViewById(R.id.qtd_A);
        final EditText editQtdB = (EditText) findViewById(R.id.qtd_B);
        editPrecoA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                precoA = i;
                calcPreco(editPrecoA);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
        editPrecoB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                precoB = i;
                calcPreco(editPrecoB);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
        editQtdA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                qtdA = i;
                calcPreco(editQtdA);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
        editQtdB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                qtdB = i;
                calcPreco(editQtdB);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });
        RadioGroup rgA = (RadioGroup) findViewById(R.id.radio_group_A);
        RadioGroup rgB = (RadioGroup) findViewById(R.id.radio_group_B);
        rgA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_kilo_A:                        // do operations specific to this selection
                        fatorA = 1;
                        calcPreco(editPrecoA);
                        break;
                    case R.id.radio_grama_A:                        // do operations specific to this selection
                        fatorA = 1000;
                        calcPreco(editPrecoA);
                        break;
                }
            }
        });
        rgB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_kilo_B:                        // do operations specific to this selection
                        fatorB = 1;
                        calcPreco(editPrecoB);
                        break;
                    case R.id.radio_grama_B:                        // do operations specific to this selection
                        fatorB = 1000;
                        calcPreco(editPrecoB);
                        break;
                }
            }
        });
        SeekBar seekBar_A = (SeekBar) findViewById(R.id.seekBar_A);
        final TextView textDesconto_A = (TextView) findViewById(R.id.desconto_A);
        SeekBar seekBar_B = (SeekBar) findViewById(R.id.seekBar_B);
        final TextView textDesconto_B = (TextView) findViewById(R.id.desconto_B);
        seekBar_A.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textDesconto_A.setText(String.valueOf(i) + " %");
                setDescontoA(Double.parseDouble(String.valueOf(i)));
                calcPreco(textDesconto_A);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });
        seekBar_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textDesconto_B.setText(String.valueOf(i) + " %");
                setDescontoB(Double.parseDouble(String.valueOf(i)));
                calcPreco(textDesconto_B);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });
    }

    /**
     * Displays the price for Product A.
     */
    public void displayForProduct(double precoA, int tam, double precoB) {
        TextView scoreViewA = (TextView) findViewById(R.id.preco_final_A);
        TextView scoreViewB = (TextView) findViewById(R.id.preco_final_B);
        scoreViewA.setText(String.format("R$ %.2f", precoA));
        scoreViewB.setText(String.format("R$ %.2f", precoB));
        if (tam == 0) {
            scoreViewA.setTextColor(Color.BLACK);
            scoreViewB.setTextColor(Color.BLACK);
        } else {
            if (tam == 2) {
                scoreViewB.setTextColor(Color.GREEN);
                scoreViewA.setTextColor(Color.BLACK);
            } else {
                scoreViewA.setTextColor(Color.GREEN);
                scoreViewB.setTextColor(Color.BLACK);
            }
        }
    }

    public void setDescontoA(Double desc) {
        desconto_A = desc / 100;
    }

    public void setDescontoB(Double desc) {
        desconto_B = desc / 100;
    }

    /**
     * This method is called when the CALCULAR button is clicked.
     */
    public void calcPreco(View view) {
        EditText editPrecoA = (EditText) findViewById(R.id.preco_A);
        EditText editPrecoB = (EditText) findViewById(R.id.preco_B);
        EditText editQtdA = (EditText) findViewById(R.id.qtd_A);
        EditText editQtdB = (EditText) findViewById(R.id.qtd_B);
        try {
            precoA = Double.parseDouble(editPrecoA.getText().toString());
        } catch (Exception e) {
            precoA = 0;
        }
        try {
            precoB = Double.parseDouble(editPrecoB.getText().toString());
        } catch (Exception e) {
            precoB = 0;
        }
        try {
            qtdA = Double.parseDouble(editQtdA.getText().toString());
        } catch (Exception e) {
            qtdA = 1;
        }
        try {
            qtdB = Double.parseDouble(editQtdB.getText().toString());
        } catch (Exception e) {
            qtdB = 1;
        }
        precoFinalA = precoA * (1 - desconto_A) / qtdA * fatorA;
        precoFinalB = precoB * (1 - desconto_B) / qtdB * fatorB;
        if (precoFinalA == precoFinalB) {
            tam = 0;
        } else {
            if (precoFinalA > precoFinalB) {
                tam = 2;
            } else {
                tam = 1;
            }
        }
        displayForProduct(precoFinalA, tam, precoFinalB);
    }

    /**
     * This method is called when the APAGAR button is clicked.
     */
    public void resetPreco(View view) {
        EditText editPrecoA = (EditText) findViewById(R.id.preco_A);
        editPrecoA.setText("");
        EditText editPrecoB = (EditText) findViewById(R.id.preco_B);
        editPrecoB.setText("");
        EditText editQtdA = (EditText) findViewById(R.id.qtd_A);
        editQtdA.setText("");
        EditText editQtdB = (EditText) findViewById(R.id.qtd_B);
        editQtdB.setText("");
        SeekBar descA = (SeekBar) findViewById(R.id.seekBar_A);
        descA.setProgress(0);
        SeekBar descB = (SeekBar) findViewById(R.id.seekBar_B);
        descB.setProgress(0);
        double value = 0;
        TextView scoreViewA = (TextView) findViewById(R.id.preco_final_A);
        scoreViewA.setText(String.format("R$ %.2f", value));
        scoreViewA.setTextColor(Color.BLACK);
        TextView scoreViewB = (TextView) findViewById(R.id.preco_final_B);
        scoreViewB.setText(String.format("R$ %.2f", value));
        scoreViewB.setTextColor(Color.BLACK);
    }
}
