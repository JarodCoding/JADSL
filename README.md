# JADSL
JavaAdvancedDataStructureLibrary is a java library with advanced/specialised data structures that I created for personal use around my projects anyone who might have use for the library is welcome to use it thoug

## Classes
###Lists
#### lib.jadsl.collections.list.StaticSizeArrayList:
  An ArrayList with the option to make the maximum size, the size or the content static. This state can be changed at any time
#### lib.jadsl.collections.list.DataVectorArrayList:
  An ArrayList with multidimensional entrys (basically each entry is an array of the type T of size d) The dimension d is static across the list and can not be changed after initializing the list
#### lib.jadsl.collections.list.StaticSizeDataVectorArrayList:
  Combines the features of the StaticSize- and the MultiProperty- ArrayList
###Data
#### lib.jadsl.collections.data.vector.DataVector
A n dimensional Vector which represents any data (if implemented) as a Vector and allows all basic vector operations (distance, add subtract, divide all dimensions, multiply all dimensions) Compatabilility between Vectors of different data types is not ensured and depends on the individual implementations
##### lib.jadsl.collections.data.vector.DataVectorType
The runtime type of a vector it is used to detirmen which data can be stored and can maipulate a vector
##### lib.jadsl.collections.data.vector.NumberVector
A numerical Vector. It behaves like a mathmatical n dimensional array
###### lib.jadsl.collections.data.vector.DefaultNumberVectorType
Is the defaultType of the numberVector if none is given in th constructor. It is also automatically asssigned by any new factroy instance
##### lib.jadsl.collections.data.vector.DataVectorFactory
Assosiates implimented DataVector classes with types at runtime (there are the bevore mentioned libary default though. These may be overwritte) and allows the creation of the righy type of data vector for a certian class;

