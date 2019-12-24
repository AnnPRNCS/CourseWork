
public class RandomGraph {

   public static Graph fill(double density, int size,ArrayValues arrayValues) {
        int [][] graph = new int [size][size];
        double probabilityRandom;
        int value;

        for (int i = 0;i<size;i++){
            for (int j = 0; j<i ; j++){
                probabilityRandom = Math.random();
                value =(int)(10+Math.random()*91);
                if (probabilityRandom < density) {//если сгенерированная вероятность меньше нашей, то ребро есть - ставим 1
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                    //Value values = new Value(i, j, value);
                    Value values = new Value(i, j, 1);
                    if(arrayValues!=null){
                        arrayValues.setValue(values);
                    }
                }
            }
        }
        return new Graph(graph,size);
    }
}
