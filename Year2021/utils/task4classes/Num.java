package Year2021.utils.task4classes;

public class Num implements Comparable<Num> {
    private final int value;
    private boolean isMarked = false;

    public Num(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked() {
        isMarked = true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Num num)) return false;

        return value == num.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(Num o) {
        return Integer.compare(value, o.value);
    }
}
