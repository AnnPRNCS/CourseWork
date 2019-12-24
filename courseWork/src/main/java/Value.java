import java.util.Objects;

public class Value {
    private int firstVertex;
    private int lastVertex;
    private int value;

    public Value(int firstVertex, int lastVertex, int value) {
        this.firstVertex = firstVertex;
        this.lastVertex = lastVertex;
        this.value = value;
    }

    public Value(Value value) {
        this.firstVertex = value.firstVertex;
        this.lastVertex = value.lastVertex;
        this.value = value.value;
    }


    public int getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(final int firstVertex) {
        this.firstVertex = firstVertex;
    }

    public int getLastVertex() {
        return lastVertex;
    }

    public void setLastVertex(final int lastVertex) {
        this.lastVertex = lastVertex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("first vertex: ")
                .append(firstVertex)
                .append(", last vertex: ")
                .append(lastVertex)
                .append(", values: ")
                .append(value);
        return sb.toString();
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Value value1 = (Value) o;
        return firstVertex == value1.firstVertex &&
                lastVertex == value1.lastVertex &&
                value == value1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstVertex, lastVertex, value);
    }
}
