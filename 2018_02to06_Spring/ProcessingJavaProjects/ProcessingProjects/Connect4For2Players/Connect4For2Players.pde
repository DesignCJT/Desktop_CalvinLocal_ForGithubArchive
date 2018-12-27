//Connect 4 for 2 players

//Object interaction, with basic falling and collision physics

//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw


CirPcs [] cirPc = new CirPcs[50];


int playPc = 0;
int columnHeight = 6;
float bottom = 450;
float leftSide = 150;
int numOfColumn = 7; 
int numOfSqs = columnHeight*numOfColumn;
float boxSize = 42;
float boxSizeH = 40;
float boxWalls = 5;


void setup() {
 size (600,600);
 stroke(0,0,200);

 for (int i = 0; i < cirPc.length; i++){
   cirPc[i] = new CirPcs();
 }
 

}

void mousePressed(){
  
  playPc++; 
  if (playPc % 2 == 0){
    cirPc[playPc-1].coloringPcOther();}
  cirPc[playPc-1].columnClick(mouseX);
}


void draw() {
  background(255);

  
  if (playPc == 0){  
  }
  
  for(int i= 0; i < playPc; i++) {
    for(int j = 0; j < playPc; j++) {
      if (playPc == 1){
      }
      else if (i != j) {
        cirPc[i].cirTouch(cirPc[j]);
      }
      else {
      }
  }
  cirPc[i].touchBottom(bottom); 
  cirPc[i].moveCir();
  cirPc[i].displayCir();
  //cirPc[i].cirBorder();
  
  }
  
  displayAllSqs(numOfSqs,columnHeight, bottom, leftSide);
  
  
}



  void displayAllSqs (int numOfSq, int colH, float setBottom, float leftSide) {
   
    float startX = leftSide;
    float startY= setBottom;
    float rowC = 0;
    float colC = 0;
    fill(0,0,100,30);
    strokeWeight(boxWalls);
    for (int i = 0; i < numOfSq; i++){
      if (i==0) {
      rect(startX, startY-boxSize,boxSize,boxSize);
    }
      else if (i % colH == 0) {
        rowC = 0; 
        colC =+1;
        startX = startX+(boxSize*colC);
        rect(startX, startY-boxSize-(boxSizeH*rowC),boxSize,boxSize);
          }
      else { rowC = rowC + 1;
    rect(startX, (startY-boxSize-(boxSizeH*rowC)),boxSize,boxSizeH);
          }

    } // closes the for loop
  }  //closes the function
  
  
