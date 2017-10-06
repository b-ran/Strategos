package mapgenerationtests;

//Vast amounts of this code are stolen from daniel
class TestMap {

    @SuppressWarnings("SameParameterValue")
    TestHex[][] constructMap(int diameter) {
        TestHex[][] map = new TestHex[diameter][diameter];

        boolean isLeftCorner = true;
        int offset = diameter / 2;

        for (int x = 0; x < diameter; x++) {
            for (int y = 0; y < diameter; y++) {
                if (isCorner(y, isLeftCorner, diameter, offset)) {
                    map[x][y] = new TestHex(false);
                }
                map[x][y] = new TestHex(true);
            }

            offset -= (offset > 0 && isLeftCorner) ? 1 : 0;

            offset += isLeftCorner ? 0 : 1;

            isLeftCorner = (offset == 0) != isLeftCorner;

        }

        return map;
    }

    /**
     * Checks if we are in a corner that needs excluding
     *
     * @param y        Y co-ordinate
     * @param isLeftCorner     Is on the left half of the board
     * @param diameter Size of the map
     * @param offset   Current offset for forming the exclusion triangle
     * @return If in top left or bottom right exclusion corners
     */
    private boolean isCorner(int y, boolean isLeftCorner, int diameter, int offset) {
        return isLeftCorner && y < offset || (!isLeftCorner && y >= diameter - offset);
    }

}
