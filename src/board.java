import java.util.ArrayList;

public class board {
    public int[][] boardValues=                 //2d array of elements at specific position
            {       {8, 6, 0, 0, 0, 0, 0, 7, 0},
                    {0, 9, 0, 1, 0, 0, 0, 0, 0},
                    {0, 4, 5, 0, 0, 0, 2, 0, 0},
                    {0, 0, 1, 0, 3, 0, 4, 0, 7},
                    {0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {6, 0, 9, 0, 7, 0, 1, 0, 0},
                    {0, 0, 6, 0, 0, 0, 9, 8, 0},
                    {0, 0, 0, 0, 0, 4, 0, 1, 0},
                    {0, 5, 0, 0, 0, 0, 0, 6, 2}};
    public ArrayList<Integer>[][]  values = new ArrayList[9][9]; //2d ArrayList of possible values to be inserted in that place
    public board(){
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                values[i][j] = new ArrayList();
                if(boardValues[i][j]==0){
                for (int n=1; n<10; n++){
                    values[i][j].add(n);
                }}
            }
        }
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(boardValues[i][j]!=0) this.clearBox(i, j, boardValues[i][j]);
            }
        }
    }
    public void print(){
        for(int i=0; i<9; i++){
            System.out.println(boardValues[i][0]+" "+boardValues[i][1]+" "+boardValues[i][2]+" "+boardValues[i][3]+" "+boardValues[i][4]+" "+boardValues[i][5]+" "+boardValues[i][6]+" "+boardValues[i][7]+" "+boardValues[i][8]);
        }
    }
    public boolean insert(){ //checks every element of possible elements (array values) in boardValues, if there is only 1 element - inserts it
        boolean ret = false; //returns true if any element changed
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if(values[i][j].size()==1){
                    boardValues[i][j] = values[i][j].get(0);
                    this.clearBox(i, j, values[i][j].get(0));
                    values[i][j].clear();
                    ret=true;
                }
            }
        }

        return ret;
    }
    public void clearBox(int x, int y, int n){
        for (int i=0; i<9; i++){
            if(values[i][y].size()>0){
                for (int j=0; j<values[i][y].size(); j++){
                    if(values[i][y].get(j)==n){
                        values[i][y].remove(j);
                        break;
                    }
                }
            }
        }
        for (int i=0; i<9; i++){
            if(values[x][i].size()>0){
                for (int j=0; j<values[x][i].size(); j++){
                    if(values[x][i].get(j)==n){
                        values[x][i].remove(j);
                        break;
                    }
                }
            }
        }
        int i = x/3;
        int j = y/3;
        for (int b = i * 3; b < (i + 1) * 3; b++) {
            for (int h = j * 3; h < (j + 1) * 3; h++) {
                if(values[b][h].size()>0){
                    for (int k=0; k<values[b][h].size(); k++){
                        if(values[b][h].get(k)==n){
                            values[b][h].remove(k);
                            break;
                        }
                    }
                }
            }
        }

    }
    public boolean rowsInsert(){
        boolean ret = false;
        for (int i=0; i<9; i++){ // i - nb of row
            int x=0;  //x, y - coordinates of box to insert, n - value
            int y=0;
            int n =0;
            for (int j=1; j<10; j++){ //j - number currently checking
                boolean only=true;
                n=0;
                for (int k=0; k<9; k++){ //which box in a row
                    for(int v=0; v<values[i][k].size(); v++){
                        if(values[i][k].get(v)==j){
                            if(n>0) only=false;
                            x=i;
                            y=k;
                            n=j;
                        }
                    }
                    if(boardValues[i][k]==j) only=false;
                }
                if(only&&n>0){
                    values[x][y].clear();
                    boardValues[x][y]=n;
                    this.clearBox(x, y, n);
                    ret=true;
                }
            }

        }
        for (int i=0; i<9; i++){ // i - nb of row
            int x=0;  //x, y - coordinates of box to insert, n - value
            int y=0;
            int n =0;
            for (int j=1; j<10; j++){ //j - number currently checking
                boolean only=true;
                n=0;
                for (int k=0; k<9; k++){ //which box in a row
                    for(int v=0; v<values[k][i].size(); v++){
                        if(values[k][i].get(v)==j){
                            if(n>0) only=false;
                            x=k;
                            y=i;
                            n=j;
                        }
                    }
                    if(boardValues[k][i]==j) only=false;
                }
                if(only&&n>0){
                    values[x][y].clear();
                    boardValues[x][y]=n;
                    this.clearBox(x, y, n);
                    ret=true;
                }
            }

        }
        return ret;
    }
    public boolean boxInsert() {
        boolean ret = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int n = 1; n < 10; n++) {
                    int x = 0, y = 0, v = 0;
                    boolean only = true;
                    for (int b = i * 3; b < (i + 1) * 3; b++) {
                        for (int h = j * 3; h < (j + 1) * 3; h++) {
                            for (int va = 0; va < values[b][h].size(); va++) {
                                if (values[b][h].get(va) == n) {
                                    if (v > 0) only = false;
                                    x = b;
                                    y = h;
                                    v = n;
                                }
                            }
                            if (boardValues[b][h] == n) only = false;
                        }
                    }
                    if (only && v > 0) {
                        values[x][y].clear();
                        boardValues[x][y] = v;
                        this.clearBox(x, y, v);
                        ret = true;
                    }
                }
            }
        }
        return ret;
    }
    public boolean eliminatePossible(){
        boolean ret = false;
        for(int i=0; i<3; i++){
            for(int j = 0; j<3; j++){
                for(int k=1; k<10; k++){
                    boolean only=true;
                    int x=-1, v=0;
                    for (int b = 0; b < 3; b++) {
                        for (int h = 0; h < 3; h++) {
                            if(values[i*3+b][j*3+h].size()>0){
                                for(int n=0; n<values[i*3+b][j*3+h].size();n++){
                                    if(values[i*3+b][j*3+h].get(n)==k){
                                        if(x>=0 && x!=i*3+b) only=false;
                                        x=i*3+b;
                                        v=k;
                                    }
                                }
                            }
                        }
                    }
                    if(only&&x>=0){
                        for(int a=0; a<3; a++){
                            if(j!=a){
                                for(int b=0; b<3; b++){
                                    for(int n=0; n<values[x][a*3+b].size();n++){
                                        if(values[x][a*3+b].get(n)==v){
                                           values[x][a*3+b].remove(n);
                                           ret=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i=0; i<3; i++){
            for(int j = 0; j<3; j++){
                for(int k=1; k<10; k++){
                    boolean only=true;
                    int x=-1, v=0;
                    for (int b = 0; b < 3; b++) {
                        for (int h = 0; h < 3; h++) {
                            if(values[i*3+b][j*3+h].size()>0){
                                for(int n=0; n<values[i*3+b][j*3+h].size();n++){
                                    if(values[i*3+b][j*3+h].get(n)==k){
                                        if(x>=0 && x!=j*3+h) only=false;
                                        x=j*3+h;
                                        v=k;
                                    }
                                }
                            }
                        }
                    }
                    if(only&&x>=0){
                        for(int a=0; a<3; a++){
                            if(i!=a){
                                for(int b=0; b<3; b++){
                                    for(int n=0; n<values[a*3+b][x].size();n++){
                                        if(values[a*3+b][x].get(n)==v){
                                            values[a*3+b][x].remove(n);
                                            ret=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    public boolean compareDoubles(){
        boolean ret=false;
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                boolean first=true;
                int x1=-1, x2=-1, y1=-1, y2=-1, a=0, b=0;
                for(int c=0; c<3; c++){
                    for (int d=0; d<3; d++){
                        if(values[i*3+c][j*3+d].size()==2){
                            if(first){
                                x1=c;
                                y1=d;
                                a=values[i*3+c][j*3+d].get(0);
                                b=values[i*3+c][j*3+d].get(1);
                                first=false;
                            }
                            else{
                                if(a==values[i*3+c][j*3+d].get(0) && b==values[i*3+c][j*3+d].get(1)) {
                                    x2 = c;
                                    y2 = d;
                                }
                            }
                        }
                    }
                }
                if(x1>=0&&y1>=0&&x2>=0&&y2>=0&&a>0&&b>0){
                    for(int c=0;c<3;c++){
                        for(int d=0; d<3; d++){
                            if((c==x1&&d==y1) || (c==x2&&d==y2) || a==0 || b==0){}
                            else {
                                for(int z=0; z<2; z++){
                                    for(int n=0; n<values[i*3+c][j*3+d].size(); n++){
                                        if(values[i*3+c][j*3+d].get(n)==a||values[i*3+c][j*3+d].get(n)==b){
                                            values[i*3+c][j*3+d].remove(n);
                                            ret=true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    public boolean compareTriples(){
        boolean ret=false;
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                int first=0;
                int x1=-1, x2=-1, y1=-1, y2=-1, x3=-1, y3=-1, a=0, b=0, v=0;
                for(int c=0; c<3; c++){
                    for (int d=0; d<3; d++){
                        if(values[i*3+c][j*3+d].size()==3){
                            if(first==0){
                                x1=c;
                                y1=d;
                                a=values[i*3+c][j*3+d].get(0);
                                b=values[i*3+c][j*3+d].get(1);
                                v=values[i*3+c][j*3+d].get(2);
                                first=1;
                            }
                            else{
                                if(first==1){
                                    if(a==values[i*3+c][j*3+d].get(0) && b==values[i*3+c][j*3+d].get(1)&&v==values[i*3+c][j*3+d].get(2)) {
                                        x2 = c;
                                        y2 = d;
                                    }
                                }
                                else{
                                    if(a==values[i*3+c][j*3+d].get(0) && b==values[i*3+c][j*3+d].get(1)&&v==values[i*3+c][j*3+d].get(2)) {
                                        x3 = c;
                                        y3 = d;
                                    }
                                }
                            }
                        }
                    }
                }
                if(x1>=0&&y1>=0&&x2>=0&&y2>=0&&a>00&&b>0&&v>0&&x3>=0&&y3>=0){
                    for(int c=0;c<3;c++){
                        for(int d=0; d<3; d++){
                            if((c==x1&&d==y1) || (c==x2&&d==y2) || (c==x3&&d==y3) || a==0 || b==0 || v==0){}
                            else {
                                for(int z=0; z<3; z++){
                                    for(int n=0; n<values[i*3+c][j*3+d].size(); n++){
                                        if(values[i*3+c][j*3+d].get(n)==a||values[i*3+c][j*3+d].get(n)==b||values[i*3+c][j*3+d].get(n)==v){
                                            values[i*3+c][j*3+d].remove(n);
                                            ret=true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    public boolean checkBox(){
        boolean ret=false;
        for (int i=0; i<9; i++){
            for(int v=1; v<10; v++) {
                boolean only=true;
                int r=-1;
                for (int j = 0; j < 3; j++) {
                    for (int n = 0; n < 3; n++) {
                        for(int z=0; z<values[i][j*3+n].size(); z++){
                            if(values[i][j*3+n].get(z)==v){
                                if(r>=0&&r!=j) only=false;
                                r=j;
                            }
                        }
                    }
                }
                if(only&&r>=0){
                    for(int a=0; a<3; a++){
                        for(int b=0; b<3; b++){
                            int w=i/3;
                            if(w*3+a!=i){
                                for(int z=0; z<values[w*3+a][r*3+b].size(); z++){
                                    if(values[w*3+a][r*3+b].get(z)==v){ values[w*3+a][r*3+b].remove(z);
                                    ret=true;}
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i=0; i<9; i++){
            for(int v=1; v<10; v++) {
                boolean only=true;
                int r=-1;
                for (int j = 0; j < 3; j++) {
                    for (int n = 0; n < 3; n++) {
                        for(int z=0; z<values[j*3+n][i].size(); z++){
                            if(values[j*3+n][i].get(z)==v){
                                if(r>=0&&r!=j) only=false;
                                r=j;
                            }
                        }
                    }
                }
                if(only&&r>=0){
                    for(int a=0; a<3; a++){
                        for(int b=0; b<3; b++){
                            int w=i/3;
                            if(w*3+a!=i){
                                for(int z=0; z<values[r*3+b][w*3+a].size(); z++){
                                    if(values[r*3+b][w*3+a].get(z)==v){ values[r*3+b][w*3+a].remove(z);
                                        ret=true;}
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
    public boolean rowDoubles(){
        boolean ret=false;
        for (int i=0; i<9; i++){
            boolean first=true;
            for(int n=1; n<10; n++){
                int x1=-1, x2=-1, y1=-1, y2=-2, a=0, b=0;
                for(int j=0; j<9; j++){
                        if(values[i][j].size()==2){
                            if(first){
                                x1=i;
                                y1=j;
                                a=values[i][j].get(0);
                                b=values[i][j].get(1);
                                first=false;
                            }
                            else{
                                if(a==values[i][j].get(0) && b==values[i][j].get(1)){
                                    x2=i;
                                    y2=j;
                                }
                            }
                        }

                }
                if(x1>=0&&y1>=0&&x2>=0&&y2>=0&&a>0&&b>0){
                    for(int j=0; j<9; j++){
                        if((i==x1&&j==y1) || (i==x2&&j==y2) || a==0 || b==0){}
                        else{
                            for(int z=0; z<2; z++){
                                for(int k=0; k<values[i][j].size(); k++){
                                    if(values[i][j].get(n)==a||values[i][j].get(n)==b){
                                        values[i][j].remove(n);
                                        ret=true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i=0; i<9; i++){
            boolean first=true;
            for(int n=1; n<10; n++){
                int x1=-1, x2=-1, y1=-1, y2=-2, a=0, b=0;
                for(int j=0; j<9; j++){
                    if(values[j][i].size()==2){
                        if(first){
                            x1=j;
                            y1=i;
                            a=values[j][i].get(0);
                            b=values[j][i].get(1);
                            first=false;
                        }
                        else{
                            if(a==values[j][i].get(0) && b==values[j][i].get(1)){
                                x2=j;
                                y2=i;
                            }
                        }
                    }

                }
                if(x1>=0&&y1>=0&&x2>=0&&y2>=0&&a>0&&b>0){
                    for(int j=0; j<9; j++){
                        if((j==x1&&i==y1) || (j==x2&&i==y2) || a==0 || b==0){}
                        else{
                            for(int z=0; z<2; z++){
                                for(int k=0; k<values[j][i].size(); k++){
                                    if(values[j][i].get(n)==a||values[j][i].get(n)==b){
                                        values[j][i].remove(n);
                                        ret=true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
