package br.com.wendelsegadilha.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String nomeTarefa;
    private int status;
    private String dataStatus;

    public Tarefa() {
    }

    public Tarefa(Long id, String nomeTarefa, int status, String dataStatus) {
        this.id = id;
        this.nomeTarefa = nomeTarefa;
        this.status = status;
        this.dataStatus = dataStatus;
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

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }
}
