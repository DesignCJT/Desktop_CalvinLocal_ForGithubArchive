//StarWorkFunction

//If you click a star will appear

//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw


void setup() {
background(51);
size(640,360);
}

void draw() {
  
  
  //// Below is for making rows and columns of stars
  //for ( int x = 25; x < width; x = x + 96) {
    //for ( int y = 55; y < height; y = y + 102) {
      
     // star(x,y);
    
  
  
  //star(100,100);
  //star(200,300);
}


//MousePressed must be it's own function with void type called, this makes a star appear at the 
//center of where the mouse clicks!
void mousePressed() {
    int x = mouseX;
    int y = mouseY;
    star(x,y);
  }

void star(float x, float y) {
  fill(127);
  stroke(255);
  strokeWeight(2);
  //hardcoding vertices
  beginShape();
  vertex(x,    y-50);
  vertex(x+14, y-20);
  vertex(x+47, y-15);
  vertex(x+23, y+7);
  vertex(x+29, y+40);
  vertex(x,    y+25);
  vertex(x-29, y+40);
  vertex(x-23, y+7);
  vertex(x-47, y-15);
  vertex(x-14, y-20);
  endShape(CLOSE);
}
