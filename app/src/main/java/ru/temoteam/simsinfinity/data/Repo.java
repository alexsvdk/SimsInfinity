package ru.temoteam.simsinfinity.data;

import ru.temoteam.simsinfinity.data.models.Goal;
import ru.temoteam.simsinfinity.data.models.Task;
import ru.temoteam.simsinfinity.data.models.User;

public interface Repo {

    void saveGoal(Goal goal);
    void removeGoal(Goal goal);
    void updateGoal(Goal goal);

    void saveTask(Task task);
    void removeTask(Task task);
    void updateTask(Task task);

    void saveUser(User user);


}
