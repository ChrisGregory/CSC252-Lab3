import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BFSGraphTraversal {
	// O(V+E)
	public List<List<Integer>> traverse(Graph graph) {
		for(int i = 0; i < graph.vCount(); i++){
			graph.setMark(i, 0);
		}
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> queue = new ArrayList<Integer>();
		queue.add(0);
		graph.setMark(0, 1);
		while (queue.size() != 0) {
			List<Integer> list = new ArrayList<Integer>();
			int currentNode = queue.get(0);
			queue.remove(0);
			list.add(currentNode);
			int nextNode = graph.first(currentNode);
			while (nextNode != graph.vCount()) {
				if (graph.getMark(nextNode) == 0) {
					list.add(nextNode);
					queue.add(nextNode);
					graph.setMark(nextNode, 1);
				}
				nextNode = graph.next(currentNode, nextNode);
			}
			result.add(list);
		}
		return result;
	}
}
