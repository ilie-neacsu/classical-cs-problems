package ai.webtch.graphs;

import ai.webtch.graphs.framework.UnweightedGraph;

import java.util.List;



public class GraphExample {

        public static void main(String[] args) {

                UnweightedGraph<String> cityGraph = new UnweightedGraph<>(
                        List.of("Seattle", "San Francisco", "Los Angeles", "Riverside", "Phoenix", "Chicago",
                                "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston", "Detroit", "Philadelphia",
                                "Washington"));

                cityGraph.addEdge("Seattle", "Chicago");
                cityGraph.addEdge("Seattle", "San Francisco");
                cityGraph.addEdge("San Francisco", "Riverside");
                cityGraph.addEdge("San Francisco", "Los Angeles");
                cityGraph.addEdge("Los Angeles", "Riverside");
                cityGraph.addEdge("Los Angeles", "Phoenix");
                cityGraph.addEdge("Riverside", "Phoenix");
                cityGraph.addEdge("Riverside", "Chicago");
                cityGraph.addEdge("Phoenix", "Dallas");
                cityGraph.addEdge("Phoenix", "Houston");
                cityGraph.addEdge("Dallas", "Chicago");
                cityGraph.addEdge("Dallas", "Atlanta");
                cityGraph.addEdge("Dallas", "Houston");
                cityGraph.addEdge("Houston", "Atlanta");
                cityGraph.addEdge("Houston", "Miami");
                cityGraph.addEdge("Atlanta", "Chicago");
                cityGraph.addEdge("Atlanta", "Washington");
                cityGraph.addEdge("Atlanta", "Miami");
                cityGraph.addEdge("Miami", "Washington");
                cityGraph.addEdge("Chicago", "Detroit");
                cityGraph.addEdge("Detroit", "Boston");
                cityGraph.addEdge("Detroit", "Washington");
                cityGraph.addEdge("Detroit", "New York");
                cityGraph.addEdge("Boston", "New York");
                cityGraph.addEdge("New York", "Philadelphia");
                cityGraph.addEdge("Philadelphia", "Washington");

                System.out.println(cityGraph.toString());
        }
}
