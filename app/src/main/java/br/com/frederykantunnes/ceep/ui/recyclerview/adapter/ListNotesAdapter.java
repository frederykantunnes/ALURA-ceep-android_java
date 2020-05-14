package br.com.frederykantunnes.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import br.com.frederykantunnes.ceep.R;
import br.com.frederykantunnes.ceep.model.Note;
import br.com.frederykantunnes.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.NoteViewHolder> {
    private List<Note> notes;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ListNotesAdapter(Context context, List<Note> notes) {
        this.notes = notes;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCreated = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(viewCreated);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, int position) {
        holder.vincula(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void altera(int position, Note noteReceived) {
        notes.set(position, noteReceived);
    }

    public void remove(int adapterPosition) {
        notes.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    public void troca(int inicialP, int finalP) {
        Collections.swap(notes,inicialP, finalP);
        notifyItemMoved(inicialP,finalP);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        private Note nota;
        private final TextView title, description;
        NoteViewHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_details);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(nota, getAdapterPosition());
                }
            });
        }
        void vincula(Note note){
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            this.nota = note;
        }
    }
}
