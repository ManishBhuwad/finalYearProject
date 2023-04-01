package com.example.manishproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mess_food_pannel.FoodSupplyDetails;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class Mess_PostDish extends Activity {

    ImageButton imageButton;
    Button post_dish;
    Spinner Dishes;
    TextInputLayout desc, qty, pri;
    String description, quantity, price, dishes;
    Uri imageuri;
    //private Uri mCropimageuri;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference dataaa;
    FirebaseAuth FAuth;
    StorageReference ref;
    String MessId;
    String RandomUId;
    String State, City, Sub;

    private final int PICK_IMAGE_REQUEST = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_post_dish);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Dishes = (Spinner) findViewById(R.id.dishes);
        desc = (TextInputLayout) findViewById(R.id.description);
        qty = (TextInputLayout) findViewById(R.id.quantity);
        pri = (TextInputLayout) findViewById(R.id.price);
        post_dish = (Button) findViewById(R.id.post);
        FAuth = FirebaseAuth.getInstance();

        databaseReference = firebaseDatabase.getInstance().getReference("FoodSupplyDetails");

        try {
            String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            dataaa = firebaseDatabase.getInstance().getReference("Mess").child(userid);
            dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Mess messc = dataSnapshot.getValue(Mess.class);
                    State = messc.getState();
                    City = messc.getCity();
                    Sub = messc.getSuburban();
                    imageButton = (ImageButton) findViewById(R.id.imageupload);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onSelectImageClick(v);
                        }
                    });


                    post_dish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dishes = Dishes.getSelectedItem().toString().trim();
                            description = desc.getEditText().getText().toString().trim();
                            quantity = qty.getEditText().getText().toString().trim();
                            price = pri.getEditText().getText().toString().trim();

                            if (isValid()) {
                                uploadImage();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {

            Log.e("Errrrrr: ", e.getMessage());
        }
    }

    private boolean isValid() {
        desc.setErrorEnabled(false);
        desc.setError("");
        qty.setErrorEnabled(false);
        qty.setError("");
        pri.setErrorEnabled(false);
        pri.setError("");

        boolean isValiDescription = false, isValidPrice = false, isvalidQuantity = false, isvalid = false;
        if (TextUtils.isEmpty(description)) {
            desc.setErrorEnabled(true);
            desc.setError("Description is Required");

        } else {

            desc.setError(null);
            isValiDescription = true;
        }
        if (TextUtils.isEmpty(quantity)) {
            qty.setErrorEnabled(true);
            qty.setError("Quantity is Required");
        } else {
            isvalidQuantity = true;
        }
        if (TextUtils.isEmpty(price)) {
            pri.setErrorEnabled(true);
            pri.setError("Price is Required");
        } else {
            isValidPrice = true;
        }
        isvalid = (isValiDescription && isvalidQuantity && isValidPrice) ? true : false;

        return isvalid;
    }

    private void uploadImage() {

        if (imageuri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(Mess_PostDish.this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            RandomUId = UUID.randomUUID().toString();
            ref = storageReference.child(RandomUId);
            MessId= FirebaseAuth.getInstance().getCurrentUser().getUid();

            ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            FoodSupplyDetails info = new FoodSupplyDetails(dishes, quantity, price, description, String.valueOf(uri), RandomUId, MessId);
                            firebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(State).child(City).child(Sub).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId)
                                    .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                            Toast.makeText(Mess_PostDish.this, "Dish posted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    progressDialog.dismiss();
                    Toast.makeText(Mess_PostDish.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    progressDialog.setCanceledOnTouchOutside(false);
                }
            });
        }

    }


    private void onSelectImageClick(View v) {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            imageuri = data.getData();
            try {

                // Setting image on image view using Bitmap
                InputStream inputStream = getContentResolver().openInputStream(imageuri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                Bitmap bitmap = MediaStore
//                        .Images
//                        .Media
//                        .getBitmap(
//                                getContentResolver(),
//                                imageuri);
                imageButton.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
//        CropImage.startPickImageActivity(this);
//
//    }
//
//    @Override
//    @SuppressLint("NewApi")
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//
//        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            imageuri = CropImage.getPickImageResultUri(this, data);
//
//            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageuri)) {
//                mCropimageuri = imageuri;
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//
//            } else {
//
//                startCropImageActivity(imageuri);
//            }
//        }
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//                ((ImageButton) findViewById(R.id.imageupload)).setImageURI(result.getUri());
//                Toast.makeText(this, "Cropped Successfully", Toast.LENGTH_SHORT).show();
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                Toast.makeText(this, "Cropping failed" + result.getError(), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//
//        if (mCropimageuri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            startCropImageActivity(mCropimageuri);
//        } else {
//            Toast.makeText(this, "cancelling,required permission not granted", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void startCropImageActivity(Uri imageuri) {
//
//        CropImage.activity(imageuri)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMultiTouchEnabled(true)
//                .start(this);
//
//
//
    }

}
