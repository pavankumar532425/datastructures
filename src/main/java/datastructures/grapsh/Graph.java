package datastructures.grapsh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
    /*
    * BFS of graph
    * */
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        int visted[]= new int[V];
        for(int i=0; i<visted.length; i++)
            visted[i]=-1;
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> res= new ArrayList<>();
        queue.add(0);
        visted[0]=1;
        while (!queue.isEmpty()) {
            int s=queue.size();
            for(int j=0; j<s;j++){
                for(int i=0; i<adj.get(queue.peek()).size();i++){
                    if(visted[adj.get(queue.peek()).get(i)]==-1){
                        queue.add(adj.get(queue.peek()).get(i));
                        visted[adj.get(queue.peek()).get(i)]=1;
                    }

                }
                // System.out.println(queue);
                res.add(queue.peek());

                queue.remove();
            }

        }

        return res;

    }
}
