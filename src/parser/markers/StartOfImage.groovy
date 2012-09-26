package parser.markers

import parser.StreamWrapper;

class StartOfImage implements Marker {
    
    private final int length
    private final String id 
    private final String version
    private final String units
    private final int xDensity
    private final int yDensity
    private final int xThumbnail
    private final int yThumbnail
    
    StartOfImage(StreamWrapper stream) {
        stream.skip(2)
        this.length = stream.readUInt(2)
        this.id = new String(stream.getBytes(4), "UTF-8")
        stream.skip(1)
        this.version = "${stream.readUInt(1)}.0${stream.readUInt(1)}"
        def unitByte = stream.read()
        this.units = unitByte == 2 ? "dots per cm" : units == 1 ? "dots per inch" : "no units"
        (xDensity, yDensity) = (1..2).collect { stream.readUInt(2) }
        (xThumbnail, yThumbnail) = (1..2).collect { stream.readUInt(1) }
        println "\t\t\tid=$id ver=v$version units=\"$units\" x=$xDensity y=$yDensity xthumb=$xThumbnail ythumb=$yThumbnail"
    }

    public int getLength() {
		length
	}
}
