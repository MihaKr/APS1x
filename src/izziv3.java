public class izziv3 {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        CompleteBinaryTreeDrawer ctd = new CompleteBinaryTreeDrawer(size);
        StdDraw.setCanvasSize(1500, 800);
        StdDraw.setXscale(-1, size);
        StdDraw.setYscale((int) (Math.log(size + 1) / Math.log(2)) + 1, -1);
        ctd.drawLevelOrder();
    }
}

class CompleteBinaryTreeDrawer {
    int size;
    int x[];
    int y[];

    CompleteBinaryTreeDrawer(int size) {
        this.size = size;
        this.x = new int[size];
        this.y = new int[size];
        traverse(0,0,0);
    }

    int traverse(int i, int x, int y) {
        if (2 * i + 1 < size) {
            x = traverse(2 * i + 1, x, y + 1);
        }
        this.x[i] = x;
        this.y[i] = y;
        if (2 * i + 2 < size) {
            x = traverse(2 * i + 2, x + 1, y + 1) - 1;
        }
        return x + 1;
    }

    void drawNode(int i) {
        StdDraw.filledCircle(this.x[i], this.y[i], 0.20);
    }

    void drawEdgeToParent(int i) {
        int x1 = this.x[i];
        int y1 = this.y[i];
        int x2 = this.x[(i-1)/2];
        int y2 = this.y[(i-1)/2];
        StdDraw.line(x1,y1,x2,y2);
    }

    void drawLevelOrder() {
        for (int i = 0; i < size; i++) {
            drawEdgeToParent(i);
            drawNode(i);
        }
    }

    void drawInorder(int i) {
        if (2 * i + 1 < size) {
           drawInorder(2 * i + 1);
        }
        drawNode(i);
        drawEdgeToParent(i);
        if (2 * i + 2 < size) {
            drawInorder(2 * i + 2);
        }
    }

    void drawPreorder(int i) {
        drawNode(i);
        drawEdgeToParent(i);
        if (2 * i + 1 < size) {
            drawInorder(2 * i + 1);
        }
        if (2 * i + 2 < size) {
            drawInorder(2 * i + 2);
        }
    }

    void drawPostorder(int i) {
        if (2 * i + 1 < size) {
            drawInorder(2 * i + 1);
        }
        if (2 * i + 2 < size) {
            drawInorder(2 * i + 2);
        }
        drawNode(i);
        drawEdgeToParent(i);
    }
}
