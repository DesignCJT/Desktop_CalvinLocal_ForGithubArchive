//CircleTouchCircle_loneOG

//Working with moving position and using that to change bg color

//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw

float x;
float y;
float xspeed;
float yspeed;
float d;
float hueX;
float hueY; 
float ballX;
float ballY;

void setup() {
  size(320, 240);
  d = 32;
  x = random(0+(d/2),(width-(d/2)));
  y = random(0+(d/2),(height-(d/2)));
  xspeed= random(.5,1.5);
  yspeed= random(.5,1.5);
  colorMode (HSB, 100);
}

void draw() {
  colorBack();
  displayBall();
  colorBall();
  moveBall();
  checkEdges();
  
}



void displayBall () {
  stroke(0);
  //fill(40);
  ellipse(x, y, d, d);
}

void moveBall () {
  x= x + xspeed;
  y= y + yspeed;
}

void checkEdges () {
  //if (x < (d/2) || x >        ) {
  if (x + (d/2) > width || x-(d/2) < 0) {
    xspeed = xspeed * -1;
  }
  if (y + (d/2) > height || y-(d/2) < 0) {
    yspeed = yspeed * -1;
  }
}

void colorBack () {
  hueX = x * 100 / width ;
  hueY = y * 100 / height; 
  background (((hueX/2) + (hueY/2)), 100, 100);
}

void colorBall () {
  ballX = x * 100 / width ;
  ballY = y * 100 / height; 
  fill ((100-((ballX/2) + (ballY/2))), 100, 100);; 
}
