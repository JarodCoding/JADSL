package lib.jadsl.collections.data.vector;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Pascal Jarod Kuthe on 28.04.2016.
 */
public class DataVectorFactory {
    private ArrayList<Class<? extends DataVector<?>>> ClassList = new ArrayList<>();
    private ArrayList<DataVectorType> TypeList = new ArrayList<>();

    public DataVectorFactory(){
        TypeList.addAll(Arrays.asList(DefaultTypeList));
        ClassList.addAll(Arrays.asList(DefaultClassList));

    }
    public static int DefaultAmount = 1;
    public static final DataVectorType[] DefaultTypeList = new DataVectorType[DefaultAmount];
    public static final Class<? extends DataVector<?>>[] DefaultClassList = new Class[DefaultAmount];
    static{
        DefaultTypeList[0]  = NumberVector.DefaultType;
        DefaultClassList[0] = NumberVector.class;
    }

    public <T> DataVector<T> creatDataVector(int dimension,T[] data){
        Class<? extends DataVector<T>> DataVectorClass = getDataVectorClass((Class<T>) data[0].getClass());
        DataVector<T> res = null;
        try {
            res = DataVectorClass.getConstructor(Integer.class,Array.newInstance(getConstructorType(data.getClass().getComponentType()),0).getClass()).newInstance(dimension,data);
        }catch (Exception e){
            //Error using reflections
            e.printStackTrace();
        }
        return res;
    }
    public <T> DataVector<T> createEmptyDataVectorFromDataClass(int dimension,Class<T> dataClass){
        Class<? extends DataVector<T>> DataVectorClass = getDataVectorClass(dataClass);
        T[] nullPositionArray = (T[]) Array.newInstance(getConstructorType(dataClass),dimension);
        DataVector<T> res = null;
        try {
            res = DataVectorClass.getConstructor(Integer.class,nullPositionArray.getClass()).newInstance(dimension,nullPositionArray);
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
    public <T> Class<?> getConstructorType(Class<T> dataClass){
        for(int i = 0;i < TypeList.size();i++){
            if(TypeList.get(i).isTypeCompatible(dataClass))return TypeList.get(i).covertToConstructorType(dataClass);
        }
        return null;
    }
    public <T> void setDataVectorType(DataVectorType type,Class<? extends DataVector<T>> dataVectorClass){
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

