package com.example.nuovaRubricaContatti.classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nuovaRubricaContatti.R;
import com.example.nuovaRubricaContatti.data.database.MyAppDatabase;

public class DialogEliminaContatto extends DialogFragment {
    private long idContatto;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage(R.string.dialog_elimina_contatto);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(" - (dialog) contatto eliminato ", " SI");
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... voids) {
                        MyAppDatabase.getInstance(getContext()).getContactDao().deleteById(idContatto);
                        return null;
                    }
                }.execute();
            }

        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(" - (dialog) contatto eliminato ", "NO");
            }

        });
        return builder.create();
    }

    public long getIdContatto() {
        return idContatto;
    }

    public void setIdContatto(long idContatto) {
        this.idContatto = idContatto;
    }
}
