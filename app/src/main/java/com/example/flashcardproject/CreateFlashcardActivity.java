package com.example.flashcardproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CreateFlashcardActivity extends AppCompatActivity {
    private EditText questionEditText, answerEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard);

        questionEditText = findViewById(R.id.questionEditText);
        answerEditText = findViewById(R.id.answerEditText);
        saveButton = findViewById(R.id.saveFlashcardButton);

        // Save button click listener
        saveButton.setOnClickListener(view -> {
            String question = questionEditText.getText().toString();
            String answer = answerEditText.getText().toString();

            if (!question.isEmpty() && !answer.isEmpty()) {
                String id = String.valueOf(System.currentTimeMillis()); // Simple ID generation
                Flashcard flashcard = new Flashcard(id, question, answer);

                // Send back the flashcard to HomeActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("flashcard", (CharSequence) flashcard);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
