package com.example.mvpexample.main.model;

import com.example.mvpexample.MVP_Main;
import com.example.mvpexample.data.DAO;
import com.example.mvpexample.models.Note;

import java.util.ArrayList;

public class MainModel implements MVP_Main.ProvidedModelOps {
    private MVP_Main.RequiredPresenterOps mPresenter;
    private DAO mDao;
    private ArrayList<Note> mNotes;

    public MainModel(MVP_Main.RequiredPresenterOps presenter){
        this.mPresenter = presenter;
        mDao = new DAO(mPresenter.getAppContext());
    }

    public MainModel(MVP_Main.RequiredPresenterOps presenter, DAO dao){
        this.mPresenter = presenter;
        this.mDao = dao;
    }

    public int getNotePosition(Note note){
        for(int i=0;i<mNotes.size();i++){
            if(note.getId() == mNotes.get(i).getId()){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        if(!isChangingConfiguration){
            mPresenter = null;
            mDao = null;
            mNotes = null;
        }
    }

    @Override
    public int insertNote(Note note) {
        Note insertedNote = mDao.insertNote(note);
        if(insertedNote != null){
            loadData();
            return getNotePosition(insertedNote);
        }
        return -1;
    }

    @Override
    public int getNotesCount() {
        if(mNotes!=null){
            return mNotes.size();
        }
        return 0;
    }

    @Override
    public boolean loadData() {
        mNotes = mDao.getAllNotes();
        return mNotes !=null;
    }

    @Override
    public boolean deleteNote(Note note, int adapterPos) {
        long res = mDao.deleteNote(note);
        if (res>0){
            mNotes.remove(adapterPos);
            return true;
        }
        return false;
    }

    @Override
    public Note getNote(int position) {
        return mNotes.get(position);
    }
}
