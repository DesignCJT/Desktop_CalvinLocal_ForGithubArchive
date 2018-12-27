class Circles {
  float x; 
  float y; 
  float d;
  float cirColor;
  Circles(float x ,float y,float d){  
  this.x = x; 
  this.y = y; 
  this.d = d;
  } 
  
  boolean touching (Circles other) {
    float dtest = dist(this.x,this.y,other.x, other.y);
    if (dtest < (    (this.d/2) + (other.d/2)   )   ) {
       return true;}
    else {
     return false;}
  }
  
  void changeRGB() {
    fill(20,40,35);
  }
  
  void normRGB(float changeLook) {
    fill(changeLook,100,100,75);
  }

  void display() {
    stroke(255);
    strokeWeight(5);
    ellipse(x, y, d, d);
  }
  
  void move() {
    this.x = this.x + random(-2,2);
    this.y = this.y + random(-2,2);
  }
}