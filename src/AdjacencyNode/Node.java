package AdjacencyNode;

import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

class Edge{
    int price;
    Node neighbor;
    Edge(Node n, int p){
        price=p;
        neighbor=n;
    }
}

public class Node {
    int pqindex, price;
    Node previous; //For path tracing in Dijkstra
    int x, y;
    HashMap<Integer, Edge> neighbors;
    int id;
    static int currentID=1;
    public Node(int x, int y, Graphics g){
        previous=null;
        pqindex=-1;   //not in heap
        price=10000; //inital price
        this.x=x;
        this.y=y;
        id=currentID;
        currentID++;
        neighbors=new HashMap<>();
    }

    public void drawNode(Graphics g){
        g.drawOval(x-15,y-15,30,30);
        g.setColor(Color.WHITE);
        g.fillOval(x-14,y-14,28,28);
        g.setColor(Color.BLACK);
        String s=Integer.toString(id);
        g.drawString(s, x-g.getFontMetrics().stringWidth(s)/2,y+5);
    }

    public void addNeighbor(Node n, int price){
        if (!neighbors.containsKey(n.id)){
            this.neighbors.put(n.id, new Edge(n,price));
            n.neighbors.put(this.id, new Edge(this, price));
        }
        else {
            System.out.println("ERROR: Nodes "+id+" and "+n.id+" are already neighbors");
        }
    }

    public void removeNeighbor(Node target){
        target.neighbors.remove(this.id);
        this.neighbors.remove(target.id);

    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    public void setPqindex(int pqindex) {
        this.pqindex = pqindex;
    }
    public int getPqindex(){
        return pqindex;
    }
    public void setPrice(int newPrice){
        price=newPrice;
    }
    public int getPrice(){
        return price;
    }
}


