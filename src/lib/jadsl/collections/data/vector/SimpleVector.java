package lib.jadsl.collections.data.vector;

/**
 * Created by Pascal Jarod Kuthje on 13.05.2016.
 * Copyright (c) <2016> <Pascal Jarod Kuthe>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

import java.util.Arrays;


/**
 * Created by Pascal Jarod Kuthe on 13.05.2016.
 * Copyright (c) 2016 Pascal Jarod Kuthe - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
public class SimpleVector implements Cloneable{
    double[] data;
    public SimpleVector(int dimension, double... data){
        this.data = Arrays.copyOf(data,dimension);
    }



    public double length(){
        double l = 0;
        for(int i = 0;i < data.length;i++){
            l += Math.pow(data[i],2);
        }
        return Math.sqrt(l);
    }
        public double distance(SimpleVector x){
            double l = 0;
            for(int i = 0;i < data.length;i++){
                l += Math.pow(data[i]-x.data[i],2);
            }
            return Math.sqrt(l);
        }


    public SimpleVector add(double x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] += x;
        }
        return res;
    }
    public SimpleVector add(SimpleVector x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] += x.data[i];
        }
        return res;
    }
    public SimpleVector add(SimpleVector... x) {
        SimpleVector res = this.clone();
        for(int j = 0;j < x.length;j++)
            for(int i = 0;i < data.length;i++){
                res.data[i] += x[j].data[i];
            }
        return res;
    }

    public SimpleVector subtract(double x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] -= x;
        }
        return res;
    }
    public SimpleVector subtract(SimpleVector x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] -= x.data[i];
        }
        return res;
    }
    public SimpleVector subtract(SimpleVector... x) {
        SimpleVector res = this.clone();
        for(int j = 0;j < x.length;j++)
            for(int i = 0;i < data.length;i++){
                res.data[i] -= x[j].data[i];
            }
        return res;
    }

    public SimpleVector multiplyElements(double x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] *= x;
        }
        return res;
    }
    public SimpleVector multiplyElements(SimpleVector x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] *= x.data[i];
        }
        return res;
    }
    public SimpleVector multiplyElements(SimpleVector... x) {
        SimpleVector res = this.clone();
        for(int j = 0;j < x.length;j++)
            for(int i = 0;i < data.length;i++){
                res.data[i] *= x[j].data[i];
            }
        return res;
    }

    public SimpleVector divideElements(double x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] /= x;
        }
        return res;
    }
    public SimpleVector divideElements(SimpleVector x){
        SimpleVector res = this.clone();
        for(int i = 0;i < data.length;i++){
            res.data[i] /= x.data[i];
        }
        return res;
    }
    public SimpleVector divideElements(SimpleVector... x) {
        SimpleVector res = this.clone();
        for(int j = 0;j < x.length;j++)
            for(int i = 0;i < data.length;i++){
                res.data[i] /= x[j].data[i];
            }
        return res;
    }


    public double get(int i){
        return data[i];
    }
    public double set(int i){
        return data[i];
    }


    @Override
    public SimpleVector clone() {
        return new SimpleVector(data.length,data);
    }
}

