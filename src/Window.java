import javax.swing.*;
import AdjacencyNode.Graph;
import AdjacencyNode.Node;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class Window  extends JFrame implements ActionListener, MouseListener {
    Graph graph;
    JPanel north, center;
    JButton findSP;

    int selected=-1;
    public static void main(String[] args){
        Window window=new Window();
        window.graph=new Graph();

    }


    Window(){
        setTitle("Graph theory");
        setSize(800,600);
        setLayout(new BorderLayout());
        graph=new Graph();
        north=new JPanel();
        center=new JPanel();
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        center.addMouseListener(this);
        findSP=new JButton("Shortest path");
        findSP.addActionListener(this);
        north.add(findSP);



        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON1){
            graph.addNode(new Node(e.getX(),e.getY(), center.getGraphics()));
            graph.drawGraph(center.getGraphics());
        }
        else if(e.getButton()==MouseEvent.BUTTON2) {
            int todelete=-1;
            for (int i=0; i<graph.nodes.size();i++){
                if ((graph.nodes.elementAt(i).getX()-15<e.getX() && e.getX()<graph.nodes.elementAt(i).getX()+15)
                        && (graph.nodes.elementAt(i).getY()-15<e.getY() && e.getY()<graph.nodes.elementAt(i).getY()+15))
                {
                    todelete=i;
                }
            }
            graph.removeNode(graph.nodes.elementAt(todelete));
            graph.drawGraph(center.getGraphics());
        }
        else if (e.getButton()==MouseEvent.BUTTON3){
            for (int i=0; i<graph.nodes.size();i++){
                if ((graph.nodes.elementAt(i).getX()-15<e.getX() && e.getX()<graph.nodes.elementAt(i).getX()+15)
                        && (graph.nodes.elementAt(i).getY()-15<e.getY() && e.getY()<graph.nodes.elementAt(i).getY()+15))
                {
                    if (selected==-1){
                        System.out.println("Selecting "+(i+1)+"...");
                        selected=i;
                    }
                    else{
                        System.out.println("Connecting "+(i+1)+" and "+(selected+1)+"...");
                        graph.connect(graph.nodes.elementAt(selected),graph.nodes.elementAt(i),(int)(Math.random()*10)+1);
                        System.out.println("Disselecting...");
                        selected=-1;
                        graph.drawGraph(center.getGraphics());
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
