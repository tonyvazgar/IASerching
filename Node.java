import java.util.Collection;
import java.util.Random;
import java.util.Vector;
import java.util.Collections;

public class Node implements Comparable{
    private Node parent;
    private Vector <Node> children;
    private Situation state;
    int heuristicValue;

    static int numberOfExpantions = 0;
    static Random random = new Random(System.currentTimeMillis());

    public Node() {
        children = null;
    }

    public void setState(Situation aState){
        state = aState;
        heuristicValue = state.heuristicFunction(); //Vamos a aplicar la funcion heuristica cada vez que que a un nodo le pongamos un estado
    }

    public Situation getState() {
        return state;
    }

    public void setParent(Node aNode){
        parent = aNode;
    }

    public Node getParent(){
        return parent;
    }

    public Vector<Node> getChildren() {
        return children;
    }

    public void expand(){
        int i;
        numberOfExpantions = numberOfExpantions + 1;
        children = Knowledge.getNextPossibleNodes(state);   //Lo expande
        if(children != null){
            for (Node aChild: children){
                aChild.setParent(this);
            }
        }
    }

    @Override
    public int compareTo(Object anObject) {

        Node aNode = (Node) anObject;

        int comparation = 0;
        if(heuristicValue < aNode.heuristicValue)
        {
            comparation = -1;
        }else{
            if(heuristicValue > aNode.heuristicValue)
            {
                comparation = 1;
            }
        }
        return comparation;
    }

    public void sortChildren(){
        Collections.sort(children);
        children = changeOrderIfTwoBestChilds(children);
    }

    private Vector<Node> changeOrderIfTwoBestChilds(Vector<Node> orderedChildren) {
        Node aux;
        if(orderedChildren.size() >= 2)
        {
            if(orderedChildren.get(0).heuristicValue == (orderedChildren.get(1).heuristicValue))
            {
                if(random.nextInt(100) > 50)
                {
                    //Swap 0 <-> 1
                    aux = orderedChildren.get(0);
                    orderedChildren.setElementAt(orderedChildren.get(1), 0);
                    orderedChildren.setElementAt(aux, 1);
                }
            }
        }
        return orderedChildren;
    }

    public Vector<Node> getRoute(){
        //Se recorre toda una rama
        Vector<Node> route;
        Node node;

        route = new Vector<Node>();
        route.add(this);
        node = getParent();

        while (node != null)
        {
            route.add(node);
            node = node.getParent();
        }
        Collections.reverse(route);
        //Para empezar con el primero y seguir con la secuencia
        return route;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
