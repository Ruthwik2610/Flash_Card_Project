package com.example.flashcardproject;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditFlashcardActivity extends AppCompatActivity {
    private EditText questionEditText, answerEditText;
    private Button saveButton;
    private Flashcard flashcard;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcard); // Using the same layout as CreateFlashcardActivity

        questionEditText = findViewById(R.id.questionEditText);
        answerEditText = findViewById(R.id.answerEditText);
        saveButton = findViewById(R.id.saveFlashcardButton);

        // Get the flashcard and position passed from the adapter
        flashcard = (Flashcard) getIntent().getSerializableExtra("flashcard");
        position = getIntent().getIntExtra("position", -1);

        // Populate the fields with existing flashcard data
        if (flashcard != null) {
            questionEditText.setText(flashcard.getQuestion());
            answerEditText.setText(flashcard.getAnswer());
        }

        // Save button click listener
        saveButton.setOnClickListener(view -> {
            String question = questionEditText.getText().toString();
            String answer = answerEditText.getText().toString();

            if (!question.isEmpty() && !answer.isEmpty()) {
                flashcard.setQuestion(question);
                flashcard.setAnswer(answer);

                // Send back the updated flashcard and position to HomeActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("flashcard", (CharSequence) flashcard);
                resultIntent.putExtra("position", position);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
