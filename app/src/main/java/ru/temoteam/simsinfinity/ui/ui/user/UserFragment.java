package ru.temoteam.simsinfinity.ui.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.data.models.User;
import ru.temoteam.simsinfinity.util.DatePickerUtil;

public class UserFragment extends Fragment implements View.OnClickListener {

    //TODO calendar bug fix

    private UserViewModel userViewModel;

    int height = 180;
    boolean f = true;

    DatePicker date;
    EditText username;
    ImageView ava, edit;
    FloatingActionButton fab;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel =
                ViewModelProviders.of(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        date = root.findViewById(R.id.date);
        fab = root.findViewById(R.id.fab);;
        username = root.findViewById(R.id.username);
        ava = root.findViewById(R.id.ava);
        edit = root.findViewById(R.id.edit_im);



        userViewModel.editing.observe(this, aBoolean -> {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(date.getLayoutParams());
            lp.addRule(RelativeLayout.BELOW,R.id.textview);
            if (aBoolean) {
                fab.show();
                height = lp.height;
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                date.setLayoutParams(lp);
                username.setEnabled(true);
                date.setEnabled(true);
            }
            else {
                fab.hide();
                if (!f)
                lp.height = height;
                date.setLayoutParams(lp);
                username.setEnabled(false);
                date.setEnabled(false);
                f = false;
            }
        });

        userViewModel.user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user==null) return;
                username.setText(user.getUsername());
                DatePickerUtil.updateDP(date,new Date(user.getBirtday()));

            }
        });

        fab.setOnClickListener(this);
        edit.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if(userViewModel.editing.getValue()){
            userViewModel.user.postValue(new User().withUsername(username.getText().toString())
                    .withBirtday(DatePickerUtil.datePicker2Date(date)));
        }else {

        }
        userViewModel.editing.postValue(!userViewModel.editing.getValue());
    }
}