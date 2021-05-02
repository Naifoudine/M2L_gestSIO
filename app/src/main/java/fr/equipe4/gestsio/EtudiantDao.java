package fr.equipe4.gestsio;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface EtudiantDao {

        @Query("SELECT * from table_etudiant ORDER BY nomEtudiant ASC")
        LiveData<List<Etudiant>> getAlphabetizedEtudiants();

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Etudiant etudiant);

        @Update
        void update(Etudiant etudiant);

        @Delete
        void delete(Etudiant etudiant);


        //Exemple de requêtes :

        @Query("SELECT * FROM table_etudiant WHERE idEtudiant IN (:userIds)")
        LiveData<List<Etudiant>> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM table_etudiant WHERE nomEtudiant LIKE :nomEtudiant AND " +
                "prenomEtudiant LIKE :prenomEtudiant LIMIT 1")
        Etudiant findByName(String nomEtudiant, String prenomEtudiant);

        @Query("DELETE FROM table_etudiant")
        void deleteAll();
}
