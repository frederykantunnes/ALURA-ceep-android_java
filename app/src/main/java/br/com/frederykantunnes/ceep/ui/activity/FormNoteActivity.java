package br.com.frederykantunnes.ceep.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.frederykantunnes.ceep.R;
import br.com.frederykantunnes.ceep.dao.NoteDAO;
import br.com.frederykantunnes.ceep.model.Note;

public class FormNoteActivity extends AppCompatActivity {
    TextView description, title;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        setTitle("Nova Note");
        loadElements();
        getDataForAlter();

    }

    private void getDataForAlter() {
        if(getIntent().hasExtra("nota") && getIntent().hasExtra("posicao")) {
            Note note = (Note) getIntent().getSerializableExtra("nota");
            position = getIntent().getIntExtra("posicao", -1);
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
    }

    private void loadElements() {
        title = findViewById(R.id.formulario_nota_titulo);
        description = findViewById(R.id.formulario_nota_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(isMenuNoteSave(item)){
            clickEvent();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isMenuNoteSave(@NonNull MenuItem item) {
        return item.getItemId() == R.id.btn_form_save;
    }

    private void clickEvent() {
        Intent intent = new Intent();
        intent.putExtra("nota", save());
        intent.putExtra("posicao", position);
        setResult(2, intent);
        finish();
    }

    private Note save() {
        NoteDAO dao = new NoteDAO();
        Note note = new Note(title.getText().toString(), description.getText().toString());
        dao.insere(note);
        return note;
    }
}
