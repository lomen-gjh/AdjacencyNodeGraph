package AdjacencyNode;

import java.awt.*;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
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
        for (Map.Entry<Integer, Edge> set: node.neighbors.entrySet()){
            set.getValue().neighbor.removeNeighbor(node);
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

    public void connect(Node n1, Node n2, int price){
        n1.addNeighbor(n2,price);
    }


    int shortestPath(){


        return -1;

    }

    void dijkstra(){

    }
}
