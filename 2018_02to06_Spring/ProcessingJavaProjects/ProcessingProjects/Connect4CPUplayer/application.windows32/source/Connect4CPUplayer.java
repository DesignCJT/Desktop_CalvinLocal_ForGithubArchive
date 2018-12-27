import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Connect4CPUplayer extends PApplet {

//Connect4CPUplayer

//Clicking on a playable tile iniates the CPU player to chose their tile
//The CPU has a % chance of playing random playeable spots or to play 
//adjacent to a previous tile
//moves and win states are printed in console

//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//summer 2018
//DesignCJT@gmail.com
//Motivated by Mike Shaw!
//Guideing code from Coding Train on youtube, Processing and p5.js tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw


SqFillStuff [] gridPc = new SqFillStuff[42];
SqFillStuff clicky;

int playPc = 0;
int columnHeight = 6;
float bottom = 450;
float leftSide = 150;
int numOfColumn = 7; 
int numOfSqs = columnHeight*numOfColumn;

float boxWalls = 5;
  float boxSize = 42;

    
    
public void setup() {
  colorMode(HSB, 100 , 100 , 100);
  
  stroke(100,100);
  clicky = new  SqFillStuff();
  for (int j = 0; j < gridPc.length; j++){
 
    
    gridPc[j] = new SqFillStuff();
  }
     for (int i = 0; i < gridPc.length; i++){
        gridPc[i].assignRowCol(i);
        //gridPc[i].displayGridPc();
      } // closes for loop
}



public void mousePressed(){
  //for (int i = 0; i < gridPc.length; i++) {
    
    // added
    clicky.clickyUse(mouseX, mouseY);

     
    //gridPc[findSq].ChangeFillType();}
  //}
 
  

}


public void draw() {

  background(100);
  for (int i = 0; i < gridPc.length; i++) {
    gridPc[i].displayGridPc(boxSize);
  }
  
  ////Below should set locations of the pcs to then be displayed
  //for (int j = 0; j < gridPc.length; j++){
     
 // } closes for loop
} //closes Draw


class SqFillStuff {
  
  int fillType = 0; 
  int colr = 40;
  int alph = 50;
  float colN;
  float rowN;
  float startX;
  float startY;
  int boxSizeH;
  int colNum;
  int rowNum;
  int sqNum;
  
  int huTurnCounter;
  int aiTurnCounter;
  float [] moveOptions;
  int activated = 0;
  
  float xCol;
  float yRow;
  
  int clickySq;
  
  SqFillStuff() {
  
  }
  
  public void clickyUse (float cxSpot, float cySpot) {
   if ((cxSpot < leftSide+boxSize*7) && (cxSpot > leftSide) && (cySpot > (bottom-(boxSize*6))) && (cySpot < (bottom+boxSize))) {
     //Above is "within grid" or not, THE BIG IF 
     
     //Below is gauging where the click took place
                //FINDING which column
                float xRemain = (cxSpot - leftSide) % boxSize;
                //Find the remainder, once making the left of the grid 0 (in x direction) instead of left of window being 0
                xCol = (((cxSpot-leftSide)-xRemain)/boxSize) + 1;
                //Subtract the remainder and add 1 
                
                //FINDING which row
                float topOfGrid = bottom-(6*boxSize);
                float yRemain =(cySpot-(topOfGrid)) % boxSize;
                //Find Y remainder once top of grid is set to 0 in the y direction
                float yRowInverse = (cySpot-topOfGrid-yRemain)/ boxSize ;
                //Subtract the remainder and add 1
                yRow = (6-yRowInverse + 1); 
                
                //FINDING which box in the grid!
                float findSq = ((yRow-1)*7)+xCol;
                //This essentially counts from the bottom left of the grid going to the right
                clickySq = PApplet.parseInt(findSq); 
        
    
        //Below is checking to see if the square human clicked on is already activated or not 
                if (gridPc[clickySq-1].checkIfActive() || !gridPc[clickySq-1].checkIfBotMo(clickySq-1)) {
                }
                else {
                  findWhichOne(clickySq);
                  huTurnCounter = huTurnCounter + 1;
                  print ("Hu turn ", huTurnCounter , " ");
                }
                
                
        //Before CPU Turn, we need to check for a win state
        
 //WIN STATE CHECKING
 //WIN STATE CHECKING
              checkForWinRow();
              checkForWinCol();
              checkForWinDiaR();
              checkForWinDiaL();
              
              
              
        //CPU PLAYING: if the human has clicked this should activate the CPU to play one round
                
                
                //The Great CPU test, "Is it my turn?"
                if (huTurnCounter == aiTurnCounter +1){
                  
                  //Create an Array to store possible moves, set each option default to -1
                  int spotNumInArray = 0;
                  int[] moveOptions = new int [7]; 
               
                    for (int a = 0; a < moveOptions.length; a++){
                      moveOptions[a] = -1;
                    }//close for, Array of all -1 created

                    
                    //Now we find all the bottom most spots in each column, these are the playable squares
                    for (int i = 0;i < gridPc.length; i++ ){
                      
                      //Now we find what's not playable: if truly active (checkIfActive function) then go to next gridpc, or 
                      //if not the bottom most open square (checkIfBotMo ...st function) then also go to the next gridpc
                      if (gridPc[i].checkIfActive() || !gridPc[i].checkIfBotMo(i)) {
                      }//closes if
              
                      //If the square is playable then it commits this else. There can only be up to 7 playable moves 
                      //as there are only 7 columns, 7 options to drop a piece into. Since it must be the bottom most and open
                      //The end goal is to place this i (square position) into the array of next plays "moveOptions"  
                      else {
                        moveOptions[spotNumInArray] = i;
                        spotNumInArray += 1;

                      }//closes else
                      
            }//closes FOR that checks gridpcs to play
            //Now you're left with an array of playable squares [-1, 16, 17, 18, 33, 19, 21] for example if column 1 was full, not playable
            
//ADDING Optimizer
//ADDING Optimizer
            
            //Take array and test it for local lining up, if it lines up with at least one then it's left as an option to pick
            for (int c =0; c < moveOptions.length; c++){
              int testSpace = moveOptions[c];
              int perNotSmartMove = 0;
              //Write a function that tests if there's a same filltype nearby, if so to have the function come back true
              //If there is a local pc nearby, then it leaves the testSpace in the array, if there is no local pc nearby
              //Then (after making the array) turn moveOptions2 at place c into -1
              //Lastly run test if any options are a viable play anymore (not all -1) if so to make that new array replace the old one as "move Options"
              if (localNearby(testSpace)){}//close if
              else {
                perNotSmartMove = PApplet.parseInt(random(0,10));
                if (perNotSmartMove > 3){ moveOptions[c] = -1;}
            }
            
            
            }//closes c FOR LOOP
            
   
              //From that array of possible "legal" plays, randomly choose one and 
              //if it's the default -1 that's regarded as unplayable and is set to reselect until not a -1 in the array
                int totalMoves = moveOptions.length;
                print ("total moves:",totalMoves, "|");
                int moveSelection;
                //boolean moveChecker = false; 
                moveSelection = PApplet.parseInt(random(0,totalMoves));
                int chosenPlay = moveOptions[moveSelection];
                  if (chosenPlay==-1){
                    while (chosenPlay == -1){
                      moveSelection = PApplet.parseInt(random(0,totalMoves));
                      chosenPlay = moveOptions[moveSelection];  
                    }}//closes both IF and WHILE
        
            //FINALLY the legal choice is made and the square FillType is activated
            gridPc[chosenPlay].aiChangeFillType();
            aiTurnCounter += 1;
            print ("CPU Turn ", aiTurnCounter,  " at grid space", chosenPlay);
            checkForWinRow();
            checkForWinCol();
            checkForWinDiaR();
            checkForWinDiaL();
        
      }//CLOSES the big test as the CPU takes it's turn
        
    }//CLOSES the big IF at the beginning that judges whether or not the human click produced a "play" and took their turn
        
  }//CLOSES void clickyUse

  
 
 
 //Smart Move Work_________________________
 //Smart Move Work_________________________
 
 public boolean localNearby(int spaceToTest){
     int switchFlip = 0; 
 //test if there's a a square locally up lr, lr, down lr, below

    int upl = spaceToTest+6;
    int upr = spaceToTest+8;
    int l = spaceToTest-1;
    int r = spaceToTest+1;
    int dnl = spaceToTest-8; 
    int dnr = spaceToTest-6;
    int b = spaceToTest-7;

      //If any surrounding spaces are too high...
      if (upl > 41){upl = -1;} if (upr > 41){upr = -1;} if (upr > 41){upr = -1;}
      //...on the left or right edge...
      if (spaceToTest % 7 == 0){upl= -1; l = -1; dnl = -1; }
      if (spaceToTest % 7 == 6){upr = -1; r = -1; upr = -1;}
      //...or are too low...
      if (dnl < 0){dnl = -1;}if (dnr < 0){dnr = -1;} if (l < 0){l = -1;} if (b < 0){b = -1;}
      //then make the spaceToTest = -1 to be ruled out later
      
        //print ("spaceToTest ");
        //print (spaceToTest);
        
        //Make array of surrounding spaces
        int[] arrayCheckLocal = new int [7];
        arrayCheckLocal[0] = upl;  arrayCheckLocal[1] = l;  arrayCheckLocal[2] = dnl;  arrayCheckLocal[3] = b;  arrayCheckLocal[4] = dnr;
        arrayCheckLocal[5] = r;  arrayCheckLocal[6] = upr;
        //print (" arrayCheckLocal ");
        
        //Iterate through the array 
        for (int c = 0; c < 7; c++){
          //print(arrayCheckLocal[c]);
          //print (",");
          
          //Weed out the -1 options
          if (arrayCheckLocal[c]!= -1){
              //Now check if it's a CPU fillType!
              if (gridPc[arrayCheckLocal[c]].fillType == 2){
                //If any of the locals are cpu filled then switch flip will be used to let those options live on based on a percentage
                switchFlip = 1;
             }
          }
         
      }//end for loop


   if (switchFlip == 1){
     //print (" localNearby returning TRUE ");
     return true;}
   else {
     //print (" localNearby returning false ");
     return false;}
   
 }//closes Boolean localNearby
 
  
  
//WIN ROW WORK _______________________
//WIN COL WORK _______________________
//WIN DIA R WORK _______________________
//WIN DIA L WORK _______________________

  public void checkForWinRow() {
    //First FOR loop checks each row after another until at the top
    
    
    for (int i = 0; i < 6; i++){
     
        //print(" Row",(i+1)," ");  //This prints the rows to help track
      
      int aClone = -1;
      int[] rowToCheck = new int [7]; 
      //int rowUp = ((i+1)*7)-7; //This prints the row number to help track
      for (int a = 0; a < rowToCheck.length; a++){
        rowToCheck[a] = gridPc[a+(i*7)].fillType ;
        //print(rowToCheck[a]);
        if (a == rowToCheck.length - 1){
        aClone = aClone +1;
        }
      }
  
     
      //Second FOR loop now sifts through the row 4 sequential squares at a time, excluding reach around by limiting to 4 possible win states per row
      for (int j = 0; j < 4; j++){
        if (rowToCheck[aClone+j]==0 || rowToCheck[aClone+1+j]==0 || rowToCheck[aClone+2+j]==0 || rowToCheck[aClone+3+j]==0){}
        else if (rowToCheck[aClone+j]==rowToCheck[aClone+3+j] & rowToCheck[aClone+j]==rowToCheck[aClone+2+j] & rowToCheck[aClone+j]==rowToCheck[aClone+1+j]){
             if (rowToCheck[aClone+j] == 1) {print ("HUMAN WINS rowicly! ");}
             if (rowToCheck[aClone+j] == 2) {print ("CPU WINS rowicly! ");}
            }
          
        
      }
    } //Closes Row counting FOR loop
  
  }//Close CheckForWinRow
  
  public void checkForWinCol(){
   for (int i = 0; i < 7; i++){
     
        //print(" Col",(i+1)," ");
      
      int aClone = -1;
      int[] colToCheck = new int [6]; 
      //int rowUp = ((i+1)*7)-7;
      for (int a = 0; a < colToCheck.length; a++){
        colToCheck[a] = gridPc[(a*7)+i].fillType ;
        //print(colToCheck[a]);
        if (a == colToCheck.length - 1){
        aClone = aClone +1;
        }
      }

     
      //Second FOR loop now sifts through the row 4 sequential squares at a time, excluding reach around by limiting to 4 possible win states per row
      for (int j = 0; j < 3; j++){
        if (colToCheck[aClone+j]==0 || colToCheck[(aClone*7)+j]==0 || colToCheck[(aClone*7)+j]==0 || colToCheck[(aClone*7)+j]==0){}
        else if (colToCheck[aClone+j]==colToCheck[aClone+3+j] & colToCheck[aClone+j]==colToCheck[aClone+2+j] & colToCheck[aClone+j]==colToCheck[aClone+1+j]){
              
              if (colToCheck[aClone+j] == 1) {print ("HUMAN WINS columnly! ");}
              if (colToCheck[aClone+j] == 2) {print ("CPU WINS columnly! ");}
            }
          
        
      }
    }//Closes First FOR loop
  
  }//Closes checkForWinCol()
  
  public void checkForWinDiaR(){
     
    //This FOR loop moves the starting step one to the direct right
    for (int i = 0; i < 4; i++){
     
        //print(" Dia",(i+1)," ");
      
      //int a = -1;
      int[] diaRToCheck = new int [4];
      int diaCount = 0;
      //int aClone = 0;
      
               //This 1st level nested FOR loop bumps the starting of the array up one step in the staircase, in the up/right diagonal
               for (int diaPoss = 0; diaPoss < 3; diaPoss++){ 
                 diaCount = 0;
               
                 //if (diaPoss ==0){print ("Bottom ");}
                 //else {print (" NextUp ");}
                       
                       //This FOR loop creates an array of 
                       for (int a = 0; a < diaRToCheck.length; a++){
                          diaRToCheck[a] = gridPc[i+diaCount+(diaPoss*7)].fillType ;
                          //diaRToCheck[a] = i+diaCount+(diaPoss*7);
                          diaCount = diaCount + 8;
                          //print(diaRToCheck[a]);
                          //print(",");
                          //aClone = aClone +1;
                          //a = a +1;
                       }
                
                         //Second FOR loop now sifts through the row 4 sequential squares at a time, excluding reach around by limiting to 4 possible win states per row
                  
                    if (diaRToCheck[0]==0 || diaRToCheck[(1)]==0 || diaRToCheck[(2)]==0 || diaRToCheck[(3)]==0){}
                    else if (diaRToCheck[0]==diaRToCheck[3] & diaRToCheck[0]==diaRToCheck[2] & diaRToCheck[0]==diaRToCheck[1]){
                          
                          if (diaRToCheck[0]==1) {print ("HUMAN WINS diagonally right! ");}
             if (diaRToCheck[0]==2) {print ("CPU WINS diagonally right! ");}
                        }
              
                
              }

     
 
          
        
     // }//Closes 2nd FOR loop
    }//Closes First FOR loop
  
  }//Closes checkForWinDiaR()
  
  
   public void checkForWinDiaL(){
     
    //This FOR loop moves the starting step one to the direct right
    for (int i = 3; i < 7; i++){
     
        //print(" Dia",(i+1)," ");
      
      //int a = -1;
      int[] diaLToCheck = new int [4];
      int diaCount = 0;
      //int aClone = 0;
      
               //This 1st level nested FOR loop bumps the starting of the array up one step in the staircase, in the up/left diagonal
               for (int diaPoss = 0; diaPoss < 3; diaPoss++){ 
                 diaCount = 0;
                 //aClone = 0;
                 //if (diaPoss ==0){print ("Bottom ");}
                 //else {print (" NextUp ");}
                       
                       //This FOR loop creates an array of 
                       for (int a = 0; a < diaLToCheck.length; a++){
                          diaLToCheck[a] = gridPc[i+diaCount+(diaPoss*7)].fillType ;
                          //diaLToCheck[a] = i+diaCount+(diaPoss*7);
                          diaCount = diaCount + 6;
                          //print(diaLToCheck[a]);
                          //print(",");
                          //aClone = aClone +1;
                          //a = a +1;
                       }
                
                         //Second FOR loop now sifts through the row 4 sequential squares at a time, excluding reach around by limiting to 4 possible win states per row
                  
                    if (diaLToCheck[0]==0 || diaLToCheck[(1)]==0 || diaLToCheck[(2)]==0 || diaLToCheck[(3)]==0){}
                    else if (diaLToCheck[0]==diaLToCheck[3] & diaLToCheck[0]==diaLToCheck[2] & diaLToCheck[0]==diaLToCheck[1]){
                          if (diaLToCheck[0]==1) {print ("HUMAN WINS diagonally left! ");}
             if (diaLToCheck[0]==2) {print ("CPU WINS diagonally left! ");}
                        }
              
                
              }

     
 
          
        
     // }//Closes 2nd FOR loop
    }//Closes First FOR loop
  
  }//Closes checkForWinDiaL()
  
  public boolean checkIfActive() {
    if (this.activated == 0){
      return false;
    }
    else {return true;}
  }
  
  
  public boolean checkIfBotMo(int testSq){
    int belowSq = testSq - 7;  
    
    if (testSq <= 6){return true;}
    if (gridPc[belowSq].checkIfActive()){
      return true;
    }
    else {return false;}
  }
  
  public void assignRowCol(int blockNum){
    this.sqNum = blockNum +1;
    this.colNum = this.sqNum % 7;
    if (this.colNum == 0){this.colNum = 7;}
    this.rowNum = ((this.sqNum-this.colNum)/7)+1;
    //print ("sqNum", sqNum, "C" ,this.colNum,"R", this.rowNum, " ");
    
  }//closes function
  
  public void findWhichOne (int whichOne){
    gridPc[whichOne-1].changeFillType(); 
  }
  public void changeFillType() {
    this.fillType = 1;
    this.activated = 1;
  }
  
  public void aiChangeFillType() {
    this.fillType = 2;
    this.activated = 1;
  }
  
  public void ifInChangeFillType(int xSpot, int ySpot) {
    if ((xSpot < leftSide+boxSize*7) && (xSpot > leftSide) && (ySpot > (bottom-(boxSize*6))) && (ySpot < (bottom+boxSize))) {
      float xRemain = (xSpot - leftSide) % boxSize;
      xCol = (((xSpot-leftSide)-xRemain)/boxSize) + 1;
      float topOfGrid = bottom-(6*boxSize);
      float yRemain =(ySpot-(topOfGrid)) % boxSize;
      float yRowInverse = (ySpot-topOfGrid-yRemain)/ boxSize ;
      yRow = (6-yRowInverse + 1); 
      float findSq = ((yRow-1)*7)+xCol;
      if (this.sqNum == (findSq))
      {this.fillType = 1;}
      
      print ("Col",xCol," ");
      print ("Row",yRow," ");
    }
  }
  
    
    
    
  
  public void displayGridPc (float boxSizeUse) {
    startY = bottom - ((this.rowNum-1)* (boxSizeUse));
    startX = leftSide + ((this.colNum-1)* (boxSizeUse));
    fill(200,102,0);
   
     if (this.fillType == 0){
     fill(colr,100,100,alph);
     }
     else if (this.fillType == 1) {
       this.colr = 95;
       this.alph = 100;
       fill(colr,100,100, alph);
     }
     else if (this.fillType ==2) {
       this.colr = 60;
       this.alph = 100;
       fill(colr,100,100,alph);
     }
   
   rect(startX, startY, boxSizeUse, boxSizeUse);
    
    
    
  } //closes func
  
 
  
} //closes the class
  public void settings() {  size (600,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Connect4CPUplayer" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
