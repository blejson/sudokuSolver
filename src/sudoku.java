public class sudoku {
    public static void main(String[] args){
    board board1 = new board();
    boolean done = true;
    while(done){
        done=board1.insert();
        if(done) board1.rowsInsert();
        else done=board1.rowsInsert();
        if(done) board1.boxInsert();
        else done=board1.boxInsert();
        if(done) board1.eliminatePossible();
        else done=board1.eliminatePossible();
        if(done) board1.compareDoubles();
        else done=board1.compareDoubles();
        if(done) board1.compareTriples();
        else done=board1.compareTriples();
        if(done) board1.checkBox();
        else done=board1.checkBox();
        if(done) board1.rowDoubles();
        else done=board1.rowDoubles();
    }
    board1.print();
    }
}
