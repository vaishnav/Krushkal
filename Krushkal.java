import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;


public class Krushkal {

  public static void main(String[] args) {
		
		Krushkal obj = new Krushkal();
		
		// Create a new graph.
		Graph g = new Graph(9);
				
		// Add the required edges.
		g.addEdge(0, 1, 4); g.addEdge(0, 7, 8);
		g.addEdge(1, 2, 8); g.addEdge(1, 7, 11); g.addEdge(2, 1, 8);
		g.addEdge(2, 8, 2); g.addEdge(2, 5, 4); g.addEdge(2, 3, 7);
		g.addEdge(3, 2, 7); g.addEdge(3, 5, 14); g.addEdge(3, 4, 9); 
		g.addEdge(4, 3, 9); g.addEdge(4, 5, 10); 
		g.addEdge(5, 4, 10); g.addEdge(5, 3, 9); g.addEdge(5, 2, 4); g.addEdge(5, 6, 2);
		g.addEdge(6, 7, 1); g.addEdge(6, 8, 6); g.addEdge(6, 5, 2);
		g.addEdge(7, 0, 8); g.addEdge(7, 8, 7); g.addEdge(7, 1, 11); g.addEdge(7, 6, 1);
		g.addEdge(8, 2, 2); g.addEdge(8, 7, 7); g.addEdge(8, 6, 6);
		
		obj.calculate(g);
		
	}
	
	public void calculate(Graph g){
		
    //Sort by Edge weights.
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		for(Vertex v:g.getVertices()){
			for(Edge e:v.neighbours){
				queue.add(e);
			}
		}
		
		List<HashSet<Vertex>> vertex_sets = new ArrayList<HashSet<Vertex>>();
		
		//Print all the edges that do not form a cycle.
    //Algo:
    //If(Source and Destination belong to the same group) Continue; -- Will form cycle
    //If(Source belongs to some group but Destination does not belong to any group) Add Destination to Source group.
    //If(Destination belongs to some group but Source does not belong to any group) Add Source to Destination group.
    //If(Both the vertices do not belong to any group) Create a new group with both the vertices in it.
    
		while(!queue.isEmpty()){
			Edge e = queue.poll();
			HashSet<Vertex> a = checkVertexGroup(e.source,vertex_sets);
			HashSet<Vertex> b = checkVertexGroup(e.target,vertex_sets);
						
			if(a==null && b==null){
				HashSet<Vertex> newKEdge = new HashSet<Vertex>();
				newKEdge.add(e.source);
				newKEdge.add(e.target);
				vertex_sets.add(newKEdge);
			} 
			if(a==null && b!=null){
				b.add(e.source);
			}
			if(b==null && a!=null){
				a.add(e.target);
				
			}
			if(a!=null && b!=null){				
				if(a!=b){
					a.addAll(b);
					vertex_sets.remove(b);
				}
				if(a==b){
					continue;
				}
			}
			System.out.println("Edge from "+e.source+" to "+e.target+" present.");
		}
	}
	
	public HashSet<Vertex> checkVertexGroup(Vertex v, List<HashSet<Vertex>> vertex_sets){
		for(HashSet<Vertex> s:vertex_sets){
			if(s.contains(v)){
				return s;
			}
		}
		return null;
	}
}
