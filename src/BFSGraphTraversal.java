import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BFSGraphTraversal {
	// O(V+E)
	public List<List<Integer>> traverse(Graph graph) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> queue = new ArrayList<Integer>();
		queue.add(0);
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

		return null;
	}
}
