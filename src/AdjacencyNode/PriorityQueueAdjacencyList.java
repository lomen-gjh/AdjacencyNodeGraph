package AdjacencyNode;

import java.util.Arrays;

public class PriorityQueueAdjacencyList {
    Node[] nodes;
    int size;

    PriorityQueueAdjacencyList(){
        nodes=new Node[10];
        size=0;
    }

    void ensureCapacity(){
        if (size==nodes.length-1){
            nodes= Arrays.copyOf(nodes, nodes.length*2);
        }
    }

    void addNode(){

    }

    void poll(){

    }

    void heapifyUp(int target){

    }

    void heapifyDown(){}

}
