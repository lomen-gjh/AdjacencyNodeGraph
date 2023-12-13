package AdjacencyNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Graph {

    public Vector<Node> nodes;

    public Graph(){
        nodes=new Vector<>(0);
    }
    public void addNode(Node node){
        nodes.add(node);
    }
    public void removeNode(Node node){
        //First, remove all neighbors
        //To do this, iterate all neighbors and remove "node" from neighbor hashmap
        //NOTE: you cant delete from hashmap during itteration
        ArrayList<Node> neighborsToRemove=new ArrayList<>();
        for (Map.Entry<Integer, Edge> set: node.neighbors.entrySet()){
            Node neighbor= set.getValue().neighbor;
            neighborsToRemove.add(neighbor);
        }
        //Remove the node as neighbor from other nodes
        for (Node neighborToRemove:neighborsToRemove){
            neighborToRemove.removeNeighbor(node);
        }
        nodes.remove(node);
    }
    //DFS
    public void drawGraph(Graphics g){  //Nieco tu nejde
        g.clearRect(0,0,600,600);
        Vector<Integer> visited=new Vector<>();
        for (Node node: nodes){
            if (!visited.contains(node.id)){
                //node.neighbors.forEach(); // learn how to use built-in foreach
                dfs(node, visited, g);

            }
            node.drawNode(g);
        }
    }
    public void dfs(Node node, Vector<Integer> visited, Graphics g){
        visited.add(node.id);
        for (Map.Entry<Integer, Edge> set: node.neighbors.entrySet()){

                g.drawLine(node.x, node.y, set.getValue().neighbor.x,set.getValue().neighbor.y);
                String s=Integer.toString(set.getValue().price);
                g.setFont(new Font("Arial",Font.BOLD,14));
                g.drawString(s,(node.x+set.getValue().neighbor.getX())/2,(node.y+set.getValue().neighbor.getY())/2);
                g.setFont(new Font("Arial", Font.PLAIN,10));
                //Check if visited before dfs
                if (!visited.contains(set.getValue().neighbor.id))
                    dfs(set.getValue().neighbor, visited, g);

        }
        //node.drawNode(g);
    }

    PriorityQueueAdjecencyNode reset(){
        PriorityQueueAdjecencyNode pq=new PriorityQueueAdjecencyNode();
        for (Node node:nodes){
            node.price=10000;
            node.previous=null; //in case Dijkstra was already running previously
            pq.insert(node);
        }
        return pq;
    }

    public void shortestPath(Node start, Node end, Graphics2D g){
        PriorityQueueAdjecencyNode pq=reset();
        start.price=0;
        pq.heapifyUp(start.pqindex);
        dijkstra(start, pq);
        drawPath(end,g);

    }

    void drawPath(Node current, Graphics2D g){
        g.clearRect(0,0,600,600);
        drawGraph(g);
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(3));
        while (current.previous!=null){
            g.drawLine(current.x, current.y, current.previous.x, current.previous.y);
            current=current.previous;
        }
    }

    void dijkstra(Node current, PriorityQueueAdjecencyNode pq){
        for (Map.Entry<Integer, Edge> set: current.neighbors.entrySet()){
            //set.getValue().price -> price of current edge
            //set.getValue().neighbor.price -> current neighbor price
            if (set.getValue().neighbor.price>current.price+set.getValue().price){
                set.getValue().neighbor.price=current.price+set.getValue().price;
                set.getValue().neighbor.previous=current; //set current as previous
                pq.heapifyUp(set.getValue().neighbor.pqindex);
            }
        }
        pq.poll();
        System.out.println(pq.size);
        if (pq.size!=0)
            dijkstra(pq.data[0],pq);
    }

    public void connect(Node n1, Node n2, int price){
        n1.addNeighbor(n2,price);
    }

}
