/*
 * Author:       Thái Quang Tính
 * Created on    17-06-2024
 * GitHub:       https://github.com/notrealqt
 * Email:        tinhthai2000@gmail.com
 * Description:  [Brief description of the file or project]
*/


package model;

public interface ICostEvaluator {
    int evaluateWeight(IEdge edge);

    int evaluateHeuristic(INode node, INode start, INode end);

    int evaluateCost(INode candidate, IEdge edge, INode start, INode end);
}
