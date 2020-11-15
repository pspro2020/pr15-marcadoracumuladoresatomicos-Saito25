package acumulator;


import java.util.concurrent.atomic.LongAdder;

public class Acumulator {

    private final LongAdder[] longAdders = new LongAdder[6];

    public Acumulator() {
        initAdders();
    }

    private void initAdders() {
        for (int i = 0; i < longAdders.length; i++) {
            longAdders[i] = new LongAdder();
        }
    }

    public int getResultOfDice(int face) {
        if (face >= longAdders.length || face < 0) {
            throw new IllegalArgumentException();
        }
        return longAdders[face].intValue();
    }

    public void acumulateIn(int face) {
        if (face >= longAdders.length || face < 0) {
            throw new IllegalArgumentException();
        }
        longAdders[face].add(1);
    }

    public int getTotalRoll() {
        int result = 0;
        for (LongAdder adder: longAdders) {
            result += adder.intValue();
        }
        return result;
    }
}
