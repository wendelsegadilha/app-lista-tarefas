package br.com.wendelsegadilha.listadetarefas.dao;

import java.util.List;

import br.com.wendelsegadilha.listadetarefas.model.Tarefa;

public interface ITarefaDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean excluir(Tarefa tarefa);
    public List<Tarefa> listar();
}
