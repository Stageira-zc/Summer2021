package com.aicity.smartparkingapplication.TileComponent;

public class TileCollectionRange {
    long zoom;
    long xStart;
    long xRange;
    long yStart;
    long yRange;
    public TileCollectionRange(long zoom, long xStart, long xRange, long yStart, long yRange) {
        this.zoom = zoom;
        this.xStart = xStart;
        this.xRange = xRange;
        this.yStart = yStart;
        this.yRange = yRange;
    }

    public boolean isEqual(TileCollectionRange range) {
        return ((this.zoom == range.zoom) &&
                (this.xStart == range.xStart) &&
                (this.xRange == range.xRange) &&
                (this.yStart == range.yStart) &&
                (this.yRange == range.yRange));
    }
}
