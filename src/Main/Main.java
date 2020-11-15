package Main;

import acumulator.Acumulator;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public Main() {
        Acumulator acumulator = new Acumulator();
        Thread[] threads = new Thread[3];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                int result;
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        result = ThreadLocalRandom.current().nextInt(0, 6);
                        acumulator.acumulateIn(result);
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 6; i++) {
            System.out.printf("La cara %d ha salido %d\n",
                    i + 1,
                    acumulator.getResultOfDice(i));
        }

        System.out.printf("El dado se ha lanzado %d veces\n",
                acumulator.getTotalRoll());
    }

    public static void main(String[] args) {
        new Main();
    }
}
