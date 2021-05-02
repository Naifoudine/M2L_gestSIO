package fr.equipe4.gestsio;

import com.google.gson.annotations.SerializedName;

public class Appreciation {
    @SerializedName("id")
    private int idEtudiant;

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public String getObservationEtudiant() {
        return observationEtudiant;
    }

    @SerializedName("appreciation")
    private String observationEtudiant;


}
