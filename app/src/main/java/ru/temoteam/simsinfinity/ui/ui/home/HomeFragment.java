package ru.temoteam.simsinfinity.ui.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;

import ru.temoteam.simsinfinity.R;
import ru.temoteam.simsinfinity.ui.GoalActivity;
import ru.temoteam.simsinfinity.ui.TaskActivity;
import ru.temoteam.simsinfinity.ui.adapters.GoalsAdapter;
import ru.temoteam.simsinfinity.ui.adapters.TasksAdapter;

public class HomeFragment extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView rw;
    private TasksAdapter tasksAdapter;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        fab = root.findViewById(R.id.fab);
        rw = root.findViewById(R.id.recycler);


        fab.setOnClickListener(v -> startActivity(new Intent(getActivity(), TaskActivity.class)));

        rw.setLayoutManager(new LinearLayoutManager(getActivity()));
        tasksAdapter =  new TasksAdapter(getActivity());
        rw.setAdapter(tasksAdapter);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        tasksAdapter.update();
    }
}