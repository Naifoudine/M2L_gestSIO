
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

import android.app.Activity;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EtudiantListAdapter extends RecyclerView.Adapter<EtudiantListAdapter.EtudiantViewHolder> {

    private final LayoutInflater mInflater;
    private List<Etudiant> mEtudiants; // copie en cache de liste
    private Context mContext;



    class EtudiantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private final TextView nomItemView;
        private final TextView prenomItemView;
        private final TextView naissanceItemView;
        public static final String EXTRA_REPLY_ETUDIANT_LA = "fr.equipe4.gestsio.REPLY_ETUDIANT_LA";


        private EtudiantViewHolder(View itemView) {
            super(itemView);
            nomItemView = itemView.findViewById(R.id.nomTextView);
            prenomItemView = itemView.findViewById(R.id.prenomTextView);
            naissanceItemView = itemView.findViewById(R.id.naissanceTextView);

            itemView.setOnClickListener(this);
        }

        //gestion du clic sur un étudiant
        @Override
        public void onClick(View view) {
            Etudiant etudiant = mEtudiants.get(getAdapterPosition());
            Intent intent = new Intent(mContext, ViewEtudiantActivity.class);
            intent.putExtra(EXTRA_REPLY_ETUDIANT_LA, etudiant);
            ((Activity) mContext).startActivityForResult(intent,MainActivity.UPDATE_ETUDIANT_ACTIVITY_REQUEST_CODE);
            //mContext.startActivity(intent);
        }
    }

    EtudiantListAdapter(Context context) {
        //permet de récupérer le context pour la gestion du clic en dessous
       this.mContext = context;
       //this.mEtudiants = etudiants;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public EtudiantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new EtudiantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EtudiantViewHolder holder, int position) {
        if (mEtudiants != null) {
            Etudiant current = mEtudiants.get(position);
            holder.nomItemView.setText(current.getNomEtudiant());
            holder.prenomItemView.setText(current.getPrenomEtudiant());
            holder.naissanceItemView.setText(current.getNaissanceEtudiant());


        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Aucun étudiant");
        }
    }

    void setEtudiants(List<Etudiant> etudiants) {
        mEtudiants = etudiants;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEtudiants != null)
            return mEtudiants.size();
        else return 0;
    }
}


