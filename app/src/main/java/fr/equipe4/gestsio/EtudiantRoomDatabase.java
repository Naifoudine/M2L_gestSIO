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

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.  In a real
 * app, consider exporting the schema to help you with migrations.
 */


/**
 * declaration de la base de données Room avec la liste des entités et la version.
 */

@Database(entities = {Etudiant.class}, version = 1, exportSchema = false)
public abstract class EtudiantRoomDatabase extends RoomDatabase {

    /**
     * Définition du Dao qui sera utilisé avec la base.
     */
    public abstract EtudiantDao etudiantDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile  EtudiantRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * création de la base de données Room comme étant un singleton
     * singleton : patron de conception (design pattern) dont l'objectif est de restreindre
     * l'instanciation d'une classe à un seul objet (ou bien à quelques objets seulement).
     * voir : https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)
     */

    static EtudiantRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EtudiantRoomDatabase.class) {
                if (INSTANCE == null) {
                    /**
                     * Création de la base de données nommée etudiant_database en utilisant Room.
                     */
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EtudiantRoomDatabase.class, "etudiant_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     *
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */



    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        //public void onOpen(@NonNull SupportSQLiteDatabase db) {
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                EtudiantDao dao = INSTANCE.etudiantDao();

                //Décommentez la ligne ci dessous si vous souhaitez supprimer le contenu de la base à chaque lancement.
                //dao.deleteAll();

                //permet d'insérer un étudiant en exemple :
                Etudiant etudiant = new Etudiant();
                etudiant.setNomEtudiant("Mohammed");
                etudiant.setPrenomEtudiant("Ali");
                etudiant.setNaissanceEtudiant("17/01/1942");
                etudiant.setSpeEtudiant("SLAM");
                etudiant.setAdresseEtudiant("144 N. Sixth Street");
                etudiant.setCpEtudiant("40202");
                etudiant.setVilleEtudiant("LOUISVILLE");
                etudiant.setTelEtudiant("502.584.9254");
                etudiant.setCourrielEtudiant("info@alicenter.org");
                dao.insert(etudiant);
                });
        }
    };


}
