package com.besandr.util;

/**Each parsing thread add itself into the THREADS_POOL and after finishing parsing
 * remove itself from it. ThreadsBarrier is waiting until there are any parsing threads.
 * Also it prints progress bar while looping.
 */

public class ThreadsBarrier {

    public static void makeBarrier() {
        while (!SetsHolder.THREADS_POOL.isEmpty()) {
            Utils.printProgressBar();
            try {
                Thread.sleep(100);
            }
            catch (Exception ignored) {}
        }
        System.out.println("\n");
    }
}
