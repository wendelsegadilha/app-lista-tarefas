package br.com.wendelsegadilha.listadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.wendelsegadilha.listadetarefas.R;
import br.com.wendelsegadilha.listadetarefas.dao.TarefaDAO;
import br.com.wendelsegadilha.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private CheckBox checkStatus;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);
        checkStatus = findViewById(R.id.checkStatus);

        //recuperar tarefa, caso seja edicao
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //configura tarefa na caixa de texto
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
            checkStatus.setEnabled(true);
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
                        //verifica se status foi marcado como concluído
                        if (checkStatus.isChecked()){
                            tarefa.setStatus(1);
                            tarefa.setDataStatus(getDateTime());
                        }
                        if(tarefaDAO.atualizar(tarefa)){//verifica se atualizou a tarefa
                            finish();//finaliza a activity
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar tarefa", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Digite uma tarefa", Toast.LENGTH_SHORT).show();
                    }
                }else{ //salvar
                    if(!nomeTarefa.isEmpty()){ //verifica se nao o campo esta vazio
                        tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setStatus(0);
                        tarefa.setDataStatus("");
                        if(tarefaDAO.salvar(tarefa)){ //verifica se salvou a tarefa
                            finish();//finaliza a activity
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Digite uma tarefa", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     *Recupera a data atual do sistema
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
