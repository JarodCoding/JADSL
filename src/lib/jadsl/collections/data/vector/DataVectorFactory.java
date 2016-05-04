package lib.jadsl.collections.data.vector;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Pascal Jarod Kuthe on 28.04.2016.
 */
public class DataVectorFactory {
    private ArrayList<Class<? extends DataVector>> ClassList = new ArrayList<>();
    private ArrayList<DataVectorType> TypeList = new ArrayList<>();

    public DataVectorFactory(){
        TypeList.addAll(Arrays.asList(DefaultTypeList));
        ClassList.addAll(Arrays.asList(DefaultClassList));

    }
    public static int DefaultAmount = 1;
    public static final DataVectorType[] DefaultTypeList = new DataVectorType[DefaultAmount];
    public static final Class<? extends DataVector>[] DefaultClassList = new Class[DefaultAmount];
    static{
        DefaultTypeList[0]  = NumberVector.DefaultType;
        DefaultClassList[0] = NumberVector.class;
    }

    public <T> DataVector creatDataVector(int dimension,T[] data){
        Class<? extends DataVector> DataVectorClass = getDataVectorClass(data[0].getClass());
        DataVector res = null;
        try {
            res = DataVectorClass.getConstructor(Integer.class,new Object[0].getClass()).newInstance(dimension,data);
        }catch (Exception e){
            //Error using reflections
            e.printStackTrace();
        }
        return res;
    }
    public DataVector createEmptyDataVectorFromDataClass(int dimension,Class<?> dataClass){
        DataVector res = null;
        try {
            res = getDataVectorClass(dataClass).getConstructor(Integer.class).newInstance(dimension);
        }catch (Exception e){
            //Error using reflections
            e.printStackTrace();
        }
        return res;
    }
    public DataVector createEmptyDataVectorFromType(int dimension,DataVectorType type){
        DataVector res = null;
        try {
            res = getDataVectorClass(type).getConstructor(Integer.class).newInstance(dimension);
        }catch (Exception e){
            //Error using reflections
            e.printStackTrace();
        }
        return res;
    }
    public Class<? extends DataVector> getDataVectorClass(Class<?> dataClass){
        for(int i = 0;i < TypeList.size();i++){
            if(TypeList.get(i).isAssignableFrom(dataClass))return ClassList.get(i);
        }
        return null;
    }
    public Class<? extends DataVector> getDataVectorClass(DataVectorType type){
        for(int i = 0;i < TypeList.size();i++){
            if(TypeList.get(i).getClass().isInstance(type))return  ClassList.get(i);
        }
        return null;
    }

    public void setDataVectorType(DataVectorType type,Class<? extends DataVector> dataVectorClass){
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

