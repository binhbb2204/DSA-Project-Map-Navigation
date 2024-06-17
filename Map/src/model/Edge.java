/*
 * Author:       Thái Quang Tính
 * Created on    17-06-2024
 * GitHub:       https://github.com/notrealqt
 * Email:        tinhthai2000@gmail.com
 * Description:  [Brief description of the file or project]
*/


package model;

public class Edge implements IEdge {
    private INode nodeA;
    private INode nodeB;
    private int weight;

    @Override
    public INode getNodeA() {
        return this.nodeA;
    }

    @Override
    public INode getNodeB() {
        return this.nodeB;
    }

    @Override
    public INode getOpposite(INode node) {
        if(node == nodeA) return this.nodeB;
        if(node == nodeB) return this.nodeA;
        return null;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }
    
    public void setNodeA(Node nodeA){
        this.nodeA = nodeA;
    }

    public void setNodeB(Node nodeB){
        this.nodeB = nodeB;
    }
}
