package brains;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;
import edu.unlam.snake.engine.Point;

public class MyBrain extends Brain {

	public MyBrain() {
		super("168 | 11C | 11E");
	}

	public Direction getDirection(Point head, Direction previous) {
		List<Point> fruits = getInfo().getFruits();
		List<List<Point>> snakes = getInfo().getSnakes();
		List<Point> obstacles = getInfo().getObstacles();
		
		return decidirMovimiento(head, previous, obstacles);
	}
	
	public Direction decidirMovimiento(Point head, Direction previous, List<Point> obstacles) {
		//Si ya me vengo moviendo hacia PREVIOUS direccion
		Direction dir = previous;			
		
		
			
		switch(previous) {
		
		case LEFT: if (obstacles.contains(new Point(head.getX()-1, head.getY()))) {
			//Chequeo si hay colision arriba...
			
			if (obstacles.contains(new Point(head.getX()-1, head.getY()-1)))			
				dir = Direction.DOWN;				
			
			else 			
				dir = Direction.UP;
		}		
		
		case RIGHT: if (obstacles.contains(new Point(head.getX()+1, head.getY()))) { 
			if (obstacles.contains(new Point(head.getX()+1, head.getY()-1)))
				dir = Direction.DOWN;				
			else 
				dir = Direction.UP;
		}
		
		case DOWN: if (obstacles.contains(new Point(head.getX(), head.getY()+1))) {
			if (obstacles.contains(new Point(head.getX()-1, head.getY()+1)))
				dir = Direction.RIGHT;				
			else 
				dir = Direction.LEFT;
		}
		
		case UP: if (obstacles.contains(new Point(head.getX(), head.getY()-1))) {
			//
			if (obstacles.contains(new Point(head.getX()-1, head.getY()-1)))
				dir = Direction.RIGHT;				
			else 
				dir = Direction.LEFT;
			
		}

		default:
			break;				
		}		
		
		return dir;
	}	
}
