public class Graph
{
	private final int V;
	private int E;
	private Bag<Integer>[] adj;

	public Graph(int V)
	{
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	public Graph(In in)
	{
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	public int V() { return V; }
	public int E() { return E; }
	public void addEdge(int v, int w)
	{
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	public Iterable<Integer> adj(int v) { return adj[v]; }
	public String toString()
	{
		String s = V + " vertices, " + E + " edges \n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (int w : this.adj(v))
				s += w + " ";
			s += "\n";
		}
		return s;
	}
	public static int degree(Graph G, int v)
	{
		int degree = 0;
		for (int w : G.adj(v)) degree++;
		return degree;
	}
	public static int maxDegree(Graph G)
	{
		int max = 0, V = G.V();
		for (int v = 0; v < V; v++) {
			int degree = degree(G, v);
			if (max < degree) max = degree;
		}
		return max;
	}
	public static double avgDegree(Graph G) { return 2.0 * G.E() / G.V(); }
	public static int numberOfSelfLoops(Graph G)
	{
		int cnt = 0, V = G.V();
		for (int v = 0; v < V; v++)
			for (int w : G.adj(v))
				if (w == v) cnt++;
		return cnt/2; //every edge have be counted twice!
	}
}