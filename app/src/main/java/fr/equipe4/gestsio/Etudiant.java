package fr.equipe4.gestsio;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "table_etudiant")
public class Etudiant implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idEtudiant")
    private int mIdEtudiant;

    @ColumnInfo(name = "nomEtudiant")
    private String mNomEtudiant;

    @ColumnInfo(name = "prenomEtudiant")
    private String mPrenomEtudiant;

    @ColumnInfo(name = "naissanceEtudiant")
    private String mNaissanceEtudiant;

    @ColumnInfo(name = "speEtudiant")
    private String mSpeEtudiant;

    @ColumnInfo(name = "adresseEtudiant")
    private String mAdresseEtudiant;

    @ColumnInfo(name = "cpEtudiant")
    private String mCpEtudiant;

    @ColumnInfo(name = "villeEtudiant")
    private String mVilleEtudiant;

    @ColumnInfo(name = "telEtudiant")
    private String mTelEtudiant;

    @ColumnInfo(name = "courrielEtudiant")
    private String mCourrielEtudiant;

    @ColumnInfo(name = "observationsEtudiant")
    private String mObservationsEtudiant;


    public int getIdEtudiant() {
        return mIdEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.mIdEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return mNomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.mNomEtudiant = nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return mPrenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.mPrenomEtudiant = prenomEtudiant;
    }

    public String getNaissanceEtudiant() {
        return mNaissanceEtudiant;
    }

    public void setNaissanceEtudiant(String naissanceEtudiant) {
        this.mNaissanceEtudiant = naissanceEtudiant;
    }

    public String getSpeEtudiant() {
        return mSpeEtudiant;
    }

    public void setSpeEtudiant(String speEtudiant) {
        this.mSpeEtudiant = speEtudiant;
    }

    public String getAdresseEtudiant() {
        return mAdresseEtudiant;
    }

    public void setAdresseEtudiant(String adresseEtudiant) {
        this.mAdresseEtudiant = adresseEtudiant;
    }

    public String getCpEtudiant() {
        return mCpEtudiant;
    }

    public void setCpEtudiant(String cpEtudiant) {
        this.mCpEtudiant = cpEtudiant;
    }

    public String getVilleEtudiant() {
        return mVilleEtudiant;
    }

    public void setVilleEtudiant(String villeEtudiant) {
        this.mVilleEtudiant = villeEtudiant;
    }

    public String getTelEtudiant() {
        return mTelEtudiant;
    }

    public void setTelEtudiant(String telEtudiant) {
        this.mTelEtudiant = telEtudiant;
    }

    public String getCourrielEtudiant() {
        return mCourrielEtudiant;
    }

    public void setCourrielEtudiant(String courrielEtudiant) {
        this.mCourrielEtudiant = courrielEtudiant;
    }

    public String getObservationsEtudiant() {
        return mObservationsEtudiant;
    }

    public void setObservationsEtudiant(String observationsEtudiant) {
        this.mObservationsEtudiant = observationsEtudiant;
    }


}

