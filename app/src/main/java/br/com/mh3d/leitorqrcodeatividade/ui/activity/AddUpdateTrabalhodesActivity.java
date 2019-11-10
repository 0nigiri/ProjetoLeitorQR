package br.com.mh3d.leitorqrcodeatividade.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;
import br.com.mh3d.leitorqrcodeatividade.ui.adapter.RelatorioAdapter;
import br.com.mh3d.leitorqrcodeatividade.ui.fragments.ConfirmFragment;
import br.com.mh3d.leitorqrcodeatividade.ui.viewmodel.AtividadeViewModel;
import br.com.mh3d.leitorqrcodeatividade.ui.adapter.AtividadeAdapter;
import br.com.mh3d.leitorqrcodeatividade.ui.viewmodel.RelatorioViewModel;

import static br.com.mh3d.leitorqrcodeatividade.ui.activity.IdentificationActivity.PRIMEIRA_VEZ;

public class AddUpdateTrabalhodesActivity extends AppCompatActivity implements ConfirmFragment.EnviarDialogListener {
    private AtividadeViewModel atividadeViewModel;
    private RelatorioViewModel relatorioViewModel;
    private TextView titulo;
    private Bundle data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades_relatorio);

        titulo = (TextView) findViewById(R.id.txt_titulo_addupdate_trabalho);
        this.data = getIntent().getExtras();


        if (data.containsKey("Criar nova Tarefa")) {


            RecyclerView recyclerView = findViewById(R.id.recyclerViewAtividadesRelatorio);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);



                titulo.setText("Selecione sua atividade");

                /**
                 *  Novo Trabalho primeira vez
                 */

                final Trabalho nTrabalho = data.getParcelable("Criar nova Tarefa");


                final AtividadeAdapter adapter = new AtividadeAdapter();
                recyclerView.setAdapter(adapter);

                atividadeViewModel = ViewModelProviders.of(this).get(AtividadeViewModel.class);
                atividadeViewModel.getAllAtividades().observe(this, new Observer<List<Atividades>>() {
                    @Override
                    public void onChanged(List<Atividades> atividades) {
                        adapter.setAtividade(atividades);
                    }
                });

                adapter.setOnItemClickListener(new AtividadeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Atividades atividades) {
                        ConfirmFragment confirmFragment = new ConfirmFragment();
                        Bundle args = new Bundle();
                        args.putBoolean("IniciarTrabalho", true);
                        args.putBoolean("PrimeiroTrabalho", true);
                        args.putParcelable("Trabalho", nTrabalho);
                        args.putParcelable("Atividade", atividades);
                        confirmFragment.setArguments(args);
                        confirmFragment.show(

                                getSupportFragmentManager(), "Enviar");
                    }
                });









            /**
             *  Finalizar Trabalho
             */

        } else if (data.containsKey("Atualizar nova Tarefa")) {

            titulo.setText("Selecione se tem algo a reportar");
            final TrabalhoComAtividadeRelatorio fTrabalho = data.getParcelable("Atualizar nova Tarefa");

            RecyclerView recyclerView = findViewById(R.id.recyclerViewAtividadesRelatorio);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            final RelatorioAdapter adapter = new RelatorioAdapter();
            recyclerView.setAdapter(adapter);

            relatorioViewModel = ViewModelProviders.of(this).get(RelatorioViewModel.class);
            relatorioViewModel.getAllRelatorio().observe(this, new Observer<List<Relatorio>>() {
                @Override
                public void onChanged(List<Relatorio> relatorios) {
                    adapter.setRelatorio(relatorios);
                }
            });
            adapter.setOnItemClickListener(new RelatorioAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Relatorio relatorio) {
                    ConfirmFragment confirmFragment = new ConfirmFragment();
                    Bundle args = new Bundle();
                    args.putBoolean("FinalizarTrabalho", true);
                    args.putParcelable("Trabalho", fTrabalho);
                    args.putParcelable("Relatorio", relatorio);
                    confirmFragment.setArguments(args);
                    confirmFragment.show(getSupportFragmentManager(), "Enviar");
                }
            });
        }


    }

    @Override
    public void finalizarTrabalho(TrabalhoComAtividadeRelatorio trabalho, Relatorio relatorio, String tempo) {
        trabalho.getTrabalho().setTempo_fim(tempo);
        trabalho.getTrabalho().setId_relatorio(relatorio.getId_relatorio());

        Intent data = new Intent();
        data.putExtra("Trabalho", trabalho);

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public void salvarTrabalho (Trabalho trabalho, Atividades atividades, String tempo) {
        trabalho.setTempo_inicio(tempo);
        trabalho.setId_atividade(atividades.getId_atividade());



        Intent data = new Intent();
        data.putExtra("Trabalho", trabalho);

        setResult(RESULT_OK, data);
        finish();

    }



}
