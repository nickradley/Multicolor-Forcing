/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolor.forcing;

import java.util.ArrayList;

/**
 *
 * @author nick
 */
public class Vertex {
    
    public char color;
    public int pos;
    public ArrayList<Vertex> neighbors = new ArrayList<>();
    public Vertex(char c, int p) {
        
        color = c;
        pos = p;
        
    }
    
    public char getColor() {
        return color;
    }
    
    public int getPos() {
        return pos;
    }
    
    public ArrayList<Vertex> getNeighbors() {
        
        return neighbors;
    }
    
    public void setColor(char n) {
        color = n;
    }
    public void addNeighbor(Vertex v) {
        neighbors.add(v);
    }
    @Override
    public String toString() {
        String res = "";
        res += "Color: " + color + " Neighbors: ";
        for(Vertex v : neighbors) {
            res += v.getPos() + " ";
        }
        return res;
    }
}
