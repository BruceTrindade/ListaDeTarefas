package com.example.listadetarefasapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    private Context context;

    private static  final  String DatabaseName = "GerenciadorDeTarefas.db";
    public static  final  int DatabaseVersion = 1;

    private static final String Tabela = "GerenciadorDeTarefas";
    private static final String ColunaID = "ID";
    private static final String ColunaTarefas = "Tarefa";
    private static final String ColunaDescricao = "Descricao";
    private static final String ColunaData ="Data";
    private static final String ColunaHora = "Hora";

    public DataBase (@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) { //Criar Tabela
        String query = "CREATE TABLE " + Tabela +
                " (" + ColunaID + " Integer Primary Key Autoincrement, " +
                ColunaTarefas + " TEXT, " +
                ColunaDescricao + " TEXT, " +
                ColunaData + " TEXT, " +
                ColunaHora + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tabela);
        onCreate(db);
    }

    //Adicionar Tarefa
    void AdicionarTarefa (String Tarefa, String Descricao, String Data, String Hora){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

            cv.put(ColunaTarefas, Tarefa);
            cv.put(ColunaDescricao, Descricao);
            cv.put(ColunaData, Data);
            cv.put(ColunaHora, Hora);
            long resultado = db.insert(Tabela, null, cv);
        if (resultado == -1) {
            Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
        }





    }







}
