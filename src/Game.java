import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author vtan29
 */

// grid (LinearNode<T> array)
// board (LinkedGrid<Character>)
public class Game {
       private LinkedGrid<Character> board;  
       private LinkedGrid<GUICell> cells; 
       public static int width,height;
       private boolean isPlaying;
       GUI gui;
       public Game(int width, int height, boolean fixedRandom, int seed){
        this.width=width;
        this.height=height;
        board = new LinkedGrid<Character>(width, height);

        //intializing board with '_'
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                board.setElement(j, i, '_');
                     
             }
        }
        
          cells = new LinkedGrid<GUICell>(width, height);
         for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                cells.setElement(j, i, new GUICell(i,j)); 
            }
         }
         BombRandomizer.placeBombs(board, fixedRandom, seed);
         
         determineNumbers();
         isPlaying=true;
        gui = new GUI(this, cells);

       }
       
       //2nd Constructor
       public Game(LinkedGrid<Character> board)
       {
           this.board=board;
           width=board.getWidth();
           height=board.getHeight();
          cells = new LinkedGrid<GUICell>(board.getHeight(), board.getWidth());
         for(int i=0;i<board.getHeight();i++){
            for(int j=0;j<board.getWidth();j++){
                cells.setElement(j, i, new GUICell(i,j)); 
            }
         }
        
         determineNumbers();
         isPlaying=true;
       

           gui = new GUI(this, cells);
                  

  
       }
       
       
       
        public int getWidth()
       {         
            return width;     
       } 
              
       
       public int getHeight()
       {         
                    return height;     
       } 
       
        public LinkedGrid  getCells()
       {         
                return cells;     
       } 
        
          //col , row : representing the cell you want to find neighbors to 
        public  void  determineNumbers()
        {
        
 
            for(int i=0;i<cells.getHeight();i++){
            for(int j=0;j<cells.getWidth();j++){
                int numOfBoombs = checkBoomb(i,j);
               GUICell cell= cells.getElement(i, j);
                   cell.setNumber(numOfBoombs);
                   
            }
            
         }
          
        }
        
     //helping function for  determineNumbers()
     // return int   
    // Function to return the number of boombs 
    // around a cell
    //  for a cell (board,i, j)
   private int checkBoomb(int i, int j)
    {
        int num=0;
         
              int[] ith = { 0, 1, 1, -1, 0, -1 ,-1, 1};
    int[] jth = { 1, 0, 1, 0, -1, -1 ,1,-1};
     // All neighbours of cell
     for (int k = 0; k < 8; k++) {
            if (isValid(i + ith[k], j + jth[k],cells.getWidth())) {
                if(board.getElement(i + ith[k], j + jth[k]) == 'x' )
                {
                   cells.getElement(i + ith[k], j + jth[k]).setNumber(-1);
                    num++;
                }
            }
        }
    
        return num;
    }
   private boolean isValid(int i, int j, int l) {
        if(i < 0 || j < 0 || i >= l || j >= l)
            return false;
        return true;
}
   
  public int  processClick(int i,int j)
  {
      
       GUICell cell= cells.getElement(i, j);
       int noReveledCells=cell.getNumber();
       if(!isPlaying)
           return -10;
       if(noReveledCells==-1)
       {
         cell.setBackground(Color.red);
         cell.reveal();
         isPlaying=false;
       }
       else if(noReveledCells==0){  
           noReveledCells=recClear(i,j);
       }
       else if(noReveledCells>0 && noReveledCells<9){
           if(cell.isRevealed() ){
               return 0;
           }
           else
           {  
           cell.reveal();
           cell.setBackground(Color.white);
           
           return 1;

           }
       }
      return noReveledCells;
  }
 private int  recClear(int c, int r)
 {
     GUICell cell=null;
if (c<0 || c>=width || r<0 || r>=height)
     return 0;
else
  cell= cells.getElement(c, r);

 if(cell.isRevealed())
     return 0;
 if(cell.getNumber()==-1)
     return 0;
 else if(cell.getNumber()>0){
     cell.reveal();
     if(gui!=null)
            cell.setBackground(Color.white);
     return 1;
 }
 else
 {
    cell.reveal();
     if(gui!=null)
            cell.setBackground(Color.white);
     int result=1;
 //  result += recClear(c-1, r) +recClear(c+1, r)+recClear(c, r-1)+recClear(c-1, r-1)+ recClear(c+1, r-1)+recClear(c, r+1)+recClear(c+1, r+1)+recClear(c-1, r+1);
     result += recClear(c-1, r);
     result += recClear(c+1, r);
     result += recClear(c, r-1);
     result += recClear(c-1, r-1);
     result += recClear(c+1, r-1);
     result += recClear(c, r+1);
     result += recClear(c+1, r+1);
     result += recClear(c-1, r+1);

     return result;
 }
 }
   
}
