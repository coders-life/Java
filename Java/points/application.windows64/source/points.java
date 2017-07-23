import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.lang.Math; 
import java.lang.Math; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class points extends PApplet {



boolean[] keys;

int pg;
int pa;

square bg;
square fg;
public void setup() {
  keys = new boolean[256];
  
  pg = 60;
  pa = 6;
  bg = new square(pa, pg);
  bg.initarr();
  fg = new square(pa, pg);
  fg.initarr(bg.arr);
}

public void draw() {
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

public void keyPressed() {
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
    double[][] input = {{.5f, 0, 0}, {0, .5f, 0}, {0, 0, 1}};
    fg.arr = fg.multbyma(fg.arr, input);
  }
  if (keys[82]) { //r
    double radian = radians(10);
    double[][] input = {{Math.cos(radian), -Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};
    fg.arr = fg.multbyma(fg.arr, input);
  }
}

public void keyReleased() {
  keys[keyCode] = false;
}



class square {
  int pa;
  int pg;
  int[][] pixelz;
  int[][] arr;
  public square(int _pa, int _pg) {
    pa = _pa;
    pg = _pg;
    pixelz = new int[pg][pg];
  }

  public void initarr(int[][] _arr) {
    arr = _arr;
  }

  public void initarr() {
    for (int i = 0; i < pa*pg; i += pg/pa) {
      for (int j = 0; j < pa*pg; j += pg/pa) {
        int colorR = floor(random(0, 255));
        int colorG = floor(random(0, 255)); 
        int colorB = floor(random(0, 255));
        int hex = color(colorR, colorG, colorB);
        for (int k = i; k < pg; k++) {
          for (int l = j; l < pg; l++) {
            pixelz[k][l] = hex;
          }
        }
      }
    }
    arr = new int[pg*pa][pg*pa];
    for (int i = 0; i < pg; i++) {
      for (int j = 0; j < pg; j++) {
        int x = i * pa;
        int y = j * pa;
        arr[x][y] = pixelz[i][j];
        for (int k = 0; k < pg/10; k++) {
          for (int l = 0; l < pg/10; l++) {
            try {
              arr[x + k][y + l] = pixelz[i][j];
            }
            catch(Exception e) {
            }
          }
        }
      }
    }
  }
  public int check(int x, int y) {
    return arr[x][y];
  }

  public int[][] move(int dir, int[][] arr) {
    int[][] newarr = new int[arr.length][arr[0].length];
    int x = 0;
    int y = 0;
    switch(dir) {
    case 0:
      x = 5;
      break;
    case 1:
      x = -5;
      break;
    case 2:
      y = 5;
      break;
    case 3:
      y = -5;
      break;
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        try{
        newarr[i + x][j + y] = arr[i][j];
        }
        catch(Exception e){}
      }
    }
    return newarr;
  }

  public int[][] multbyma(int[][] arr, double[][] ma) {
    int[][] outarr = new int[pg * pa][pg * pa];
    for ( int x = 0; x < arr.length; x++ ) {
      for ( int y = 0; y < arr[0].length; y++) {
        try {
          double xnew = ma[0][0] * x + ma[1][0] * y + ma[2][0] * 1;
          double ynew = ma[0][1] * x + ma[1][1] * y + ma[2][1] * 1; 
          double znew = ma[0][2] * x + ma[1][2] * y + ma[2][2] * 1;   
          outarr[(int)Math.floor(xnew)][(int)Math.floor(ynew)] = arr[x][y];
        }
        catch(Exception e) {
        }
      }
    }
    return outarr;
  }
}

  public void settings() {  size(360, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "points" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
