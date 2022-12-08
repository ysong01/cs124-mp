package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;
import edu.illinois.cs.cs124.ay2022.mp.network.Client;
import java.util.concurrent.CompletableFuture;

public class AddPlaceActivity extends AppCompatActivity {
  private static final String TAG = AddPlaceActivity.class.getSimpleName();
  private static final int GET_FROM_GALLERY = 3;
  private static final int RESULT_LOAD_IMG = 3;

  // private Place desc2 = new Place();
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Load the layout for this activity and set the title
    setContentView(R.layout.activity_addplace);
    Intent returnToMain = new Intent(this, MainActivity.class);
    returnToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(
        v -> {
          startActivity(returnToMain);
        });
    Button uploadPic = findViewById(R.id.photo_button);
    uploadPic.setOnClickListener(
        c -> {
          Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
          photoPickerIntent.setType("image/*");
          startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        });
    Button descButton = findViewById(R.id.save_button);
    descButton.setOnClickListener(
        b -> {
          // getting desc, lat, long, id, name

          String lat = getIntent().getStringExtra("latitude");
          String long1 = getIntent().getStringExtra("longitude");
          double lat2 = Double.valueOf(lat);
          double long2 = Double.valueOf(long1);
          String appID = "809f2bc7-d1a5-452a-a76a-ed277e35ac36";
          String name = "description";
          EditText text = findViewById(R.id.description);
          String text1 = text.getText().toString();
          Place userDesc = new Place(appID, name, lat2, long2, text1);

          CompletableFuture<ResultMightThrow<Boolean>> completableFuture =
              new CompletableFuture<>();

          Client.start().postFavoritePlace(userDesc, completableFuture::complete);

          startActivity(returnToMain);
        });
  }

  @Override
  protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      if (requestCode == RESULT_LOAD_IMG) {

        // Get ImageURi and load with help of picasso
        // Uri selectedImageURI = data.getData();

        Picasso.get().load(data.getData()).into((ImageView) findViewById(R.id.imageView3));
      }
    }
  }
}
