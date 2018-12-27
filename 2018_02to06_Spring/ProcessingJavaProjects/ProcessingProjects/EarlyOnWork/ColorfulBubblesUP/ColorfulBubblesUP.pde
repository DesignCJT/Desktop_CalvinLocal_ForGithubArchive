//Colorful Bubbles UP!

//writen by Calvin Tomaschko, but mostly a copy from tutorial
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw

Bubble[] bubbles = new Bubble[200];

void setup(){
  size(640, 360);
  
  for (int i = 0; i < bubbles.length; i++) {
    bubbles[i] = new Bubble(30);
  }
}
void draw(){
  background(255);
  for (int i = 0; i < bubbles.length; i++) {
  bubbles[i].display();
  bubbles[i].ascend();
  bubbles[i].top();
  }
}
