package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.CompletableFuture;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;
import edu.illinois.cs.cs124.ay2022.mp.network.Client;

public class AddPlaceActivity extends AppCompatActivity {
  private static final String TAG = AddPlaceActivity.class.getSimpleName();

  //private Place desc2 = new Place();
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Load the layout for this activity and set the title
    setContentView(R.layout.activity_addplace);
    Intent returnToMain = new Intent(this, MainActivity.class);
    returnToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(v -> {
      startActivity(returnToMain);
    });

    Button descButton = findViewById(R.id.save_button);
    descButton.setOnClickListener(b -> {
      //getting desc, lat, long, id, name

      String lat = getIntent().getStringExtra("latitude");
      String long1 = getIntent().getStringExtra("longitude");
      double lat2 = Double.parseDouble(lat);
      double long2 = Double.parseDouble(long1);
      String appID = "809f2bc7-d1a5-452a-a76a-ed277e35ac36";
      String name = "description";
      EditText text = findViewById(R.id.description);
      String text1 = text.getText().toString();
      Place userDesc = new Place(appID, name, lat2, long2, text1);

      CompletableFuture<ResultMightThrow<Boolean>> completableFuture = new CompletableFuture<>();


      Client.start().postFavoritePlace(userDesc, completableFuture::complete);

      startActivity(returnToMain);
    });
  }
}


