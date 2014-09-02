public class DotsAndBoxes {
	public int[] playerScores;

	Graph graph;
	int rows;
	int columns;
	int coinRows;
	int coinColumns;
	
	public DotsAndBoxes(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		coinRows = rows+1;
		coinColumns = columns+1;
		playerScores = new int[2];
		graph = new Graph(coinRows * coinColumns);
		for(int i = 0; i < coinRows; i++){
			for(int j = 0; j < coinColumns; j++){
				if(i != rows && i != 0 && j != columns){
					//Connect this node to the node on the right.
					graph.addEdge(coinColumns * i + j, coinColumns * i + j + 1, 1);
				}
				if(j != columns && j != 0 && i != rows){
					//Connect this node to the node below it.
					graph.addEdge(coinColumns * i + j, coinColumns * (i + 1) + j, 1);
				}
			}
		}
	}

	public void printCoins(){
		for(int i = 0; i < coinRows; i++){
			for(int j = 0; j < coinColumns; j++){
				int charcode = coinColumns * i + j;
				if(charcode > 90 - 65){
					System.out.print((char)(coinColumns * i + j + 65 + 6));
				} else {
					System.out.print((char)(coinColumns * i + j + 65));	
				}
				if(graph.isEdge(coinColumns * i + j, coinColumns * i + j + 1)){
					System.out.print("-");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
			for(int j = 0; j < columns; j++){
				if(graph.isEdge(coinColumns * i + j, coinColumns * (i + 1) + j)){
					System.out.print("| ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
	}	
	
	// The graph nodes are the "coins" with the edges between them acting as the
	// "Strings". When a D&B Line is drawn, the string is severed.

	// draws a line from (x1, y1) to (x2, y2) (0,0) is in the upper-left corner,
	// returning how many points were earned, if any
	public int drawLine(int player, int x1, int y1, int x2, int y2) {
		// Player is 1 based. In a two player game, possible input is 1 & 2.
		int result = 0;
		boolean stringToRight = x1 == x2;
		int vertex1 = 0;
		int vertex2 = 0;
		if(stringToRight){
			vertex1 = (coinColumns * x1) + y1 + 1;
			vertex2 = (coinColumns * (x2 + 1)) + y2;
		} else {
			vertex1 = (coinColumns * (x1 + 1)) + y1;
			vertex2 = (coinColumns * x2) + y2 + 1;
		}
		if(graph.degree(vertex1) == 0 && !edge(vertex1)){
			result--;
		}
		if(graph.degree(vertex2) == 0 && !edge(vertex2)){
			result--;
		}
		graph.removeEdge(vertex1, vertex2);
		System.out.println("Severing line between " + (vertex1 > 25 ? ((char)(vertex1+71)) : ((char)(vertex1+65))) + " and " +  (vertex2 > 25 ? ((char)(vertex2+71)) : ((char)(vertex2+65))));
		
		if(graph.degree(vertex1) == 0 && !edge(vertex1)){
			result++;
			System.out.println("Scored Coin: " + (vertex1 > 25 ? ((char)(vertex1+71)) : ((char)(vertex1+65))));
		}
		if(graph.degree(vertex2) == 0 && !edge(vertex2)){
			result++;
			System.out.println("Scored Coin: " + (vertex2 > 25 ? ((char)(vertex2+71)) : ((char)(vertex2+65))));
		}
		addScore(player, result);
		return result;
	}

	private boolean edge(int vertex) {
		if(vertex <= coinColumns){
			return true;
		} else if(vertex % coinColumns == 0){
			return true;
		} else if((vertex + 1) % coinColumns == 0){
			return true;
		} else if(vertex > graph.vCount() - coinColumns){
			return true;
		}
		System.out.println((vertex > 25 ? ((char)(vertex+71)) : ((char)(vertex+65)))+ " determined to not be an edge.");
		return false;
	}

	public int getScore(int player) {
		// Player is 1 based. In a two player game, possible input is 1 & 2.
		return playerScores[player - 1];
	}
	
	private void addScore(int player, int score) {
		playerScores[player - 1] += score;
	}


	// returns whether or not there are any lines to be drawn.
	public boolean areMovesLeft() {
		return graph.eCount() > 0;
	}

	// returns the number of double-crosses on the board
	public int countDoubleCrosses() {
		int result = 0;
		for(int i = 0; i < graph.vCount(); i++){
			if(!edge(i) && graph.degree(i) == 1){
				if(graph.degree(graph.first(i)) == 1){
					result++;
				}
			}
		}
		return result/2;
	}

	// returns the number of open chains on the board
	public int countOpenChains() {
		//Find a chain of 4 coins connected to 2 things max.
		int result = 0;
		for(int i = 0; i < graph.vCount(); i++){
			if(!edge(i) && graph.degree(i) == 1){
				if(graph.degree(graph.first(i)) == 1){
					result++;
				}
			}
		}
		return result/2;
	}

	public Object countCycles() {
		// TODO Auto-generated method stub
		return 0;
	}
}
