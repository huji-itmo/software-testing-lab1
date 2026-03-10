package org.example.dijkstra

class Edge(val from: String, val to: String, val weight: Double)

class Graph {
    private val edges = mutableListOf<Edge>()
    private val vertices = mutableSetOf<String>()

    fun addEdge(from: String, to: String, weight: Double) {
        edges.add(Edge(from, to, weight))
        vertices.add(from)
        vertices.add(to)
    }

    fun getEdges(): List<Edge> = edges.toList()
    
    fun getVertices(): Set<String> = vertices.toSet()

    fun getNeighbors(vertex: String): List<Pair<String, Double>> {
        return edges
            .filter { it.from == vertex }
            .map { Pair(it.to, it.weight) }
    }
}
