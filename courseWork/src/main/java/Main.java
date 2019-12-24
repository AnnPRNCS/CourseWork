public class Main {
    public static void main(String[] args) throws InterruptedException {
       /* int [][]graph = {{0, 1, 0, 0, 0},{1, 0, 0, 1, 0},{0, 1, 0, 0, 0},{0, 0, 1, 0, 1},{0, 0, 0, 1, 0}};
        Graph gr = new Graph(graph, 5);

        System.out.println( "edgeConnectivityTest1: " );
        System.out.println(MethodsForGraphs.bfs(graph, 0, 4, new int[5], 5));

        int [][]graph1 = {{0, 9, 6, 0, 0, 0},{0, 0, 0, 12, 8, 0},{0, 0, 0, 6, 2, 0},{0, 0, 0, 0, 0, 9},{0, 0, 0, 0, 0, 5},{0, 0, 0, 0, 0, 0}};
        Graph gr1 = new Graph(graph1, 6);

        System.out.println( "fordFulkersonTest1:");
        System.out.println(MethodsForGraphs.fordFulkerson(gr1,0, 5) );

        int [][]graph2 = {{0, 1, 1, 0, 0, 0},{1, 0, 0, 1, 1, 0},{1, 0, 0, 1, 1, 0},{0, 1, 1, 0, 0, 1},{0, 1, 1, 0, 0, 1},{0, 0, 0, 1, 1, 0}};

        Graph gr2 = new Graph(graph2, 6);

        System.out.println("edgeConnectivityTest2:" );
        System.out.println(MethodsForGraphs.globalEdgeConnectivity(gr2) );

        int [][]graph3 = {{0, 1, 1, 1, 0},{1, 0, 1, 0, 1},{1, 1, 0, 1, 1},{1, 0, 1, 0, 1},{0, 1, 1, 1, 0}};

        Graph gr3 = new Graph(graph3, 5);

        System.out.println( "vertexConnectivityTest1:" );
        System.out.println(MethodsForGraphs.globalVertexConnectivity(gr3));

        int [][]graph4 = {{0, 1, 1, 0, 0, 0},{1, 0, 0, 1, 1, 0},{1, 0, 0, 1, 1, 0},{0, 1, 1, 0, 0, 1},{0, 1, 1, 0, 0, 1},{0, 0, 0, 1, 1, 0}};

        Graph gr4 = new Graph(graph4, 6);

        System.out.println("edgeConnectivityTest2:" );
System.out.println(MethodsForGraphs.globalEdgeConnectivity(gr4));*/
     /*  ArrayValues arrayValues = new ArrayValues(100);
       Graph graph = RandomGraph.fill(0.,5, arrayValues);
        System.out.println(MethodsForGraphs.globalEdgeConnectivity(graph)+"connectivity");
       System.out.println(graph.toString());
       System.out.println(arrayValues.toString());
        MethodsForGraphs.greadyReverseWithVertex(graph,arrayValues,2,1);
        System.out.println(graph.toString());
        System.out.println(arrayValues.toString());*/
       int[] countWin = new int[6];
        for(int i=0;i<6;i++){
            countWin[i]=0;
        }
        for(int j=0;j<100;j++) {
            int count = 1;
            int sizeGraph = 10;
            int sizeArrayValues = 100;

            int sumValuesForSumMax = 0;
            int sumValuesForSumMix = 0;
            int sumValuesForMaxConnectivityMax = 0;
            int sumValuesForMinConnectivityMax = 0;
            int sumValuesForMaxConnectivityMin = 0;
            int sumValuesForMinConnectivityMin = 0;

            Graph[] arrayGraphForTesting = new Graph[count];
            ArrayValues[] arrayValuesForTesting = new ArrayValues[count];
            Graph[] arrayGraphsForSumConnectivityMax = new Graph[count];
            Graph[] arrayGraphsForSumConnectivityMin = new Graph[count];
            Graph[] arrayGraphsForMaxConnectivityMax = new Graph[count];
            Graph[] arrayGraphsForMinConnectivityMax = new Graph[count];
            Graph[] arrayGraphsForMaxConnectivityMin = new Graph[count];
            Graph[] arrayGraphsForMinConnectivityMin = new Graph[count];

            ArrayValues[] arrayValuesForSumConnectivityMax = new ArrayValues[count];
            ArrayValues[] arrayValuesForSumConnectivityMin = new ArrayValues[count];
            ArrayValues[] arrayValuesForMaxConnectivityMax = new ArrayValues[count];
            ArrayValues[] arrayValuesForMinConnectivityMax = new ArrayValues[count];
            ArrayValues[] arrayValuesForMaxConnectivityMin = new ArrayValues[count];
            ArrayValues[] arrayValuesForMinConnectivityMin = new ArrayValues[count];

            for (int i = 0; i < count; i++) {
                arrayValuesForTesting[i] = new ArrayValues(sizeArrayValues);
            }
            for (int i = 0; i < count; i++) {
                arrayGraphForTesting[i] = RandomGraph.fill(0.67, sizeGraph, arrayValuesForTesting[i]);
            }
            for (int i = 0; i < count; i++) {
                arrayGraphsForSumConnectivityMax[i] = new Graph(arrayGraphForTesting[i]);
                arrayGraphsForSumConnectivityMin[i] = new Graph(arrayGraphForTesting[i]);
                arrayGraphsForMaxConnectivityMax[i] = new Graph(arrayGraphForTesting[i]);
                arrayGraphsForMinConnectivityMax[i] = new Graph(arrayGraphForTesting[i]);
                arrayGraphsForMaxConnectivityMin[i] = new Graph(arrayGraphForTesting[i]);
                arrayGraphsForMinConnectivityMin[i] = new Graph(arrayGraphForTesting[i]);

                arrayValuesForSumConnectivityMax[i] = new ArrayValues(arrayValuesForTesting[i]);
                arrayValuesForSumConnectivityMin[i] = new ArrayValues(arrayValuesForTesting[i]);
                arrayValuesForMaxConnectivityMax[i] = new ArrayValues(arrayValuesForTesting[i]);
                arrayValuesForMinConnectivityMax[i] = new ArrayValues(arrayValuesForTesting[i]);
                arrayValuesForMaxConnectivityMin[i] = new ArrayValues(arrayValuesForTesting[i]);
                arrayValuesForMinConnectivityMin[i] = new ArrayValues(arrayValuesForTesting[i]);
            }
           for (int i = 0; i < count; i++) {
                MethodsForGraphs.greadyReverseWithVertex(arrayGraphsForSumConnectivityMax[i], arrayValuesForSumConnectivityMax[i], 2, 1);
                MethodsForGraphs.greadyReverseWithVertex(arrayGraphsForSumConnectivityMin[i], arrayValuesForSumConnectivityMin[i], 2, 2);
                MethodsForGraphs.greadyReverseWithVertex(arrayGraphsForMaxConnectivityMax[i], arrayValuesForMaxConnectivityMax[i], 2, 3);
                MethodsForGraphs.greadyReverseWithVertex(arrayGraphsForMinConnectivityMax[i], arrayValuesForMinConnectivityMax[i], 2, 4);
                MethodsForGraphs.greadyReverseWithVertex(arrayGraphsForMaxConnectivityMin[i], arrayValuesForMaxConnectivityMin[i], 2, 5);
                MethodsForGraphs.greadyReverseWithVertex(arrayGraphsForMinConnectivityMin[i], arrayValuesForMinConnectivityMin[i], 2, 6);
            }
            for (int i = 0; i < count; i++) {
                sumValuesForSumMax += arrayValuesForSumConnectivityMax[i].getSumValues();
                sumValuesForSumMix += arrayValuesForSumConnectivityMin[i].getSumValues();
                sumValuesForMaxConnectivityMax += arrayValuesForMaxConnectivityMax[i].getSumValues();
                sumValuesForMinConnectivityMax += arrayValuesForMinConnectivityMax[i].getSumValues();
                sumValuesForMaxConnectivityMin += arrayValuesForMaxConnectivityMin[i].getSumValues();
                sumValuesForMinConnectivityMin += arrayValuesForMinConnectivityMin[i].getSumValues();
            }
           /* for (int i = 0; i < count; i++) {
                arrayGraphForTesting[i] = RandomGraph.fill(0.67, sizeGraph, arrayValuesForTesting[i]);
                System.out.println(arrayGraphsForSumConnectivityMax[i].toString());
                System.out.println(arrayValuesForSumConnectivityMax[i].toString());
                System.out.println();
                System.out.println(arrayValuesForSumConnectivityMin[i].toString());
                System.out.println();
                System.out.println( arrayValuesForMaxConnectivityMax[i].toString());
                System.out.println();
                System.out.println(arrayValuesForMinConnectivityMax[i].toString());
                System.out.println();
                System.out.println(arrayValuesForMaxConnectivityMin[i].toString());
                System.out.println();
                System.out.println(arrayValuesForMinConnectivityMin[i].toString());
            }*/
            System.out.println("Сумма стоимостей для максимальной суммы степеней вершин: " + sumValuesForSumMax/count);
            System.out.println("Сумма стоимостей для минимальной суммы степеней вершин: " + sumValuesForSumMix/count);
            System.out.println("Сумма стоимостей для максимальной степени из максимальных степеней вершин ребра: " + sumValuesForMaxConnectivityMax/count);
            System.out.println("Сумма стоимостей для минимальной степени из максимальных степеней вершин ребра:  " + sumValuesForMinConnectivityMax/count);
            System.out.println("Сумма стоимостей для максимальной степени из минимальных степеней вершин ребра:  " + sumValuesForMaxConnectivityMin/count);
            System.out.println("Сумма стоимостей для минимальной степени из минимальных степеней вершин ребра:  " + sumValuesForMinConnectivityMin/count);

            int[] array = new int[6];
            array[0]=sumValuesForSumMax;
            array[1]=sumValuesForSumMix;
            array[2]=sumValuesForMaxConnectivityMax;
            array[3]=sumValuesForMinConnectivityMax;
            array[4]=sumValuesForMaxConnectivityMin;
            array[5]=sumValuesForMinConnectivityMin;
            int number=0;
            int min=2147483647;
            for(int i=0;i<6;i++){
                if(min>array[i]){
                    min=array[i];
                    number=i;
                }
            }
            countWin[number]++;

        }
        for(int i=0;i<6;i++){
            System.out.println("Количество побед для алгоритма "+i+" равняется "+countWin[i]);
        }
       /* ArrayValues arrayValues = new ArrayValues(25);
        Graph graph = RandomGraph.fill(1,5, arrayValues);
        Graph graph1 = new Graph(graph);
        System.out.println(graph.toString());
        System.out.println(graph1.toString());
        graph.addElem(3,3,6);
        System.out.println(graph.toString());
        System.out.println(graph1.toString());*/

    }
}
