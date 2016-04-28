package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthje on 28.04.2016.
 */
public interface DataVectorType{
    public boolean isTypeCompatible(Class<?> c);
    public boolean isTypeDistanceCompatible(Class<?> c);
    public boolean isTypeArithmeticallyCompatible(Class<?> c);
}
