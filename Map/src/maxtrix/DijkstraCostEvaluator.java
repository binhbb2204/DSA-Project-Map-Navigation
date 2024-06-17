/*
 * Author:       Thái Quang Tính
 * Created on    17-06-2024
 * GitHub:       https://github.com/notrealqt
 * Email:        tinhthai2000@gmail.com
 * Description:  [Brief description of the file or project]
*/


package matrix;

import model.INode;

// Dijkstra and A* are one but A* has heuristic so just reuse the algo from AStar but return 0 on the heuristic method
public class DijkstraCostEvaluator extends AStarCostEvaluator{
    @Override
    public int evaluateHeuristic(INode node, INode start, INode end) {
        return 0;
    }
}
