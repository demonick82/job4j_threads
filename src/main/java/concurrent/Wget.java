package concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread=new Thread(()->{
            try {
                System.out.print("Start loading ... ");
                for (int i = 0; i <=100 ; i++) {
                    Thread.sleep(1000);
                    System.out.printf("\rLoading : %d %%",i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("Main");
    }
}
