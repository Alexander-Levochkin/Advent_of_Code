package Year2021.utils.task5classes;

public record Vent(Point point1, Point point2) {

    public boolean isHorizontal() {
        return point1.y() == point2.y();
    }

    public boolean isVertical() {
        return point1.x() == point2.x();
    }

    public int minX() {
        return Math.min(point1.x(), point2.x());
    }

    public int maxX() {
        return Math.max(point1.x(), point2.x());
    }

    public int minY() {
        return Math.min(point1.y(), point2.y());
    }

    public int maxY() {
        return Math.max(point1.y(), point2.y());
    }

    public int getY() {
        assert isHorizontal();
        return point1().y();
    }

    public int getX() {
        assert isVertical();
        return point1().x();
    }

    public boolean isXReversed() {
        return point1.x() > point2.x();
    }

    public boolean isYReversed() {
        return point1.y() > point2.y();
    }

    @Override
    public String toString() {
        return "%d%d %d%d".formatted(point1.x(), point2.x(), point1.y(), point2.y());
    }
}
