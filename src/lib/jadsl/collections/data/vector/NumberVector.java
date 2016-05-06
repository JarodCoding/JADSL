package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public class NumberVector extends DataVector{
    public static final DataVectorType DefaultType = new DataVectorType.SimpleDataVectorType(Number.class);
    public NumberVector(Integer dimension,DataVectorType type,Object... data){
        super(dimension,type!=null? type:DefaultType,data);
        for(int i = 0; i < dimension;i++){
            if(this.get(i) == null )this.set(i,0);
        }
    }
    public NumberVector(Integer dimension,Object... data){
        super(dimension,DefaultType,data);
        for(int i = 0; i < dimension;i++){
            if(this.get(i) == null )this.set(i,0);
        }
    }


    public double length(){
        double l = 0;
        for(int i = 0;i < getDimension();i++){
            l += Math.pow(get(i).doubleValue(),2);
        }
        return Math.sqrt(l);
    }
    public <T> DataVector add(T x){
        checkArithmeticCompatible(x.getClass());
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((get(i).doubleValue()+((Number)x).doubleValue())));
        }
        return res;
    }

        public DataVector add(DataVector x){
            checkArithmeticCompatible(x.getType());
            DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((get(i).doubleValue()+((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public DataVector add(DataVector... x) {
        for(DataVector vec:x){
            checkArithmeticCompatible(vec.getType());
        }
        DataVector res = (DataVector) this.clone();
        for (int i = 0; i < getDimension(); i++) {
            for (int j = 0; j < x.length; j++) {
                res.set(i, ( (((Number)res.get(i)).doubleValue() + ((Number)x[j].get(i)).doubleValue())));
            }
        }
        return res;
    }
    public <T> DataVector subtract(T x) {
        checkArithmeticCompatible(x.getClass());
        DataVector res = (DataVector) this.clone();
        for (int i = 0; i < getDimension(); i++) {
            res.set(i, ( (get(i).doubleValue() - ((Number)x).doubleValue())));
        }
        return res;
    }
    public DataVector subtract(DataVector x){
        checkArithmeticCompatible(x.getType());
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((get(i).doubleValue()-((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public  DataVector subtract(DataVector... x){
        for(DataVector xn:x){
            checkArithmeticCompatible(xn.getType());
        }
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            for(int j = 0;j < x.length;j++){
                res.set(i,((Number)res.get(i)).doubleValue()-((Number)x[j].get(i)).doubleValue());
            }        }
        return res;
    }

    public <T> DataVector divideElements(T x){
        checkArithmeticCompatible(x.getClass());
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((get(i).doubleValue()/((Number)x).doubleValue())));
        }
        return res;
    }
    public  DataVector divideElements(DataVector x){
        checkArithmeticCompatible(x.getType());
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((get(i).doubleValue()/((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public  DataVector divideElements(DataVector... x){
        for(DataVector xn:x){
            checkArithmeticCompatible(xn.getType());
        }        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            for(int j = 0;j < x.length;j++){
                res.set(i,((Number)res.get(i)).doubleValue()/((Number)x[j].get(i)).doubleValue());
            }        }
        return res;
    }

    public <T> DataVector multiplyElements(T x){
        checkArithmeticCompatible(x.getClass());
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,( (get(i).doubleValue()*((Number)x).doubleValue())));
        }
        return res;
    }
    public  DataVector multiplyElements(DataVector x){
        checkArithmeticCompatible(x.getType());
        DataVector res = (DataVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            res.set(i,((get(i).doubleValue()*((Number)x.get(i)).doubleValue())));
        }
        return res;
    }
    public  DataVector multiplyElements(DataVector... x){
        for(DataVector xn:x){
            checkArithmeticCompatible(xn.getType());
        }
        NumberVector res = (NumberVector) this.clone();
        for(int i = 0;i < getDimension();i++){
            for(int j = 0;j < x.length;j++){
                res.set(i,(res.get(i).doubleValue()*((Number)x[j].get(i)).doubleValue()));
            }        }
        return res;
    }
    public Number get(int i){
        return (Number)super.get(i);
    }
}
