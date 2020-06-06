import java.util.Iterator;

//Dynamic Array Implementation
public class Array<T> implements Iterable<T> {
    private T[] arr;
    private int len;
    private int capacity;

    public Array() {
        this(16);
    }

    public Array(Integer capacity){
        if(capacity<0)
            throw new IllegalArgumentException("Illegal Capacity:"+capacity);
        this.capacity=capacity;
        arr=(T[])new Object[capacity];
    }

    public int size(){
        return len;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public T get(int index){
        if(index<0||index>=len){
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            return arr[index];
        }
    }

    public void set(int index,T element){
        if(index<0||index>=len){
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
             arr[index]=element;
        }
    }

    public void clear(){
        for(int i=0;i<capacity;i++){
            arr[i]=null;
        }
        len=0;
    }

    public void add(T elem){
        if(len+1>=capacity){
            if(capacity==0){
                capacity=1;
            }else {
                capacity*=2;
            }
            T[] resizedArr=(T[])new Object[capacity];
            for(int i=0;i<len;i++){
                resizedArr[i]=arr[i];
            }
            arr=resizedArr;
        }
        arr[len++]=elem;
    }

    public T removeAt(int index){
        if(index<0||index>=len){
            throw new ArrayIndexOutOfBoundsException();
        }
        T data=arr[index];
        T[] resizedArr=(T[])new Object[capacity-1];
        for(int i=0,j=0;i<len;i++,j++){
            if(i==index){
                j--;
            }else {
                resizedArr[j]=arr[i];
            }
        }
        arr=resizedArr;
        capacity--;
        return data;
    }

    public boolean remove(T object){
        for(int i=0;i<len;i++){
            if(arr[i].equals(object)){
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T object){
        for(int i=0;i<len;i++){
            if(arr[i].equals(object)){
                removeAt(i);
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T object){
        return indexOf(object)!=-1;
    }

    @Override
    public String toString() {
        if(len==0){
            return "[]";
        }else {
            StringBuilder sb= new StringBuilder();
            sb.append("[");
            for(int i=0;i<len-1;i++){
                sb.append(arr[i]);
                sb.append(",");
            }
            return sb.append(arr[len-1]+"]").toString();
        }

}

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index=0;
            @Override
            public boolean hasNext() {
                return index<len;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }
}
