package fr.equipe4.gestsio;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.rb.gestsio.R;

/**
 * Activity for entering a etudiant.
 */

public class NewEtudiantActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NEW = "fr.rb.gestsio.REPLY_NEW";

    private EditText mEditNomView;
    private EditText mEditPrenomView;
    private EditText mEditNaissanceView;
    private EditText mEditSpeView;
    private EditText mEditAdresseView;
    private EditText mEditCpView;
    private EditText mEditVilleView;
    private EditText mEditTelView;
    private EditText mEditCourrielView;
    private EditText mEditObservationsView;


    private Etudiant etudiant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_etudiant);
        mEditNomView = findViewById(R.id.editNom);
        mEditPrenomView = findViewById(R.id.editPrenom);
        mEditNaissanceView=findViewById(R.id.editNaissanceEtudiant);
        mEditSpeView=findViewById(R.id.editSpeEtudiant);
        mEditAdresseView=findViewById(R.id.editAdresseEtudiant);
        mEditCpView=findViewById(R.id.editCpEtudiant);
        mEditVilleView=findViewById(R.id.editVilleEtudiant);
        mEditTelView=findViewById(R.id.editTelEtudiant);
        mEditCourrielView=findViewById(R.id.editCourrielEtudiant);
        mEditObservationsView=findViewById(R.id.editObservationsEtudiant);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent replyIntent = new Intent();

                if ((TextUtils.isEmpty(mEditNomView.getText())) || (TextUtils.isEmpty(mEditPrenomView.getText())) ||
                        (TextUtils.isEmpty(mEditNaissanceView.getText()))) {
                    setResult(RESULT_CANCELED, replyIntent);
                }

                else {
                    String nomEtudiant = mEditNomView.getText().toString();
                    String prenomEtudiant = mEditPrenomView.getText().toString();
                    String naissanceEtudiant=mEditNaissanceView.getText().toString();
                    String speEtudiant=mEditSpeView.getText().toString();
                    String adresseEtudiant=mEditAdresseView.getText().toString();
                    String cpEtudiant=mEditCpView.getText().toString();
                    String villeEtudiant=mEditVilleView.getText().toString();
                    String telEtudiant=mEditTelView.getText().toString();
                    String courrierEtudiant=mEditCourrielView.getText().toString();
                    String observationsEtudiant=mEditObservationsView.getText().toString();

                    Etudiant etudiant = new Etudiant();
                    etudiant.setNomEtudiant(nomEtudiant);
                    etudiant.setPrenomEtudiant(prenomEtudiant);
                    etudiant.setNaissanceEtudiant(naissanceEtudiant);
                    etudiant.setSpeEtudiant((speEtudiant));
                    etudiant.setAdresseEtudiant(adresseEtudiant);
                    etudiant.setCpEtudiant(cpEtudiant);
                    etudiant.setVilleEtudiant(villeEtudiant);
                    etudiant.setTelEtudiant(telEtudiant);
                    etudiant.setCourrielEtudiant(courrierEtudiant);
                    etudiant.setObservationsEtudiant(observationsEtudiant);

                    replyIntent.putExtra(EXTRA_REPLY_NEW, etudiant);
                    setResult(RESULT_OK, replyIntent);

                }
                finish();
            }
        });
    }
}

