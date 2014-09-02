import java.util.ArrayList;
import java.util.List;


public class DFSGraphTraversal {
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	public List<List<Integer>> traverse(Graph graph){
		for(int i = 0; i < graph.vCount(); i++){
			graph.setMark(i, 0);
		}
		List<List<Integer>> result = DFSHelper(graph, 0);
		List<List<Integer>> reversedResult = new ArrayList<List<Integer>>();
		while(result.size() > 0){
			reversedResult.add(result.get(result.size()-1));
			result.remove(result.size()-1);
		}
		return reversedResult;
	}
	public List<List<Integer>> DFSHelper(Graph graph, int currentVertex){
		List<Integer> list = new ArrayList<Integer>();
		list.add(currentVertex);
		graph.setMark(currentVertex, graph.getMark(currentVertex)+1);
		int nextVertex = graph.first(currentVertex);
		boolean traversing = true;
		while(traversing){
			if(graph.getMark(nextVertex) == 0){
				list.add(nextVertex);
				DFSHelper(graph, nextVertex);
			}
			nextVertex = graph.next(currentVertex, nextVertex);
			if(nextVertex == graph.vCount())
			{
				traversing = false;
			}
		}
		result.add(list);
		return result;
	}
}
