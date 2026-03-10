package org.example.dijkstra

class DfsAlgorithm(private val graph: Graph) {
    
    fun findAllPaths(start: String, end: String): List<List<String>> {
        val allPaths = mutableListOf<List<String>>()
        dfs(start, end, mutableListOf(start), allPaths, mutableSetOf(start))
        return allPaths
    }

    private fun dfs(
        current: String,
        end: String,
        currentPath: MutableList<String>,
        allPaths: MutableList<List<String>>,
        visited: MutableSet<String>
    ) {
        if (current == end) {
            allPaths.add(currentPath.toList())
            return
        }

        for ((neighbor, _) in graph.getNeighbors(current)) {
            if (neighbor !in visited) {
                visited.add(neighbor)
                currentPath.add(neighbor)
                dfs(neighbor, end, currentPath, allPaths, visited)
                currentPath.removeAt(currentPath.size - 1)
                visited.remove(neighbor)
            }
        }
    }

    fun findShortestPathByDfs(start: String, end: String): Pair<Double, List<String>>? {
        val allPaths = findAllPaths(start, end)
        if (allPaths.isEmpty()) return null

        var minDistance = Double.MAX_VALUE
        var shortestPath: List<String>? = null

        for (path in allPaths) {
            val distance = calculatePathDistance(path)
            if (distance < minDistance) {
                minDistance = distance
                shortestPath = path
            }
        }

        return if (shortestPath != null) {
            Pair(minDistance, shortestPath)
        } else null
    }

    private fun calculatePathDistance(path: List<String>): Double {
        var distance = 0.0
        for (i in 0 until path.size - 1) {
            val from = path[i]
            val to = path[i + 1]
            for ((neighbor, weight) in graph.getNeighbors(from)) {
                if (neighbor == to) {
                    distance += weight
                    break
                }
            }
        }
        return distance
    }
}
