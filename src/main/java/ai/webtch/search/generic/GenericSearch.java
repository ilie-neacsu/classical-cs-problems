package ai.webtch.search.generic;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class GenericSearch {

    public static <T extends Comparable<T>> boolean linearContains(List<T> list, T key) {
        for (T item: list) {
            if (item.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    // assumes list is already sorted
    public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T key) {

        int low = 0;
        int high = list.size() - 1;

        while (high <= low) {

            int middle = (high - low) / 2;
            int comparison = list.get(middle).compareTo(key);

            if(comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static class Node<T> implements Comparable<Node<T>> {

        final T state;
        Node<T> parent;
        double cost;
        double heuristic;

        // for dfs and bfs, we won't use cost and heuristic
        Node(T state, Node<T> parent) {
            this.state = state;
            this.parent = parent;
        }

        // for astar, we will use cost and heuristic
        Node(T state, Node<T> parent, double cost, double heuristic) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        @Override
        public int compareTo(Node<T> other) {
            Double mine = cost + heuristic;
            Double theirs = other.cost + other.heuristic;
            return mine.compareTo(theirs);
        }
    }

    public static <T> Node<T> dfs(
            T initial,
            Predicate<T> isGoal,
            Function<T, List<T>> succesors
    ) {

        // frontier is where we've yet to go
        Stack<Node<T>> frontier = new Stack<>();
        frontier.push(new Node<T>(initial, null));

        // explored is where we've been
        Set<T> explored = new HashSet<>();
        explored.add(initial);

        // keep looking while there is more to explore
        while(!frontier.isEmpty()) {

            Node<T> currentNode = frontier.pop();
            T currentState = currentNode.state;

            // if we're found the goal, we're done
            if (isGoal.test(currentState)) {
                return currentNode;
            }

            // check were we can go next and haven't explored
            for (T child :  succesors.apply(currentState)) {
                if(explored.contains(child)) {
                    // skip children we already explored
                    continue;
                }
                explored.add(child);
                frontier.push(new Node<>(child, currentNode));
            }

        }
        // went through everything and never found a goal
        return null;
    }

    public static <T> Node<T> bfs(
            T initial,
            Predicate<T> isGoal,
            Function<T, List<T>> successors
    ) {
        // frontier is where we've yet to go
        Queue<Node<T>> frontier = new LinkedList<>();
        frontier.offer(new Node<>(initial, null));

        // explored is where we've been
        Set<T> explored = new HashSet<>();
        explored.add(initial);

        // keep looking while there is more to explore
        while (!frontier.isEmpty()) {
            Node<T> currentNode = frontier.poll();
            T currentState = currentNode.state;

            // if we're found the goal, we're done
            if(isGoal.test(currentState)) {
                return currentNode;
            }

            for (T child: successors.apply(currentState)) {
                if (explored.contains(child)) {
                    // skip children we already explored
                    continue;
                }
                explored.add(child);
                frontier.add(new Node<>(child, currentNode));
            }
        }

        // went through everything and never found goal
        return null;
    }

    public static <T> List<T> nodeToPath(Node<T> node) {
        List<T> path = new ArrayList<>();
        path.add(node.state);
        while (node.parent != null) {
            node = node.parent;
            path.addFirst(node.state);
        }
        return path;
    }
}
