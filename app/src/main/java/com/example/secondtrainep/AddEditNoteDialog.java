package com.example.secondtrainep;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditNoteDialog extends DialogFragment {

    private static final String ARG_NOTE = "note";
    private static final String ARG_POSITION = "position";

    private EditText titleEditText;
    private EditText contentEditText;
    private Button btnSave;

    private Note note;
    private int position;
    private OnNoteAddedListener onNoteAddedListener;

    public interface OnNoteAddedListener {
        void onNoteAdded(Note note);

        void onNoteEdited(Note note, int position);
    }

    public AddEditNoteDialog() {
        // Required empty public constructor
    }

    public static AddEditNoteDialog newInstance(Note note, int position) {
        AddEditNoteDialog fragment = new AddEditNoteDialog();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnNoteAddedListener(OnNoteAddedListener listener) {
        onNoteAddedListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_edit_note, null);
        titleEditText = view.findViewById(R.id.titleEditText);
        contentEditText = view.findViewById(R.id.contentEditText);
        btnSave = view.findViewById(R.id.btnSave);

        note = getArguments().getParcelable(ARG_NOTE);
        position = getArguments().getInt(ARG_POSITION);

        if (note != null) {
            // If editing an existing note, pre-fill the fields with existing data
            titleEditText.setText(note.getTitle());
            contentEditText.setText(note.getContent());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        final AlertDialog dialog = builder.create();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();

                if (title.isEmpty() || content.isEmpty()) {
                    // Display a toast message if either title or content is empty
                    Toast.makeText(requireContext(), "Please enter both title and content.", Toast.LENGTH_SHORT).show();
                } else {
                    if (note == null) {
                        // If adding a new note, create a new Note object and add it to the list
                        Note newNote = new Note(title, content);
                        if (onNoteAddedListener != null) {
                            onNoteAddedListener.onNoteAdded(newNote);
                        }
                    } else {
                        // If editing an existing note, update the Note object and notify the adapter
                        note.setTitle(title);
                        note.setContent(content);
                        if (onNoteAddedListener != null) {
                            onNoteAddedListener.onNoteEdited(note, position);
                        }
                    }

                    dialog.dismiss();
                }
            }
        });

        return dialog;
    }
}
