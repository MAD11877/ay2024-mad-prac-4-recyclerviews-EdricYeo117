package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button buttonFollow;
    private TextView tvName, tvDescription;
    private boolean followed;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        buttonFollow = findViewById(R.id.btnFollow);

        // Get user information from intent
        Intent intent = getIntent();
        if (intent != null) {
            currentUser = (User) intent.getSerializableExtra("user");
            if (currentUser != null) {
                // Set user information
                tvName.setText(currentUser.getName());
                tvDescription.setText(currentUser.getDescription());
                // Set initial follow state
                followed = currentUser.isFollowed();
                updateButtonMessageText();
            }
        }

        // Setup follow button click listener
        setupFollowButton();
    }

    private void setupUserInformation() {
        Intent intent = getIntent();
        if (intent != null) {
            User user = (User) intent.getSerializableExtra("user");
            if (user != null) {
                String userName = user.getName();
                String userDescription = user.getDescription();
                tvName.setText(userName);
                tvDescription.setText(userDescription);
            }
        }
    }
    private void setupFollowButton() {
        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle follow state
                followed = !followed;
                updateButtonMessageText();
                String toastMessage = followed ? getString(R.string.button_follow_text) : getString(R.string.button_unfollow_text);
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();

                // Update isFollowed in User object
                if (currentUser != null) {
                    currentUser.setFollowed(followed);
                }
            }
        });
    }

    private void updateButtonMessageText() {
        buttonFollow.setText(followed ? R.string.button_unfollow_text : R.string.button_follow_text);
    }
}