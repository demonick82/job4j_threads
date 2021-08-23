package resoursesynch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Map<Integer, User> store = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean rsl = false;
        User tmpUser = store.get(user.getId());
        if (tmpUser == null) {
            store.put(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean update(User user) {
        boolean rsl = false;
        if (store.containsKey(user.getId())) {
            store.put(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean delete(User user) {
        boolean rsl = false;
        if (store.containsKey(user.getId()) && Objects.equals(store.get(user.getId()), user)) {
            store.remove(user.getId());
            rsl = true;
        }
        return rsl;
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        boolean rsl = false;
        User fromAccount = store.get(fromId);
        User toAccount = store.get(told);
        if (fromAccount != null && toAccount != null && fromAccount.getAmount() >= toAccount.getAmount()) {
            fromAccount.setAmount(fromAccount.getAmount() - amount);
            toAccount.setAmount(toAccount.getAmount() + amount);
            rsl = true;
        }
        return rsl;
    }
}
