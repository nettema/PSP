package numbers;

import java.util.List;

/**
 * Created by nettema on 19.09.2016.
 */
public class Pair implements Comparable {
    Float key;
    List<Float> value;

    public Pair(Float key, List<Float> value) {
        this.key = key;
        this.value = value;
    }

    public List<Float> getValue() {
        return value;
    }

    @Override
    public int compareTo(Object o) {
        return key.compareTo(((Pair)o).key);
    }
}
