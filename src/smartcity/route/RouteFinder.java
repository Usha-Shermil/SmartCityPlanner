package smartcity.route;



import smartcity.graph.Graph;

import java.util.*;



public class RouteFinder {

    public List<String> findRoute(Graph graph, String start, String end) {

        Map<String, List<String>> adjList = graph.getAdjList();

        if (!adjList.containsKey(start) || !adjList.containsKey(end)) {

            System.out.println("⚠️ One or both locations do not exist!");

            return Collections.emptyList();

        }



        Queue<String> queue = new LinkedList<>();

        Map<String, String> parent = new HashMap<>();

        Set<String> visited = new HashSet<>();



        queue.add(start);

        visited.add(start);



        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(end)) break;



            for (String neighbor : adjList.get(current)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    parent.put(neighbor, current);

                    queue.add(neighbor);

                }

            }

        }



        List<String> path = new ArrayList<>();

        String step = end;

        while (step != null) {

            path.add(0, step);

            step = parent.get(step);

        }



        if (path.size() == 1 && !path.get(0).equals(start)) {

            System.out.println("⚠️ No route found between " + start + " and " + end);

            return Collections.emptyList();

        }



        return path;

    }

}