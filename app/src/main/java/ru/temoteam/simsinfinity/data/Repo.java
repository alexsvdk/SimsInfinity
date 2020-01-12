package ru.temoteam.simsinfinity.data;

import ru.temoteam.simsinfinity.data.models.Goal;
import ru.temoteam.simsinfinity.data.models.User;

public interface Repo {
    void saveGoal(Goal goal);
    void removeGoal(Goal goal);
    void getGoals();
    void updateGoal(Goal goal);

    void saveUser(User user);


}
