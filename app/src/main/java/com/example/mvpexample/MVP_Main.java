package com.example.mvpexample;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvpexample.main.view.recycler.NotesViewHolder;
import com.example.mvpexample.models.Note;

public interface MVP_Main {

    //Presenter 에서 필요한 View 에서 제공되는 작업들
    interface RequiredViewOps {
        Context getAppContext();
        Context getActivityContext();
        void showToast(Toast toast);
        void showProgress();
        void hideProgress();
        void showAlert(AlertDialog dialog);
        void notifyItemInserted(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
        void notifyItemRemoved(int position);
        void notifyDataSetChanged();
        void clearEditText();
    }

    //View 와 통신하기 위해서 Presenter 에서 제공되는 작업
    interface ProvidedPresenterOps {
        int getNoteCount();
        NotesViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(NotesViewHolder holder, int position);
        void clickNewNote(EditText editText);
        void clickDeleteNote(Note note, int adapterPos, int layoutPos);
        void setView(RequiredViewOps view);
        void onDestroy(boolean isChangingConfiguration);
    }

    //Model 에 필요한 Presenter 에서 제공되는 작업
    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    //Presenter 와 통신을 위해 Model 에서 제공하는 작업
    interface ProvidedModelOps {
        void onDestroy(boolean isChangingConfiguration);
        int insertNote(Note note);
        int getNotesCount();
        boolean loadData();
        boolean deleteNote(Note note, int adapterPos);
        Note getNote(int position);
    }
}
