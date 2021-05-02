package fr.equipe4.gestsio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ViewEtudiantActivity extends AppCompatActivity  {

    private TextView mTextViewNom;
    private TextView mTextViewPrenom;
    private TextView mTextViewNaissance;
    private TextView mTextViewSpe;
    private TextView mTextViewAdresse;
    private TextView mTextViewCp;
    private TextView mTextViewVille;
    private TextView mTextViewTel;
    private TextView mTextViewCourriel;
    private TextView mTextViewObservations;
    private TextView textViewResult;


    public static final String EXTRA_REPLY_VIEW = "fr.equipe4.gestsio.REPLY_VIEW";


    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // affectation du fichier de layout
        setContentView(R.layout.activity_view_etudiant);

         mTextViewNom=findViewById(R.id.textViewNom);
         mTextViewPrenom=findViewById(R.id.textViewPrenom);
         mTextViewNaissance=findViewById(R.id.textViewNaissance);
        mTextViewSpe=findViewById(R.id.textViewSpe);
        mTextViewAdresse=findViewById(R.id.textViewAdresse);
         mTextViewCp=findViewById(R.id.textViewCp);
         mTextViewVille=findViewById(R.id.textViewVille);
         mTextViewTel=findViewById(R.id.textViewTel);
         mTextViewCourriel=findViewById(R.id.textViewCourriel);
        mTextViewObservations=findViewById(R.id.textViewObservations);

        final Etudiant etudiant = (Etudiant) getIntent().getSerializableExtra(EtudiantListAdapter.EtudiantViewHolder.EXTRA_REPLY_ETUDIANT_LA);


        loadEtudiant(etudiant);


        textViewResult = findViewById(R.id.textViewAppreciation);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://165.169.241.28:21195/MyApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface1 = retrofit.create(ApiInterface.class);
        Call<List<Appreciation>> call = apiInterface1.getAppreciations(etudiant.getIdEtudiant());
        call.enqueue(new Callback<List<Appreciation>>() {
            @Override
            public void onResponse(Call<List<Appreciation>> call, Response<List<Appreciation>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code :" + response.code());
                }

                List<Appreciation> appreciations = response.body();
                for (Appreciation appreciation : appreciations) {
                    String content = "";
                    content += /*"Appreciation ;" + */  appreciation.getObservationEtudiant() + "\n";
                    textViewResult.append(content);
                    Log.v("Tag", "La reponse :" + appreciation.getObservationEtudiant());
                }
            }

            @Override
            public void onFailure(Call<List<Appreciation>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        //gestion de la modification

        final Button bm = findViewById(R.id.button_modifier);
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewEtudiantActivity.this, UpdateEtudiantActivity.class);
                intent.putExtra(EXTRA_REPLY_VIEW,etudiant);
                intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(intent);
                finish();
            }
        });


        //gestion de la suppression d'un étudiant

        findViewById(R.id.button_supprimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewEtudiantActivity.this);
                builder.setTitle(R.string.confirmation);

                builder.setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //deleteEtudiant(etudiant);

                        Intent replyIntent = new Intent();
                        replyIntent.putExtra(EXTRA_REPLY_VIEW, etudiant);
                        setResult(MainActivity.RESULT_SUPP, replyIntent);
                        finish();

                    }
                });

                builder.setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });


    }

    private void loadEtudiant(Etudiant etudiant) {
        mTextViewNom.setText(etudiant.getNomEtudiant());
        mTextViewPrenom.setText(etudiant.getPrenomEtudiant());
        mTextViewNaissance.setText(etudiant.getNaissanceEtudiant());
        mTextViewSpe.setText(etudiant.getSpeEtudiant());
        mTextViewAdresse.setText(etudiant.getAdresseEtudiant());
        mTextViewCp.setText(etudiant.getCpEtudiant());
        mTextViewVille.setText(etudiant.getVilleEtudiant());
        mTextViewTel.setText(etudiant.getTelEtudiant());
        mTextViewCourriel.setText(etudiant.getCourrielEtudiant());
        mTextViewObservations.setText(etudiant.getObservationsEtudiant());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}