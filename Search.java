import java.util.Vector;

public abstract class Search {
    public static Node foundNode;
    public static boolean found;
    public static Result result;
    public static Vector <Situation> memory;

    public static boolean nodeIsFinalState(Node aNode, Node goal){

        //Prueba para ver si es el estado final o no
        boolean isFinalState = false;

        if(aNode.getState().equals(goal.getState()))
        {
            isFinalState = true;
        }
        return isFinalState;
    }

}
