package com.example.secondtrainep;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment implements NoteAdapter.OnNoteClickListener, AddEditNoteDialog.OnNoteAddedListener {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FloatingActionButton fabAddNote = view.findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNoteDialog();
            }
        });

        // Initialize the noteList with an empty ArrayList
        noteList = new ArrayList<>();

        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Note note = noteList.get(position);
        showEditNoteDialog(note, position);
    }

    @Override
    public void onNoteDeleteClick(int position) {
        noteList.remove(position);
        noteAdapter.notifyItemRemoved(position);
    }

    private void showAddNoteDialog() {
        AddEditNoteDialog dialog = AddEditNoteDialog.newInstance(null, -1);
        dialog.setOnNoteAddedListener(this);
        dialog.show(getChildFragmentManager(), "add_note_dialog");
    }

    private void showEditNoteDialog(Note note, int position) {
        AddEditNoteDialog dialog = AddEditNoteDialog.newInstance(note, position);
        dialog.setOnNoteAddedListener(this);
        dialog.show(getChildFragmentManager(), "edit_note_dialog");
    }

    @Override
    public void onNoteAdded(Note note) {
        noteList.add(note);
        noteAdapter.notifyItemInserted(noteList.size() - 1);
    }

    @Override
    public void onNoteEdited(Note note, int position) {
        noteList.set(position, note);
        noteAdapter.notifyItemChanged(position);
    }
}
