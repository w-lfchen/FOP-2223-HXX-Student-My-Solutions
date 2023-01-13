package h09.basic;

public interface BasicFactory<T> {

    /**
     * Create a new instance of {@link T}
     *
     * @return a new instance of {@link T}
     */
    T create();
}
