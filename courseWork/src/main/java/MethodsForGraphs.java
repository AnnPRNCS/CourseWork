import java.util.PriorityQueue;
import java.util.Queue;

public class MethodsForGraphs {
    private static int k=0;
    private static final int INT_MAX = 2147483647;
    public static boolean bfs(int [][]rGraph, int s, int t, int parent[], int size) {
        boolean[]visited = new boolean[size];
       for(int i=0;i<size;i++){
           visited[i]=false;
       }
        Queue<Integer> q = new PriorityQueue<Integer>();
        q.offer(s);
        visited[s] = true;
        parent[s] = -1;
        while(!q.isEmpty()) {
            int l = q.remove();

            for (int k = 0; k < size; k++) {
                if (visited[k] == false && rGraph[l][k] > 0) {
                    q.offer(k);
                    parent[k] = l;
                    visited[k] = true;
                }
            }
        }
        return (visited[t] == true);
    }
    public static int fordFulkerson(Graph graph, int s, int t) {
        int u, v;
        int size = graph.getSize();
        int[][] gr = graph.getGraph();
        int [][]rGraph =new int [size][size];
        for (u = 0; u < size; u++) {
            for (v = 0; v < size; v++) {
                rGraph[u][v] = gr[u][v];
            }
        }
        int []parent = new int[size];

        int max_flow = 0;
        while (bfs(rGraph,  s, t, parent,size)) {
            int path_flow = INT_MAX;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            max_flow += path_flow;
        }
        return max_flow;
    }
    public static int localEdgeConnectivity(Graph graph,int s, int t) {
        return fordFulkerson(graph,s, t);
    }
    public static int globalEdgeConnectivity(Graph graphs) {
        int size = graphs.getSize();
        int [][] graph = graphs.getGraph();
        if (!bfs(graph, 0, size - 1, new int[size],size)) {
           // System.out.println("Граф несвязен" );
            return 0;
        }
        int edgeConnectivity=INT_MAX;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                edgeConnectivity = Math.min(edgeConnectivity,localEdgeConnectivity(graphs,i, j));
            }
        }
        return edgeConnectivity;
    }
    public static Graph separationVertex(Graph graph) {
        int u, v;
        int size = graph.getSize();
        int[][] gr = graph.getGraph();
        int [][]rGraph = new int [size][size];
        for (u = 0; u < size; u++) {
            for (v = 0; v < size; v++) {
                rGraph[u][v] = gr[u][v];
            }
        }
        if (!bfs(rGraph, 0, size - 1, new int[size],size)) {
            System.out.println("Граф несвязен");
            return null;
        }
        int [][]arr = new int [size*2][size * 2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
                if (i == j) {
                    arr[i][j + size] = 1;
                } else {
                    arr[i][j + size] = 0;
                }
                arr[i + size][j] = gr[i][j];
                arr[i + size][j + size] = 0;
            }

        }
        return new Graph(arr, size * 2);
    }
    public static int localVertexConnectivity(Graph graph,int s, int t) {
        int u, v;
        int size = graph.getSize();
        int[][] gr = graph.getGraph();
        int [][]rGraph = new int [size][size];
        for (u = 0; u < size; u++) {
            for (v = 0; v < size; v++) {
                rGraph[u][v] = gr[u][v];
            }
        }
        Graph resultGraph = MethodsForGraphs.separationVertex(new Graph(rGraph,size));
        return MethodsForGraphs.fordFulkerson(resultGraph,s, t);
    }
    public static int globalVertexConnectivity(Graph graph) {
        int edgeConnectivity=INT_MAX;
        int size = graph.getSize();
        Graph resultGraph = MethodsForGraphs.separationVertex(graph);
        for (int i = size; i < resultGraph.getSize(); i++) {
            for (int j = 0; j < size; j++) {
                edgeConnectivity = Math.min(edgeConnectivity,localVertexConnectivity(graph,i, j));
            }
        }
        return edgeConnectivity;
    }
    public static Graph adjacencyMatrix(Graph graph) {
        int size = graph.getSize();
        int [][] rGraph = graph.getGraph();
        int [][]gr = new int [size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (rGraph[i][j] == 1) {
                    gr[i][j] = 1;
                    gr[j][i] = 1;
                } else {
                    gr[i][j] = 0;
                    gr[j][i] = 0;
                }
            }
        }
        return new Graph(gr, size);
    }
    public static void sortWeightFunction(ArrayValues arrayValues) {

        for (int i = 1; i <arrayValues.getSize(); i++) {
            for (int r = 0; r < arrayValues.getSize() - i; r++) {
                if (arrayValues.getValue(r).getValue() <arrayValues.getValue(r+1).getValue()) {
                    int weight = arrayValues.getValue(r).getValue();
                    int lastVertex = arrayValues.getValue(r).getLastVertex();
                    int firstVertex = arrayValues.getValue(r).getFirstVertex();
                    arrayValues.getValue(r).setFirstVertex(arrayValues.getValue(r+1).getFirstVertex());
                    arrayValues.getValue(r).setLastVertex(arrayValues.getValue(r+1).getLastVertex());
                    arrayValues.getValue(r).setValue(arrayValues.getValue(r+1).getValue());
                    arrayValues.getValue(r+1).setFirstVertex(firstVertex);
                    arrayValues.getValue(r+1).setLastVertex(lastVertex);
                    arrayValues.getValue(r+1).setValue(weight);
                }
            }
        }
    }
    //для к=2 связность
    public static void greadyReverse(Graph graph, ArrayValues arrayValues, int connectivity) {
        int row;
        int column;
        int graphSize = graph.getSize();
        sortWeightFunction(arrayValues);
        for (int i = 0; i < graphSize; i++) {
            row = arrayValues.getValue(i).getFirstVertex();
            column = arrayValues.getValue(i).getLastVertex();
            graph.addElem(row, column, 0);
            graph.addElem(column, row, 0);
            if (MethodsForGraphs.localEdgeConnectivity(graph,row, column) < connectivity) {
                graph.addElem(row, column, 1);
                graph.addElem(column, row, 1);
            }

        }
    }
    private static boolean isMore(Value weightFunctionOne, Value weightFunctionTwo, Graph graph, int sheme) {
        int firstVertexOne = weightFunctionOne.getFirstVertex();
        int lastVertexOne = weightFunctionOne.getLastVertex();
        int firstVertexTwo = weightFunctionTwo.getFirstVertex();
        int lastVertexTwo = weightFunctionTwo.getLastVertex();
        int sumPowerVertexOne;
        int sumPowerVertexTwo;
        int maxPowerVertexOne;
        int maxPowerVertexTwo;
        int minPowerVertexOne;
        int minPowerVertexTwo;
        if (weightFunctionOne.getValue() < weightFunctionTwo.getValue()) {
            return true;
        }
        switch (sheme) {
            case 1:
                sumPowerVertexOne = graph.getPowerVertex(firstVertexOne) + graph.getPowerVertex(lastVertexOne);
                sumPowerVertexTwo = graph.getPowerVertex(firstVertexTwo) + graph.getPowerVertex(lastVertexTwo);
                if (weightFunctionOne.getValue() == weightFunctionTwo.getValue()) {
                    if (sumPowerVertexOne < sumPowerVertexTwo) {
                        return true;
                    }
                }
            case 2:
                sumPowerVertexOne = graph.getPowerVertex(firstVertexOne) + graph.getPowerVertex(lastVertexOne);
                sumPowerVertexTwo = graph.getPowerVertex(firstVertexTwo) + graph.getPowerVertex(lastVertexTwo);
                if (weightFunctionOne.getValue() == weightFunctionTwo.getValue()) {
                    if (sumPowerVertexOne > sumPowerVertexTwo) {
                        return true;
                    }
                }
            case 3:
                maxPowerVertexOne=Math.max(graph.getPowerVertex(firstVertexOne),graph.getPowerVertex(lastVertexOne));
                maxPowerVertexTwo=Math.max(graph.getPowerVertex(firstVertexTwo),graph.getPowerVertex(lastVertexTwo));
                if (weightFunctionOne.getValue() == weightFunctionTwo.getValue()) {
                    if (maxPowerVertexOne < maxPowerVertexTwo) {
                        return true;
                    }
                }
            case 4:
                maxPowerVertexOne=Math.max(graph.getPowerVertex(firstVertexOne),graph.getPowerVertex(lastVertexOne));
                maxPowerVertexTwo=Math.max(graph.getPowerVertex(firstVertexTwo),graph.getPowerVertex(lastVertexTwo));
                if (weightFunctionOne.getValue() == weightFunctionTwo.getValue()) {
                    if (maxPowerVertexOne > maxPowerVertexTwo) {
                        return true;
                    }
                }
            case 5:
                minPowerVertexOne=Math.min(graph.getPowerVertex(firstVertexOne),graph.getPowerVertex(lastVertexOne));
                minPowerVertexTwo=Math.min(graph.getPowerVertex(firstVertexTwo),graph.getPowerVertex(lastVertexTwo));
                if (weightFunctionOne.getValue() == weightFunctionTwo.getValue()) {
                    if (minPowerVertexOne < minPowerVertexTwo) {
                        return true;
                    }
                }
            case 6:
                minPowerVertexOne=Math.min(graph.getPowerVertex(firstVertexOne),graph.getPowerVertex(lastVertexOne));
                minPowerVertexTwo=Math.min(graph.getPowerVertex(firstVertexTwo),graph.getPowerVertex(lastVertexTwo));
                if (weightFunctionOne.getValue() == weightFunctionTwo.getValue()) {
                    if (minPowerVertexOne > minPowerVertexTwo) {
                        return true;
                    }
                }

        }
        return false;
    }
   private static void sortWeightFunctionWithVertex(ArrayValues arrayValues, Graph graph, int sheme) {

        for (int i = 1; i < arrayValues.getSize(); i++) {
            for (int r = 0; r < arrayValues.getSize() - i; r++) {
                if (isMore(arrayValues.getValue(r), arrayValues.getValue(r + 1), graph,sheme)) {
                    int weight = arrayValues.getValue(r).getValue();
                    int lastVertex = arrayValues.getValue(r).getLastVertex();
                    int firstVertex = arrayValues.getValue(r).getFirstVertex();
                    arrayValues.getValue(r).setFirstVertex(arrayValues.getValue(r+1).getFirstVertex());
                    arrayValues.getValue(r).setLastVertex(arrayValues.getValue(r+1).getLastVertex());
                    arrayValues.getValue(r).setValue(arrayValues.getValue(r+1).getValue());
                    arrayValues.getValue(r+1).setFirstVertex(firstVertex);
                    arrayValues.getValue(r+1).setLastVertex(lastVertex);
                    arrayValues.getValue(r+1).setValue(weight);
                }
            }
        }
    }
    public static void greadyReverseWithVertex(Graph graph, ArrayValues arrayValues, int connectivity, int sheme) {
        k++;
        Graph forBFS = new Graph(graph);
        if(globalVertexConnectivity(graph)>connectivity) {
            int row;
            int column;
            int graphSize = graph.getSize();
            sortWeightFunctionWithVertex(arrayValues, graph, sheme);
            for (int i = 0; i < arrayValues.getSize(); i++) {
                row = arrayValues.getValue(i).getFirstVertex();
                column = arrayValues.getValue(i).getLastVertex();
                graph.addElem(row, column, 0);
                graph.addElem(column, row, 0);
               // if (MethodsForGraphs.localEdgeConnectivity(graph, row, column) < connectivity) {
                if (MethodsForGraphs.localVertexConnectivity(graph, row, column) < connectivity) {
                    graph.addElem(row, column, 1);
                    graph.addElem(column, row, 1);
                }else{
                    arrayValues.removeElement(i);
                }

            }
            System.out.println(k+"+");

        }else{System.out.println(k+"-");
            arrayValues.clear();
        }

    }
}
