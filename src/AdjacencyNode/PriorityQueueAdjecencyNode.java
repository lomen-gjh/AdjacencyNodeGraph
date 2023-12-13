package AdjacencyNode;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class PriorityQueueAdjecencyNode {
    Node[] data;
    int size;
    PriorityQueueAdjecencyNode(){
        data=new Node[10];
        size=0;
    }
    public void ensureCapacity(){
        if (size==data.length){
            data= Arrays.copyOf(data,data.length*2);
        }
    }
    public int getParent(int index){
        if (index!=0){
            return (index-1)/2;
        }
        return -1; //no parent
    }
    public int leftChild(int index){
        int novy=2*index+1;
        if (novy>=size)
            return -1;
        return novy;
    }
    public int rightChild(int index){
        int novy=2*index+2;
        if (novy>=size)
            return -1;
        return novy;
    }

    public void insert(Node node){
        data[size]=node;
        data[size].pqindex=size;  //Initial pqindex
        size++;
        ensureCapacity();
        heapifyUp(size-1);
    }

    public void heapifyUp(int index){
        while(index>0){
            if (data[index].price<data[getParent(index)].price){
                swap(index,getParent(index));
                index=getParent(index);
            }
            else{
                break;
            }
        }
    }

    void swap(int i1, int i2){
        Node oblacik=data[i1];
        data[i1]=data[i2];
        data[i2]=oblacik;
        //PQ index must be also updated
        data[i1].pqindex+=data[i2].pqindex;
        data[i2].pqindex=data[i1].pqindex-data[i2].pqindex;
        data[i1].pqindex-=data[i2].pqindex;

    }
    public Node poll(){
        Node deleted=data[0];
        data[0]=data[size-1];
        data[0].pqindex=0;
        data[size-1]=null;
        size--;
        heapifyDown();
        return deleted;
    }

    void heapifyDown(){
        int index=0;
        while(index<size){
            if (leftChild(index)!=-1)  // finction leftCHild returns -1 if it does not exist
            {
                int lesser=leftChild(index);
                if (rightChild(index)!=-1 && data[rightChild(index)].price!=data[lesser].price){
                    lesser=rightChild(index);  //lesser is index of a lesser node between left and right child
                }
                if (data[index].price>data[lesser].price){
                    swap(index,lesser);
                    index=lesser;
                }
                else break;
            }
            else break;
        }
    }

    void print(){
        for (int i=0; i<size;i++){
            System.out.println("ID:"+data[i].id+" PQ index: "+data[i].pqindex+" Price: "+data[i].price);
        }
    }

    public static void main(String[] args) {
        //Testing
        Vector<Node> nodes=new Vector<>();
        nodes.add(new Node(3,4,null));
        nodes.add(new Node(3,3,null));
        nodes.add(new Node(5,5,null));
        nodes.add(new Node(5,5,null));
        nodes.add(new Node(5,5,null));
        PriorityQueueAdjecencyNode pq=new PriorityQueueAdjecencyNode();
        pq.insert(nodes.elementAt(0));
        pq.insert(nodes.elementAt(1));
        pq.insert(nodes.elementAt(2));
        pq.insert(nodes.elementAt(3));
        pq.insert(nodes.elementAt(4));
        pq.print();
        nodes.elementAt(4).price=5;
        pq.heapifyUp(nodes.elementAt(4).pqindex);
        System.out.println("---------");
        pq.print();
        nodes.elementAt(1).price=6;
        pq.heapifyUp(nodes.elementAt(1).pqindex);
        System.out.println("-------");
        pq.print();
    }

}
