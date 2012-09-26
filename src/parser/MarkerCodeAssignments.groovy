package parser

public enum MarkerCodeAssignments {
    SOF0_Baseline_DCT(0xC0),
    SOF1_Extended_Sequential_DCT(0xC1),
    SOF2_Progressive_DCT(0xC2),
    SOF3_LossLess_Sequential(0xC3),
    // Start Of Frame markers, differential, Huffman coding
    SOF5_Differential_Sequential_DCT(0xC5),
    SOF6_Differential_Progressive_DCT(0xC6),
    SOF7_Differential_Lossless_Sequential(0xC7),
    // Start Of Frame markers, non-differential, arithmetic coding
    JPG_Reserved(0xC8),
    SOF9_Extended_Sequential_DCT(0xC9),
    SOF10_Progressive_DCT(0xCA),
    SOF11_Lossless_Sequential(0xCB),
    // Start Of Frame markers, differential, arithmetic coding
    SOF13_Differential_Sequential_DCT(0xCD),
    SOF14_Differential_Progressive_DCT(0xCE),
    SOF15_Differential_Lossless(0xCF),
    // Huffman table specification
    Huffman_Table(0xC4),
    // Arithmetic coding conditioning specification
    Arithmethic_coding_spec(0xCC),
    Start_Of_Image(0xD8),
    End_Of_Image(0xD9),
    Start_Of_Scan(0xDA),
    Define_Quantization_Table(0xDB),
    Define_Number_Of_Lines(0xDC),
    Define_Restart_Interval(0xDD),
    Define_Heirarchical_Progression(0xDE),
    Expand_Reference_Component(0xDF),
    Comment(0xFE),
    RestartWithModulo(0xD0),
    Reserved(0xE0)

    private final byte id
    
    private static final CACHE = [:]
    static {
        MarkerCodeAssignments.values().each {
            CACHE.put(it.getId(), it)
        }
        (0xD0..0xD7).each {
            CACHE.put((byte) it, MarkerCodeAssignments.RestartWithModulo)
        }
        (0xE1..0xFD).each {
            CACHE.put((byte) it, MarkerCodeAssignments.Reserved)
        }
        (0x01..0xBF).each {
            CACHE.put((byte) it, MarkerCodeAssignments.Reserved)
        }
    }

    private MarkerCodeAssignments(int id) {
        this.id = (byte) id
    }

    public byte getId() {
        return id
    }
    
    public static getValue(byte id) {
        return CACHE.get(id)
    }

}
