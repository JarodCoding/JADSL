package lib.jadsl.collections.list;

import lib.jadsl.collections.data.vector.DataVector;
import java.util.*;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class DataVectorArrayList<T> extends ArrayList<DataVector<T>> {
    private final int dimension;
    public DataVectorArrayList(int dimension){
        super();
        this.dimension  = dimension ;
    }
    public DataVectorArrayList(int dimension,int size){
        super(size);
        this.dimension  = dimension ;
    }
    public DataVectorArrayList(int dimension, List<DataVector<T>> data){
        super(data);
        for(DataVector e:this){
            if(e.getDimension() != dimension)throw new IllegalArgumentException("Dimension needs to be constant! "+e+" has dimension "+e.getDimension()+" dimension of this Array is "+dimension);
        }
        this.dimension  = dimension ;
    }
    public DataVectorArrayList(int dimension, DataVector<T>[] data){
        this(dimension,Arrays.asList(data));
    }


    @Override
    public boolean add(DataVector<T> ts) {
        if(ts.getDimension()!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        return super.add(ts);
    }
    @Override
    public boolean addAll(Collection<? extends DataVector<T>> c) {
        for(DataVector<T> ts:c){
            if(ts.getDimension()!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        }
        return super.addAll(c);
    }
    @Override
    public void add(int index, DataVector<T> ts) {
        if(ts.getDimension() !=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        super.add(index, ts);
    }
    @Override
    public boolean addAll(int index, Collection<? extends DataVector<T>> c) {
        for(DataVector<T> ts:c){
            if(ts.getDimension()!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        }
        return super.addAll(index, c);
    }

    @Override
    public DataVector<T> set(int index, DataVector<T> ts) {
        if(ts.getDimension() !=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        return super.set(index, ts);
    }
    public int getDimension(){
        return dimension;
    }
}

