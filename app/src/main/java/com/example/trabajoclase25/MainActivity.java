package com.example.trabajoclase25;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    View circulo;
    Button btnHilo;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circulo = findViewById(R.id.circulo);
        btnHilo = findViewById(R.id.btnHilo);

        btnHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (thread == null || !thread.isAlive()) {

                    btnHilo.setText("Detener Semáforo");

                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int estado = 0;

                            try {
                                while (true) {
                                    int finalEstado = estado;

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            switch (finalEstado) {
                                                case 0:
                                                    circulo.setBackgroundColor(Color.RED);
                                                    break;
                                                case 1:
                                                    circulo.setBackgroundColor(Color.GREEN);
                                                    break;
                                                case 2:
                                                    circulo.setBackgroundColor(Color.YELLOW);
                                                    break;
                                            }
                                        }
                                    });

                                    estado = (estado + 1) % 3;
                                    Thread.sleep(5000);
                                }
                            } catch (InterruptedException e) {
                            
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    btnHilo.setText("Iniciar Semáforo");
                                }
                            });
                        }
                    });

                    thread.start();

                } else {

                    thread.interrupt();
                }
            }
        });
    }
}