package lib.jadsl.collections.data.vector;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
/**
 * Created by Pascal Jarod Kuthe on 28.04.2016.
 */
public abstract class DataVector implements Cloneable {
    private Object[] data;
    protected DataVectorType type;
    //Use DataVectorFactory
    public DataVector(Integer dimension, DataVectorType type,Object... data) {
        this.data = new Object[dimension];
        for(int i = 0;i < data.length;i++){
            this.data[i] = data[i];
        }
        this.type = type;
        if(!type.isAssignableFrom(data.getClass().getComponentType()))
            throw new IllegalArgumentException("Data with DataClass "+data.getClass()+" are not compatible with Vectors of the Type"+type.getClass().getName());
    }

    public double distance(DataVector x){
        DataVector res = subtract(x);
        return res.length();
    }
    public abstract double length();

    public abstract <X> DataVector add(X x);
    public abstract DataVector add(DataVector x);
    public abstract DataVector add(DataVector... x);
    public DataVector add(Collection<DataVector> x){
        return subtract((DataVector[])x.toArray());
    }
    public abstract <X> DataVector subtract(X x);
    public abstract  DataVector subtract(DataVector x);
    public abstract DataVector subtract(DataVector... x);
    public DataVector subtract(Collection<DataVector> x){
        return subtract((DataVector[])x.toArray());
    }
    public abstract <X> DataVector divideElements(X x);
    public abstract DataVector divideElements(DataVector x);
    public abstract DataVector divideElements(DataVector... x);
    public <X> DataVector divideElements(Collection<DataVector> x){
        return divideElements((DataVector[])x.toArray());
    }
    public abstract <X> DataVector multiplyElements(X x);
    public abstract DataVector multiplyElements(DataVector x);
    public abstract DataVector multiplyElements(DataVector... x);
    public DataVector multiplyElements(Collection<DataVector> x){
        return multiplyElements((DataVector[])x.toArray());
    }
    public void checkArithmeticCompatible(Class<?> c){
        if(!type.isArithmeticallyCompatible(c))
            throw new IllegalArgumentException("Data of the Class "+c.getName()+" are not compatible with arethmetic operations with Vectors of the Type"+type);    }
    public void checkArithmeticCompatible(Class<?>... c){
        if(!type.isArithmeticallyCompatible(c))
            throw new IllegalArgumentException("Data of the Classes "+c.toString()+" are not compatible with arethmetic operations with Vectors of the Type"+type);    }
    public void checkArithmeticCompatible(DataVectorType type){
        if(!this.type.isArithmeticallyCompatible(type))
            throw new IllegalArgumentException("Data of the Classes "+type+" are not compatible with arethmetic operations with Vectors of the Type"+this.type);    }

    public Object get(int dimension){
        return data[dimension];
    }
    public void set(int dimension,Object data){
        this.data[dimension] = data;
    }
    public int getDimension(){
        return data.length;
    }

    @Override
    public Object clone() {
        DataVector res = null;
        try {
            res = this.getClass().getConstructor(Integer.class,DataVectorType.class,Object.class).newInstance(data.length,type,data);
        }catch (Exception e){
            //Error using reflections
        e.printStackTrace();
    }
    return res;
    }
    public DataVectorType getType(){
        return  type;
    }

    @Override
    public String toString() {
        String res = getDimension()+" dimensional DataVector of type "+type.toString()+" with Data: ["+(data.length>0?data[0]:"");
        for(int i = 1;i< data.length;i++)res+=","+data[1].toString();
        res += "]";
        return res;
    }
}
