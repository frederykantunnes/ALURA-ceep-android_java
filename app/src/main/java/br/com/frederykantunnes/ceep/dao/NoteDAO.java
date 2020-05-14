package br.com.frederykantunnes.ceep.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.frederykantunnes.ceep.model.Note;

public class NoteDAO {
    private final static ArrayList<Note> NOTES = new ArrayList<>();

    public List<Note> todos(){
        return (List<Note>) NOTES.clone();
    }

    public static void insere(Note note){
        NoteDAO.NOTES.addAll(Arrays.asList(note));
    }

    public void altera(int posicao, Note note){
        NOTES.set(posicao, note);
    }

    public void remove( int posicao){
        NOTES.remove(posicao);
    }

    public void removerTodos(){
        NOTES.clear();
    }
}
