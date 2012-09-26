package parser

import parser.markers.Marker;
import parser.markers.StartOfFrame;
import parser.markers.StartOfImage;
import parser.markers.StartOfScan;

class JPEGParser {

    private final StreamWrapper stream
    private final FileInputStream inStream

    JPEGParser(FileInputStream inStream) {
        stream = new StreamWrapper(inStream)
    }

    void parse() {
        println "pos\tlength\t\tcode"
        boolean keepReading = true
        while (keepReading) {
            def position = stream.position()
            def (marker, length) = stream.readMarker()
            println "${Long.toHexString(position)}\t${length}\t\t${marker}"
            parseMarker(marker, length)
        }
        inStream.close()
    }

    private Marker parseMarker(MarkerCodeAssignments code, int length) {
        Marker marker
        // TODO: get rid of this silly switch statement
        switch (code) {
            case MarkerCodeAssignments.Start_Of_Scan:
                return new StartOfScan(stream, length)
            case MarkerCodeAssignments.Start_Of_Image:
                return new StartOfImage(stream)
            case MarkerCodeAssignments.SOF0_Baseline_DCT:
            case MarkerCodeAssignments.SOF1_Extended_Sequential_DCT:
            case MarkerCodeAssignments.SOF2_Progressive_DCT:
            case MarkerCodeAssignments.SOF3_LossLess_Sequential:
            case MarkerCodeAssignments.SOF5_Differential_Sequential_DCT:
            case MarkerCodeAssignments.SOF6_Differential_Progressive_DCT:
            case MarkerCodeAssignments.SOF7_Differential_Lossless_Sequential:
            case MarkerCodeAssignments.SOF9_Extended_Sequential_DCT:
            case MarkerCodeAssignments.SOF10_Progressive_DCT:
            case MarkerCodeAssignments.SOF11_Lossless_Sequential:
            case MarkerCodeAssignments.SOF13_Differential_Sequential_DCT:
            case MarkerCodeAssignments.SOF14_Differential_Progressive_DCT:
            case MarkerCodeAssignments.SOF15_Differential_Lossless:
                return new StartOfFrame(stream, length)
            default:
                if (length > 0) {
                    stream.skip(length - 2)
                    return null
                }
        }
    }
}
