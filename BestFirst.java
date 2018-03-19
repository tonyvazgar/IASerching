import java.util.Vector;

public abstract class BestFirst extends Search {

    public static Result search(Situation initialState, Situation finalState){
        Vector<Node> route;
        Node root, goal;

        root = new Node();
        root.setState(initialState);
        goal = new Node();
        goal.setState(finalState);
        memory = new Vector<Situation>();
        found = false;
        result = new Result();

        if(root != null){
            if(!nodeIsFinalState(root,goal)){
                result = bestFirst(root, goal);
            }else{
                result.setFound(true);
                route = new Vector<Node>();
                route.add(root);
                result.setPlan(route);
                found = true;
            }
        }//End if(root != null)
        System.out.println("MOVES: " + (result.getPlan().size()-1));
        System.out.println("EXPANTIONS: " + Node.numberOfExpantions);
        return result;
    }

    private static Result bestFirst(Node node, Node goal) {
        Node nextChild;
        int i;
        Vector<Node> route;

        if(!found){
            node.expand();
            memory.add(node.getState());
            node.sortChildren();
            if(node.getChildren() != null){
                i = 0;
                while ((i < node.getChildren().size()) && !found){
                    nextChild = node.getChildren().get(i);
                    if(nodeIsFinalState(nextChild, goal)){
                        found = true;
                        foundNode = nextChild;
                    }else if(!memory.contains(nextChild.getState())){
                        result = bestFirst(nextChild, goal);
                    }
                    i = i + 1;
                }//End while
            }//End if(node.getChildren() != null)
        }//end if !found
        if(found){
            result.setFound(true);
            route = foundNode.getRoute();
            result.setPlan(route);
        }else{
            result.setFound(false);
            result.setPlan(null);
        }
        return result;
    }//End bestFirst
}
