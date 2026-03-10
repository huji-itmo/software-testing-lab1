package org.example.dijkstra

class DijkstraResult(
    val distances: Map<String, Double>,
    val paths: Map<String, List<String>>
)

class DijkstraAlgorithm(private val graph: Graph) {
    
    fun findShortestPath(start: String, end: String): DijkstraResult {
        val distances = mutableMapOf<String, Double>()
        val paths = mutableMapOf<String, List<String>>()
        val visited = mutableSetOf<String>()
        
        for (vertex in graph.getVertices()) {
            distances[vertex] = Double.MAX_VALUE
            paths[vertex] = emptyList()
        }
        distances[start] = 0.0
        paths[start] = listOf(start)

        repeat(graph.getVertices().size) {
            var minVertex: String? = null
            for (vertex in graph.getVertices()) {
                if (vertex !in visited) {
                    if (minVertex == null || distances[vertex]!! < distances[minVertex]!!) {
                        minVertex = vertex
                    }
                }
            }
            
            if (minVertex == null || distances[minVertex]!! == Double.MAX_VALUE) {
                return DijkstraResult(distances.toMap(), paths.toMap())
            }
            
            visited.add(minVertex)
            
            for ((neighbor, weight) in graph.getNeighbors(minVertex)) {
                val newDistance = distances[minVertex]!! + weight
                if (newDistance < distances[neighbor]!!) {
                    distances[neighbor] = newDistance
                    paths[neighbor] = paths[minVertex]!! + neighbor
                }
            }
        }
        
        return DijkstraResult(distances.toMap(), paths.toMap())
    }

    fun findShortestPathFrom(start: String): DijkstraResult {
        return findShortestPath(start, "")
    }
}
