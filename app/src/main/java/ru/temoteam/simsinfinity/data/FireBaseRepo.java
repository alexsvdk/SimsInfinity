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
import ru.temoteam.simsinfinity.data.models.User;

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
        String id = (String) goal.getAdditionalProperties().get("id");
        db.collection("users")
                .document(auth.getUid()).collection("goals").document(id).delete();
    }

    @Override
    public void getGoals() {

    }

    @Override
    public void updateGoal(Goal goal) {
        String id = (String) goal.getAdditionalProperties().get("id");
        db.collection("users")
                .document(auth.getUid()).collection("goals").document(id).set(goal);
    }

    @Override
    public void saveUser(User user) {
        db.collection("users")
                .document(auth.getUid()).set(user);
    }

    public DocumentReference getDB(){
        return db.collection("users")
                .document(auth.getUid());
    }
}
