package ke.co.kelynpaul.scribblr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteHolder> {
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Note model) {
        holder.noteTitle.setText(model.getTitle());
        holder.noteDescription.setText(model.getDescription());

    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new NoteHolder(v);

    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView noteTitle;
        TextView noteDescription;

        public NoteHolder(@NonNull View itemView) {

            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_item_title);
            noteDescription = itemView.findViewById(R.id.note_item_description);
        }
    }
}
