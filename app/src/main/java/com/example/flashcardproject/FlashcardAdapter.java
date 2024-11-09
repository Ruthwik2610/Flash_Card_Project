package com.example.flashcardproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlashcardAdapter extends RecyclerView.Adapter<FlashcardAdapter.FlashcardViewHolder> {
    private List<Flashcard> flashcards;
    private Context context;

    public FlashcardAdapter(List<Flashcard> flashcards, Context context) {
        this.flashcards = flashcards;
        this.context = context;
    }

    @Override
    public FlashcardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flashcard_item, parent, false);
        return new FlashcardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlashcardViewHolder holder, int position) {
        Flashcard flashcard = flashcards.get(position);
        holder.flashcardQuestion.setText(flashcard.getQuestion());

        // Handle edit button
        holder.editButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, EditFlashcardActivity.class);
            intent.putExtra("flashcard", (CharSequence) flashcard);
            intent.putExtra("position", position);
            ((HomeActivity) context).startActivityForResult(intent, 200); // Use request code 200 for edit
        });

        // Handle delete button
        holder.deleteButton.setOnClickListener(view -> {
            flashcards.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, flashcards.size());
        });
    }

    @Override
    public int getItemCount() {
        return flashcards.size();
    }

    public static class FlashcardViewHolder extends RecyclerView.ViewHolder {
        TextView flashcardQuestion;
        Button editButton, deleteButton;

        public FlashcardViewHolder(View itemView) {
            super(itemView);
            flashcardQuestion = itemView.findViewById(R.id.flashcardQuestion);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
