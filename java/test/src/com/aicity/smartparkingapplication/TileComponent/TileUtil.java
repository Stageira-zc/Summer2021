package com.aicity.smartparkingapplication.TileComponent;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TileUtil {

    public static String getTileCode(long zoom, long x, long y) {
        return String.format(Locale.getDefault(), "%d/%d/%d", x, y, zoom);
    }
    public static long convert(long y, long zoom) {
        return (long) Math.pow(2, zoom) - 1 - y;
    }

    public static long getAvailableTileX(long zoom, long x) {
        long max = (long) Math.pow(2, zoom) - 1;
        if (x < 0) {
            return x + max;
        }
        if (x > max) {
            return x - max;
        }
        return x;
    }

    public static long getTileYRange(long zoom) {
        return (long) Math.pow(2, zoom) -1 ;
    }

    public static boolean isTileYAvailable(long zoom, long y) {
        return y >=0 && y <= ((long) Math.pow(2, zoom) - 1);
    }
    public static TileRegion getTileRegion(long zoom, LatLng coordinate) {
        return MercatorProjector.getTileRegion(zoom, coordinate);
    }
    public static TileRegion getTileRegion(long zoom, long x, long y) {
        return MercatorProjector.getTileRegion(zoom, x, y);
    }

    public Point coordinateToMeters(LatLng coordinate) {
        return MercatorProjector.coordinateToMeters(coordinate);
    }

    public LatLng metersToCoordinate(Point meters) {
        return MercatorProjector.metersToCoordinate(meters);
    }

    public static String getTileCode(long zoom, LatLng coordinate) {
        Point tileXY = MercatorProjector.getTileXY(zoom, coordinate);
        return TileUtil.getTileCode(zoom, (long)tileXY.x, (long)tileXY.y);
    }


    public static TileCollection getTileCollection(long zoom, LatLng coordinate, long dimension) {
        TileCollectionRange range = getTileCollectionRange(zoom, coordinate, dimension);
        return getTileCollection(range);
    }

    public static TileCollectionRange getTileCollectionRange(long zoom, LatLng coordinate, long dimension) {
        Point tileXY = MercatorProjector.getTileXY(zoom, coordinate);
        long length = (dimension - 1) / 2;
        long xOrigin = (long)tileXY.x - length;
        long xMax = xOrigin + dimension - 1;
        long yOrigin = (long)tileXY.y - length;
        long yMax = yOrigin + dimension - 1;

        long availableRange = getTileYRange(zoom);
        if (yOrigin < 0) {
            yOrigin = 0;
        }
        if (yMax > availableRange) {
            yMax = availableRange;
        }

        long xStart = xOrigin;
        long xRange = xMax - xOrigin;
        long yStart = yOrigin;
        long yRange = yMax - yOrigin;
        return new TileCollectionRange(zoom, xStart, xRange, yStart, yRange);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static TileCollectionRange getTileCollectionRange(long zoom, LatLng from, LatLng to) {
        Point fromXY = MercatorProjector.getTileXY(zoom, from);
        Point toXY = MercatorProjector.getTileXY(zoom, to);
        long fromX = (long) Double.min(fromXY.x, toXY.x);
        long toX = (long) Double.max(fromXY.x, toXY.x);
        long fromY = (long) Double.min(fromXY.y, toXY.y);
        long toY = (long) Double.max(fromXY.y, toXY.y);

        long xStart = fromX;
        long xRange = toX - fromX;
        long yStart = fromY;
        long yRange = toY - fromY;
        return new TileCollectionRange(zoom, xStart, xRange, yStart, yRange);
    }

    public static TileCollection getTileCollection(TileCollectionRange range) {
        List<TileRegion> tiles = new ArrayList<TileRegion>();
        for (long x = range.xStart; x <= range.xStart + range.xRange; x++) {
            for (long y = range.yStart; y <= range.yStart + range.yRange; y++) {
                TileRegion tile = MercatorProjector.getTileRegion(range.zoom, x, y);
                tiles.add(tile);
            }
        }
        return new TileCollection(range, tiles);
    }

    public static List<TileRegion> getTileRegions(Point fromXY, Point toXY, long zoom) {
        List<TileRegion> tiles = new ArrayList<TileRegion>();
        long fromX = (long) Math.min(fromXY.x, toXY.x);
        long toX = (long) Math.max(fromXY.x, toXY.x);
        long fromY = (long) Math.min(fromXY.y, toXY.y);
        long toY = (long) Math.max(fromXY.y, toXY.y);

        for (long x = fromX; x <= toX; x++) {
            for (long y = fromY; y <= toY; y++) {
                TileRegion tile = getTileRegion(zoom, x, y);
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public static List<TileRegion> getTiles(LatLng from, LatLng to, long zoom) {
        Point fromXY = MercatorProjector.getTileXY(zoom, from);
        Point toXY = MercatorProjector.getTileXY(zoom, to);
        return getTileRegions(fromXY, toXY, zoom);
    }

    public static List<String> getTilesCodes(LatLng from, LatLng to, long zoom) {
        Point fromXY = MercatorProjector.getTileXY(zoom, from);
        Point toXY = MercatorProjector.getTileXY(zoom, to);

        List<String> codes = new ArrayList<String>();
        long fromX = (long) Math.min(fromXY.x, toXY.x);
        long toX = (long) Math.max(fromXY.x, toXY.x);
        long fromY = (long) Math.min(fromXY.y, toXY.y);
        long toY = (long) Math.max(fromXY.y, toXY.y);
        for (long x = fromX; x <= toX; x++) {
            for (long y = fromY; y <= toY; y++) {
                String code = TileUtil.getTileCode(zoom, x, y);
                codes.add(code);
            }
        }
        return codes;
    }
}
