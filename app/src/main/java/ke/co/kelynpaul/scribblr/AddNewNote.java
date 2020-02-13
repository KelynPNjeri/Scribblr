package ke.co.kelynpaul.scribblr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNewNote extends AppCompatActivity {
    private static String NOTE_TITLE="title";
    private static String NOTE_DESCRIPTION="description";
    private Button saveButton;
    private EditText noteTitle;
    private EditText noteDescription;
    private FirebaseFirestore documentRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        saveButton = findViewById(R.id.save_btn);
        noteTitle = findViewById(R.id.note_title);
        noteDescription = findViewById(R.id.note_description);
        documentRef = FirebaseFirestore.getInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFirestore(noteTitle.getText().toString(), noteDescription.getText().toString());
                noteTitle.setText("");
                noteDescription.setText("");
            }
        });
    }

    private void saveToFirestore(String title, String description) {
        Map<String, Object> notes = new HashMap<>();
        notes.put(NOTE_TITLE, title);
        notes.put(NOTE_DESCRIPTION, description);
        documentRef.collection("notes")
                .add(notes)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddNewNote.this, "Saved To Firestore.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddNewNote.this, "Error.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
