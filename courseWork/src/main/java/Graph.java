public class Graph {
    private int[][] graph;
    private int size;

    public Graph(int size) {
        graph = new int[size][size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph[i][j] = 0;
            }
        }
    }

    public Graph(int[][] graph, int size){
        this.graph=graph;
        this.size=size;
    }

    public Graph(Graph graph){
        this.size = graph.size;
        this.graph = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.graph[i][j] = graph.getElement(i,j);
            }
        }
    }

    public void addElem(int row, int column, int elem) {
        if (row >= 0 && column >= 0 && elem >= 0) {
            graph[row][column] = elem;
        }
    }

    public int getSize() {
        return size;
    }

    public int[][] getGraph() {
        return graph;
    }
    public int getElement(int i, int j){
        return graph[i][j];
    }
    public int getPowerVertex(int i) {
        int powerVertex = 0;
        for (int k = 0; k < size; k++) {
            powerVertex += graph[i][k];
        }
        return powerVertex;
    }
@Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
}
}
