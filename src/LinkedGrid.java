import java.awt.Color;
import java.util.ArrayList;



/**
 *
 * @author vatn29
 * @param <T>
 */
public class LinkedGrid<T> {
   private int width,height;
   private ArrayList<LinearNode<T>> grid; 
   
   //Constructor
    public LinkedGrid(int width, int height)
    { 
     this.width = width;  
    this.height = height;   
    grid = new ArrayList<>();       
        for(int i=0;i<width;i++){             
            grid.add(new LinearNode<T>());             
            LinearNode<T> temp = grid.get(i);             
            for(int j=1;j<height;j++){                 
                  temp.setNext(new LinearNode<T>());                 
                  temp = temp.getNext();            
            }         
        }      
    } 
    
     public void setElement(int col, int row, T data){   
        
          if(col <0 || col >= width || row >= height || row<0)
          {    
            throw new LinkedListException("Parameters are not in range"); 
           }   
            LinearNode<T> temp = grid.get(col); 
             for(int i=0;i<row;i++){  
                temp = temp.getNext();         
             }         
             temp.setElement(data);     
             }
     
       public T getElement(int col, int row){         
        if(col<0 || col >= width || row<0 || row >= height){             
        throw new LinkedListException("Parameters are not in valid range");         
       }         
       LinearNode<T> temp = grid.get(col);         
       for(int i=0;i<row;i++){             
        temp = temp.getNext();         
       }         
       return temp.getElement();     
       }
       
       public int getWidth()
       {         
            return width;     
       } 
              
       
       public int getHeight()
       {         
                    return height;     
       }  
                    
   @Override
   public String toString()
   {
       ArrayList<LinearNode<T>> temp = new ArrayList<>();         
            for(int i=0;i<width;i++){             
                temp.add(grid.get(i));         }          
                String res = "";                  
                    for(int i=0;i<height;i++){             
                        for(int j=0;j<width;j++){                 
                        res = res + String.valueOf(temp.get(j).getElement()) + "  ";                 
                        temp.set(j, temp.get(j).getNext());             
                    }             
                    res = res + "\n";         
                    }          
                    return res;
   }   

    void setBackground(Color red) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}