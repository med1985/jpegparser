package parser.markers

import javax.print.attribute.standard.NumberOfInterveningJobs;

import parser.StreamWrapper;

class StartOfFrame implements Marker {
    
    private final int length
    private final int samplePrecision
    private final int numberOfLines
    private final int samplesPerLine
    private final int numberOfImageComponents
    
    public StartOfFrame(StreamWrapper stream, int length) {
        this.length = length
        samplePrecision = stream.readUInt(1)
        (numberOfLines, samplesPerLine) = (1..2).collect { stream.readUInt(2) }
        numberOfImageComponents = stream.readUInt(1)
        stream.skip(numberOfImageComponents * 3)
        println "\t\t\tsamplePrecition=$samplePrecision numberOfLines=$numberOfLines" \
            + " samplesPerLine=$samplesPerLine numberOfComponents=$numberOfImageComponents "
    }
    
    public int getLength() {
        length
	}
}
