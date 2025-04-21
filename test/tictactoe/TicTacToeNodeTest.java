package tictactoe;

import core.State;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;


public class TicTacToeNodeTest {

    @Test
    public void winsAndPlayouts() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState(Position.parsePosition("X . 0\nX O .\nX . 0", TicTacToe.X));
        TicTacToeNode node = new TicTacToeNode(state);
        assertTrue(node.isLeaf());
        assertEquals(2, node.wins());
        assertEquals(1, node.playouts());
    }

    @Test
    public void state() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertEquals(state, node.state());
    }

    @Test
    public void white() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertTrue(node.white());
    }

    @Test
    public void children() {
        TicTacToe.TicTacToeState state = new TicTacToe().new TicTacToeState();
        TicTacToeNode node = new TicTacToeNode(state);
        assertNotNull(node.children());
        assertEquals(0, node.children().size()); // Should be empty initially
    }

    @Test
    public void addChild() {
        TicTacToe game = new TicTacToe();
        State<TicTacToe> state = game.start();
        TicTacToeNode node = new TicTacToeNode(state);

        for (var move : state.moves(state.player())) {
            State<TicTacToe> childState = state.next(move);
            node.addChild(childState);
        }

        Collection<?> children = node.children();
        assertFalse(children.isEmpty());
    }

    @Test
    public void backPropagate() {
        TicTacToe game = new TicTacToe();
        State<TicTacToe> state = game.start();
        TicTacToeNode node = new TicTacToeNode(state);

        for (var move : state.moves(state.player())) {
            State<TicTacToe> childState = state.next(move);
            TicTacToeNode childNode = new TicTacToeNode(childState);
            childNode.setWins(2);      // Simulate 1 win
            childNode.setPlayouts(1);  // Simulate 1 playout
            node.children().add(childNode);
        }

        node.backPropagate();
        assertEquals(node.children().size(), node.playouts());
        assertEquals(2 * node.children().size(), node.wins());
    }
}
