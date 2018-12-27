
//add random color change when touching that stays once they stop touching
//make the color change slowly switch and switch back when touching/not touching


class Circles {
  float x; 
  float y; 
  float d;
  float cirColor;
  float xspeed;
  float yspeed;
  float hueX;
  float hueY; 
  float ballX;
  float ballY;
  
  Circles(float x ,float y,float d){  
  this.x = x; 
  this.y = y; 
  this.d = d;
  this.xspeed = random (.5,1.2);
  this.yspeed = random (.5,1.2);
  } 
  
  //Chose to work with a boolean here rather than a function that alters an int or float
  //this was mainly to learn about how else to activate a change, but also the boolean is 
  //a neat way of asking a logic question "Are they touching? True if yes." 
  
  boolean touching (Circles other) {
    
    //Using the built in distance function I compare radii to assess overlapping or not
    float dtest = dist(this.x,this.y,other.x, other.y);
    if (dtest < (    (this.d/2) + (other.d/2)   )   ) {
       return true;}
    else {
     return false;}
  }
  
  //ChangeRGB is ran when tester is true
  void changeRGB() {
    fill(20,40,35,50);
  }
  
  void normRGB(float changeLook) {
    fill(changeLook,100,100,45);
  }

  void display() {
    stroke(255);
    strokeWeight(5);
    ellipse(x, y, d, d);
  }
  
  void move() {
    this.x= this.x + this.xspeed;
    this.y= this.y + this.yspeed;
  }
  
  
  //This is for the circle's movement and bouncing at edges
  void checkEdges () {
    
    if (x + (d/2) > width || x-(d/2) < 0) {
      xspeed = xspeed * -1;
    }
    if (y + (d/2) > height || y-(d/2) < 0) {
      yspeed = yspeed * -1;
    }
  }
  
  //Based on the first ball in the array's position
  void colorBack () {
    hueX = x * 100 / width ;
    hueY = y * 100 / height; 
    background (((hueX/2) + (hueY/2)), 100, 75);
  }
  
}
