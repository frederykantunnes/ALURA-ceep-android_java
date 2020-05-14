package br.com.frederykantunnes.ceep.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;
import br.com.frederykantunnes.ceep.R;
import br.com.frederykantunnes.ceep.dao.NoteDAO;
import br.com.frederykantunnes.ceep.model.Note;
import br.com.frederykantunnes.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;
import br.com.frederykantunnes.ceep.ui.recyclerview.adapter.ListNotesAdapter;
import br.com.frederykantunnes.ceep.ui.recyclerview.helper.callback.NotaItemTouchCallback;

public class ListNotesActivity extends AppCompatActivity {

    String title = "Notas";
    private ListNotesAdapter adapter;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        setTitle(title);
        clickEvent();
        adapterConfigure();
    }

    private void clickEvent() {
        Button button = findViewById(R.id.btn_form_create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ListNotesActivity.this, FormNoteActivity.class), 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode==1 && data.hasExtra("nota")){
                addNote(data);
            }
            if(requestCode==2 && data.hasExtra("nota") && data.hasExtra("posicao")){
                alterNote(data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addNote(Intent data) {
        Note noteReceived = (Note) data.getSerializableExtra("nota");
        adiciona(noteReceived);
    }

    private void alterNote(Intent data) {
        Note noteReceived = (Note) data.getSerializableExtra("nota");
        int position = data.getIntExtra("posicao", -1);
        new NoteDAO().altera(position, noteReceived);
        adapter.altera(position, noteReceived);
    }

    private void adapterConfigure() {
        notes = pegaTodas();
        RecyclerView listNotes = findViewById(R.id.list_notes_recycleview);
        adapter = new ListNotesAdapter(this, notes);
        listNotes.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Note note, int position) {
                goStartForm(note, position);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchCallback(adapter));
        itemTouchHelper.attachToRecyclerView(listNotes);
    }

    private void goStartForm(Note note, int position) {
        Intent intent = new Intent(ListNotesActivity.this, FormNoteActivity.class);
        intent.putExtra("nota", note);
        intent.putExtra("posicao", position);
        startActivityForResult(intent, 2);
    }

    public List<Note> pegaTodas(){
        NoteDAO dao = new NoteDAO();
        return dao.todos();
    }

    public void adiciona(Note note){
        notes.add(note);
        adapter.notifyDataSetChanged();
    }


}
