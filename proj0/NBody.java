public class NBody {
    public static double readRadius(String file){
        In in = new In(file);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file){
        In in = new In(file);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        in.readDouble();
        for (int i=0; i<num; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args) {
        /* get data */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        /* draw background */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "/images/starfield.jpg");
        /* draw planets */
        for (Planet planet: planets){
            planet.draw();
        }
        /* create animation */
        StdDraw.enableDoubleBuffering();
        for (double t=0; t<T; t+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i=0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i=0; i < planets.length; i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"/images/starfield.jpg");
            for (Planet planet:planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        /* print the result */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}
