public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double xSquare = (p.xxPos - xxPos) * (p.xxPos - xxPos);
        double ySquare = (p.yyPos - yyPos) * (p.yyPos - yyPos);
        return Math.sqrt(xSquare + ySquare);
    }

    public double calcForceExertedBy(Planet p){
        final double G = 6.67430e-11;
        double distance = calcDistance(p);
        return G * p.mass * mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        double force = calcForceExertedBy(p);
        return force * dx / r;
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        double force = calcForceExertedBy(p);
        return force * dy / r;
    }

    // Calculate the net X force exerted by all planets in the array
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) { // Skip this planet
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    // Calculate the net Y force exerted by all planets in the array
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) { // Skip this planet
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        double accelerationX = fX / mass;
        double accelerationY = fY / mass;

        xxVel += accelerationX * dt;
        yyVel += accelerationY * dt;

        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
}
