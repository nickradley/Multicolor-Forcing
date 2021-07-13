/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolor.forcing;

/**
 *
 * @author nick
 */
public class Edge {
    Vertex a;
    Vertex b;
    
    public Edge(Vertex one, Vertex two) {
        a = one;
        b = two;
    }
    
    public Vertex getVertexOne() {
        return a;
    }
    
    public Vertex getVertexTwo() {
        return b;
    }
    
    @Override
    public String toString() {
        String res = "(" + a.getPos() + ", " + b.getPos() + ")";
        return res;
    }
        
}
