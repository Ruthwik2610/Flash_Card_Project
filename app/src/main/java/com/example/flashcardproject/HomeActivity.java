package com.example.flashcardproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton addFlashcardButton;
    private FlashcardAdapter flashcardAdapter;
    private List<Flashcard> flashcardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        addFlashcardButton = findViewById(R.id.addFlashcardButton);

        // Initialize flashcard list and adapter
        flashcardList = new ArrayList<>();
        flashcardAdapter = new FlashcardAdapter(flashcardList, this);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(flashcardAdapter);

        // Add button click listener to open CreateFlashcardActivity
        addFlashcardButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, CreateFlashcardActivity.class);
            startActivityForResult(intent, 100); // Use request code 100 for create
        });

        // Load flashcards from local data or Firebase (if connected)
        loadFlashcards();
    }

    private void loadFlashcards() {
        // In a real app, this would fetch from a local database or Firebase
        // For now, we'll just create a few sample flashcards for demonstration
        flashcardList.add(new Flashcard("1", "What is Java?", "A programming language."));
        flashcardList.add(new Flashcard("2", "What is Android?", "A mobile operating system."));
        flashcardList.add(new Flashcard("3", "What is an Intent?", "An Android component for navigation."));
        flashcardAdapter.notifyDataSetChanged();
    }

    // This will handle result from both CreateFlashcardActivity and EditFlashcardActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Flashcard flashcard = (Flashcard) data.getSerializableExtra("flashcard");
            if (requestCode == 100) {
                // Adding a new flashcard
                flashcardList.add(flashcard);
            } else if (requestCode == 200) {
                // Editing an existing flashcard
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    flashcardList.set(position, flashcard);
                }
            }
            flashcardAdapter.notifyDataSetChanged();
        }
    }
}
