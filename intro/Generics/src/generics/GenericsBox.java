package generics;

public class GenericsBox<T> {
    private T t;

    /** 
     * @return T
     */
    public T getT() {
        return t;
    }
 
    /** 
     * @param t
     */
    public void setT(T t) {
        this.t = t;
    }    
}
