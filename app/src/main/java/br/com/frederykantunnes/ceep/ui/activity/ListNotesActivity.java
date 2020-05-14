package br.com.frederykantunnes.ceep.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import br.com.frederykantunnes.ceep.R;
import br.com.frederykantunnes.ceep.model.Note;
import br.com.frederykantunnes.ceep.ui.recyclerview.adapter.ListNotesAdapter;

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
        if(requestCode==1 && resultCode==2 && data.hasExtra("nota")){
            Note noteReceived = (Note) data.getSerializableExtra("nota");
            adiciona(noteReceived);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adapterConfigure() {
        notes = new ArrayList<>();
        RecyclerView listNotes = findViewById(R.id.list_notes_recycleview);
        adapter = new ListNotesAdapter(this, notes);
        listNotes.setAdapter(adapter);
    }


    public void adiciona(Note note){
        notes.add(note);
        adapter.notifyDataSetChanged();
    }
}
