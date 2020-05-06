package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph<Airport, DefaultWeightedEdge> graph;
	private List<Airport> aeroporti ;
	private Map<Integer, Airport> aeroportiIdMap ;
	ExtFlightDelaysDAO dao;
	
	public Model() {
		
		dao = new ExtFlightDelaysDAO() ;
		
		aeroporti = dao.loadAllAirports();
		
		this.aeroportiIdMap = new HashMap<>() ;
		for(Airport a: this.aeroporti) {
			aeroportiIdMap.put(a.getId(), a) ;
		}
	}
	
	public void creaGrafo(Integer distanzaMinima) {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.graph, this.aeroporti);
		//	System.out.println(this.graph);
		
		// CREAZIONE DEI VERTICI
		this.aeroporti = dao.loadAllAirports();
	
	
		// CREAZIONE DEGLI ARCHI 
		
			List<CoppiaAeroporti> coppie = dao.loadFlightsByAvgDistance(aeroportiIdMap) ;
			
			for(CoppiaAeroporti c : coppie) {
				Airport a = c.getAeroportoA();
				Airport b = c.getAeroportoB();
				
				if (this.graph.containsEdge(a, b)) {
					DefaultWeightedEdge e = this.graph.getEdge(a, b);
					double peso = (this.graph.getEdgeWeight(e)+c.getAvgDistanza())/2;
					this.graph.setEdgeWeight(a, b, peso);
				}else
				
						if (c.getAvgDistanza() >= distanzaMinima)
								Graphs.addEdge(this.graph, c.getAeroportoA(), c.getAeroportoB(), c.getAvgDistanza());
			}
					
		//	System.out.println(this.graph) ;
		//	System.out.format("Grafo caricato con %d vertici %d archi",
		//			this.graph.vertexSet().size(),
		//			this.graph.edgeSet().size());

	}

	public Graph<Airport, DefaultWeightedEdge> getGraph() {
		return graph;
	}	
	
}