package org.example.dijkstra

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DijkstraTest {
    
    @Test
    fun testSimpleGraph() {
        val graph = Graph()
        graph.addEdge("A", "B", 4.0)
        graph.addEdge("A", "C", 2.0)
        graph.addEdge("B", "C", 1.0)
        graph.addEdge("B", "D", 5.0)
        graph.addEdge("C", "D", 8.0)
        graph.addEdge("C", "E", 10.0)
        graph.addEdge("D", "E", 2.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "E")
        
        val dfs = DfsAlgorithm(graph)
        val dfsResult = dfs.findShortestPathByDfs("A", "E")
        
        assertNotNull(dfsResult)
        assertEquals(dfsResult!!.first, result.distances["E"] ?: Double.MAX_VALUE, 0.001)
    }

    @Test
    fun testDirectPath() {
        val graph = Graph()
        graph.addEdge("A", "B", 5.0)
        graph.addEdge("B", "C", 3.0)
        graph.addEdge("A", "C", 10.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "C")
        
        val dfs = DfsAlgorithm(graph)
        val dfsResult = dfs.findShortestPathByDfs("A", "C")
        
        assertNotNull(dfsResult)
        assertEquals(dfsResult!!.first, result.distances["C"] ?: Double.MAX_VALUE, 0.001)
        assertEquals(listOf("A", "B", "C"), result.paths["C"])
    }

    @Test
    fun testNoPath() {
        val graph = Graph()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("C", "D", 1.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "D")
        
        assertEquals(Double.MAX_VALUE, result.distances["D"] ?: Double.MAX_VALUE, 0.001)
    }

    @Test
    fun testSingleVertex() {
        val graph = Graph()
        graph.addEdge("A", "A", 0.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "A")
        
        assertEquals(0.0, result.distances["A"] ?: Double.MAX_VALUE, 0.001)
    }

    @Test
    fun testTwoVertices() {
        val graph = Graph()
        graph.addEdge("A", "B", 5.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "B")
        
        val dfs = DfsAlgorithm(graph)
        val dfsResult = dfs.findShortestPathByDfs("A", "B")
        
        assertNotNull(dfsResult)
        assertEquals(dfsResult!!.first, result.distances["B"] ?: Double.MAX_VALUE, 0.001)
        assertEquals(5.0, result.distances["B"] ?: Double.MAX_VALUE, 0.001)
    }

    @Test
    fun testGraphWithCycle() {
        val graph = Graph()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("B", "C", 1.0)
        graph.addEdge("C", "A", 1.0)
        graph.addEdge("A", "D", 5.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "D")
        
        assertEquals(5.0, result.distances["D"] ?: Double.MAX_VALUE, 0.001)
        assertEquals(listOf("A", "D"), result.paths["D"])
    }

    @Test
    fun testMultiplePaths() {
        val graph = Graph()
        graph.addEdge("A", "B", 2.0)
        graph.addEdge("A", "C", 2.0)
        graph.addEdge("B", "D", 2.0)
        graph.addEdge("C", "D", 2.0)
        graph.addEdge("A", "D", 10.0)

        val dijkstra = DijkstraAlgorithm(graph)
        val result = dijkstra.findShortestPath("A", "D")
        
        val dfs = DfsAlgorithm(graph)
        val dfsResult = dfs.findShortestPathByDfs("A", "D")
        
        assertNotNull(dfsResult)
        assertEquals(dfsResult!!.first, result.distances["D"] ?: Double.MAX_VALUE, 0.001)
        assertEquals(4.0, result.distances["D"] ?: Double.MAX_VALUE, 0.001)
    }

    @Test
    fun testDfsFindsAllPaths() {
        val graph = Graph()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 1.0)
        graph.addEdge("B", "D", 1.0)
        graph.addEdge("C", "D", 1.0)

        val dfs = DfsAlgorithm(graph)
        val allPaths = dfs.findAllPaths("A", "D")
        
        assertTrue(allPaths.isNotEmpty())
        assertEquals(2, allPaths.size)
    }

    @Test
    fun testDfsNoPath() {
        val graph = Graph()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("C", "D", 1.0)

        val dfs = DfsAlgorithm(graph)
        val allPaths = dfs.findAllPaths("A", "D")
        
        assertTrue(allPaths.isEmpty())
    }

    @Test
    fun testCompareDijkstraAndDfs() {
        val testCases = listOf(
            createTestGraph1(),
            createTestGraph2(),
            createTestGraph3()
        )

        for ((index, graph) in testCases.withIndex()) {
            val dijkstra = DijkstraAlgorithm(graph)
            val dfs = DfsAlgorithm(graph)
            
            val vertices = graph.getVertices().toList()
            for (start in vertices) {
                for (end in vertices) {
                    if (start == end) continue
                    
                    val dijkstraResult = dijkstra.findShortestPath(start, end)
                    val dfsResult = dfs.findShortestPathByDfs(start, end)
                    
                    if (dfsResult != null) {
                        assertEquals(
                            dfsResult.first,
                            dijkstraResult.distances[end] ?: Double.MAX_VALUE,
                            0.001,
                            "Graph $index: Path from $start to $end"
                        )
                    }
                }
            }
        }
    }

    private fun createTestGraph1(): Graph {
        val graph = Graph()
        graph.addEdge("A", "B", 4.0)
        graph.addEdge("A", "C", 2.0)
        graph.addEdge("B", "C", 1.0)
        graph.addEdge("B", "D", 5.0)
        graph.addEdge("C", "D", 8.0)
        graph.addEdge("C", "E", 10.0)
        graph.addEdge("D", "E", 2.0)
        return graph
    }

    private fun createTestGraph2(): Graph {
        val graph = Graph()
        graph.addEdge("S", "A", 4.0)
        graph.addEdge("S", "B", 2.0)
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "C", 3.0)
        graph.addEdge("B", "C", 4.0)
        graph.addEdge("B", "D", 7.0)
        graph.addEdge("C", "D", 2.0)
        return graph
    }

    private fun createTestGraph3(): Graph {
        val graph = Graph()
        graph.addEdge("1", "2", 1.0)
        graph.addEdge("1", "3", 4.0)
        graph.addEdge("2", "3", 2.0)
        graph.addEdge("2", "4", 5.0)
        graph.addEdge("3", "4", 1.0)
        return graph
    }
}
