package br.com.wendelsegadilha.listadetarefas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.wendelsegadilha.listadetarefas.helper.DbHelper;
import br.com.wendelsegadilha.listadetarefas.model.Tarefa;

public class TarefaDAO implements ITarefaDAO {

    private DbHelper db;
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        this.db = new DbHelper(context);
        this.escrever = db.getWritableDatabase();
        this.ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        cv.put("status", tarefa.getStatus());
        try{
            escrever.insert(DbHelper.TABELA_NOME, null, cv);
            Log.i("INFO", "Tarefa salva com sucesso");
        }catch(Exception e){
            Log.i("INFO", "Erro ao salvar tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        cv.put("status", tarefa.getStatus());
        try{
            String[] argumentos = {String.valueOf(tarefa.getId())};
            escrever.update(DbHelper.TABELA_NOME, cv, "id=?",argumentos);
            Log.i("INFO", "Tarefa atualizada com sucesso");
        }catch(Exception e){
            Log.i("INFO", "Erro ao atualizar tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean excluir(Tarefa tarefa) {
        try{
            String[] argumentos = {String.valueOf(tarefa.getId())};
            escrever.delete(DbHelper.TABELA_NOME, "id=?",argumentos);
            Log.i("INFO", "Tarefa excluída com sucesso");
        }catch(Exception e){
            Log.i("INFO", "Erro ao excluída tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_NOME + ";";
        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            int status = cursor.getInt(cursor.getColumnIndex("status"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nome);
            tarefa.setStatus(status);

            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
