package br.com.wendelsegadilha.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String nomeTarefa;
    private int status;

    public Tarefa() {
    }

    public Tarefa(Long id, String nomeTarefa, int status) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
