package SmartPhoneRealTimeApplication;

public class InternetBrowser extends Thread {
    private static int totalPagesLoaded = 0;

    public static synchronized void loadPage() {
        totalPagesLoaded++;
        System.out.println("Internet Browser: Total pages loaded: " + totalPagesLoaded);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Internet Browser: Loading webpage " + i + "...");
                Thread.sleep(4000);
                loadPage();
            }
        } catch (InterruptedException e) {
            System.out.println("Internet Browser: Interrupted.");
        }
    }
}

