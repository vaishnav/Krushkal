public class Edge implements Comparable<Edge>{
  public final Vertex target;
	public final double weight;
	public final Vertex source;
	public Edge(Vertex source, Vertex target, double weight){
		this.target = target;
		this.weight = weight;
		this.source = source;
	}
	// To build a priority queue with based on weights.
	public int compareTo(Edge other_edge){
		return Double.compare(weight, other_edge.weight);
	}
}
