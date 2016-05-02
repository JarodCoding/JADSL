package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthje on 28.04.2016.
 */
public interface DataVectorType{
    public boolean isTypeCompatible(Class<?> c);
    public boolean isTypeDistanceCompatible(Class<?> c);
    public boolean isTypeArithmeticallyCompatible(Class<?> c);
    public Class<?> covertToConstructorType(Class<?> c);
    public String toString();

    public static class SimpleDataVectorType implements DataVectorType{
        protected Class<?> VectorTypeClass;
        public SimpleDataVectorType(Class<?> VectorTypeClass){
            this.VectorTypeClass = VectorTypeClass;
        }
        public boolean isTypeCompatible(Class<?> c){
            return VectorTypeClass.isAssignableFrom(c);
        }
        public boolean isTypeDistanceCompatible(Class<?> c){
            return VectorTypeClass.isAssignableFrom(c);
        }
        public boolean isTypeArithmeticallyCompatible(Class<?> c){
            return VectorTypeClass.isAssignableFrom(c);
        }
        public Class<?> getVectorTypeClass(){
            return VectorTypeClass;
        }
        public String toString(){
            return VectorTypeClass.getName();
        }
        public Class<?> covertToConstructorType(Class<?> c){
            return VectorTypeClass.isAssignableFrom(c)?VectorTypeClass:c;
        }

    }
}
