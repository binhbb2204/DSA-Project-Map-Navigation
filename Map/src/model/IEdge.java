/*
 * Author:       Thái Quang Tính
 * Created on    17-06-2024
 * GitHub:       https://github.com/notrealqt
 * Email:        tinhthai2000@gmail.com
 * Description:  [Brief description of the file or project]
*/


package model;

public interface IEdge {
    INode getNodeA(); //Return node A
    INode getNodeB(); //return node B
    INode getOpposite(INode node); //given either node A or B, return other of 2 nodes
    int getWeight(); //return weight of the edge

}
