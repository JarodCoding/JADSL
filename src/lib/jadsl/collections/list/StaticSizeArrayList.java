package lib.jadsl.collections.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Created by Pascal Jarod Kuthe on 28/04/2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

/* Allows an ArrayList to have a static size for a temporary amount of time*/
public class StaticSizeArrayList<T> extends ArrayList<T> {
    private int maxSize;
    public StaticSizeArrayList(int size){
        super(size);
    }
    public StaticSizeArrayList(){
        super();
    }
    public StaticSizeArrayList(List<T> data){
        super(data);
    }
    public int getMaxSize(){
        return  maxSize == -1?this.size():maxSize;
    }
    public boolean hasStaticSize(){
        return maxSize > 0;
    }
    public boolean hasStrictlyStaticSize(){
        return maxSize==-1;
    }
    public void setMaxSize(int maxSize){
        if(maxSize<1)throw new IllegalArgumentException("Max size needs to be greater than 0");
        this.maxSize = maxSize;
    }
    public void removeSizeControl(){
        this.maxSize = 0;
    }
    public void makeBoundriesStrictlyStatic(){
        maxSize = -1;
    }
    public void makeContentStrictlyStatic(){
        maxSize = -2;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if(maxSize==-1)throw new IllegalAccessError("This list is strictly Static!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        return super.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        if(maxSize==-1)throw new IllegalAccessError("This list is strictly Static!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        return super.removeIf(filter);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean remove(Object o) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        return super.remove(o);
    }

    @Override
    public T remove(int index) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        return super.remove(index);
    }

    @Override
    public boolean add(T t) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        if(maxSize > 0 && maxSize < this.size()+1)throw new IllegalAccessError("Adding "+t+" would exceed this lists maximum size of "+getMaxSize());
        return super.add(t);
    }

    @Override
    public void add(int index, T element) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        if(maxSize > 0 && maxSize < this.size()+1)throw new IllegalAccessError("Adding "+element+" would exceed this lists maximum size of "+getMaxSize());
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        if(this.size() > 0 && maxSize < this.size()+c.size())throw new IllegalAccessError("Adding "+c+" would exceed this lists maximum size of "+getMaxSize()+" by"+(this.size()+c.size()-maxSize));
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if(maxSize==-1)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        if(maxSize > 0 && maxSize < this.size()+c.size())throw new IllegalAccessError("Adding "+c+" would exceed this lists maximum size of "+getMaxSize()+" by"+(this.size()+c.size()-maxSize));
        return super.addAll(index, c);
    }

    @Override
    public T set(int index, T element) {
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        return super.set(index, element);
    }

    @Override
    public void clear() {
        if(maxSize != 0)throw new IllegalAccessError("This list has a static size!");
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        super.clear();
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        if(maxSize==-2)throw new IllegalAccessError("This list has a static content and shall not be changed!");
        super.replaceAll(operator);
    }
}
