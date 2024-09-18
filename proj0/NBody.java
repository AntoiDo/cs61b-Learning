public class NBody {
    public static double readRadius(String filepath){
        In in = new In(filepath);
        int planetCnt = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filepath){
        In in = new In(filepath);
        int planetCnt = in.readInt();
        Planet[] allPlanets = new Planet[planetCnt];
        double radius = in.readDouble();
        for(int i = 0; i < planetCnt; i++){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double m = in.readDouble();
            String imgName = in.readString();
            allPlanets[i] = new Planet(xPos, yPos, xVel, yVel, m, imgName);
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        if(args.length < 3){
            System.out.println("Usage: java NBody <filepath> <planet id> <radius>");
            return;
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        // System.out.println("Planet id: " + args[1]);

        /* 设置背景 */
        String starfield = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0,0,starfield);
        /* 画planets */
        for(Planet p: allPlanets){
            p.draw();
        }

        /* 允许doubleBuffering,动画更丝滑流畅. */
        StdDraw.enableDoubleBuffering();
        double Time = 0.0;
        while(Time < T){
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];
            /* update放里面还是外面? */
            for(int i = 0; i < allPlanets.length; i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            /* 先绘制背景，再绘制planets，再show()，再暂停10ms */
            StdDraw.picture(0,0,starfield);
            for(Planet p: allPlanets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            Time += dt;
        }

        /* 按照格式打印各个数据 */
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
