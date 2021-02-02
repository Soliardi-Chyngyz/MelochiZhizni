package com.example.melochizhizni.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.melochizhizni.R;
import com.example.melochizhizni.data.models.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FormFragment extends Fragment {

    private EditText title, features, size, description, sizeInBox, weight, price, article;
    private ImageView img1;
    private Uri imageDate;
    private Button upload;
    private FirebaseFirestore firestore;
    private StorageReference storage = FirebaseStorage.getInstance().getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        img1.setOnClickListener(v -> choosePhoto());

        saveOnFireStore();
    }

    private void saveOnFireStore() {
        upload.setOnClickListener(v -> {
            String titleName = title.getText().toString();
            String articleS = article.getText().toString();
            String featuresName = features.getText().toString();
            String sizeN = size.getText().toString();
            String descrD = description.getText().toString();
            String sizeInBoxD = sizeInBox.getText().toString();
            String img = imageDate.toString();
            String weightS = weight.getText().toString();
            String priceS = price.getText().toString();
            String spinCategory = spinner.getSelectedItem().toString();
            Item item = new Item(titleName, articleS, img, featuresName, sizeN, descrD, sizeInBoxD, weightS, priceS, spinCategory);


            firestore
                    .collection("Items")
                    .document(String.valueOf(item.getArticle()))
                    .set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getContext(), "Успешно загрузилось в бэк", Toast.LENGTH_SHORT).show();
                    uploadPhoto(item, user);
                    close();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Problem", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void uploadPhoto(Item item, FirebaseUser user) {
        Uri uri = Uri.parse(item.getPhoto());
        StorageReference imageRef = storage.child("images/" + user.getUid() + "/" + uri.getLastPathSegment());
        UploadTask uploadTask = imageRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUrl = imageRef.getDownloadUrl();
                downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        item.setPhoto(uri.toString());
                        // upload our Cloud Firestore with public image URI
                        updatePhotoDatabase(item);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Storage problem", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void updatePhotoDatabase(Item item) {
        FirebaseFirestore.getInstance()
                .collection("Items")
                .document(String.valueOf(item.getArticle()))
                .set(item);
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigateUp();
    }

    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Glide.with(this).load(data.getData()).into(img1);
            imageDate = data.getData();
            img1.setImageURI(imageDate);
        }
    }

    private void init(View view) {
        firestore = FirebaseFirestore.getInstance();
        article = view.findViewById(R.id.ed_article);
        title = view.findViewById(R.id.ed_title);
        features = view.findViewById(R.id.ed_features);
        size = view.findViewById(R.id.ed_size);
        sizeInBox = view.findViewById(R.id.ed_size_in_box);
        description = view.findViewById(R.id.ed_description);
        img1 = view.findViewById(R.id.ed_add_img1);
        upload = view.findViewById(R.id.ed_btn_upload);
        weight = view.findViewById(R.id.ed_weight);
        price = view.findViewById(R.id.ed_price);
        spinner = view.findViewById(R.id.form_spinner);
    }
}