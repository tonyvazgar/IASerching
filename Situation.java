public class Situation {

    int matrix [][];

    public Situation(){ matrix = new int[3][3]; }

    public void setMatrix(int [][] aMatrix){
        matrix = aMatrix;
    }


    public boolean equals(Object anObject){

        int i, j;
        boolean areEqual;
        Situation anotherState = (Situation) anObject;
        areEqual = true;
        i = 0;

        while (i < 3)           //Renglones
        {
            j = 0;
            while (j < 3)      //Columnas
            {
                if(matrix[i][j] != anotherState.matrix[i][j])
                {
                    areEqual = false;
                }
                j = j + 1;
            }
            i = i + 1;
        }
        return areEqual;
    }


    @Override
    public String toString() {
        String string;

        string = "\n";

        string = string + matrix[0][0] + " ";
        string = string + matrix[0][1] + " ";
        string = string + matrix[0][2] + "\n";
        string = string + matrix[1][0] + " ";
        string = string + matrix[1][1] + " ";
        string = string + matrix[1][2] + "\n";
        string = string + matrix[2][0] + " ";
        string = string + matrix[2][1] + " ";
        string = string + matrix[2][2] + "\n" + "\n";

        return string;
    }

    public int heuristicFunction() {
        int value = manhattanDistance();
        return value;
    }

    private int manhattanDistance() {

        int manhattanDistance;
        int i,j;
        int token;
        int targetX;
        int targetY;
        int differenceX;
        int differenceY;

        manhattanDistance = 0;
        i = 0;
        while (i < 3){
            j = 0;
            while (j < 3){
                token = matrix[i][j];
                if(token != 0){
                    //We dont compute distance for element 0
                    targetX = (token - 1) / 3;  //expected x-coordinate (row)
                    targetY = (token - 1) % 3;  //expected y-coordinate (col)
                    differenceX = i - targetX;  //x-distance to expected coordinate
                    differenceY = j - targetY;  //y-distance to expected coordinate
                    manhattanDistance = manhattanDistance + Math.abs(differenceX) + Math.abs(differenceY);
                }
                j = j + 1;
            }
            i = i + 1;
        }
        return manhattanDistance;
    }

}
