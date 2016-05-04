package lib.jadsl.collections.list;

import lib.jadsl.collections.data.vector.DataVector;
import lib.jadsl.collections.data.vector.DataVectorType;

import java.util.*;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class DataVectorArrayList extends ArrayList<DataVector> {
    private final int dimension;
    private final DataVectorType type;
    public DataVectorArrayList(int dimension, DataVectorType type){
        super();
        this.dimension  = dimension ;
        this.type = type;
    }
    public DataVectorArrayList(int dimension,int size, DataVectorType type){
        super(size);
        this.dimension  = dimension ;
        this.type = type;
    }
    public DataVectorArrayList(int dimension, List<DataVector> data, DataVectorType type){
        super(data);
        for(DataVector e:this){
            if(e.getDimension() != dimension)throw new IllegalArgumentException("Dimension needs to be constant! "+e+" has dimension "+e.getDimension()+" dimension of this Array is "+dimension);
            if(type.getClass().isInstance(e.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+e+" is  not "+type);
        }
        this.dimension  = dimension ;
        this.type = type;
    }
    public DataVectorArrayList(DataVectorType type){
        super();
        this.dimension  = type.getDimension() ;
        this.type = type;
    }
    public DataVectorArrayList( DataVectorType type,int size){
        super(size);
        this.dimension  = type.getDimension() ;
        this.type = type;
    }
    public DataVectorArrayList(List<DataVector> data, DataVectorType type){
        super(data);
        for(DataVector e:this){
            if(e.getDimension() != type.getDimension())throw new IllegalArgumentException("Dimension needs to be constant! "+e+" has dimension "+e.getDimension()+" dimension of this Array is "+type.getDimension());
            if(type.getClass().isInstance(e.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+e+" is  not "+type);
        }
        this.dimension  = type.getDimension() ;
        this.type = type;
    }
    public DataVectorArrayList(int dimension, DataVector[] data, DataVectorType type){
        this(dimension,Arrays.asList(data),type);
    }
    public DataVectorArrayList( DataVector[] data, DataVectorType type){
        this(Arrays.asList(data),type);
    }


    @Override
    public boolean add(DataVector ts) {
        if(dimension!=ts.getDimension())throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        if(!type.isAssignableFrom(ts.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+ts+" is  not "+type);
        return super.add(ts);
    }
    @Override
    public boolean addAll(Collection<? extends DataVector> c) {
        for(DataVector ts:c){
            if(ts.getDimension()!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
            if(!type.isAssignableFrom(ts.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+ts+" is  not "+type);
        }
        return super.addAll(c);
    }
    @Override
    public void add(int index, DataVector ts) {
        if(ts.getDimension() !=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        if(!type.isAssignableFrom(ts.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+ts+" is  not "+type);
        super.add(index, ts);
    }
    @Override
    public boolean addAll(int index, Collection<? extends DataVector> c) {
        for(DataVector ts:c){
            if(ts.getDimension()!=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
            if(!type.isAssignableFrom(ts.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+ts+" is  not "+type);
        }
        return super.addAll(index, c);
    }

    @Override
    public DataVector set(int index, DataVector ts) {
        if(ts.getDimension() !=dimension)throw new IllegalArgumentException("The dimension of this list is "+dimension+" the added element"+ ts.toString() +"has a dimension of "+ ts.getDimension());
        if(!type.isAssignableFrom(ts.getType()))throw new IllegalArgumentException("Type needs to be constant! The type of: "+ts+" is  not "+type);
        return super.set(index, ts);
    }
    public int getDimension(){
        return dimension;
    }

    public DataVectorType getType(){
        return type;
    }
}

