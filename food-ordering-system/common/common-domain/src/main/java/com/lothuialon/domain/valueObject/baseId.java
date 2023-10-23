package com.lothuialon.domain.valueObject;
import java.util.Objects;


public abstract class baseId<T> {
    
    private final T value;

    public baseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof baseId)) {
            return false;
        }
        baseId baseId = (baseId) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
