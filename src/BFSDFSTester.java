import java.util.List;
import java.util.Random;

public class BFSDFSTester {
	public static void main(String[] args) {
		Random rand = new Random();
		Graph g = new Graph(10);
		for(int i = 0; i < g.vCount(); i++)
		{
			int connection1 = rand.nextInt(g.vCount());
			g.addEdge(i, connection1, 1);

			int connection2 = rand.nextInt(g.vCount());
			g.addEdge(i, connection2, 1);
			
			int connection3 = rand.nextInt(g.vCount());
			g.addEdge(i, connection3, 1);
		}
		
		System.out.println("Adjacency Matrix:");
		g.printAdjacencyMatrix();
		System.out.println();
		System.out.println("Adjacency List:");
		g.printAdjacencyList();
		
		DFSGraphTraversal DFS = new DFSGraphTraversal();
		List<List<Integer>> result = DFS.traverse(g);
		System.out.println("\nDFS:");
		for(List<Integer> list : result){
			for(int i : list){
				System.out.print(i+", ");
			}
			System.out.print("\n");
		}

		BFSGraphTraversal BFS = new BFSGraphTraversal();
		List<List<Integer>> BFSresult = BFS.traverse(g);
		System.out.println("\nBFS:");
		for(List<Integer> list : BFSresult){
			for(int i : list){
				System.out.print(i+", ");
			}
			System.out.print("\n");
		}
	}
}
