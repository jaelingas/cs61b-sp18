public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String img){
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = img;
    }

    public Planet(Planet b){
        this.xxPos = b.xxPos;
        this.xxVel = b.xxVel;
        this.yyPos = b.yyPos;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b){
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet b){
        double r = calcDistance(b);
        double G = 6.67 * Math.pow(10, -11);
        return G * this.mass * b.mass/Math.pow(r, 2);
    }

    public double calcForceExertedByX(Planet b){
        return calcForceExertedBy(b)*(-this.xxPos + b.xxPos)/calcDistance(b);
    }

    public double calcForceExertedByY(Planet b){
        return calcForceExertedBy(b)*(-this.yyPos + b.yyPos)/calcDistance(b);
    }

    public double calcNetForceExertedByX(Planet[] bs){
        double net = 0;
        for (Planet b: bs) {
            if (!b.equals(this)){
                net += calcForceExertedByX(b);
            }
        }
        return net;
    }

    public double calcNetForceExertedByY(Planet[] bs){
        double net = 0;
        for (Planet b: bs) {
            if (!b.equals(this)){
                net += calcForceExertedByY(b);
            }
        }
        return net;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX/this.mass;
        double aY = fY/this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
}
