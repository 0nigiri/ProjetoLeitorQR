package br.com.mh3d.leitorqrcodeatividade.ui.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.res.ResourcesCompat;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.Atividades;
import br.com.mh3d.leitorqrcodeatividade.models.Relatorio;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.models.TrabalhoComAtividadeRelatorio;
import br.com.mh3d.leitorqrcodeatividade.utils.DateFormatUtils;

public class ConfirmFragment extends AppCompatDialogFragment {
    private EnviarDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        final Bundle mArgs = getArguments();
        if (mArgs.getBoolean("IniciarTrabalho")) {
            /**
             * Confirmar salvar nova atividade
             */
            final Trabalho nTrabalho = mArgs.getParcelable("Trabalho");
            final Atividades atividades = mArgs.getParcelable("Atividade");

            builder.setView(view)
                    .setTitle("Confirmar")
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DateFormatUtils df = new DateFormatUtils();
                            String tempoAgora = df.getUnixDateNow();
                            listener.salvarTrabalho(nTrabalho, atividades, tempoAgora);
                        }
                    });
        }



        else if (mArgs.getBoolean("FinalizarTrabalho")) {
            final TrabalhoComAtividadeRelatorio fTrabalho = mArgs.getParcelable("Trabalho");
            final Relatorio relatorio = mArgs.getParcelable("Relatorio");
            builder.setView(view)
                    .setTitle("Confirmar")
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DateFormatUtils df = new DateFormatUtils();
                            String tempoAgora = df.getUnixDateNow();
                            listener.finalizarTrabalho(fTrabalho, relatorio, tempoAgora);
                        }
                    });

        }

        return builder.create();
    }

    public interface EnviarDialogListener {
        void salvarTrabalho(Trabalho trabalho, Atividades atividades, String tempo);


        void finalizarTrabalho(TrabalhoComAtividadeRelatorio trabalho, Relatorio relatorio, String tempo);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EnviarDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement EnviarDialogListener");
        }
    }


}
