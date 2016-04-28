package lib.jadsl.collections.data.vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by Pascal Jarod Kuthe on 28.04.2016.
 */
public class DataVectorFactory {
    private ArrayList<Class<? extends DataVector<?>>> ClassList;
    private ArrayList<DataVectorType> TypeList;

    public DataVectorFactory(){
        TypeList.addAll(Arrays.asList(DefaultTypeList));
        ClassList.addAll(Arrays.asList(DefaultClassList));

    }
    public static int DefaultAmount = 1;
    public static final DataVectorType[] DefaultTypeList = new DataVectorType[DefaultAmount];
    public static final Class<? extends DataVector<?>>[] DefaultClassList = new Class[DefaultAmount];
    static{
        DefaultTypeList[0]  = new NumberVector.DefaultNumerVectorType();
        DefaultClassList[0] = NumberVector.class;
    }

    public <T> DataVector<T> creatDataVector(int dimension,T[] data){
        Class<? extends DataVector<T>> DataVectorClass = getDataVectorClass((Class<T>) data[0].getClass());
        DataVector<T> res = null;
        try {
            res = DataVectorClass.getConstructor(Integer.class,data.getClass()).newInstance(dimension,data);
        }catch (Exception e){
            //Error using reflections
            e.printStackTrace();
        }
        return res;
    }
    public <T> DataVector<T> createEmptyDataVectorFromDataClass(int dimension,Class<T> dataClass){
        Class<? extends DataVector<T>> DataVectorClass = getDataVectorClass(dataClass);
        DataVector<T> res = null;
        try {
            res = DataVectorClass.getConstructor(Integer.class,dataClass).newInstance(dimension,null );
        }catch (Exception e){
            //Error using reflections
            e.printStackTrace();
        }
        return res;
    }
    public <T> Class<? extends DataVector<T>> getDataVectorClass(Class<T> dataClass){
        for(int i = 0;i < TypeList.size();i++){
            if(TypeList.get(i).isTypeCompatible(dataClass))return (Class<? extends DataVector<T>>) ClassList.get(i);
        }
        return null;
    }
    public <T> void setDataVectorClass(DataVectorType type,Class<? extends DataVector<T>> dataVectorClass){
        for(int i = 0;i < TypeList.size();i++){
            if(type.getClass().equals(TypeList.get(i).getClass())){
                ClassList.set(i,dataVectorClass);
                return;
            }
        }
        TypeList.add(type);
        ClassList.add(dataVectorClass);
    }
}

