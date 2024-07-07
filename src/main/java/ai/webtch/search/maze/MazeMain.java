package ai.webtch.search.maze;

import ai.webtch.search.generic.GenericSearch;
import ai.webtch.search.generic.GenericSearch.Node;

import java.util.List;

public class MazeMain {
    public static void main(String[] args) {

        Maze m = new Maze();

        System.out.println("Unsolved maze:");
        System.out.println(System.lineSeparator());
        System.out.println(m);
        System.out.println(System.lineSeparator());

        Node<Maze.MazeLocation> dfsSolution = GenericSearch.dfs(m.start, m::goalTest, m::successors);

        if (dfsSolution == null) {
            System.out.println("No solution found using depth-first search.");
        } else {
            List<Maze.MazeLocation> dfsPath = GenericSearch.nodeToPath(dfsSolution);
            m.mark(dfsPath);
            System.out.println("Depth-first search solved maze:");
            System.out.println(System.lineSeparator());
            System.out.println(m);
            System.out.println(System.lineSeparator());
            m.clear(dfsPath);
        }

        Node<Maze.MazeLocation> bfsSolution = GenericSearch.bfs(m.start, m::goalTest, m::successors);

        if (bfsSolution == null) {
            System.out.println("No solution found using breadth-first search.");
        } else {
            List<Maze.MazeLocation> bfsPath = GenericSearch.nodeToPath(bfsSolution);
            m.mark(bfsPath);
            System.out.println("Breadth-first search solved maze:");
            System.out.println(System.lineSeparator());
            System.out.println(m);
            System.out.println(System.lineSeparator());
            m.clear(bfsPath);
        }



    }
}
