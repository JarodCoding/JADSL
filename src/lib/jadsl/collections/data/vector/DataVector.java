package lib.jadsl.collections.data.vector;

import java.util.Arrays;
import java.util.Collection;
/**
 * Created by Pascal Jarod Kuthe on 28.04.2016.
 */
public abstract class DataVector<T> implements Cloneable {
    private T[] data;
    protected DataVectorType type;
    //Use DataVectorFactory
    public DataVector(int dimension, T[] data, DataVectorType type) {
        this.data = Arrays.copyOfRange(data, 0, dimension);
        this.type = type;
        if(!type.isTypeArithmeticallyCompatible(data.getClass()))
            throw new IllegalArgumentException("Data with DataClass "+data.getClass()+" are not compatible with Vectors of the Type"+type.getClass().getName());
    }

    public abstract <X> double distance(DataVector<X> x);
    public abstract <X> DataVector<T> add(X x);
    public abstract <X> DataVector<T> add(DataVector<X> x);
    public abstract <X> DataVector<T> add(DataVector<X>... x);
    public <X> DataVector<T> add(Collection<DataVector<X>> x){
        return subtract((DataVector<X>[])x.toArray());
    }
    public abstract <X> DataVector<T> subtract(X x);
    public abstract <X> DataVector<T> subtract(DataVector<X> x);
    public abstract <X> DataVector<T> subtract(DataVector<X>... x);
    public <X> DataVector<T> subtract(Collection<DataVector<X>> x){
        return subtract((DataVector<X>[])x.toArray());
    }
    public abstract <X> DataVector<T> divideElements(X x);
    public abstract <X> DataVector<T> divideElements(DataVector<X> x);
    public abstract <X> DataVector<T> divideElements(DataVector<X>... x);
    public <X> DataVector<T> divideElements(Collection<DataVector<X>> x){
        return divideElements((DataVector<X>[])x.toArray());
    }
    public abstract <X> DataVector<T> multiplyElements(X x);
    public abstract <X> DataVector<T> multiplyElements(DataVector<X> x);
    public abstract <X> DataVector<T> multiplyElements(DataVector<X>... x);
    public <X> DataVector<T> multiplyElements(Collection<DataVector<X>> x){
        return multiplyElements((DataVector<X>[])x.toArray());
    }
    public void checkArithmeticCompatible(Class<?> c){
        if(!type.isTypeArithmeticallyCompatible(c))
            throw new IllegalArgumentException("Data of the Class "+c.getName()+" are not compatible with arethmetic operations with Vectors of the Type"+type.getClass().getName());    }
    public void checkDistanceCompatible(Class<?> c){
        if(!type.isTypeDistanceCompatible(c))
        throw new IllegalArgumentException("DataVectors with DataClass "+c.getName()+" are not compatible with distance operations with Vectors of the Type"+type.getClass().getName());

    }
    public T get(int dimension){
        return data[dimension];
    }
    public void set(int dimension,T data){
        this.data[dimension] = data;
    }
    public int getDimension(){
        return data.length;
    }

    @Override
    public Object clone() {
        DataVector<T> res = null;
        try {
            res = this.getClass().getConstructor(Integer.class,data.getClass(),DataVectorType.class).newInstance(data.length,data,type);
        }catch (Exception e){
            //Error using reflections
        e.printStackTrace();
    }
    return res;
    }
}