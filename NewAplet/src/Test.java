import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;

public class Test extends PApplet{
    float rset=60, r;
    int x = 0;
    int w = 400;
    int h = 200;
    int grid=20, many = grid*grid;
    boolean stressenable=true;

    public void settings() {
        //size(w, h); // default use JAVA2D 
        //  size(w, h,FX2D);
          size(w, h,P2D);
        //   size(w, h,P3D);
        }

        public void setup() {
        frameRate(rset);
        println("target "+rset+" FPS");
        background(200, 200, 0);
        println("use key [p] for sysinfo, [n] numinfo, \n[s] stresstest ( "+grid+"*"+grid+" = "+many+" ) points, [+][-] grid +-5" );
        sysinfo();
    }

    public void draw() {
        surface.setTitle("SYS INFO "+ nf(r, 0, 1)+" FPS" );
        if ( stressenable ) stress();
        r = frameRate;
        if ( r > 30 )      stroke(0, 200, 0); 
        else               stroke(200, 0, 0);
        line(x, height-2, x, height-2-r);            // FPS graph
        x++;
        if ( x > width ) { 
            x = 0; 
            background(200, 200, 0);
        }
        // TEST: delete later following 3 lines
        translate(200,50);
        stroke(200,0,0);
        sphere(20);
    }

    void stress() {
        push();
        for ( int i=0; i<grid; i++) {
            for ( int j=0; j<grid; j++) {
            stroke(random(255), random(255), random(255) );
            point(5+i, 5+j);
            }
        }
        pop();
    }

    void sysinfo() {
        println( "__SYS INFO :");
        println( "System     : " + System.getProperty("os.name") + "  " + System.getProperty("os.version") + "  " + System.getProperty("os.arch") );
        println( "JAVA       : " + System.getProperty("java.home")  + " rev: " +javaVersionName);
        //println( System.getProperty("java.class.path") );
        //println( "\n" + isGL() + "\n" );
        println( "OPENGL     : VENDOR " + PGraphicsOpenGL.OPENGL_VENDOR+" RENDERER " + PGraphicsOpenGL.OPENGL_RENDERER+" VERSION " + PGraphicsOpenGL.OPENGL_VERSION+" GLSL_VERSION: " + PGraphicsOpenGL.GLSL_VERSION);
        println( "user.home  : " + System.getProperty("user.home") );
        println( "user.dir   : " + System.getProperty("user.dir") );
        println( "user.name  : " + System.getProperty("user.name") );
        println( "sketchPath : " + sketchPath() );
        println( "dataPath   : " + dataPath("") );
        println( "dataFile   : " + dataFile("") );
        println( "frameRate  : target "+nf(rset, 0, 1)+" actual "+nf(r, 0, 1));
        println( "canvas     : width "+width+" height "+height+" pix "+(width*height));
    }

    void numinfo() {
        println( "__NUM INFO :");
        println( "byte    min: "+Byte.MIN_VALUE+   "\t\t\t max: "+Byte.MAX_VALUE);
        println( "short   min: "+Short.MIN_VALUE+  "\t\t\t max: "+Short.MAX_VALUE);
        println( "int     min: "+Integer.MIN_VALUE+"\t\t max: "  +Integer.MAX_VALUE);
        println( "long    min: "+Long.MIN_VALUE+   "\t max: "    +Long.MAX_VALUE);
        println( "float   min: "+Float.MIN_VALUE+  "\t\t\t max: "+Float.MAX_VALUE);
        println( "double  min: "+Double.MIN_VALUE+ "\t\t\t max: "+Double.MAX_VALUE);
    }

    public void keyPressed() {
        if ( key == 'p' ) sysinfo();
        if ( key == 'n' ) numinfo();
        if ( key == 's' ) stressenable = ! stressenable;
        if ( key == '+' ) {
            grid +=5;
            many = grid*grid;
            println("stresstest ( "+grid+"*"+grid+" = "+many+" ) points" );
        }
        if ( key == '-' ) {
            grid -=5;
            many = grid*grid;
            println("stresstest ( "+grid+"*"+grid+" = "+many+" ) points" );
        }
    }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "Test"};
        if (passedArgs != null) {
          PApplet.main(concat(appletArgs, passedArgs));
        } else {
          PApplet.main(appletArgs);
        }
      }
}
