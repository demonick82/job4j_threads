package concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(() -> {

        });
        Thread second = new Thread(() -> {

        });

        System.out.printf("%s %s%s", first.getName(), first.getState(), System.lineSeparator());
        System.out.printf("%s %s%s", second.getName(), second.getState(), System.lineSeparator());
        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            System.out.printf("%s %s%s", first.getName(), first.getState(), System.lineSeparator());
            System.out.printf("%s %s%s", second.getName(), second.getState(), System.lineSeparator());

        }
        if (first.getState() == Thread.State.TERMINATED && second.getState() == Thread.State.TERMINATED) {
            System.out.println("Работа завершена");
        }
    }
}
