package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class NumberVector extends DataVector<Number>{
    public static final DataVectorType DefaultType = new DataVectorType.SimpleDataVectorType(Number.class);
    public NumberVector(Integer dimension,DataVectorType type,Number[] data){
        super(dimension,type!=null? type:DefaultType,data);
        for(int i = 0; i < dimension;i++){
            if(this.get(i) == null )this.set(i,0);
        }
    }
    public NumberVector(Integer dimension,Number[] data){
        super(dimension,DefaultType,data);
        for(int i = 0; i < dimension;i++){
            if(this.get(i) == null )this.set(i,0);
        }
    }

    public <T> double distance(DataVector<T> x){
        checkDistanceCompatible(x.get(0).getClass());
        if(x.getDimension()!=getDimension())throw new IllegalArgumentException("Euclidean distance can only be calculated for vectors with equal dimensions!\n Dimension of "+ this+" is "+getDimension()+"\n Dimension of "+ x+" is "+x.getDimension());
        double l = 0;
        for(int i = 0;i < getDimension();i++){
            l += Math.pow(((Number)x.get(i)).doubleValue()-get(i).doubleValue(),2);
        }
        return Math.sqrt(l);
    }
    public double length(){
        double l = 0;
        for(int i = 0;i < getDimension();i++){
            l += Math.pow(get(i).doubleValue(),2);
        }
        return Math.sqrt(l);
    }
    public <T> DataVector<Number> add(T x){
        checkArithmeticCompatible(x.getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()+((Number)x).doubleValue())));
        }
        return res;
    }

        public <T> DataVector<Number> add(DataVector<T> x){
            checkArithmeticCompatible(x.get(0).getClass());
            DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()+((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> add(DataVector<T>... x) {
        checkArithmeticCompatible(x[0].getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for (int i = 0; i < getDimension(); i++) {
            for (int j = 0; j < x.length; j++) {
                res.set(i, ((Double) (res.get(i).doubleValue() + ((Number)x[j].get(i)).doubleValue())));
            }
        }
        return res;
    }
    public <T> DataVector<Number> subtract(T x) {
        checkArithmeticCompatible(x.getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for (int i = 0; i < getDimension(); i++) {
            res.set(i, ((Double) (get(i).doubleValue() - ((Number)x).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> subtract(DataVector<T> x){
        checkArithmeticCompatible(x.get(0).getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()-((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> subtract(DataVector<T>... x){
        checkArithmeticCompatible(x[0].get(0).getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            for(int j = 0;j < x.length;j++){
                res.set(i,((Double)(res.get(i).doubleValue()-((Number)x[j].get(i)).doubleValue())));
            }        }
        return res;
    }

    public <T> DataVector<Number> divideElements(T x){
        checkArithmeticCompatible(x.getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()/((Number)x).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> divideElements(DataVector<T> x){
        checkArithmeticCompatible(x.getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()/((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> divideElements(DataVector<T>... x){
        checkArithmeticCompatible(x[0].get(0).getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            for(int j = 0;j < x.length;j++){
                res.set(i,((Double)(res.get(i).doubleValue()/((Number)x[j].get(i)).doubleValue())));
            }        }
        return res;
    }

    public <T> DataVector<Number> multiplyElements(T x){
        checkArithmeticCompatible(x.getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()*((Number)x).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> multiplyElements(DataVector<T> x){
        checkArithmeticCompatible(x.get(0).getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((Double)(get(i).doubleValue()*((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public <T> DataVector<Number> multiplyElements(DataVector<T>... x){
        checkArithmeticCompatible(x[0].get(0).getClass());
        DataVector<Number> res = (DataVector<Number>) this.clone();
        for(int i = 0;i < getDimension();i++){
            for(int j = 0;j < x.length;j++){
                res.set(i,((Double)(res.get(i).doubleValue()*((Number)x[j].get(i)).doubleValue())));
            }        }
        return res;
    }
}
