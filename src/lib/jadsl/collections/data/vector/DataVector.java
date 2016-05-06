package lib.jadsl.collections.data.vector;

import java.util.Collection;
import java.util.List;
import java.util.Iterator;
/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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




    //Utility Methods

    // Mean/Middle DataVector of a Collection of DataVectors

        public static DataVector calculateMeanDataVector(DataVector... xn){
            DataVector res = xn[0];
            for(int i = 1;i < xn.length;i++){
                res.add(xn[i]);
            }

            return res.divideElements(xn.length);
        }

        public static DataVector calculateMeanDataVector(Collection<DataVector> xn){
            Iterator<DataVector> i = xn.iterator();
            DataVector res = i.next();
            while(i.hasNext()){
                res.add(i.next());
            }
            return res.divideElements(xn.size());
        }

    // Determines the DataVector out of a collection which is closest to the given DataVector

    public static DataVector determineClosestDataVector(DataVector x,Collection<DataVector> xn){
        Iterator<DataVector> i = xn.iterator();
        DataVector res = i.next();
        double smallestDistance = x.distance(res);
        DataVector tmp;
        double distance;
        while(i.hasNext()){
            if((distance = x.distance(tmp = i.next()))>smallestDistance){
                res = tmp;
                smallestDistance = distance;
            }
        }
        return res;
    }
    public static DataVector determineClosestDataVector(DataVector x,DataVector... xn){
        int closestDataVectorIndex = 0;
        double smallestDistance = x.distance(xn[0]);
        double distance;
        for(int i = 1;i < xn.length;i++){
            if((distance = x.distance(xn[i]))>smallestDistance){
                closestDataVectorIndex = i;
                smallestDistance = distance;
            }
        }
        return xn[closestDataVectorIndex];
    }
    public DataVector determineClosestDataVector(Collection<DataVector> xn){
        Iterator<DataVector> i = xn.iterator();
        DataVector res = i.next();
        double smallestDistance = distance(res);
        DataVector tmp;
        double distance;
        while(i.hasNext()){
            if((distance = distance(tmp = i.next()))>smallestDistance){
                res = tmp;
                smallestDistance = distance;
            }
        }
        return res;
    }
    public DataVector determineClosestDataVector(DataVector... xn){
        int closestDataVectorIndex = 0;
        double smallestDistance = distance(xn[0]);
        double distance;
        for(int i = 1;i < xn.length;i++){
            if((distance = distance(xn[i]))>smallestDistance){
                closestDataVectorIndex = i;
                smallestDistance = distance;
            }
        }
        return xn[closestDataVectorIndex];
    }
    // Determines the Index of the DataVector in a List which is closest to the given DataVector

    public static int determineClosestDataVectorIndex(DataVector x,DataVector... xn){
        int closestDataVectorIndex = 0;
        double smallestDistance = x.distance(xn[0]);
        double distance;
        for(int i = 1;i < xn.length;i++){
            if((distance = x.distance(xn[i]))>smallestDistance){
                closestDataVectorIndex = i;
                smallestDistance = distance;
            }
        }
        return closestDataVectorIndex;
    }
    public static int determineClosestDataVectorIndex(DataVector x,List<DataVector> xn){
        int closestDataVectorIndex = 0;
        double smallestDistance = x.distance(xn.get(0));
        double distance;
        for(int i = 1;i < xn.size();i++){
            if((distance = x.distance(xn.get(i)))>smallestDistance){
                closestDataVectorIndex = i;
                smallestDistance = distance;
            }
        }
        return closestDataVectorIndex;
    }
    public int determineClosestDataVectorIndex(DataVector... xn){
        int closestDataVectorIndex = 0;
        double smallestDistance = distance(xn[0]);
        double distance;
        for(int i = 1;i < xn.length;i++){
            if((distance = distance(xn[i]))>smallestDistance){
                closestDataVectorIndex = i;
                smallestDistance = distance;
            }
        }
        return closestDataVectorIndex;
    }
    public int determineClosestDataVectorIndex(List<DataVector> xn){
        int closestDataVectorIndex = 0;
        double smallestDistance = distance(xn.get(0));
        double distance;
        for(int i = 1;i < xn.size();i++){
            if((distance = distance(xn.get(i)))>smallestDistance){
                closestDataVectorIndex = i;
                smallestDistance = distance;
            }
        }
        return closestDataVectorIndex;
    }
}
