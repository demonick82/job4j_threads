package resoursesynch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();

    public synchronized boolean add(User user) {
        return store.putIfAbsent(user.getId(),user)!=null;
    }

    public synchronized boolean update(User user) {
        return store.replace(user.getAmount(),user)!=null;
    }

    public synchronized boolean delete(User user) {
        return store.remove(user.getId(),user);
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
