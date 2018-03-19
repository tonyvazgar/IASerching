import java.util.Vector;

public class Result {

    private boolean found;
    private Vector<Node> plan;

    public Result(){
        found = false;
        plan = null;
    }

    public void setFound(boolean hasBeenFound){
        found = hasBeenFound;
    }

    public void setPlan(Vector<Node> aPlan){
        plan = aPlan;
    }

    public Vector<Node> getPlan(){
        return plan;
    }

}
