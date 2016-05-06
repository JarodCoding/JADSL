package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
public abstract class DataVectorType{

    public boolean isAssignableFrom(Class<?>... cs){
        if(cs.length == 0||(cs.length != 1 && cs.length != getDimension()))return false;
        if(cs.length == 1){
            if(getDimension() == -1)return isAssignableFrom(cs[0],0);
            for(int i = 0;i < getDimension();i++){
                if(!isAssignableFrom(cs[0],i))return false;
            }
        }else {
            for (int i = 0; i < getDimension(); i++) {
                if (!isAssignableFrom(cs[i], i)) return false;
            }
        }
        return true;
    }
    public abstract boolean isAssignableFrom(Class<?> c,int index);
    public boolean isAssignableFrom(DataVectorType type){
        return type.isAssignableTo(this);
    }
        protected abstract boolean isAssignableTo(DataVectorType type);

    public boolean isArithmeticallyCompatible(Class<?>... cs){
        if(cs.length == 0||(cs.length != 1 && cs.length != getDimension()))return false;
        if(cs.length == 1){
            if(getDimension() == -1)return isArithmeticallyCompatible(cs[0],0);
            for(int i = 0;i < getDimension();i++){
                if(!isArithmeticallyCompatible(cs[0],i))return false;
            }
        }else {
            for (int i = 0; i < getDimension(); i++) {
                if (!isArithmeticallyCompatible(cs[i], i)) return false;
            }
        }
        return true;
    }
    public abstract boolean isArithmeticallyCompatible(Class<?> c,int index);
    public boolean isArithmeticallyCompatible(DataVectorType type){
        return type.isArithmeticallyCompatibleTo(this);
    }
        protected abstract boolean isArithmeticallyCompatibleTo(DataVectorType type);

    public abstract int getDimension();
    public abstract String toString();

    public static class SimpleDataVectorType extends DataVectorType{

        protected Class<?> VectorTypeClass;
        public SimpleDataVectorType(Class<?> VectorTypeClass){
            this.VectorTypeClass = VectorTypeClass;
        }

        @Override
        public boolean isAssignableFrom(Class<?> c, int index){
            return VectorTypeClass.isAssignableFrom(c);
        }
        @Override
        protected boolean isAssignableTo(DataVectorType type) {
            return type.isAssignableFrom(VectorTypeClass);
        }

        @Override
        public boolean isArithmeticallyCompatible(Class<?> c, int index){
            return VectorTypeClass.isAssignableFrom(c);
        }
        @Override
        protected boolean isArithmeticallyCompatibleTo(DataVectorType type) {return type.isArithmeticallyCompatible(VectorTypeClass);}

        @Override
        public int getDimension() {
            return -1;
        }
        @Override
        public String toString(){
            return VectorTypeClass.getName();
        }
    }
}
