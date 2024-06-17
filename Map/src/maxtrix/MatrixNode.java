/*
 * Author:       Thái Quang Tính
 * Created on    17-06-2024
 * GitHub:       https://github.com/notrealqt
 * Email:        tinhthai2000@gmail.com
 * Description:  [Brief description of the file or project]
*/


package matrix;

import model.Node;

public class MatrixNode extends Node{
    private boolean enabled = true;

    private int row;
    private int col;

    public boolean isEnabled(){
        return enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public int getRow(){
        return row;
    }
    public void setRow(int row){
        this.row = row;
    }

    public int getCol(){
        return col;
    }
    public void setCol(int col){
        this.col = col;
    }
}
