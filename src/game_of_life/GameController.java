package game_of_life;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class GameController {

    @FXML
    private Button next_step_btn;

    @FXML
    private Canvas draw_canvas;
    private int[][] matrix;
    private final int num_of_rows=10, num_of_columns=10;
    private double canvas_height, canvas_width, cell_height, cell_width;
    private GraphicsContext gc;
    
    //This function runs the program
    @FXML
    public void initialize() {
    	gc=draw_canvas.getGraphicsContext2D();
    	this.matrix=new int [num_of_rows][num_of_columns];
        this.canvas_height=this.draw_canvas.getHeight();
        this.canvas_width=this.draw_canvas.getWidth();
        this.cell_height=this.canvas_height/num_of_columns;
        this.cell_width=this.canvas_width/num_of_rows;
        initalize_matrix(matrix);
        draw_matrix(matrix);        
    }

    //Initalize the matrix with random values
    //0 value = dead, 1 value = alive
    @FXML
    private void initalize_matrix (int[][] matrix) {
        int i,j;
        Random r = new Random();
        for (i=0; i<num_of_rows;i++)
            for (j=0;j<num_of_columns;j++)
                matrix[i][j]=r.nextInt(2);
    }
    
    //Draw a random initial matrix
    private void draw_matrix (int [][]  matrix) {
        int i,j;
        for (i=0; i<num_of_rows;i++)
            for (j=0;j<num_of_columns;j++) {
                if (matrix[i][j]==1)
                    gc.fillRect(j*cell_height,i*cell_width,cell_height,cell_width);
                else
                    gc.clearRect(j*cell_height,i*cell_width,cell_height,cell_width);
            }    
    }

    //Count the number of the alive neighbors of the current cell
    private int count_neighbors (int[][] matrix, int i, int j) {
        int count=0;
        switch(i) {
            case 0:
                switch(j) {
                    case 0:
                       count+=matrix[i+1][j]+matrix[i+1][j+1]+matrix[i][j+1];
                       break;
                   case num_of_columns-1:
                        count+=matrix[i][j-1]+matrix[i+1][j-1]+matrix[i+1][j];
                        break;
                    default:
                        count+=matrix[i][j-1]+matrix[i+1][j-1]+matrix[i+1][j]+matrix[i+1][j+1]+matrix[i][j+1];
                }
            break;
            case num_of_rows-1:
                switch(j) {
                    case 0:
                        count+=matrix[i-1][j]+matrix[i-1][j+1]+matrix[i][j+1];
                        break;
                    case num_of_columns-1:
                        count+=matrix[i][j-1]+matrix[i-1][j-1]+matrix[i-1][j];
                        break;
                    default:
                        count+=matrix[i][j-1]+matrix[i-1][j-1]+matrix[i-1][j]+matrix[i-1][j+1]+matrix[i][j+1];
                }
                break;
            default:
            switch(j) {
                case 0:
                    count+=matrix[i-1][j]+matrix[i-1][j+1]+matrix[i][j+1]+matrix[i+1][j+1]+matrix[i+1][j];
                    break;
                case num_of_columns-1:
                    count+=matrix[i-1][j]+matrix[i-1][j-1]+matrix[i][j-1]+matrix[i+1][j-1]+matrix[i+1][j];
                    break;
                default:
                    count+=matrix[i-1][j]+matrix[i-1][j-1]+matrix[i][j-1]+matrix[i+1][j-1]+matrix[i+1][j]+matrix[i+1][j+1]+matrix[i][j+1]+matrix[i-1][j+1];
            }
            break;
            }

        return count;
    }

    //The update button is pressed
    @FXML
    void next_step_btn_pressed(ActionEvent event) {
        int [][] neighbors_matrix;
        neighbors_matrix=new int [num_of_rows][num_of_columns];
        int i,j;
        //Count the amount of neighbors for every element
        for (i=0; i<num_of_rows;i++)
            for (j=0;j<num_of_columns;j++) {
                neighbors_matrix[i][j]=count_neighbors(this.matrix, i, j);
            }
        //Update the matrix
        for (i=0; i<num_of_rows;i++)
            for (j=0;j<num_of_columns;j++) {
                if((this.matrix[i][j]==0)&&(neighbors_matrix[i][j]==3))
                    this.matrix[i][j]=1;
                if((this.matrix[i][j]==1)&&((neighbors_matrix[i][j]==0)||(neighbors_matrix[i][j]==1)||(neighbors_matrix[i][j]==4)))
                    this.matrix[i][j]=0;
            }
        //Draw the matrix again
        draw_matrix(matrix);
    }
}
