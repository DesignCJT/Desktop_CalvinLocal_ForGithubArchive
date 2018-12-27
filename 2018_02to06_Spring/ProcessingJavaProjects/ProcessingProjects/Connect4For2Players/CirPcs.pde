class CirPcs {
  
  float x = width/2;
  float y = 0;
  float yspeed = 5;
  float sizeCir = 40;
  float r = sizeCir/2;
  float colR = 200;
  float colG = 0;
  float colB = 0;
  float roomForError = 15;

  
  CirPcs () {
  }
  
  void columnClick (float xPlace) {
    int whichCol = 0;
    
    for (int i=0;i < numOfColumn+1; i++) {
      if (xPlace < leftSide) {break;}
      if (xPlace > (leftSide+(i*boxSize))) {whichCol++;}
    }
    
    float xSlot = leftSide+(whichCol*boxSize)-(boxSize/2);
    if (whichCol == 0) {xSlot = leftSide-(boxSize*2);}
    if (whichCol > numOfColumn) {xSlot= leftSide+(boxSize*numOfColumn)+(boxSize*2);}
    this.x = xSlot ;
  }
  
  void coloringPcOther () {
   this.colR= 230 ;
   this.colG= 230;
  }
  
  void displayCir(){
    fill(this.colR,this.colG,colB);
    strokeWeight(1);
    ellipse(this.x, y, sizeCir, sizeCir);
  }
  
  void moveCir(){
    this.y = this.y + this.yspeed;
  }
  
  void touchBottom(float setBottom) {
     if (this.y >= (setBottom-r)){
      this.yspeed = 0;
      this.y = setBottom-r;
    }
  }
  
  void cirTouch (CirPcs otherCir){
    float dtest = dist(this.x,this.y,otherCir.x, otherCir.y);
    if (dtest <= (this.r + otherCir.r + 10)) {
      if (this.yspeed != 0) { this.yspeed = 1;}
    }    
    if (dtest <= (this.r + otherCir.r)) {
      this.yspeed = 0;
    }
    
  }
  
  
  
  
  //void touchBottom() {
  //  if (this.y >= height/2){
  //    this.yspeed = 0;
  //  }
  //}
  
  //void cirBorder(){
  //  fill(0,50,50,50);
  //  ellipse(this.x, this.y,boxSize,boxSize);
  //}
  
// Below is to boolean test and then return true if touch, I just want this.yspeed to 0
  
  
  //boolean cirTouch (CirPcs otherCir){
  //  float dtest = dist(this.x,this.y,otherCir.x, otherCir.y);
  //  if (dtest < (this.r + otherCir.r)) {
  //    this.yspeed = 0;
  //    return true; 
  //  } 
  //  else {
  //    return false;
  //  }

  //}
  
}