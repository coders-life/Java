import java.lang.Math;

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

  void initarr(int[][] _arr) {
    arr = _arr;
  }

  void initarr() {
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
  int check(int x, int y) {
    return arr[x][y];
  }

  int[][] move(int dir, int[][] arr) {
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

  int[][] multbyma(int[][] arr, double[][] ma) {
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
