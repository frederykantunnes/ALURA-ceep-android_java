package br.com.frederykantunnes.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.frederykantunnes.ceep.R;
import br.com.frederykantunnes.ceep.model.Note;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.NoteViewHolder> {
    private List<Note> notes;
    private Context context;

    public ListNotesAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.vincula(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private final TextView title, description;
        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_details);
        }
        void vincula(Note note){
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
    }

}
