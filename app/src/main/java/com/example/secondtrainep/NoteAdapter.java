package com.example.secondtrainep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnNoteClickListener onNoteClickListener;

    public interface OnNoteClickListener {
        void onNoteClick(int position);

        void onNoteDeleteClick(int position);
    }

    public NoteAdapter(List<Note> noteList, OnNoteClickListener onNoteClickListener) {
        this.noteList = noteList;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView, onNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.contentTextView.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTextView;
        TextView contentTextView;
        OnNoteClickListener onNoteClickListener;

        NoteViewHolder(@NonNull View itemView, OnNoteClickListener onNoteClickListener) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            this.onNoteClickListener = onNoteClickListener;

            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.btnDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteClickListener.onNoteDeleteClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            onNoteClickListener.onNoteClick(getAdapterPosition());
        }
    }
}
