package br.com.frederykantunnes.ceep.ui.recyclerview.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import br.com.frederykantunnes.ceep.dao.NoteDAO;
import br.com.frederykantunnes.ceep.ui.recyclerview.adapter.ListNotesAdapter;

public class NotaItemTouchCallback extends ItemTouchHelper.Callback {

    private final ListNotesAdapter listNotesAdapter;

    public NotaItemTouchCallback(ListNotesAdapter listNotesAdapter) {
        this.listNotesAdapter = listNotesAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoes_deslizes = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacoes_arrastar = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(marcacoes_arrastar, marcacoes_deslizes);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int inicialP = viewHolder.getAdapterPosition();
        int finalP = target.getAdapterPosition();
        trocaNotas(inicialP, finalP);
        return false;
    }

    private void trocaNotas(int inicialP, int finalP) {
        new NoteDAO().troca(inicialP, finalP);
        listNotesAdapter.troca(inicialP, finalP);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        new NoteDAO().remove(viewHolder.getAdapterPosition());
        listNotesAdapter.remove(viewHolder.getAdapterPosition());
    }
}
