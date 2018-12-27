//Ball Objects Overlap

//Object interaction on Overlap
//with one object also adjusting background color based on position

//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw


Circles [] roundOne = new Circles[20];
//Number above establishes how many circles will be generated 
//initializes class

void setup(){
  
  size(1600,800);

  //ForLoop creates each circle getting larger based on its array position+1
  for (int i = 0; i < roundOne.length; i++) {
    float d = (i+1)*10;
    float x = random(0+(d/2),(width-(d/2)));
    float y = random(0+(d/2),(height-(d/2)));
    
    roundOne[i] = new Circles(x,y,d);
    
    colorMode(HSB,100); 
  }
}


void draw() {
  
  //This first ball's positioning is used to change the background's hue
  roundOne[0].colorBack();
  
  for (int i = 0; i < roundOne.length; i++) {
    
      boolean tester = false;
        
          //ForLoop here, a loop within a loop, checks each circle against each other
          //and results in switching tester boolean if circles touch
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
    roundOne[i].checkEdges();

  }//Closes ForLoop

}//Closes Draw
