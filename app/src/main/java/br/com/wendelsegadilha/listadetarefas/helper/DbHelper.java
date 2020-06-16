package br.com.wendelsegadilha.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DB_NOME = "db_tarefas";
    public static String TABELA_NOME = "tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NOME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABELA_NOME);
        sql.append(" (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);");

        try{
            db.execSQL(sql.toString());
            Log.i("INFO DB", "Tabela criada com sucesso");
        }catch (Exception e){
            Log.i("INFO DB", "Falha criar tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_NOME + ";";

        try{
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar app");
        }catch (Exception e){
            Log.i("INFO DB", "Falha ao atualizar app" + e.getMessage());
        }
    }
}
