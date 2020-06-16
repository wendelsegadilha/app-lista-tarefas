package br.com.wendelsegadilha.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.wendelsegadilha.listadetarefas.R;
import br.com.wendelsegadilha.listadetarefas.dao.TarefaDAO;
import br.com.wendelsegadilha.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //recuperar tarefa, caso seja edicao
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //configura tarefa na caixa de texto
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:
                //executa acao para o item salvar ou editar
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                Tarefa tarefa;
                String nomeTarefa = editTarefa.getText().toString();

                if(tarefaAtual != null){ //edicao
                    if(!nomeTarefa.isEmpty()){//verifica se nao o campo esta vazio
                        tarefa = new Tarefa();
                        tarefa.setId(tarefaAtual.getId());
                        tarefa.setNomeTarefa(nomeTarefa);
                        if(tarefaDAO.atualizar(tarefa)){//verifica se atualizou a tarefa
                            finish();//finaliza a activity
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar tarefa", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar tarefa", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Digite uma tarefa", Toast.LENGTH_LONG).show();
                    }
                }else{ //salvar
                    if(!nomeTarefa.isEmpty()){ //verifica se nao o campo esta vazio
                        tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if(tarefaDAO.salvar(tarefa)){ //verifica se salvou a tarefa
                            finish();//finaliza a activity
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Digite uma tarefa", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
