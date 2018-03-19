import java.util.Vector;

public abstract class BreadthFirst extends Search{

    private static Result breadthFirst(Vector<Node> aGeneration, Node goal){
       int i, j;
       Node node;
       Vector<Node> nextGeneration;
       Node nextChild;
       Vector<Node> route;

       if(!found){
           if(aGeneration != null){
               nextGeneration = new Vector<Node>();
               i = 0;
               while (i < aGeneration.size() && (!found)){
                   node = aGeneration.get(i);
                   node.expand();
                   memory.add(node.getState());
                   if(node.getChildren() != null){
                       j = 0;
                       while (j < node.getChildren().size() && !found){
                           nextChild = node.getChildren().get(j);
                           if(!memory.contains(nextChild.getState())){
                               if(nodeIsFinalState(nextChild, goal)){
                                   found = true;
                                   foundNode = nextChild;
                               }
                               nextGeneration.add(nextChild);
                           }
                           j = j + 1;
                       }//End while j
                   }
                   i = i + 1;
               }//End while i
               if(nextGeneration.size() > 0){
                   breadthFirst(nextGeneration, goal);
               }
               if(found){
                   result.setFound(true);
                   route = foundNode.getRoute();
                   result.setPlan(route);
               }else{
                   result.setFound(false);
                   result.setPlan(null);
               }
           }
       }
       return result;
    }

    public static Result search(Situation initialState, Situation finalState){

        Vector<Node> route;
        Vector<Node> founderGeneration;
        Node root;
        Node goal;

        root = new Node();  //Nodo del arbol de busqueda tiene una situtation
        root.setState(initialState);
        goal = new Node();  //
        goal.setState(finalState);

        memory = new Vector<Situation>();
        found = false;
        result = new Result();

        if(!nodeIsFinalState(root, goal)){  //Si no es el edo final
            founderGeneration = new Vector<Node>();
            founderGeneration.add(root);
            result = breadthFirst(founderGeneration, goal);
        }else{
            result.setFound(true);
            route = new Vector<Node>();
            route.add(root);
            result.setPlan(route);
        }
        return result;
    }

}
