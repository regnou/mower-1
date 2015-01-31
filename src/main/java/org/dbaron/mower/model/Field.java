package org.dbaron.mower.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.apache.commons.lang3.Validate;

/**
 * Created by dbaron on 27/01/15.
 */
public class Field {

    private Position lowerLeftHandCorner;
    private Position upperRightHandCorner;

    private Table<Integer, Integer, Boolean> mowingIndex = HashBasedTable.create();

    public Field() {
        this.lowerLeftHandCorner = null;
        this.upperRightHandCorner = null;
    }

    public Field(Position lowerLeftHandCorner, Position upperRightHandCorner) {

        Validate.notNull(lowerLeftHandCorner);
        Validate.notNull(upperRightHandCorner);

        Validate.isTrue(lowerLeftHandCorner.getX() >= 0);
        Validate.isTrue(lowerLeftHandCorner.getY() >= 0);

        Validate.isTrue(upperRightHandCorner.getX() >= 0);
        Validate.isTrue(upperRightHandCorner.getY() >= 0);

        Validate.isTrue(upperRightHandCorner.getX() > lowerLeftHandCorner.getX());
        Validate.isTrue(upperRightHandCorner.getY() > lowerLeftHandCorner.getY());

        this.lowerLeftHandCorner = lowerLeftHandCorner;
        this.upperRightHandCorner = upperRightHandCorner;
        for (int x = lowerLeftHandCorner.getX(); x <= upperRightHandCorner.getX(); x++) {
            for (int y = lowerLeftHandCorner.getY(); y <= upperRightHandCorner.getY(); y++) {
                mowingIndex.put(x, y, Boolean.FALSE);
            }
        }
    }

    public Position getLowerLeftHandCorner() {
        return lowerLeftHandCorner;
    }

    public void setLowerLeftHandCorner(Position lowerLeftHandCorner) {
        this.lowerLeftHandCorner = lowerLeftHandCorner;
    }

    public Position getUpperRightHandCorner() {
        return upperRightHandCorner;
    }

    public void setUpperRightHandCorner(Position upperRightHandCorner) {
        this.upperRightHandCorner = upperRightHandCorner;
    }

    public Table<Integer, Integer, Boolean> getMowingIndex() {
        return mowingIndex;
    }

    public boolean isMowed() {
        return getMowingIndex() != null && !getMowingIndex().containsValue(Boolean.FALSE);
    }

    public boolean isMowed(int x, int y) {
        return getMowingIndex() != null && getMowingIndex().get(x, y);
    }
}