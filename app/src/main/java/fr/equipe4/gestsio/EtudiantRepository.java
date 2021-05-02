package fr.equipe4.gestsio;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class EtudiantRepository {

    /**
     * Variables membres pour le DAO et la liste d'étudiants
     */
    private EtudiantDao mEtudiantDao;
    private LiveData<List<Etudiant>> mAllEtudiants;

    // Note that in order to unit test the epository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in thÒe android-architecture-components repository at
    // https://github.com/googlesamples

    /**
     * Constructeur qui permet la gestion de la base et initialise les variables membres
     *
     * @param application
     */
    EtudiantRepository(Application application) {
        EtudiantRoomDatabase db = EtudiantRoomDatabase.getDatabase(application);
        mEtudiantDao = db.etudiantDao();
        mAllEtudiants = mEtudiantDao.getAlphabetizedEtudiants();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    /**
     * Méthode "wrapper" (emballage) qui renvoie les étudiants en cache en tant que LiveData.
     *
     * @return
     */
    LiveData<List<Etudiant>> getAllEtudiants() {
        return mAllEtudiants;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.

    /**
     * Méthode wrapper qui permet d'insérer un étudiant dans un thread non UI.
     *
     * @param etudiant
     */
    void insert(Etudiant etudiant) {
        EtudiantRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEtudiantDao.insert(etudiant);
        });
    }

    void update(Etudiant etudiant) {
        EtudiantRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEtudiantDao.update(etudiant);
        });
    }

    public void delete(Etudiant etudiant) {
        EtudiantRoomDatabase.databaseWriteExecutor.execute(() -> {
            mEtudiantDao.delete(etudiant);
        });
    }
}

