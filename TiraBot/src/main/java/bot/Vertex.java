package bot;

import dto.GameState;

import java.util.Comparator;
import java.util.List;


public class Vertex {
    private final GameState.Position position;
    private final List<Vertex> adjacentVertices;


    public Vertex(GameState.Position position, List<Vertex> adjacentVertices) {
        this.position = position;
        this.adjacentVertices = adjacentVertices;
    }

    public GameState.Position getPosition() {
        return position;
    }

    public List<Vertex> getAdjacentVertices() {
        return adjacentVertices;
    }

}
