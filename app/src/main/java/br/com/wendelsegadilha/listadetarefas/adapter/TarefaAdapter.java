package br.com.wendelsegadilha.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.wendelsegadilha.listadetarefas.R;
import br.com.wendelsegadilha.listadetarefas.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> listaTarefas;
    public TarefaAdapter(List<Tarefa> lista) {
        this.listaTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_tarefa_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = listaTarefas.get(position);
        holder.tarefa.setText(tarefa.getNomeTarefa());
        if (tarefa.getStatus() == 1){
            holder.status.setText("Concluída");
        }else{
            holder.status.setText("Não concluída");
        }
        holder.dataStatus.setText(tarefa.getDataStatus());
    }

    @Override
    public int getItemCount() {
        return this.listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tarefa;
        private TextView status;
        private TextView dataStatus;
        public MyViewHolder(View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textTarefa);
            status = itemView.findViewById(R.id.textStatus);
            dataStatus = itemView.findViewById(R.id.textDataStatus);
        }
    }

}
