package ru.temoteam.simsinfinity.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import ru.temoteam.simsinfinity.data.Repo;
import ru.temoteam.simsinfinity.data.models.Goal;

public class FireBaseRepo implements Repo {

    static String TAG = "FireBaseRepo";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();


    public FireBaseRepo(){

    }

    @Override
    public void saveGoal(Goal goal) {
        db.collection("users")
                .document(auth.getUid()).collection("goals")
                .add(goal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    @Override
    public void removeGoal(Goal goal) {

    }

    @Override
    public void getGoals() {

    }

}
