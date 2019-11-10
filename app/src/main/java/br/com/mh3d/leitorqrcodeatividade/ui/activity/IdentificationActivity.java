package br.com.mh3d.leitorqrcodeatividade.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;
import br.com.mh3d.leitorqrcodeatividade.ui.viewmodel.TrabalhoViewModel;
import br.com.mh3d.leitorqrcodeatividade.utils.DateFormatUtils;

public class IdentificationActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int UPDATE_NOTE_REQUEST = 2;
    public static final String PRIMEIRA_VEZ =
            "br.com.mh3d.leitorqrcodeatividade.ui.activity.PRIMEIRA_VEZ";


    private TrabalhoViewModel trabalhoViewModel;
    private DateFormatUtils dateFormatUtils = new DateFormatUtils();
    private TextView txt_resultado;
    private Button btn_iniciar_atividade;
    private Button btn_finalizar_atividade;
    private String resultado;
    private EditText edit_txt_local;
    private String local;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        Intent intent = getIntent();
        final Trabalho trabalho = intent.getParcelableExtra("funcionarioRecebido");

        trabalhoViewModel = ViewModelProviders.of(this).get(TrabalhoViewModel.class);
        txt_resultado = (TextView) findViewById(R.id.txt_resultado);
        btn_iniciar_atividade = (Button) findViewById(R.id.btn_iniciar_atividade);
        btn_finalizar_atividade = (Button) findViewById(R.id.btn_finalizar_atividade);
        edit_txt_local = (EditText)findViewById(R.id.edit_txt_local);
        loadData();
        updateLocal();





        trabalhoViewModel.getUserById(trabalho.getId_usuario()).observe(this, new Observer<TrabalhoComAtividadeRelatorio>() {
            @Override
            public void onChanged(@Nullable final TrabalhoComAtividadeRelatorio ultimoTrabalho) {
                if (ultimoTrabalho == null) {
                    // Caso seja a primeira vez o usuario estiver utilizando este dispositivo
                    resultado = "Id : " + trabalho.getId_usuario() + "\nNome : " + trabalho.getNome_usuario();
                    txt_resultado.setText(resultado);
                    btn_iniciar_atividade.setVisibility(View.VISIBLE);
                    btn_finalizar_atividade.setVisibility(View.GONE);
                    btn_iniciar_atividade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            local = edit_txt_local.getText().toString();
                            trabalho.setLocal(local);
                            Intent intent = new Intent(IdentificationActivity.this, AddUpdateTrabalhodesActivity.class);

                            Bundle data = new Bundle();
                            data.putParcelable("Criar nova Tarefa", trabalho);
                            intent.putExtras(data);
                            saveLocal();
                            startActivityForResult(intent, ADD_NOTE_REQUEST);
                        }
                    });

                } else {
                    if (ultimoTrabalho.getTrabalho().getTempo_inicio() == null || ultimoTrabalho.getTrabalho().getTempo_fim() != null) {
                        //Iniciar nova atividade
                        resultado = "Id : " + ultimoTrabalho.getTrabalho().getId_usuario() + "\nNome : " + ultimoTrabalho.getTrabalho().getNome_usuario();
                        txt_resultado.setText(resultado);
                        btn_iniciar_atividade.setVisibility(View.VISIBLE);
                        btn_finalizar_atividade.setVisibility(View.GONE);
                        btn_iniciar_atividade.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                local = edit_txt_local.getText().toString();
                                Intent intent = new Intent(IdentificationActivity.this, AddUpdateTrabalhodesActivity.class);
                                Bundle data = new Bundle();
                                Trabalho novoTrabalho = new Trabalho();
                                novoTrabalho.setId_usuario(ultimoTrabalho.getTrabalho().getId_usuario());
                                novoTrabalho.setNome_usuario(ultimoTrabalho.getTrabalho().getNome_usuario());
                                novoTrabalho.setLocal(local);
                                data.putParcelable("Criar nova Tarefa", novoTrabalho);
                                saveLocal();
                                intent.putExtras(data);
                                startActivityForResult(intent, ADD_NOTE_REQUEST);
                            }
                        });


                    } else {
                        //Abrir ultima atividade e finalizar atividade
                        resultado = "Id : " + ultimoTrabalho.getTrabalho().getId_usuario() +
                                "\nNome : " + ultimoTrabalho.getTrabalho().getNome_usuario() +
                                "\nAtividade : " + ultimoTrabalho.getAtividades().getAtividade() +
                                "\nIniciado em : " + dateFormatUtils.convertUnixStringToDateString(ultimoTrabalho.getTrabalho().getTempo_inicio());
                        txt_resultado.setText(resultado);
                        btn_finalizar_atividade.setVisibility(View.VISIBLE);
                        btn_iniciar_atividade.setVisibility(View.GONE);
                        btn_finalizar_atividade.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(IdentificationActivity.this, AddUpdateTrabalhodesActivity.class);
                                Bundle data = new Bundle();
                                data.putParcelable("Atualizar nova Tarefa", ultimoTrabalho);
                                saveLocal();
                                intent.putExtras(data);
                                startActivityForResult(intent, UPDATE_NOTE_REQUEST);
                            }
                        });


                    }
                }
            }
        });


    }

    public void saveLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, edit_txt_local.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
    }
    public void updateLocal(){
        edit_txt_local.setText(text);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && data != null) {
            if (resultCode == RESULT_OK) {
                Trabalho trabalho = data.getParcelableExtra("Trabalho");
                //Toast.makeText(this, "Trabalho recebido", Toast.LENGTH_SHORT).show();
                trabalhoViewModel = ViewModelProviders.of(this).get(TrabalhoViewModel.class);
                trabalhoViewModel.insert(trabalho);
                Toast.makeText(this, "Trabalho salvo", Toast.LENGTH_LONG).show();
                finish();
            }
        }else if (requestCode ==  UPDATE_NOTE_REQUEST && data != null){
            if (resultCode == RESULT_OK) {
                TrabalhoComAtividadeRelatorio trabalho = data.getParcelableExtra("Trabalho");
                //Toast.makeText(this, "Trabalho recebido", Toast.LENGTH_SHORT).show();
                trabalhoViewModel = ViewModelProviders.of(this).get(TrabalhoViewModel.class);
                trabalhoViewModel.update(trabalho.getTrabalho());
                Toast.makeText(this, "Trabalho salvo", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
