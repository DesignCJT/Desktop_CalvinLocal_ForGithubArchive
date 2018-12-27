//Testing object overlap

//Object interaction of color change on Overlap


//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw


Circles [] roundOne = new Circles[10];

void setup(){
  size(600,400);

  for (int i = 0; i < roundOne.length; i++) {
    float x = random(0,width);
    float y = random(0,height);
    float d = (i+1)*10;
    boolean tester = false;
    roundOne[i] = new Circles(x,y,d);
    colorMode(HSB,100);  
  }
}


void draw() {
  
  background(0);
  
  for (int i = 0; i < roundOne.length; i++) {
    
    boolean tester = false;
            for(int j = 0; j < roundOne.length; j++) {
              if  ( (i != j) && (roundOne[i].touching(roundOne[j]))  ) {
              tester = true;}
            }
  
  if (tester) {
    roundOne[i].changeRGB();
  }
  
  else {
    roundOne[i].normRGB(40);
  }
  
roundOne[i].display();
roundOne[i].move();
}

roundOne[0].x = mouseX;
roundOne[0].y = mouseY;

}
