import java.util.Arrays;

public class Description {
private long[] desc;

public Description(long[] desc, int size) {
        this.desc = Arrays.copyOf(desc, size);
        Arrays.sort(this.desc);
}

public long[] getDescriptions() {
        return this.desc;
}

@Override
public int hashCode() {
        return Arrays.hashCode(desc);
}

@Override
public boolean equals(Description other) {
        return Arrays.equals(this.getDescriptions(), other.getDescriptions());
}
}
