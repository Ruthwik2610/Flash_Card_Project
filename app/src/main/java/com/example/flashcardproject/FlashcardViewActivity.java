package com.example.flashcardproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardViewActivity extends AppCompatActivity {
    private TextView flashcardText;
    private Button markAsKnownButton, shuffleButton;
    private boolean showingQuestion = true;
    private Flashcard currentFlashcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_view);

        flashcardText = findViewById(R.id.flashcardText);
        markAsKnownButton = findViewById(R.id.markAsKnownButton);
        shuffleButton = findViewById(R.id.shuffleButton);

        // Load the first flashcard
        loadNextFlashcard();

        // Set up flip animation on click
        flashcardText.setOnClickListener(view -> flipFlashcard());

        // Mark as known
        markAsKnownButton.setOnClickListener(view -> markFlashcardAsKnown());

        // Shuffle flashcards
        shuffleButton.setOnClickListener(view -> shuffleFlashcards());
    }

    private void flipFlashcard() {
        if (showingQuestion) {
            flashcardText.setText(currentFlashcard.getAnswer());
        } else {
            flashcardText.setText(currentFlashcard.getQuestion());
        }
        showingQuestion = !showingQuestion;
    }

    private void loadNextFlashcard() {
        // Logic to load the next flashcard from Firebase or list
    }

    private void markFlashcardAsKnown() {
        // Mark flashcard as known in Firebase
    }

    private void shuffleFlashcards() {
        // Shuffle flashcards logic
    }
}
