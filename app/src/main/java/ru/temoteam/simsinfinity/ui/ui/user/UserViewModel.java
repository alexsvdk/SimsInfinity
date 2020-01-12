package ru.temoteam.simsinfinity.ui.ui.user;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import ru.temoteam.simsinfinity.data.FireBaseRepo;
import ru.temoteam.simsinfinity.data.models.User;

public class UserViewModel extends ViewModel {

    MutableLiveData<User> user;
    MutableLiveData<Boolean> editing;
    FireBaseRepo fbr = new FireBaseRepo();

    public UserViewModel() {
        editing = new MutableLiveData<>();
        user = new MutableLiveData<>();
        editing.setValue(false);

        fbr.getDB().addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot==null || documentSnapshot.getData()==null) return;
                User u = User.fromMap(documentSnapshot.getData());
                u.getAdditionalProperties().put("read",true);
                user.postValue(u);
            }
        });

        user.observeForever(user -> {
            if (user==null || user.getAdditionalProperties().containsKey("read")) return;
            fbr.saveUser(user);
        });
    }


}