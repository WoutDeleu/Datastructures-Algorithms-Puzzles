public class Vetrice {
    int id;
    int x;
    int y;

    public Vetrice(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    private void voegEdgeToe(Vetrice v) {
        int afstand = this.berekenAfstand(v);
    }

    public int berekenAfstand(Vetrice v) {
        double x2 = (double)v.getX();
        double y2 = (double)v.getY();
        double x1 = (double) x;
        double y1 = (double) y;
        return (int) Math.ceil( Math.sqrt( Math.pow((x1-x2),2) + Math.pow((y1-y2),2) ) );
    }

    public int getId() {
        return id;
    }
}
