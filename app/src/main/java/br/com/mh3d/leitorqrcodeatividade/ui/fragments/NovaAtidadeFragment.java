package br.com.mh3d.leitorqrcodeatividade.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.mh3d.leitorqrcodeatividade.R;
import br.com.mh3d.leitorqrcodeatividade.models.Trabalho;
import br.com.mh3d.leitorqrcodeatividade.ui.activity.IdentificationActivity;

public class NovaAtidadeFragment extends Fragment {

    private Button btn_nova_atividade;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_nova_atividade, container, false);
        final TextView textView = root.findViewById(R.id.text_nova_atividade);
        btn_nova_atividade = root.findViewById(R.id.btn_nova_atividade);
        btn_nova_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQRScanner();
            }
        });




        return root;
    }


    // Metodo ler QR code
    private void startQRScanner() {

        IntentIntegrator integrator =  IntentIntegrator.forSupportFragment(NovaAtidadeFragment.this);
        integrator.setPrompt("Scan a barcode");
        integrator.setCameraId(0);  // 0 camera normal 1 camera frontal
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(false);
        integrator.setRequestCode(1);
        integrator.initiateScan();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
        switch(requestCode) {
            case 1:
                if (result != null) {
                    if (result.getContents() == null) {
                        Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_LONG).show();
                    } else {
                        abrirIdentification(result.getContents());
                    }
                } else {
                    super.onActivityResult(requestCode, resultCode, data);
                }


        }
    }


    public void abrirIdentification(String scanCode)  {
        try {
            JSONObject reader = new JSONObject(scanCode);
            JSONObject usuarioJson = reader.getJSONObject("usuario");
            Trabalho user = new Trabalho(usuarioJson.getInt("id_usuario"),usuarioJson.getString("nome_usuario"));
            abrirIdentification(user);
        } catch (JSONException e ) {
            e.printStackTrace();
        }

    }


    public void abrirIdentification(Trabalho func){
        Intent intent = new Intent(getActivity(), IdentificationActivity.class);
        intent.putExtra("funcionarioRecebido", func);
        startActivity(intent);

    }




}