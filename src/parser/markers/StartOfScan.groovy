package parser.markers

import parser.StreamWrapper;

class StartOfScan implements Marker {

    private final int length
    private final int numberOfImageComponents
    private final scanComponentSelectors = []
    private final int startOfSpectral
    private final int endOfSpectral
    private final int successiveAprox

    StartOfScan(StreamWrapper stream, int length) {
        this.length = length
        this.numberOfImageComponents = stream.readUInt(1)
        numberOfImageComponents.times {
            scanComponentSelectors.add(new ScanComponentSelector(stream.readUInt(1), stream.readUInt(1)))
        }
        println "\t\t\t$scanComponentSelectors"
        startOfSpectral = stream.readUInt(1)
        endOfSpectral = stream.readUInt(1)
        successiveAprox = stream.readUInt(1)
        byte b
        while ((b = stream.read()) != (byte) 0xff) { }
    }

    public int getLength() {
        length
    }

    public int getNumberOfImageComponents() {
        numberOfImageComponents
    }

    public class ScanComponentSelector {
        final int selector
        final int entropyCodingTableSelector

        ScanComponentSelector(int selector, int ectSelector) {
            this.selector = selector
            this.entropyCodingTableSelector = ectSelector
        }

        @Override
        public String toString() {
            "selector=$selector ECTS=$entropyCodingTableSelector"
        }
    }
}
