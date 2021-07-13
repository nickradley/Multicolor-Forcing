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
public class Graph {
    public ArrayList<Vertex> vertices = new ArrayList<>();
    public ArrayList<Edge> edges = new ArrayList<>();
    
    public Graph(String s) {
        for(int i = 0; i < s.length(); i++) {
            Vertex n = new Vertex(s.charAt(i), i);
            vertices.add(n);
            if(vertices.size() > 1) {
                Edge e = new Edge(vertices.get(i-1), vertices.get(i));
                edges.add(e);
            }
        }
        for(Edge e : edges) {
            e.getVertexOne().addNeighbor(e.getVertexTwo());
            e.getVertexTwo().addNeighbor(e.getVertexOne());
        }
    }
    
    public boolean terminated() {
        char first = vertices.get(0).getColor();
        boolean flag = true;
        for(Vertex v : vertices) {
            if(v.getColor() != first) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    public String force() {
        String res = "";
        ArrayList<Integer> toForce = new ArrayList<>();
        while(true) {
            if(terminated()) {
                break;
            }
            while(true){
                //PFS1
                for(Vertex v : vertices) {
                    if(v.getColor() == '0') {
                        ArrayList<Vertex> neighbors = v.getNeighbors();
                        for(Vertex k : neighbors) {
                            if(k.getColor() == '1') {
                                toForce.add(k.getPos());
                            }
                        }
                        for(int i : toForce) {
                            vertices.get(i).setColor('0');
                        }
                    }
                }
                if(toForce.size() == 0) {
                    break;
                }
                toForce.clear();
            }
            while(true){
                //PFS2
                for(Vertex v : vertices) {
                    if(v.getColor() == '1') {
                        ArrayList<Vertex> neighbors = v.getNeighbors();
                        for(Vertex k : neighbors) {
                            if(k.getColor() == '2') {
                                toForce.add(k.getPos());
                            }
                        }
                        for(int i : toForce) {
                            vertices.get(i).setColor('1');
                        }
                    }
                }
                if(toForce.size() == 0) {
                    break;
                }
                toForce.clear();
            }
            while(true){
                //PFS3
                for(Vertex v : vertices) {
                    if(v.getColor() == '2') {
                        ArrayList<Vertex> neighbors = v.getNeighbors();
                        for(Vertex k : neighbors) {
                            if(k.getColor() == '0') {
                                toForce.add(k.getPos());
                            }
                        }
                        for(int i : toForce) {
                            vertices.get(i).setColor('2');
                        }
                    }
                }
                if(toForce.size() == 0) {
                    break;
                }
                toForce.clear();
            }
        }
        for(Vertex v : vertices) {
            res += v.getColor();
        }
        return res;
    }
    

    @Override
    public String toString() {
        String res = "";
        res += "Vertices: ";
            for(Vertex v : vertices) {
            res += v.toString() + " ";
        } 
        res += "Edges: ";
        for(Edge e : edges) {
            res += e.toString() + " ";
        }    
        return res;
    }
}
