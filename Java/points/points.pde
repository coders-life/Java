import  java.lang.Math;

boolean[] keys;

int pg;
int pa;

square bg;
square fg;
void setup() {
  keys = new boolean[256];
  size(360, 360);
  pg = 60;
  pa = 6;
  bg = new square(pa, pg);
  bg.initarr();
  fg = new square(pa, pg);
  fg.initarr(bg.arr);
}

void draw() {
  background(0);
  for (int i = 0; i < pg*pa; i++) {
    for (int j = 0; j < pg*pa; j++) {
      int bgcol = bg.check(i, j);
      int fgcol = fg.check(i, j);
      if (fgcol == bgcol) {
        stroke(255, 0, 0, 255);
      } else if (fgcol == 0) stroke(bgcol);
      else stroke(fgcol);
      noFill(); 
      point(i, j);
    }
  }
}

void keyPressed() {
  keys[keyCode] = true;

  if (keys[37]) {   //left
    fg.arr = fg.move(1, fg.arr);
  } 
  if (keys[39]) {   //right
    fg.arr = fg.move(0, fg.arr);
  } 
  if (keys[38]) {   //up
    fg.arr = fg.move(3, fg.arr);
  } 
  if (keys[40]) {   //down
    fg.arr = fg.move(2, fg.arr);
  }   
  if (keys[32]) { //space
    double[][] input = {{.5, 0, 0}, {0, .5, 0}, {0, 0, 1}};
    fg.arr = fg.multbyma(fg.arr, input);
  }
  if (keys[82]) { //r
    double radian = radians(10);
    double[][] input = {{Math.cos(radian), -Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};
    fg.arr = fg.multbyma(fg.arr, input);
  }
}

void keyReleased() {
  keys[keyCode] = false;
}