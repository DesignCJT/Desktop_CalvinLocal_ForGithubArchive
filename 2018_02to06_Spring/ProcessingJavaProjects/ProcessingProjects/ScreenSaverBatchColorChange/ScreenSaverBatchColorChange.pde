//ScreenSaver, batch color change

//Object interaction on Overlap
//with one object also adjusting background
//also changing colors until all are objects have overlapped 
//another, then new random color is applied

//by Calvin Tomaschko
//www.reannatrombley.com/designcjt/
//spring 2018
//DesignCJT@gmail.com
//Guideing code from Coding Train on youtube, Processing tutorials
//https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw



Circles [] roundOne = new Circles[20];

Circles compare;

int allSame;


void setup(){
  size(1900,1030);
  compare = new  Circles();

  for (int i = 0; i < roundOne.length; i++) {
  
    float d = random(30,70); //sets diameter
    float x = random(0+(d/2),(width-(d/2))); //sets the circle left or right
    float y = random(0+(d/2),(height-(d/2))); //sets the circle up or down
    
    roundOne[i] = new Circles(x,y,d);
    colorMode(HSB,100);
    
  }
}


void draw() {
  roundOne[0].colorBack(); //Circle zero is used to color the background by position
  
  //This forLoop section is all to compare if each circle is touching
  //then it displays, moves, and checks edges
  
  for (int i = 0; i < roundOne.length; i++) { 
    boolean tester = false;
            for(int j = 0; j < roundOne.length; j++) { 
              //this loop inside a loop compares each circle to each other
              
              if  ( (i != j) && (roundOne[i].touching(roundOne[j]))  ) {
              tester = true;}
            }
    if (tester) {
      roundOne[i].changeRGB();}
    else {
      roundOne[i].normRGB();
    }
    
  roundOne[i].display();
  roundOne[i].move();
  roundOne[i].checkEdges();
  }
  
  //This section below is for assessing whether every circle is the same color and 
  //to reset to a new color if so. For each circle of the same "newest" color, variable
  //allSame goes up by one

allSame = 0;

  for (int a = 0; a <roundOne.length; a++) {
    if (roundOne[a].selfColorCheck()){allSame= allSame+1;
    }
    else {}
  } //closes for
  
  if (allSame == roundOne.length){ //<>//
    int newHue = int(random(0,100));
    for (int b = 0; b <roundOne.length; b++) {
      roundOne[b].changeHue(newHue);
    }
  } //closes if

} //closes Draw
