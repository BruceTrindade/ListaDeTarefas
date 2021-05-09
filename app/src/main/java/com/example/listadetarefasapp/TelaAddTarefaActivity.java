package com.example.listadetarefasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TelaAddTarefaActivity extends AppCompatActivity {
    Context context;
    EditText Data;
    TextView Hora;
    int  hora, minuto;
    DatePickerDialog.OnDateSetListener setListener;

    EditText TarefaInput, DescricaoInput, DataInput;
    TextView HoraInput;
    Button ButaoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_add_tarefa);
        getSupportActionBar().setHomeButtonEnabled(true);// Ativa a seta de voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//Mostra a seta
        getSupportActionBar().setTitle("Gerenciador de Tarefas");//Titulo na bar

        TarefaInput = findViewById(R.id.TextTarefa);
        DescricaoInput = findViewById(R.id.TextDescricao);
        HoraInput = findViewById(R.id.TextHora);
        DataInput = findViewById(R.id.TextData);
        ButaoInput = findViewById(R.id.ButaoCadastar);
        ButaoInput.setOnClickListener(new View.OnClickListener() { //ação do botão Salvar Tarefa
            @Override
            public void onClick(View v) {
                DataBase myDB = new DataBase(TelaAddTarefaActivity.this);
                myDB.AdicionarTarefa(TarefaInput.getText().toString().trim(),
                        DescricaoInput.getText().toString().trim(),
                        DataInput.getText().toString().trim(),
                        HoraInput.getText().toString().trim());
            }

        });









        //Aqui começa o DatePicker

        Data = findViewById(R.id.TextData);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // DatePicker da data de cadastro
        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TelaAddTarefaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        Data.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        // Hora

        Hora = findViewById(R.id.TextHora);

        Hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inicializa o timePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        TelaAddTarefaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //inicializando hora minuto
                                hora = hourOfDay;
                                minuto = minute;

                                String time = hora + ":" + minuto;
                                //inicializando o formato 24 horas
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );

                                try {
                                    Date date = f24Hours.parse(time);
                                    //inicializando o formato 12 horas
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //Setando a hora no TextView
                                    Hora.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false

                );

                //setando o background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //setando a tela de seleção do horario
                timePickerDialog.updateTime(hora, minuto);
                //show dialog
                timePickerDialog.show();
            }
        });

    }
}