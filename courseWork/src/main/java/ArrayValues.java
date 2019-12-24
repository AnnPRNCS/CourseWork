import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayValues {
    private List<Value> array;
    private int size;
    public ArrayValues(int size){
        array = new LinkedList<Value>();
    }
    public ArrayValues(ArrayValues arrayValues){
        array = new LinkedList<Value>();
        this.size = arrayValues.getSize();
        for(int i=0;i<this.size;i++){
            this.array.add(new Value(arrayValues.getValue(i)));
        }
    }

    public int getSize() {
        return array.size();
    }
    public Value getValue(int i){
        return array.get(i);
    }
    public void setValue(Value value){
        array.add(value);
    }
    public int getSumValues(){
        int sum=0;
        for(Value item:array){
            sum=sum+item.getValue();
        }
        return sum;
    }
    public void removeElement(int value){
        array.remove(value);
    }
    public void clear(){
        array.clear();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Value item: array){
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
