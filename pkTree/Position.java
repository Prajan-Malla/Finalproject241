package pkTree;

import java.util.LinkedList;

public class Position<T> {
    // Private member variables
    private LinkedList<Position<T>> children_;
    private Position<T> parent_;
    private T data_;
    private int ctChildren_; // number of children

    //default constructor
    public Position() {
        this.data_ = null;
        this.parent_ = null;
        this.children_ = new LinkedList<>();
        this.ctChildren_ = 0;
    }

    // parametrized Constructor
    public Position(T data, Position<T> parent) {
        this.data_ = data;
        this.parent_ = parent;
        this.children_ = new LinkedList<>();
        this.ctChildren_ = 0;
    }

    // Getter for data
    public T getData() {
        return data_;
    }

    // Setter for data
    public void setData(T data) {
        this.data_ = data;
    }

    // Getter for parent
    public Position<T> getParent() {
        return parent_;
    }

    // Setter for parent
    public void setParent(Position<T> parent) {
        this.parent_ = parent;
    }

    // Getter for children
    public LinkedList<Position<T>> getChildren() {
        return children_;
    }

    // Setter for children
    public void setChildren(LinkedList<Position<T>> children) {
        this.children_ = children;
        this.ctChildren_ = children.size(); // Update child count when setting children
    }

    // Method to add a child
    public void addChild(Position<T> child) {
        children_.add(child);
        ctChildren_++;
    }

    // Method to remove a child
    public boolean removeChild(Position<T> child) {
        boolean removed = children_.remove(child);
        if (removed) {
            ctChildren_--;
        }
        return removed;
    }





    boolean isRoot(Position<T> p) {
        return p.parent_==null;
    }

    void setParent(Position<T> p, Position<T> parent){p.parent_=parent;}

    boolean isInternal(Position<T> p){  return p.ctChildren_!=0;  }

    boolean isExternal(Position<T> p){  return p.ctChildren_==0;  }

    int getNbChildren(Position<T>p){return ctChildren_;}


}