package fr.equipe4.gestsio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.rb.gestsio.R;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_ETUDIANT_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_ETUDIANT_ACTIVITY_REQUEST_CODE = 2;
    public static final int RESULT_SUPP = 3;




    private EtudiantViewModel mEtudiantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final EtudiantListAdapter adapter = new EtudiantListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mEtudiantViewModel = new ViewModelProvider(this).get(EtudiantViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedEtudiants.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mEtudiantViewModel.getAllEtudiants().observe(this, new Observer<List<Etudiant>>() {
            @Override
            public void onChanged(@Nullable final List<Etudiant> etudiants) {
                // Update the cached copy of the words in the adapter.
                adapter.setEtudiants(etudiants);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewEtudiantActivity.class);
                startActivityForResult(intent, NEW_ETUDIANT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_ETUDIANT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Etudiant etudiant = (Etudiant) data.getExtras().getSerializable(UpdateEtudiantActivity.EXTRA_REPLY_UPDATE);
            mEtudiantViewModel.update(etudiant);

            Toast.makeText(
                    getApplicationContext(),
                    R.string.updated,
                    Toast.LENGTH_LONG).show();

        } else if (requestCode == UPDATE_ETUDIANT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_SUPP) {
            Etudiant etudiant = (Etudiant) data.getExtras().getSerializable(ViewEtudiantActivity.EXTRA_REPLY_VIEW);

            mEtudiantViewModel.delete(etudiant);

            Toast.makeText(
                    getApplicationContext(),
                    R.string.deleted,
                    Toast.LENGTH_LONG).show();
        }

        else if (requestCode == NEW_ETUDIANT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Etudiant etudiant = (Etudiant) data.getExtras().getSerializable(NewEtudiantActivity.EXTRA_REPLY_NEW);
            mEtudiantViewModel.insert(etudiant);

            Toast.makeText(
                    getApplicationContext(),
                    R.string.saved,
                    Toast.LENGTH_LONG).show();

        } else if (requestCode == NEW_ETUDIANT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}


