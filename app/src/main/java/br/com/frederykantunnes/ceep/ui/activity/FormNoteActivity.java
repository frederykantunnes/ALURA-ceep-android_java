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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        setTitle("Nova Note");
        loadElements();
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
        setResult(2, intent);
        finish();
    }

    private Note save() {
        Note note = new Note(title.getText().toString(), description.getText().toString());
        NoteDAO.insere(note);
        return note;
    }
}
