package lib.jadsl.list;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class StaticSizeMultiPropertyArrayList<T> extends StaticSizeArrayList<T[]> {
    private final int dimension ;
    public StaticSizeMultiPropertyArrayList(int dimension){
        super();
        this.dimension  = dimension ;
    }
    public StaticSizeMultiPropertyArrayList(int dimension, List<T[]> data){
        super(data);
        for(T[] e:this){
            if(e.length != dimension)throw new IllegalArgumentException("Dimension needs to be constant! "+e+" has dimension "+e.length+" dimension of this Array is "+dimension);
        }
        this.dimension  = dimension ;
    }
    public StaticSizeMultiPropertyArrayList(int dimension, T[][] data){
        this(dimension,Arrays.asList(data));
    }
    public StaticSizeMultiPropertyArrayList(int dimension, T[] data, boolean perfektFit, int size ){
        super(size);
        if(dimension*size!=data.length&&perfektFit)throw new IllegalArgumentException("Dimension needs to be constant! Array is "+(data.length-dimension*size())+"offset from correct size for "+size+" "+dimension+" dimensional entrys! Array given: "+ data);
        T[] entry ;
        for(int i = 0;i < size; i++){
            entry =  ((Object)data.getClass() == (Object)Object[].class) ? (T[]) new Object[dimension] : (T[]) Array.newInstance(data.getClass().getComponentType(), dimension);
            for(int j = 0;j < dimension; j++){
                if(perfektFit||size*dimension+j<data.length)
                    entry[j] = data[size*dimension+j];
            }
            add(entry);
        }
        this.dimension  = dimension ;
    }
    public StaticSizeMultiPropertyArrayList(int dimension, T[] data, int size){
        this(dimension,data,true,size);

    }
    public StaticSizeMultiPropertyArrayList(int dimension, T[] data){
        this(dimension,data,true,data.length/dimension);

    }

    @Override
    public boolean add(T[] ts) {
        if(ts.length!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.length);
        return super.add(ts);
    }
    @Override
    public boolean addAll(Collection<? extends T[]> c) {
        for(T[] ts:c){
            if(ts.length!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.length);
        }
        return super.addAll(c);
    }
    @Override
    public void add(int index, T[] ts) {
        if(ts.length !=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.length);
        super.add(index, ts);
    }
    @Override
    public boolean addAll(int index, Collection<? extends T[]> c) {
        for(T[] ts:c){
            if(ts.length!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.length);
        }
        return super.addAll(index, c);
    }

    @Override
    public T[] set(int index, T[] ts) {
        if(ts.length !=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.length);
        return super.set(index, ts);
    }
    public int getDimension(){
        return dimension;
    }
}
