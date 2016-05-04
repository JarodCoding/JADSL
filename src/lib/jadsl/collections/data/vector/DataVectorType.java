package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthe on 28.04.2016.
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
